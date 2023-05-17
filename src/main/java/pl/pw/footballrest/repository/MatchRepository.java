package pl.pw.footballrest.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.pw.footballrest.entity.Match;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @EntityGraph(attributePaths = {"host", "visitor"})
    List<Match> findByHost_IdOrVisitor_Id(Long id, Long id1);

    @EntityGraph(attributePaths = {"host", "visitor", "playerMatches", "playerMatches.player"})
    List<Match> findByHost_Id(Long id);

    List<Match> findByVisitor_Id(Long id);
}