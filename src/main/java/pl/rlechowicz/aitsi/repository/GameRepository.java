package pl.rlechowicz.aitsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rlechowicz.aitsi.model.Game;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {

    List<Game> findAllByType(String type);
}
