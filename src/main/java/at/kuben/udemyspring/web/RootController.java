package at.kuben.udemyspring.web;

import at.kuben.udemyspring.db.BenutzerRepository;
import at.kuben.udemyspring.db.FahrzeugRepository;
import at.kuben.udemyspring.db.KostenstelleRepository;
import at.kuben.udemyspring.model.Fahrzeug;
import at.kuben.udemyspring.model.Kostenstelle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class RootController {

    private FahrzeugRepository fahrzeugRepo;

    private BenutzerRepository benutzerRepo;

    private KostenstelleRepository kostenstelleRepo;

    public RootController(FahrzeugRepository fahrzeugRepo, BenutzerRepository benutzerRepo, KostenstelleRepository kostenstelleRepo) {
        this.fahrzeugRepo = fahrzeugRepo;
        this.benutzerRepo = benutzerRepo;
        this.kostenstelleRepo = kostenstelleRepo;
    }

    @GetMapping(path = "/login")
    public String login() {
        return "client/login";
    }

    @GetMapping(path = "/access-denied")
    public String accessDenied() {
        return "/fehler/access-denied";
    }

}
