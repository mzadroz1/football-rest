package pl.pw.footballrest.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link pl.pw.footballrest.entity.Club} entity
 */
@Data
public class ClubWithMatchesDto implements Serializable {
    private final Long id;
    private final String name;
    private final String shortName;
    private final Set<MatchDto> homeMatches;
    private final Set<MatchDto> visitorMatches;
}