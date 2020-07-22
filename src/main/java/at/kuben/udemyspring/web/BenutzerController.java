package at.kuben.udemyspring.web;

import at.kuben.udemyspring.db.BenutzerRepository;
import at.kuben.udemyspring.db.FahrzeugRepository;
import at.kuben.udemyspring.db.KostenstelleRepository;
import at.kuben.udemyspring.model.Benutzer;
import at.kuben.udemyspring.model.Fahrzeug;
import at.kuben.udemyspring.model.pojo.PasswortAenderung;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class BenutzerController {

    private FahrzeugRepository fahrzeugRepo;

    private BenutzerRepository benutzerRepo;

    private KostenstelleRepository kostenstelleRepo;

    public BenutzerController(FahrzeugRepository fahrzeugRepo, BenutzerRepository benutzerRepo, KostenstelleRepository kostenstelleRepo) {
        this.fahrzeugRepo = fahrzeugRepo;
        this.benutzerRepo = benutzerRepo;
        this.kostenstelleRepo = kostenstelleRepo;
    }

    @GetMapping(path = "/benutzer/uebersicht")
    public String uebersicht(Model model){
        model.addAttribute("benutzer", benutzerRepo.findAll());
        return "client/list-benutzer";
    }

    @GetMapping(path = "/benutzer/hinzufuegen")
    public String hinzufuegen(Model model) {

        Benutzer b = new Benutzer();
        model.addAttribute("benutzer", b);

        return "client/hinzufuegen-benutzer";
    }

    @PostMapping(path = "/benutzer/hinzufuegen")
    public String saveBenutzer(Model model, @Valid @ModelAttribute("benutzer") Benutzer b, BindingResult results) {

        if(results.hasErrors()) {
            return "client/hinzufuegen-benutzer";
        }

        b.setPasswort(new BCryptPasswordEncoder().encode(b.getPasswort()));
        benutzerRepo.save(b);

        return "redirect:/benutzer/uebersicht";
    }

    @GetMapping(path = "/benutzer/passwortaendern/{id}")
    public String bearbeiten(Model model, @PathVariable String id) {

        PasswortAenderung pa = new PasswortAenderung();
        model.addAttribute("password", pa);

        return "client/passwort-aendern";
    }

    @PostMapping(path = "/benutzer/passwortaendern/{id}")
    public String editFahrzeug(Model model, @Valid @ModelAttribute("password") PasswortAenderung f, BindingResult results, @PathVariable String id) {

        if(results.hasErrors()) {
            return "client/bearbeiten-fahrzeug";
        }

        Optional<Benutzer> byId = benutzerRepo.findById(id);
        if(!byId.isPresent()) {
            return "redirect:/benutzer/uebersicht";
        }

        Benutzer b = byId.get();

        String pass = f.getPasswort();
        pass = new BCryptPasswordEncoder().encode(pass);

        b.setPasswort(pass);

        benutzerRepo.save(b);

        return "redirect:/benutzer/uebersicht";
    }

    @GetMapping(path = "/benutzer/loeschen/{id}")
    public String loeschen(Model model, @PathVariable String id) {

        benutzerRepo.deleteById(id);

        return "redirect:/benutzer/uebersicht";
    }


}
