package at.kuben.udemyspring.web;

import at.kuben.udemyspring.db.BenutzerRepository;
import at.kuben.udemyspring.db.FahrzeugRepository;
import at.kuben.udemyspring.db.KostenstelleRepository;
import at.kuben.udemyspring.model.Benutzer;
import at.kuben.udemyspring.model.Fahrzeug;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class FahrzeugController {

    private FahrzeugRepository fahrzeugRepo;

    private BenutzerRepository benutzerRepo;

    private KostenstelleRepository kostenstelleRepo;

    public FahrzeugController(FahrzeugRepository fahrzeugRepo, BenutzerRepository benutzerRepo, KostenstelleRepository kostenstelleRepo) {
        this.fahrzeugRepo = fahrzeugRepo;
        this.benutzerRepo = benutzerRepo;
        this.kostenstelleRepo = kostenstelleRepo;
    }

    @GetMapping(path = "/uebersicht")
    public String uebersicht(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Benutzer b = benutzerRepo.findBenutzerByBenutzername(auth.getName()).get();

        model.addAttribute("fahrzeuge", fahrzeugRepo.findAllByBenutzer(b));
        return "client/list-fahrzeug";
    }

    @GetMapping(path = "/hinzufuegen")
    public String hinzufuegen(Model model) {

        Fahrzeug f = new Fahrzeug();
        model.addAttribute("fahrzeug", f);

        return "client/hinzufuegen-fahrzeug";
    }

    @PostMapping(path = "/hinzufuegen")
    public String saveFahrzeug(Model model, @Valid @ModelAttribute("fahrzeug") Fahrzeug f, BindingResult results) {

        if(results.hasErrors()) {
            return "client/hinzufuegen-fahrzeug";
        }

        if(f.getBaujahr() > f.getKaufjahr()) {
            results.rejectValue("baujahr", "fahrzeug.kaufjahrvorbaujahr", "Das Baujahr muss vor dem Kaufjahr liegen.");
            return "client/hinzufuegen-fahrzeug";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Benutzer b = benutzerRepo.findBenutzerByBenutzername(auth.getName()).get();

        f.setBenutzer(b);
        fahrzeugRepo.save(f);

        return "redirect:/uebersicht";
    }

    @GetMapping(path = "/bearbeiten/{id}")
    public String bearbeiten(Model model, @PathVariable Integer id) {

        Optional<Fahrzeug> f = fahrzeugRepo.findById(id);
        if(f.isPresent()) {
            model.addAttribute("fahrzeug", f.get());
        } else {
            return "redirect:/uebersicht";
        }

        return "client/bearbeiten-fahrzeug";
    }

    @PostMapping(path = "/bearbeiten/{id}")
    public String editFahrzeug(Model model, @Valid @ModelAttribute("fahrzeug") Fahrzeug f, BindingResult results, @PathVariable Integer id) {

        if(results.hasErrors()) {
            return "client/bearbeiten-fahrzeug";
        }

        if(f.getBaujahr() > f.getKaufjahr()) {
            results.rejectValue("baujahr", "fahrzeug.kaufjahrvorbaujahr", "Das Baujahr muss vor dem Kaufjahr liegen.");
            return "client/bearbeiten-fahrzeug";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Benutzer b = benutzerRepo.findBenutzerByBenutzername(auth.getName()).get();

        f.setBenutzer(b);
        fahrzeugRepo.save(f);

        return "redirect:/uebersicht";
    }

    @GetMapping(path = "/loeschen/{id}")
    public String loeschen(Model model, @PathVariable Integer id) {

        fahrzeugRepo.deleteById(id);

        return "redirect:/uebersicht";
    }




}
