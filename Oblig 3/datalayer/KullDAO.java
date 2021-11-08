package no.uib.info233.oblig3.datalayer;

import no.uib.info233.oblig3.model.Kull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static no.uib.info233.oblig3.datalayer.DatabaseConnection.getConn;

/**
 * data assecc objekt klasse for kull
 * @author Cecilie Lyshoel
 * @author sqlitetutorial.net
 * @version oblig 3 v2.0
 */
public class KullDAO {
    private static KullDAO instance;

    /**
     * Metode for å hente instanse fra dao-objektet
     * @return instanse
     */
    public static KullDAO getInstance() {
        if (instance == null) {
            instance = new KullDAO();
        }
        return instance;
    }

    /**
     * metode for å hente ut kull fra database
     * @return kull
     */
    public List<Kull> getKull() {
        ArrayList<Kull> kull = new ArrayList<Kull>();
        String sql = "SELECT * FROM Kull";

        try (Connection conn = getConn();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("kode") + "\t");

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kull;
    }

    /**
     * Metode for å hente ut kull basert på kullkode
     * @param kullKode
     * @return kull
     */
    public Kull getKullByKullKode(String kullKode){

        String sql = "SELECT * FROM Kull WHERE kode=?";

        try (Connection conn = getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, kullKode);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Kull kull = new Kull(rs.getString("kullKode"), rs.getString("skoleNavn"));
                return kull;
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    /**
     * Metode for å hente ut liste med kull basert på skole
     * @param skoleNavn
     * @return liste med kull
     */
    public List<Kull> getKullBySkole(String skoleNavn){
        ArrayList<Kull> kuller = new ArrayList<Kull>();
        String sql = "SELECT * FROM Kull WHERE skole=?";

        try (Connection conn = getConn();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                Kull kull = new Kull(rs.getString("kullKode"), rs.getString("skoleNavn"));
                kuller.add(kull);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kuller;
    }

}
