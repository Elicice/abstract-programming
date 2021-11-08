package no.uib.info233.oblig3.model;
/**
 * Modellklasse av karakter
 * @author Cecilie Lyshoel
 * @version Oblig3 v2.0
 */

public class Karakter {
    private int karakterId;
    private String karakter;
    private int karakterYear;
    private int studentNummer;
    private String kursKode;

    public Karakter(int id, String karakter, int ar, String student, String kurs, String kode, String navn, String skole) {}

    public Karakter(int karakterId, String karakter, int karakterYear, int studentNummer, String kursKode) {
        this.karakterId = karakterId;
        this.karakter = karakter;
        this.karakterYear = karakterYear;
        this.studentNummer = studentNummer;
        this.kursKode = kursKode;
    }

    public int getKarakterId() {
        return karakterId;
    }

    public void setKarakterId(int karakterId) {
        this.karakterId = karakterId;
    }

    public String getKarakter() {
        return karakter;
    }

    public void setKarakter(String karakter) {
        this.karakter = karakter;
    }

    public int getKarakterYear() {
        return karakterYear;
    }

    public void setKarakterYear(int karakterYear) {
        this.karakterYear = karakterYear;
    }

    public int getStudentNummer() {
        return studentNummer;
    }

    public void setStudentNummer(int studentNummer) {
        this.studentNummer = studentNummer;
    }

    public String getKursKode() {
        return kursKode;
    }

    public void setKursKode(String kursKode) {
        this.kursKode = kursKode;
    }
}

