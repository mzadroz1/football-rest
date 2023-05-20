package pl.pw.footballrest.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link pl.pw.footballrest.entity.Player} entity
 */
@Data
public class PlayerWithClubDto implements Serializable {
    private final Long id;
    private final String name;
    private final String surname;
    private final String nationality;
    private final LocalDate birthDate;
    private final Integer height;
    private final Integer weight;
    private final ClubDto club;
}