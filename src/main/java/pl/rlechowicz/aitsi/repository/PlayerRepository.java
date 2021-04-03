package pl.rlechowicz.aitsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rlechowicz.aitsi.model.Player;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    List<Player> findAllByPosition(String position);
}
