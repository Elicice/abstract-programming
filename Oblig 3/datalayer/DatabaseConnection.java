package no.uib.info233.oblig3.datalayer;

import java.sql.*;


/**
 * Klasse for å koble database
 * @author Cecilie Lyshoel
 * @author sqlitetutorial.net
 * @version Oblig 3 v2.0
 */


public class DatabaseConnection {

    private static Connection conn;

    /**
     * Metode for å opprette kontakt med database
     * @return connection
     * @throws SQLException
     */
    public static Connection getConn() throws SQLException {

        if (conn != null && !conn.isClosed()) return conn;

            String url = "jdbc:sqlite:Opprettdatabase.db"; //hvor databasen opprettes

            conn = DriverManager.getConnection(url);//Oppretter kontakt med databasen

            System.out.println("Tilkobling OK");

        return conn;

    }


}


