package pl.rlechowicz.aitsi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.rlechowicz.aitsi.model.Game;
import pl.rlechowicz.aitsi.model.Player;
import pl.rlechowicz.aitsi.service.MainService;

@Controller
public class AdminController {

    private final MainService mainService;

    @Autowired
    public AdminController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/admin")
    public ModelAndView showAdminPage() {
        ModelAndView mav = new ModelAndView("admin");
        mav.addObject("isUserLoggedIn", true);
        mav.addObject("players", mainService.findAllPlayers());
        return mav;
    }

    @GetMapping("/admin/games")
    public ModelAndView showAdminGamesPage() {
        ModelAndView mav = new ModelAndView("admin-games");
        mav.addObject("isUserLoggedIn", true);
        mav.addObject("games", mainService.findAllGames());
        return mav;
    }

    @GetMapping("/admin/player/{id}")
    public ModelAndView showEditPlayerForm(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("admin");
        mav.addObject("isUserLoggedIn", true);
        mav.addObject("players", mainService.findAllPlayers());
        mav.addObject("player", mainService.findPlayerById(id));
        return mav;
    }

    @PostMapping("/admin/player")
    public String createOrUpdatePlayer(@ModelAttribute("playerForm") Player player) {
        try {
            mainService.savePlayer(player);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/admin/?savePlayerError";
        }
        return "redirect:/admin/?savePlayerOk";
    }

    @GetMapping("/admin/player/delete/{id}")
    public String deletePlayer(@PathVariable Integer id) {
        try {
            mainService.deletePlayer(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/admin/?deletePlayerError";
        }
        return "redirect:/admin/?deletePlayerOk";
    }

    @GetMapping("/admin/game/{id}")
    public ModelAndView showEditGameForm(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("admin-games");
        mav.addObject("isUserLoggedIn", true);
        mav.addObject("games", mainService.findAllGames());
        mav.addObject("game", mainService.findGameById(id));
        return mav;
    }

    @PostMapping("/admin/game")
    public String createOrUpdateGame(@ModelAttribute("gameForm") Game game) {
        try {
            mainService.saveGame(game);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/admin/games/?saveGameError";
        }
        return "redirect:/admin/games/?saveGameOk";
    }

    @GetMapping("/admin/game/delete/{id}")
    public String deleteGame(@PathVariable Integer id) {
        try {
            mainService.deleteGame(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/admin/games/?deleteGameError";
        }
        return "redirect:/admin/games/?deleteGameOk";
    }

}
