package pl.pw.footballrest.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link pl.pw.footballrest.entity.Player} entity
 */
@Data
public class PlayerWithMatchesDto implements Serializable {
    private final Long id;
    private final String name;
    private final String surname;
    private final Set<PlayerMatchDto> playerMatches;
}