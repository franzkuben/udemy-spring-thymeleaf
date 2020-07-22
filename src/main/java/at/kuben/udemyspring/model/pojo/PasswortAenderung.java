package at.kuben.udemyspring.model.pojo;

import javax.validation.constraints.NotBlank;

public class PasswortAenderung {

    @NotBlank
    private String passwort;

    public PasswortAenderung() {
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
