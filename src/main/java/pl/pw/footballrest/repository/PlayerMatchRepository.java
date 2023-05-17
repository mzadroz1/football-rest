package pl.pw.footballrest.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.pw.footballrest.entity.PlayerMatch;
import pl.pw.footballrest.entity.PlayerMatchId;

import java.util.List;

public interface PlayerMatchRepository extends JpaRepository<PlayerMatch, PlayerMatchId> {

//    @EntityGraph(attributePaths = {"player"})
    List<PlayerMatch> findById_PlayerId(Long playerId);

}