package no.uib.info233.oblig3.model;

/**
 * Modellklasse av kull
 * @author Cecilie Lyshoel
 * @version oblig3 v2.0
 */
public class Kull {
    private String kullKode;
    private String skoleNavn;


    public Kull(){}
    public Kull(String kullKode, String skoleNavn) {
        this.kullKode = kullKode;
        this.skoleNavn = skoleNavn;
    }


    public String getKullKode() {
        return kullKode;
    }

    public void setKullKode(String kullKode) {
        this.kullKode = kullKode;
    }

    public String getSkoleNavn() {
        return skoleNavn;
    }

    public void setSkoleNavn(String skoleNavn) {
        this.skoleNavn = skoleNavn;
    }
}
