package no.uib.info233.oblig3.model;

/**
 * Modellklasse av kurs
 * @author Cecilie Lyshoel
 * @version oblig3 v2.0
 */
public class Kurs {
    private String kursKode;
    private String kursNavn;
    private String skoleNavn;



    public Kurs(){}
    public Kurs(String kursKode, String kursNavn, String skoleNavn) {
        this.kursKode = kursKode;
        this.kursNavn = kursNavn;
        this.skoleNavn = skoleNavn;

    }


    public String getKursKode() {
        return kursKode;
    }

    public String getKursNavn() {
        return kursNavn;
    }

    public void setKursKode(String kursKode) {
        this.kursKode = kursKode;
    }

    public void setKursNavn(String kursNavn) {
        this.kursNavn = kursNavn;
    }

    public String getSkoleNavn() {
        return skoleNavn;
    }

    public void setSkoleNavn(String skoleNavn) {
        this.skoleNavn = skoleNavn;
    }
}