package at.kuben.udemyspring.db;

import at.kuben.udemyspring.model.Benutzer;
import at.kuben.udemyspring.model.Kostenstelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KostenstelleRepository extends JpaRepository<Kostenstelle, Integer> {

    @Query("select sum(k.wert) from Kostenstelle k where k.fahrzeug.id = ?1")
    Double sumKosten(int fid);

}
