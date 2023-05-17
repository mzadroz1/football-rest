package pl.pw.footballrest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "player_matches")
public class PlayerMatch {
    @EmbeddedId
    private PlayerMatchId id;

    @MapsId("matchId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @MapsId("playerId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Column(name = "shirt_number", nullable = false)
    private Integer shirtNumber;

    @Column(name = "goals", nullable = false)
    private Integer goals;

    @Column(name = "yellow_cards", nullable = false)
    private Integer yellowCards;

    @Column(name = "red_card", nullable = false)
    private Integer redCard;

    @Column(name = "start_time", nullable = false)
    private Integer startTime;

    @Column(name = "end_time", nullable = false)
    private Integer endTime;

}