package pl.pw.footballrest.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link pl.pw.footballrest.entity.Match} entity
 */
@Data
public class MatchDto implements Serializable {
    private final Long id;
    private final LocalDate matchDate;
    private final ClubDto host;
    private final ClubDto visitor;
    private final Integer hostGoals;
    private final Integer visitorGoals;
}