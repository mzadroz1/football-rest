package pl.pw.footballrest.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link pl.pw.footballrest.entity.PlayerMatch} entity
 */
@Data
public class PlayerMatchDto implements Serializable {
    private final Long idMatchId;
    private final Long idPlayerId;
    private final Integer shirtNumber;
    private final Integer goals;
    private final Integer yellowCards;
    private final Integer redCard;
    private final Integer startTime;
    private final Integer endTime;
}