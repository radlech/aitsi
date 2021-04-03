package pl.rlechowicz.aitsi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rlechowicz.aitsi.model.Game;
import pl.rlechowicz.aitsi.model.Player;
import pl.rlechowicz.aitsi.repository.GameRepository;
import pl.rlechowicz.aitsi.repository.PlayerRepository;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MainService {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    @Autowired
    public MainService(PlayerRepository playerRepository, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    public List<Player> findAllPlayers() {
        return playerRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(Player::getSkills).reversed())
                .collect(Collectors.toList());
    }

    public List<Player> findPlayersByPosition(String position) {
        return playerRepository.findAllByPosition(position)
                .stream()
                .sorted(Comparator.comparingInt(Player::getSkills).reversed())
                .collect(Collectors.toList());
    }

    public Player findPlayerById(int id) {
        return playerRepository.getOne(id);
    }

    public void savePlayer(Player player) {
        playerRepository.saveAndFlush(player);
    }

    public void deletePlayer(int id) {
        playerRepository.deleteById(id);
    }

    public List<Game> findAllGames() {
        return gameRepository.findAll()
                .stream()
                .sorted(Comparator.comparingLong(game -> game.getDate().toEpochDay()))
                .collect(Collectors.toList());
    }

    public List<Game> findGamesByType(String type) {
        return gameRepository.findAllByType(type)
                .stream()
                .sorted(Comparator.comparingLong(game -> game.getDate().toEpochDay()))
                .collect(Collectors.toList());
    }

    public Game findGameById(int id) {
        return gameRepository.getOne(id);
    }

    public void saveGame(Game game) {
        gameRepository.saveAndFlush(game);
    }

    public void deleteGame(int id) {
        gameRepository.deleteById(id);
    }
}
