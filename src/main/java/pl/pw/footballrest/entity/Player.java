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
@Table(name = "player")
public class Player {
    @Id
    @Column(name = "player_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "surname", nullable = false, length = 20)
    private String surname;

    @Column(name = "nationality", nullable = false, length = 35)
    private String nationality;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "height", nullable = false)
    private Integer height;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @OneToMany(mappedBy = "player")
    private Set<PlayerMatch> playerMatches = new LinkedHashSet<>();

}