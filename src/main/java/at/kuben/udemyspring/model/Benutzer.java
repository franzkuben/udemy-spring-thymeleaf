package at.kuben.udemyspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "benutzer")
public class Benutzer {

    @Id
    @Column(name = "b_benutzername", length = 50)
    private String benutzername;

    @Column(name = "b_passwort", nullable = false)
    private String passwort;

    public Benutzer() {
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Benutzer benutzer = (Benutzer) o;
        return Objects.equals(benutzername, benutzer.benutzername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(benutzername);
    }

    @Override
    public String toString() {
        return "Benutzer{" +
                "benutzername='" + benutzername + '\'' +
                ", passwort='" + passwort + '\'' +
                '}';
    }
}
