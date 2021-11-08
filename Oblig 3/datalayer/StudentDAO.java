package no.uib.info233.oblig3.datalayer;
import no.uib.info233.oblig3.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static no.uib.info233.oblig3.datalayer.DatabaseConnection.getConn;

/**
 * data access object klasse for Student
 * @author Cecilie Lyshoel
 * @author sqlitetutorial.net
 * @version oblig 3 v 2.0
 */
public class StudentDAO {

    private static StudentDAO instance;

    /**
     * Metode for å hente instanse fra dao-objektet
     * @return instanse
     */
    public static StudentDAO getInstance() {
        if (instance == null) {
            instance = new StudentDAO();
        }
        return instance;
    }

    /**
     * Metode for å kunne hente ut student fra database
     * @return liste med studenter
     */
    public List<Student> getStudent() {
        ArrayList<Student> studenter = new ArrayList<Student>();
        String sql = "SELECT * FROM Student";

        try (Connection conn = getConn();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Student student = new Student(rs.getInt("nr"), rs.getString("navn"), rs.getString("kull"));
                studenter.add(student);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studenter;
    }

    /**
     * Metode for å finne student basert på kull
     * @param kullKode
     * @return liste med studenter
     */
    public List<Student> getStudentByKull(String kullKode){
        ArrayList<Student> studenter = new ArrayList<Student>();
        String sql = "SELECT * FROM Student WHERE kull=?";

        try (Connection conn = getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, kullKode);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Student student = new Student(rs.getInt("nr"), rs.getString("navn"), rs.getString("kull"));
                studenter.add(student);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studenter;
    }

    /**
     * Metod efor å hente ut studenter basert på kurs
     * @param kursKode
     * @return liste emd studenetr
     */
    public List<Student> getStudentByKurs(String kursKode){
        ArrayList<Student> studenter = new ArrayList<Student>();
        String sql = "SELECT student FROM Karakter JOIN Kurs ON Kurs.kode WHERE kode=?";

        try (Connection conn = getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, kursKode);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Student student = new Student(rs.getInt("nr"), rs.getString("navn"), rs.getString("kull"));
                studenter.add(student);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studenter;

    }
    public Student getStudentById(int studentId){

        String sql = "SELECT * FROM Student WHERE nr=?";

        try (Connection conn = getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Student student = new Student(rs.getInt("nr"), rs.getString("navn"), rs.getString("kull"));
                return student;
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public ArrayList<Student> getStudentBySkole(String skole){
        ArrayList<Student> studenter = new ArrayList<Student>();
        String sql = "SELECT * FROM Student JOIN Kull on Student.kull=Kull.kode WHERE skole=?";

        try (Connection conn = getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, skole);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                Student student = new Student(rs.getInt("nr"), rs.getString("navn"), rs.getString("kull"));
                studenter.add(student);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studenter;
    }

    /**
     * Metode for å lagre student i database hvis tabell blir oppdatert
     * @param student
     */
    public void saveStudent(Student student) {

        String sql = "UPDATE Student SET navn=? where nr=?";

        try (Connection conn = getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getStudentNavn());
            pstmt.setInt(2, student.getStudentId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metode for å hente neste studentId
     * @return neste studentid
     * @throws SQLException
     */
    public int getNextStudentId() {
        String sql = "SELECT max (nr) AS nr FROM Student";

        try (Connection conn = getConn()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()){
                return rs.getInt("nr")+1;
            }else{
                return 1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

}
