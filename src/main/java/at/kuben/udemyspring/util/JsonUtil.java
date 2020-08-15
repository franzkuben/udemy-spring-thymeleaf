package at.kuben.udemyspring.util;

import at.kuben.udemyspring.db.BenutzerRepository;
import at.kuben.udemyspring.model.Benutzer;
import at.kuben.udemyspring.model.Fahrzeug;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {

    private BenutzerRepository benutzerRepository;

    public JsonUtil(BenutzerRepository benutzerRepository) {
        this.benutzerRepository = benutzerRepository;
    }

    public Fahrzeug fromJson(String csv) {

        String[] split = csv.split(";");

        for (int i = 0; i < split.length; i++) {
            if (split[i] == null && i != 6) {
                throw new IllegalArgumentException("spalte " + (i + 1) + " ist null");
            }
        }

        Fahrzeug f = new Fahrzeug();
        f.setName(split[0]);
        f.setMarke(split[1]);
        f.setModell(split[2]);

        int baujahr = Integer.parseInt(split[3]);
        double anschaffungswert = Double.parseDouble(split[4]);
        int kaufjahr = Integer.parseInt(split[5]);

        f.setBaujahr(baujahr);
        f.setAnschaffungswert(anschaffungswert);
        f.setKaufjahr(kaufjahr);

        f.setNotiz(split[6]);

        Benutzer b = benutzerRepository.findById(split[7]).orElse(null);

        if(b == null)
            throw new IllegalArgumentException("user" + split[7] + " not found");

        f.setBenutzer(b);

        return f;
    }


}
