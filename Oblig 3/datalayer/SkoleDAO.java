package no.uib.info233.oblig3.datalayer;

import no.uib.info233.oblig3.model.Skole;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import static no.uib.info233.oblig3.datalayer.DatabaseConnection.getConn;

/**
 * Data access objekt klasse for skole
 * @author Cecilie Lyshoel
 * @author sqlitetutorial.net
 * @version oblig3 v2.0
 */
public class SkoleDAO {

    private static SkoleDAO instance;

    /**
     * Metode for å hente instanse fra dao-objektet
     * @return instanse
     */
    public static SkoleDAO getInstance() {
        if (instance == null) {
            instance = new SkoleDAO();
        }
        return instance;
    }
    /**
     * metode for å hente ut skoler
     * @return lise med skoler
     */
    public List<Skole> getSkoler() {
        ArrayList<Skole> skoler = new ArrayList<Skole>();
        String sql = "SELECT * FROM Skole";

        try (Connection conn = getConn();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Skole skole = new Skole(rs.getString("navn"));
                skoler.add(skole);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return skoler;
    }

    /**
     * Metode for å hente ut skoler basert på kull
     * @param kullKode
     * @return null
     */
    public Skole getSkoleByKull(String kullKode) {
        String sql = "SELECT skole FROM Kull WHERE kode=?";

        try (Connection conn = getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, kullKode);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Skole skole = new Skole(rs.getString("skoleNavn"));
                return skole;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Metode for å hente skole basert på kurs
     * @param kursKode
     * @return null
     */
    public Skole getSkoleByKurs(String kursKode) {
        String sql = "SELECT skole FROM Kurs WHERE kode=?";

        try (Connection conn = getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, kursKode);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Skole skole = new Skole(rs.getString("skoleNavn"));
                return skole;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
