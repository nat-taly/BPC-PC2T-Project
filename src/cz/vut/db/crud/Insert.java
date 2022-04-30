package cz.vut.db.crud;

import cz.vut.db.conn.DBConnection;
import cz.vut.pc2t.student.*;

import java.sql.*;

public class Insert {
    public static void student(Student student){

        Connection conn = DBConnection.getDBConnection();

        String sql = "INSERT INTO student(id,name, surname, birth_date, student_type) VALUES (?,?,?,?,?);";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getSurname());
            pstmt.setString(4, student.getBirth_date());
            pstmt.setInt(5, Select.idStudenType(student.getStudent_type()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void mark(int student_id,int mark){
        Connection conn = DBConnection.getDBConnection();

        String sql = "INSERT INTO mark(value, student) VALUES (?,?);";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, mark);
            pstmt.setInt(2, student_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void rewriteDatabse(ListOfStudents students){
        Delete.allMakrs();
        Delete.allStudents();

        for (Student student: students.getAllStudents().values()){
            Insert.student(student);
            for (int mark: student.getMarks()){
                Insert.mark(student.getId(), mark);
            }
        }
    }

    public static void init_data(String command){
        Connection conn = DBConnection.getDBConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement(command);
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
