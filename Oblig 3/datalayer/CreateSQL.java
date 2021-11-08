package no.uib.info233.oblig3.datalayer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static no.uib.info233.oblig3.datalayer.DatabaseConnection.getConn;

/**
 * Klasse for å opprette tabeller i database
 * @author sqlitetutorial.net
 * @author Cecilie Lyshoel
 * @version oblig 3 v2.0
 */
public class CreateSQL {

    /**
     * Metode for å sette inn tabell skole i database
     */
    public static void createNewTableSkole() {
        String sql = "CREATE TABLE Skole (navn text PRIMARY KEY)";
        try (Connection conn = getConn();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metode for å sette inn tabell kull i database
     */

    public static void createNewTableKull() {
        String sql = "CREATE TABLE Kull (kode text PRIMARY KEY, skole text REFERENCES Skole(navn))";
        try (Connection conn = getConn();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Metode for å sette inn tabell Student i database
     */
    public static void createNewTableStudent() {
        String sql = "CREATE TABLE Student (nr integer PRIMARY KEY, navn text NOT NULL, kull text REFERENCES Kull(kode))";
        try (Connection conn = getConn();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Metode for å sette inn tabell karakter i database
     */
    public static void createNewTableKarakter() {
        String sql = "CREATE TABLE Karakter (id integer PRIMARY KEY, karakter text NOT NULL, ar integer NOT NULL, student text REFERENCES Student(nr), kurs text REFERENCES Kurs(kode))";
        try (Connection conn = getConn();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Metode for å sette inn tabell kurs i database
     */
    public static void createNewTableKurs() {
        String sql = "CREATE TABLE Kurs (kode text PRIMARY KEY, navn text NOT NULL, skole text REFERENCES Skole (navn))";
        try (Connection conn = getConn();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




    /**
     * Main metide for å legge til tabeller fra skjema i database
     * @param args
     */
    public static void main(String[] args) {
        createNewTableSkole();
        createNewTableKull();
        createNewTableStudent();
        createNewTableKarakter();
        createNewTableKurs();
    }
}
