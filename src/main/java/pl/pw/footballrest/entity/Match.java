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
@Table(name = "match")
public class Match {
    @Id
    @Column(name = "match_id", nullable = false)
    private Long id;

    @Column(name = "match_date", nullable = false)
    private LocalDate matchDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "host", nullable = false)
    private Club host;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "visitor", nullable = false)
    private Club visitor;

    @OneToMany(mappedBy = "match")
    private Set<PlayerMatch> playerMatches = new LinkedHashSet<>();

    @Column(name = "host_goals")
    private Integer hostGoals;

    @Column(name = "visitor_goals")
    private Integer visitorGoals;

}