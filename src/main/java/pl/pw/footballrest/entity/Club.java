package pl.pw.footballrest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "club")
public class Club {
    @Id
    @Column(name = "club_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(name = "short_name", nullable = false, length = 4)
    private String shortName;

    @Column(name = "founding_date", nullable = false)
    private LocalDate foundingDate;

    @OneToMany(mappedBy = "club")
    private Set<Player> players = new LinkedHashSet<>();

    @OneToMany(mappedBy = "host")
    private Set<Match> homeMatches = new LinkedHashSet<>();

    @OneToMany(mappedBy = "visitor")
    private Set<Match> visitorMatches = new LinkedHashSet<>();

}