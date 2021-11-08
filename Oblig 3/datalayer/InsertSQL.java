package no.uib.info233.oblig3.datalayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static no.uib.info233.oblig3.datalayer.DatabaseConnection.getConn;

/**
 * Klasse for å inserte data inn i database
 * @author sqlitetutorial.net
 * @author Cecilie Lyshoel
 * @version oblig3 v 2.0
 */

public class InsertSQL {
    /**
     * Metode for å sette inn data i  karaktertabell i database
     * @param karakterId
     * @param karakter
     * @param karakterYear
     * @param studentNummer
     * @param kursKode
     */
    public static void insertKarakter(int karakterId, String karakter, int karakterYear, int studentNummer, String kursKode){
        String sql ="INSERT INTO Karakter(id,karakter,ar,student,kurs) VALUES(?,?,?,?,?)";

        try (Connection conn = getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, karakterId);
            pstmt.setString(2, karakter);
            pstmt.setInt(3, karakterYear);
            pstmt.setInt(4, studentNummer);
            pstmt.setString(5, kursKode);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metode for å sette inn data i  skoletabell i database
     * @param skoleNavn
     */
    public static void insertSkole(String skoleNavn) {
        String sql = "INSERT INTO Skole(navn) VALUES(?)";

        try (Connection conn = getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, skoleNavn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metode for å sette inn data i  studenttabell i database
     * @param studentId
     * @param studentNavn
     * @param kullKode
     */
    public static void insertStudent(int studentId,String studentNavn,String kullKode) {
        String sql = "INSERT INTO Student(nr,navn,kull) VALUES(?,?,?)";

        try (Connection conn = getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);
            pstmt.setString(2, studentNavn);
            pstmt.setString(3, kullKode);


            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Metode for å sette inn data i  kulltabell i database
     * @param kullKode
     * @param skoleNavn
     */
    public static void insertKull(String kullKode, String skoleNavn) {
        String sql = "INSERT INTO Kull(kode, skole) VALUES(?,?)";

        try (Connection conn = getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, kullKode);
            pstmt.setString(2, skoleNavn);


            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metode for å sette inn data i  kurstabell i database
     * @param kursKode
     * @param kursNavn
     * @param skoleNavn
     */
    public static void insertKurs(String kursKode, String kursNavn, String skoleNavn) {
        String sql = "INSERT INTO Kurs(kode,navn,skole) VALUES(?,?,?)";

        try (Connection conn = getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, kursKode);
            pstmt.setString(2, kursNavn);
            pstmt.setString(3, skoleNavn);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Main metode for å sette inn data i database
     * @param args
     */
    public static void main(String[] args) {

        insertSkole("UiB");
        insertKull("2019V","UiB");
        insertStudent(1,"Per", "2019V");
        insertKarakter(1, "A",2019, 1, "INFO233");
        insertKurs("INFO233", "Avansert programmering","UiB");
        insertStudent(2,"Kari", "2019V");
        insertKarakter(2, "A",2019, 2, "INFO233");
    }


}
