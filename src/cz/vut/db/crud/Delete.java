package cz.vut.db.crud;

import cz.vut.db.conn.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
    public static void student(int id){
        Connection conn = DBConnection.getDBConnection();
        Delete.mark(id);
        String sql = "DELETE FROM student WHERE id=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id );
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void mark(int student_id){
        Connection conn = DBConnection.getDBConnection();

        String sql = "DELETE FROM mark WHERE student=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, student_id );
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void allMakrs(){
        Connection conn = DBConnection.getDBConnection();

        String sql = "DELETE FROM mark";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void allStudents(){
        Connection conn = DBConnection.getDBConnection();

        String sql = "DELETE FROM student";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
