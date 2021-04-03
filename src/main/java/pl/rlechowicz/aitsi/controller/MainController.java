package pl.rlechowicz.aitsi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.rlechowicz.aitsi.service.MainService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping(value = {"", "/", "/index"})
    public ModelAndView showHomePage() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("isUserLoggedIn", isUserLoggedIn());
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("isUserLoggedIn", isUserLoggedIn());
        return mav;
    }

    @GetMapping("/logout")
    public String getLogoutView(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/?wylogowano";
    }

    @GetMapping("/info")
    public ModelAndView showPageInfo() {
        ModelAndView mav = new ModelAndView("info");
        mav.addObject("isUserLoggedIn", isUserLoggedIn());
        return mav;
    }

    @GetMapping("/klub")
    public ModelAndView showClubInfo() {
        ModelAndView mav = new ModelAndView("club");
        mav.addObject("isUserLoggedIn", isUserLoggedIn());
        return mav;
    }

    @GetMapping("/klub/trofea")
    public ModelAndView showClubTrophies() {
        ModelAndView mav = new ModelAndView("trophies");
        mav.addObject("isUserLoggedIn", isUserLoggedIn());
        return mav;
    }

    @GetMapping("/klub/legendy")
    public ModelAndView showClubLegends() {
        ModelAndView mav = new ModelAndView("legends");
        mav.addObject("isUserLoggedIn", isUserLoggedIn());
        return mav;
    }

    @GetMapping("/zawodnicy")
    public ModelAndView showPlayers() {
        ModelAndView mav = new ModelAndView("players");
        mav.addObject("isUserLoggedIn", isUserLoggedIn());
        mav.addObject("players", mainService.findAllPlayers());
        mav.addObject("position", "ALL");
        return mav;
    }

    @GetMapping("/zawodnicy/bramkarze")
    public ModelAndView showGoalkeepers() {
        ModelAndView mav = new ModelAndView("players");
        mav.addObject("isUserLoggedIn", isUserLoggedIn());
        mav.addObject("players", mainService.findPlayersByPosition("BR"));
        mav.addObject("position", "BR");
        return mav;
    }

    @GetMapping("/zawodnicy/obroncy")
    public ModelAndView showDefenders() {
        ModelAndView mav = new ModelAndView("players");
        mav.addObject("isUserLoggedIn", isUserLoggedIn());
        mav.addObject("players", mainService.findPlayersByPosition("OBR"));
        mav.addObject("position", "OBR");
        return mav;
    }

    @GetMapping("/zawodnicy/pomocnicy")
    public ModelAndView showMidfielders() {
        ModelAndView mav = new ModelAndView("players");
        mav.addObject("isUserLoggedIn", isUserLoggedIn());
        mav.addObject("players", mainService.findPlayersByPosition("POM"));
        mav.addObject("position", "POM");
        return mav;
    }

    @GetMapping("/zawodnicy/napastnicy")
    public ModelAndView showStrikers() {
        ModelAndView mav = new ModelAndView("players");
        mav.addObject("isUserLoggedIn", isUserLoggedIn());
        mav.addObject("players", mainService.findPlayersByPosition("NAP"));
        mav.addObject("position", "NAP");
        return mav;
    }

    @GetMapping("/terminarz")
    public ModelAndView showGames() {
        ModelAndView mav = new ModelAndView("games");
        mav.addObject("isUserLoggedIn", isUserLoggedIn());
        mav.addObject("games", mainService.findAllGames());
        mav.addObject("type", "ALL");
        return mav;
    }

    @GetMapping("/terminarz/ekstraklasa")
    public ModelAndView showLeagueGames() {
        ModelAndView mav = new ModelAndView("games");
        mav.addObject("isUserLoggedIn", isUserLoggedIn());
        mav.addObject("games", mainService.findGamesByType("EKS"));
        mav.addObject("type", "EKS");
        return mav;
    }

    @GetMapping("/terminarz/puchar")
    public ModelAndView showCupGames() {
        ModelAndView mav = new ModelAndView("games");
        mav.addObject("isUserLoggedIn", isUserLoggedIn());
        mav.addObject("games", mainService.findGamesByType("PP"));
        mav.addObject("type", "PP");
        return mav;
    }

    @GetMapping("/terminarz/liga_kontynentalna")
    public ModelAndView showITCGames() {
        ModelAndView mav = new ModelAndView("games");
        mav.addObject("isUserLoggedIn", isUserLoggedIn());
        mav.addObject("games", mainService.findGamesByType("LK"));
        mav.addObject("type", "LK");
        return mav;
    }

    private boolean isUserLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (auth.getPrincipal() != "anonymousUser") ? true : false;
    }

}
