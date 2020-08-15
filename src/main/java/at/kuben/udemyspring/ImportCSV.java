package at.kuben.udemyspring;

import at.kuben.udemyspring.db.FahrzeugRepository;
import at.kuben.udemyspring.model.Fahrzeug;
import at.kuben.udemyspring.util.JsonUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@Order(value = 2)
public class ImportCSV implements CommandLineRunner {

    private FahrzeugRepository fahrzeugRepo;

    private JsonUtil jsonUtil;

    public ImportCSV(FahrzeugRepository fahrzeugRepo, JsonUtil jsonUtil) {
        this.fahrzeugRepo = fahrzeugRepo;
        this.jsonUtil = jsonUtil;
    }

    @Override
    public void run(String... args) throws Exception {

        File csv = ResourceUtils.getFile("classpath:data.csv");

        Files.lines(csv.toPath()).skip(1).forEach(l -> {
            Fahrzeug f = jsonUtil.fromJson(l);
            fahrzeugRepo.save(f);
        });

    }
}
