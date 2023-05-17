package pl.pw.footballrest.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link pl.pw.footballrest.entity.Club} entity
 */
@Data
public class ClubDto implements Serializable {
    private final Long id;
    private final String name;
    private final String shortName;
    private final LocalDate foundingDate;
}