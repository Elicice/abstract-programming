package no.uib.info233.oblig3.model;

/**
 * modellklasse av skole
 * @author Cecilie Lyshoel
 * @version oblig3 v2.0
 */
public class Skole {
    private String skoleNavn;

    public Skole(){}
    public Skole(String skoleNavn) {
        this.skoleNavn = skoleNavn;
    }

    public String getSkoleNavn() {
        return skoleNavn;
    }

    public void setSkoleNavn(String skoleNavn) {
        this.skoleNavn = skoleNavn;
    }
}
