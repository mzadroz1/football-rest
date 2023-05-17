package pl.pw.footballrest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pw.footballrest.dto.MatchDto;
import pl.pw.footballrest.dto.PlayerMatchDto;
import pl.pw.footballrest.mappers.MatchMapper;
import pl.pw.footballrest.mappers.PlayerMatchMapper;
import pl.pw.footballrest.repository.MatchRepository;
import pl.pw.footballrest.repository.PlayerMatchRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;
    private final PlayerMatchRepository playerMatchRepository;
    private final PlayerMatchMapper playerMatchMapper;

    public List<MatchDto> getAllMatchesForClub(Long clubId) {
        return matchRepository.findByHost_IdOrVisitor_Id(clubId, clubId)
                .stream().map(matchMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<PlayerMatchDto> getAllMatchesForPlayer(Long playerId) {
        return playerMatchRepository.findById_PlayerId(playerId)
                .stream().map(playerMatchMapper::toDto)
                .collect(Collectors.toList());
    }
}
