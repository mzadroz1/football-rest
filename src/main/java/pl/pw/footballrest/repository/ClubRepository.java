package pl.pw.footballrest.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphCrudRepository;
import pl.pw.footballrest.entity.Club;

public interface ClubRepository extends EntityGraphCrudRepository<Club, Long> {


}