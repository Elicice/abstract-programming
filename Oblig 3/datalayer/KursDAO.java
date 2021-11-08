package no.uib.info233.oblig3.datalayer;

import no.uib.info233.oblig3.model.Kurs;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static no.uib.info233.oblig3.datalayer.DatabaseConnection.getConn;

/**
 * Data access objekt klasse for kurs
 * @author Cecilie Lyshoel
 * @author sqlitetutorial.net
 * @version oblig3 v2.0
 */
public class KursDAO {
    private static KursDAO instance;

    /**
     * Metode for å hente instanse fra dao-objektet
     * @return instanse
     */
    public static KursDAO getInstance() {
        if (instance == null) {
            instance = new KursDAO();
        }
        return instance;
    }

    /**
     * Metodefor å hente ut likste med kurs
     * @return liste med kurs
     */
    public List<Kurs> getKurs(){
        ArrayList<Kurs> kurser = new ArrayList<Kurs>();
        String sql = "SELECT * FROM Kurs";

        try (Connection conn = getConn();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                Kurs kurs = new Kurs(rs.getString("kursKode"), rs.getString("kursNavn"), rs.getString("skoleNavn"));
                kurser.add(kurs);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kurser;
    }

    /**
     * Metode som henter kurs basert på student
     * @return liste emd kurs
     */
    public List<Kurs> getKursByStudent(int studentId){
        ArrayList<Kurs> kurser = new ArrayList<Kurs>();
        String sql = "SELECT * FROM Kurs JOIN Karakter ON Kurs.kode=karakter.kurs WHERE karakter.student=?";

        try (Connection conn = getConn();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                Kurs kurs = new Kurs(rs.getString("kursKode"), rs.getString("kursNavn"), rs.getString("skoleNavn"));
                kurser.add(kurs);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kurser;
    }

    /**
     * Metode for å hente ut kurs basert på skole
     * @param skoleNavn
     * @return liste med kurs
     */
    public List<Kurs> getKursBySkole(String skoleNavn){
        ArrayList<Kurs> kurser = new ArrayList<Kurs>();
        String sql = "SELECT * FROM Kurs WHERE skole=?";

        try (Connection conn = getConn();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                Kurs kurs = new Kurs(rs.getString("kursKode"), rs.getString("kursNavn"), rs.getString("skoleNavn"));
                kurser.add(kurs);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kurser;
    }


}
