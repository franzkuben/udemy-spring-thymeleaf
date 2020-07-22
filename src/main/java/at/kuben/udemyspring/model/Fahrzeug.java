package at.kuben.udemyspring.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "fahrzeug")
@SequenceGenerator(name = "fahrzeug_seq", sequenceName = "fahrzeug_seq_id")
public class Fahrzeug {

    @Id
    @Column(name = "f_id")
    @GeneratedValue(generator = "fahrzeug_seq")
    private Integer id;

    @NotBlank
    @Size(min = 3, message = "Der Name muss mindestens 3 Zeichen lang sein.")
    @Column(name = "f_name")
    private String name;

    @NotBlank
    @Column(name = "f_marke")
    private String marke;

    @NotBlank
    @Column(name = "f_modell")
    private String modell;

    @Min(value = 1900, message = "Das Baujahr muss mindestens 1900 sein.")
    @NotNull(message = "Baujahr darf nicht leer sein!")
    @Column(name = "f_baujahr")
    private Integer baujahr;

    @PositiveOrZero(message = "Der Anschaffungswert muss positiv sein!")
    @Column(name = "f_anschaffungswert")
    private Double anschaffungswert;

    @Min(value = 1900, message = "Das Kaufjahr muss mindestens 1900 sein.")
    @NotNull(message = "Kaufjahr darf nicht leer sein!")
    @Column(name = "f_kaufjahr")
    private Integer kaufjahr;

    @Column(name = "f_notiz")
    private String notiz;

    @ManyToOne
    @JoinColumn(name = "f_benutzer")
    private Benutzer benutzer;

    @OneToMany(mappedBy = "fahrzeug")
    private List<Kostenstelle> kostenstellen;

    public Fahrzeug() {
    }

    public List<Kostenstelle> getKostenstellen() {
        return kostenstellen;
    }

    public void setKostenstellen(List<Kostenstelle> kostenstellen) {
        this.kostenstellen = kostenstellen;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMarke() {
        return marke;
    }

    public String getModell() {
        return modell;
    }

    public Integer getBaujahr() {
        return baujahr;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer b) {
        this.benutzer = b;
    }

    public Double getAnschaffungswert() {
        return anschaffungswert;
    }

    public Integer getKaufjahr() {
        return kaufjahr;
    }

    public String getNotiz() {
        return notiz;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public void setBaujahr(Integer baujahr) {
        this.baujahr = baujahr;
    }

    public void setAnschaffungswert(Double anschaffungswert) {
        this.anschaffungswert = anschaffungswert;
    }

    public void setKaufjahr(Integer kaufjahr) {
        this.kaufjahr = kaufjahr;
    }

    public void setNotiz(String notiz) {
        this.notiz = notiz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fahrzeug fahrzeug = (Fahrzeug) o;
        return Objects.equals(id, fahrzeug.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Fahrzeug{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marke='" + marke + '\'' +
                ", modell='" + modell + '\'' +
                ", baujahr='" + baujahr + '\'' +
                ", anschaffungswert=" + anschaffungswert +
                ", kaufjahr=" + kaufjahr +
                ", notiz='" + notiz + '\'' +
                '}';
    }
}
