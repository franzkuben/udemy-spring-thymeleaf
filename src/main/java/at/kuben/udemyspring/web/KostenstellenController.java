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
public class KostenstellenController {

    private FahrzeugRepository fahrzeugRepo;

    private BenutzerRepository benutzerRepo;

    private KostenstelleRepository kostenstelleRepo;

    public KostenstellenController(FahrzeugRepository fahrzeugRepo, BenutzerRepository benutzerRepo, KostenstelleRepository kostenstelleRepo) {
        this.fahrzeugRepo = fahrzeugRepo;
        this.benutzerRepo = benutzerRepo;
        this.kostenstelleRepo = kostenstelleRepo;
    }

    @GetMapping(path = "/kostenstellen/{id}")
    public String uebersicht(Model model, @PathVariable Integer id) {

        Optional<Fahrzeug> f = fahrzeugRepo.findById(id);
        if (f.isPresent()) {
            model.addAttribute("kostenstellen", f.get().getKostenstellen());
        } else {
            return "redirect:/uebersicht";
        }

        Double o = kostenstelleRepo.sumKosten(id);
        if(o == null)
            o = 0d;
        model.addAttribute("kostensumme", o);

        model.addAttribute("f", f.get());
        return "client/list-kostenstellen";
    }

    @GetMapping(path = "/kostenstelle/hinzufuegen/{fid}")
    public String hinzufuegen(Model model, @PathVariable Integer fid) {

        model.addAttribute("titel", "Kostenstelle hinzufügen");

        Optional<Fahrzeug> f = fahrzeugRepo.findById(fid);
        if (!f.isPresent()) {
            return "redirect:/uebersicht";
        }

        Kostenstelle kostenstelle = new Kostenstelle();
        kostenstelle.setFahrzeug(f.get());
        System.out.println(kostenstelle.getId());
        model.addAttribute("kostenstelle", kostenstelle);

        return "client/hinzufuegen-bearbeiten-kostenstelle";
    }

    @PostMapping(path = "/kostenstelle/hinzufuegen/{fid}")
    public String saveKostenstelle(Model model, @Valid @ModelAttribute("kostenstelle") Kostenstelle k, BindingResult results, @PathVariable Integer fid) {

        if (results.hasErrors()) {
            return "client/hinzufuegen-bearbeiten-kostenstelle";
        }

        kostenstelleRepo.save(k);

        return "redirect:/kostenstellen/" + fid;
    }

    @GetMapping(path = "/kostenstelle/bearbeiten/{fid}")
    public String bearbeiten(Model model, @PathVariable Integer fid) {

        model.addAttribute("titel", "Kostenstelle bearbeiten");

        Optional<Kostenstelle> k = kostenstelleRepo.findById(fid);
        if (k.isPresent()) {
            model.addAttribute("kostenstelle", k.get());
        } else {
            return "redirect:/uebersicht";
        }

        return "client/hinzufuegen-bearbeiten-kostenstelle";
    }

    @PostMapping(path = "/kostenstelle/bearbeiten/{fid}")
    public String editKostenstelle(Model model, @Valid @ModelAttribute("kostenstelle") Kostenstelle k, BindingResult results, @PathVariable Integer fid) {

        if (results.hasErrors()) {
            model.addAttribute("titel", "Kostenstelle bearbeiten");
            return "client/hinzufuegen-bearbeiten-kostenstelle";
        }

        kostenstelleRepo.save(k);

        return "redirect:/kostenstellen/" + k.getId();
    }

    @GetMapping(path = "/kostenstelle/loeschen/{fid}/{id}")
    public String loeschen(Model model, @PathVariable Integer fid, @PathVariable Integer id) {

        kostenstelleRepo.deleteById(id);

        return "redirect:/kostenstellen/" + fid;
    }


}
