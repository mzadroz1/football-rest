package pl.pw.footballrest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pw.footballrest.dto.*;
import pl.pw.footballrest.service.MatchService;
import pl.pw.footballrest.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/football/players")
@RequiredArgsConstructor
public class PlayersController {

    private final PlayerService playerService;
    private final MatchService matchService;

    @GetMapping
    public List<PlayerWithClubDto> players() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/ranking")
    public List<PlayerStatisticsDto> ranking() {
        return playerService.getPlayersRanking();
    }

    @GetMapping("/{player-id}")
    public PlayerWithClubDto player(@PathVariable("player-id") Long playerId) {
        return playerService.getPlayer(playerId);
    }

    @GetMapping("/{player-id}/statistics")
    public PlayerStatisticsDto playerStatistics(@PathVariable("player-id") Long playerId) {
        return playerService.getPlayerStatistics(playerId);
    }

    @GetMapping("/{player-id}/matches")
    public List<PlayerMatchDto> playerMatches(@PathVariable("player-id") Long playerId) {
        return matchService.getAllMatchesForPlayer(playerId);
    }

}
