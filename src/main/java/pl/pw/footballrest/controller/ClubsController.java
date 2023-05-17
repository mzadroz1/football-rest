package pl.pw.footballrest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pw.footballrest.dto.ClubDto;
import pl.pw.footballrest.dto.MatchDto;
import pl.pw.footballrest.dto.PlayerDto;
import pl.pw.footballrest.dto.RankedClubDto;
import pl.pw.footballrest.service.ClubRankingService;
import pl.pw.footballrest.service.ClubService;
import pl.pw.footballrest.service.MatchService;
import pl.pw.footballrest.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/football/clubs")
@RequiredArgsConstructor
public class ClubsController {

    private final MatchService matchService;
    private final ClubService clubService;
    private final ClubRankingService clubRankingService;
    private final PlayerService playerService;

    @GetMapping
    public List<ClubDto> clubs() {
        return clubService.getAllClubs();
    }

    @GetMapping("/ranking")
    public List<RankedClubDto> ranking() {
        return clubRankingService.getClubsRanking2();
    }

    @GetMapping("/{club-id}")
    public ClubDto club(@PathVariable("club-id") Long clubId) {
        return clubService.getClub(clubId);
    }

    @GetMapping("/{club-id}/matches")
    public List<MatchDto> matches(@PathVariable("club-id") Long clubId) {
        return matchService.getAllMatchesForClub(clubId);
    }

    @GetMapping("/{club-id}/players")
    public List<PlayerDto> players(@PathVariable("club-id") Long clubId) {
        return playerService.getAllPlayersFromClub(clubId);
    }
}
