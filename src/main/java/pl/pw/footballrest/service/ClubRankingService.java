package pl.pw.footballrest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pw.footballrest.dto.ClubDto;
import pl.pw.footballrest.dto.MatchDto;
import pl.pw.footballrest.dto.RankedClubDto;
import pl.pw.footballrest.entity.Match;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ClubRankingService {

    private final ClubService clubService;
    private final MatchService matchService;
//    public List<RankedClubDto> getClubsRanking() {
//        AtomicInteger i = new AtomicInteger(1);
//        List<ClubDto> clubs = clubService.getAllClubs();
//        return clubs.stream().map(club -> {
//            List<MatchDto> allMatchesForClub = matchService.getAllMatchesForClub(club.getId());
//            return RankedClubDto.from(club, allMatchesForClub);
//        }).sorted(Comparator.comparing(RankedClubDto::getPoints).reversed())
//                .map(rankedClub -> {
//                    rankedClub.setRank(i.getAndIncrement());
//                    return rankedClub;
//                }).toList();
//    }

    public List<RankedClubDto> getClubsRanking2() {
        AtomicInteger i = new AtomicInteger(1);
        return clubService.getAllClubsWithMatches().stream()
                .map(RankedClubDto::from)
                .sorted(Comparator.comparing(RankedClubDto::getPoints).reversed())
                .map(rankedClub -> {
                    rankedClub.setRank(i.getAndIncrement());
                    return rankedClub;
                }).toList();
    }
}
