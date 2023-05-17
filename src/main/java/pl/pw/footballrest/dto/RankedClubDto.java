package pl.pw.footballrest.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;

@Data
public class RankedClubDto implements Serializable {

    private Integer rank;
    private Long clubId;
    private String clubName;
    private String clubShortName;
    private Integer matchCount = 0;
    private Integer winsCount = 0;
    private Integer drawsCount = 0;
    private Integer losesCount = 0;
    private Integer goalsScored = 0;
    private Integer goalsConceded = 0;
    private Integer points = 0;

    public static RankedClubDto from(ClubWithMatchesDto club) {
        RankedClubDto rankedClubDto = new RankedClubDto();
        rankedClubDto.clubId = club.getId();
        rankedClubDto.clubName = club.getName();
        rankedClubDto.clubShortName = club.getShortName();
        List<MatchDto> matches = Stream.concat(club.getHomeMatches().stream(), club.getVisitorMatches().stream()).toList();
        rankedClubDto.matchCount = matches.size();
        matches.forEach(match -> {
                    Integer goalsScored;
                    Integer goalsConceded;
                    if (match.getHost().getName().equals(club.getName())) {
                        goalsScored = match.getHostGoals();
                        goalsConceded = match.getVisitorGoals();
                    } else {
                        goalsScored = match.getVisitorGoals();
                        goalsConceded = match.getHostGoals();
                    }
                    if(goalsScored > goalsConceded) {
                        rankedClubDto.winsCount++;
                        rankedClubDto.points += 3;
                    } else if(goalsScored.equals(goalsConceded)) {
                        rankedClubDto.drawsCount++;
                        rankedClubDto.points += 1;
                    } else {
                        rankedClubDto.losesCount++;
                    }
                    rankedClubDto.goalsScored += goalsScored;
                    rankedClubDto.goalsConceded += goalsConceded;

                }
        );
        return rankedClubDto;
    }
//    public static RankedClubDto from(Club club, List<MatchDto> matches) {
//        RankedClubDto rankedClubDto = new RankedClubDto();
//        rankedClubDto.clubName = club.getName();
//        rankedClubDto.clubShortName = club.getShortName();
//        rankedClubDto.matchCount = matches.size();
//        matches.stream().forEach(match -> {
//                    Integer goalsScored;
//                    Integer goalsConceded;
//                    if (match.getHost().getId().equals(club.getId())) {
//                       goalsScored = match.getHostGoals();
//                       goalsConceded = match.getVisitorGoals();
//                    } else {
//                        goalsScored = match.getVisitorGoals();
//                        goalsConceded = match.getHostGoals();
//                    }
//                    if(goalsScored > goalsConceded) {
//                        rankedClubDto.winsCount++;
//                        rankedClubDto.points += 3;
//                    } else if(goalsScored.equals(goalsConceded)) {
//                        rankedClubDto.drawsCount++;
//                        rankedClubDto.points += 1;
//                    } else {
//                        rankedClubDto.losesCount++;
//                    }
//                    rankedClubDto.goalsScored += goalsScored;
//                    rankedClubDto.goalsConceded += goalsConceded;
//
//                }
//        );
//        return rankedClubDto;
//    }
}
