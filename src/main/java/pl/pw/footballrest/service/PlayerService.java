package pl.pw.footballrest.service;

import com.cosium.spring.data.jpa.entity.graph.domain2.DynamicEntityGraph;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pw.footballrest.dto.PlayerDto;
import pl.pw.footballrest.dto.PlayerStatisticsDto;
import pl.pw.footballrest.mappers.PlayerMapper;
import pl.pw.footballrest.mappers.PlayerWithMatchesMapper;
import pl.pw.footballrest.repository.PlayerRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final PlayerWithMatchesMapper playerWithMatchesMapper;

    public List<PlayerDto> getAllPlayers() {
        return StreamSupport.stream(playerRepository.findAll(DynamicEntityGraph.loading()
                        .addPath("club")
                        .build()).spliterator(), false)
                .map(playerMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<PlayerDto> getAllPlayersFromClub(Long clubId) {
        return playerRepository.findByClub_Id(clubId).stream()
                .map(playerMapper::toDto)
                .collect(Collectors.toList());
    }

    public PlayerDto getPlayer(Long playerId) {
        return playerRepository.findById(playerId).map(playerMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Player with specified id=" + playerId + " does not exists."));
    }

    public List<PlayerStatisticsDto> getPlayersRanking() {
        return StreamSupport.stream(playerRepository.findAll(DynamicEntityGraph.loading()
                                .addPath("playerMatches")
                                .build())
                        .spliterator(), false)
                .map(playerWithMatchesMapper::toDto)
                .map(PlayerStatisticsDto::from)
                .sorted(Comparator.comparing(PlayerStatisticsDto::getGoalsScored).reversed())
                .collect(Collectors.toList());
    }

    public PlayerStatisticsDto getPlayerStatistics(Long playerId) {
        return playerRepository.findById(playerId,
                                DynamicEntityGraph.loading()
                                .addPath("playerMatches")
                                .build())
                .map(playerWithMatchesMapper::toDto)
                .map(PlayerStatisticsDto::from)
                .orElseThrow(() -> new IllegalArgumentException("Player with specified id=" + playerId + " does not exists."));
    }
}
