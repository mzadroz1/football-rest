package pl.pw.footballrest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class PlayerMatchId implements Serializable {
    private static final long serialVersionUID = -4291723952127132776L;
    @Column(name = "match_id", nullable = false)
    private Long matchId;

    @Column(name = "player_id", nullable = false)
    private Long playerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PlayerMatchId entity = (PlayerMatchId) o;
        return Objects.equals(this.matchId, entity.matchId) &&
                Objects.equals(this.playerId, entity.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, playerId);
    }

}