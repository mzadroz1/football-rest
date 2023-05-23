package pl.pw.footballrest.repository;

import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphCrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.pw.footballrest.entity.Club;
import pl.pw.footballrest.entity.Player;

import java.util.List;

public interface PlayerRepository extends EntityGraphCrudRepository<Player, Long> {

    List<Player> findByClub(Club club);

    List<Player> findByClub_Id(Long id);

    List<Player> findByClub_Id(Long id, EntityGraph entityGraph);


}