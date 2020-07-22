package at.kuben.udemyspring.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "kostenstelle")
@SequenceGenerator(name = "kostenstelle_seq", sequenceName = "kostenstelle_seq_id")
public class Kostenstelle {

    @Id
    @Column(name = "k_id")
    @GeneratedValue(generator = "kostenstelle_seq")
    private Integer id;

    @Size(max = 250, message = "Die Notiz darf maximal 250 Zeichen lang sein!")
    @Column(name = "k_notiz")
    private String notiz;

    @PositiveOrZero(message = "Der Wert muss positiv oder null sein!")
    @NotNull(message = "Der Wert darf nicht leer sein!")
    @Column(name = "k_wert")
    private Double wert;

    @NotBlank(message = "Der Lieferant darf nicht leer sein!")
    @Column(name = "k_lieferant")
    private String lieferant;

    @ManyToOne
    @JoinColumn(name = "k_fahrzeug")
    private Fahrzeug fahrzeug;

    public Kostenstelle() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotiz() {
        return notiz;
    }

    public void setNotiz(String notiz) {
        this.notiz = notiz;
    }

    public Double getWert() {
        return wert;
    }

    public void setWert(Double wert) {
        this.wert = wert;
    }

    public String getLieferant() {
        return lieferant;
    }

    public void setLieferant(String lieferant) {
        this.lieferant = lieferant;
    }

    public Fahrzeug getFahrzeug() {
        return fahrzeug;
    }

    public void setFahrzeug(Fahrzeug fahrzeug) {
        this.fahrzeug = fahrzeug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kostenstelle that = (Kostenstelle) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Kostenstelle{" +
                "id=" + id +
                ", notiz='" + notiz + '\'' +
                ", wert=" + wert +
                ", lieferant='" + lieferant + '\'' +
                ", fahrzeug=" + fahrzeug +
                '}';
    }
}
