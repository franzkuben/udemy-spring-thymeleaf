package at.kuben.udemyspring;

import at.kuben.udemyspring.db.BenutzerRepository;
import at.kuben.udemyspring.db.FahrzeugRepository;
import at.kuben.udemyspring.model.Benutzer;
import at.kuben.udemyspring.model.Fahrzeug;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PopulateDatabase implements CommandLineRunner {

    private FahrzeugRepository fahrzeugRepo;

    private BenutzerRepository benutzerRepository;

    public PopulateDatabase(FahrzeugRepository fahrzeugRepo, BenutzerRepository benutzerRepository) {
        this.fahrzeugRepo = fahrzeugRepo;
        this.benutzerRepository = benutzerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Benutzer b = new Benutzer();
        b.setBenutzername("admin");
        b.setPasswort(new BCryptPasswordEncoder().encode("1234"));
        benutzerRepository.save(b);

    }
}
