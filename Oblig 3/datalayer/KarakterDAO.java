package no.uib.info233.oblig3.datalayer;
import no.uib.info233.oblig3.model.Karakter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static no.uib.info233.oblig3.datalayer.DatabaseConnection.getConn;

/**
 * Klasse for Karakter data access object
 * @author Cecilie Lyshoel
 * @author sqlitetutorial.net
 * @version Oblig 3 v2.0
 */

public class KarakterDAO {

    private static KarakterDAO instance;

    /**
     * Metode for å hente instanse av KarakterDAO
     * @return instanse
     */
    public static KarakterDAO getInstance() {
        if (instance == null) {
            instance = new KarakterDAO();
        }
        return instance;
    }

    /**
     * Metode for å hente liste med karakterer
     * @return liste med karakter
     */
    public List<Karakter> getKarakter() {
        ArrayList<Karakter> karakterer = new ArrayList<Karakter>();
        String sql = "SELECT * FROM Karakter JOIN Kurs ON Karakter.kurs = Kurs.kode";

        try (Connection conn = getConn();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                Karakter karakter = new Karakter(rs.getInt("id"), rs.getString("karakter"), rs.getInt("ar"), rs.getString("student"), rs.getString("kurs"), rs.getString("kode"),rs.getString("navn"), rs.getString("skole"));
                karakterer.add(karakter);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return karakterer;
    }

    /**
     * Metode for å hente liste med karakterer basert på studentid
     * @return liste med karakterer
     */
    public List<Karakter> getKarakterByStudent(int studentNummer) {
        ArrayList<Karakter> karakterer = new ArrayList<Karakter>();
        String sql = "SELECT * FROM Karakter JOIN Kurs ON kurs.kode=karakter.kurs WHERE student=?";

        try (Connection conn = getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentNummer);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                Karakter karakter = new Karakter(
                        rs.getInt("id"),
                        rs.getString("karakter"),
                        rs.getInt("ar"),
                        rs.getString("student"),
                        rs.getString("kurs"),
                        rs.getString("kode"),
                        rs.getString("navn"),
                        rs.getString("skole"));
                karakterer.add(karakter);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return karakterer;

    }

}
