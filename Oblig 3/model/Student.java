package no.uib.info233.oblig3.model;
/**
 * Modellklasse av student
 * @author Cecilie Lyshoel
 * @version oblig3 v2.0
 */
public class Student {
    private int studentId;
    private String studentNavn;
    private String kullKode;


    public Student(){}
    public Student(int studentId, String studentNavn, String kullKode) {
        this.studentId = studentId;
        this.studentNavn = studentNavn;
        this.kullKode = kullKode;

    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentNavn() {
        return studentNavn;
    }

    public void setStudentNavn(String studentNavn) {this.studentNavn = studentNavn;}

    public String getKullKode() { return kullKode; }

    public void setKullKode(String kullKode) { this.kullKode = kullKode; }
}
