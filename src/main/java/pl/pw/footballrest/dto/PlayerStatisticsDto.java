package pl.pw.footballrest.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlayerStatisticsDto implements Serializable {

    private Long playerId;
    private String playerName;
    private String playerSurname;
    private Integer matchCount = 0;
    private Integer goalsScored = 0;
    private Integer yellowCards = 0;
    private Integer redCards = 0;

    public static PlayerStatisticsDto from(PlayerWithMatchesDto player) {
        PlayerStatisticsDto playerStatisticsDto = new PlayerStatisticsDto();
        playerStatisticsDto.matchCount = player.getPlayerMatches().size();
        playerStatisticsDto.playerId = player.getId();
        playerStatisticsDto.playerName = player.getName();
        playerStatisticsDto.playerSurname = player.getSurname();
        player.getPlayerMatches().forEach(
                playerMatch -> {
                    playerStatisticsDto.goalsScored += playerMatch.getGoals();
                    playerStatisticsDto.yellowCards += playerMatch.getYellowCards();
                    playerStatisticsDto.redCards += playerMatch.getRedCard();
                }
        );
        return playerStatisticsDto;
    }
}
