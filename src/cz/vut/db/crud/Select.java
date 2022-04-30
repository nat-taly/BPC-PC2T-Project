package cz.vut.db.crud;

import cz.vut.db.conn.DBConnection;
import cz.vut.pc2t.student.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.LinkedList;

public class Select {
    public static ListOfStudents allStudents(){
        String name_of_table = "student";

        Connection conn = DBConnection.getDBConnection();

        ListOfStudents listOfStudents = new ListOfStudents();
        String sql = "SELECT * FROM " + name_of_table;
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            while (rs.next()){
                String student_type = Select.studenType_by_id(rs.getInt("student_type"));
                if (student_type.equals("Technical")){
                    listOfStudents.addStudent(rs.getInt("id"),new TechStudent(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("birth_date"),  Select.marks(rs.getInt("id"))));
                }
                else if (student_type.equals("Humanitarian")){
                    listOfStudents.addStudent(rs.getInt("id"),new HumStudent(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("birth_date"), Select.marks(rs.getInt("id"))));
                }
                else if (student_type.equals("Tech&Hum")){
                    listOfStudents.addStudent(rs.getInt("id"),new TechHumStudent(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("birth_date"), Select.marks(rs.getInt("id"))));

                }
            }
            return listOfStudents;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static LinkedList<String> studentTypes(){
        String name_of_table = "student_type";

        Connection conn = DBConnection.getDBConnection();

        LinkedList<String> student_types = new LinkedList<>();

        String sql = "SELECT * FROM " + name_of_table;
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()){
                student_types.add(rs.getString("type"));
            }
            return student_types;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static int idStudenType(String type){

        Connection conn = DBConnection.getDBConnection();

        int id;

        String sql = "SELECT id FROM student_type WHERE type = '" + type + "'";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            id = rs.getInt("id");
            return id;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static String studenType_by_id(int id){

        Connection conn = DBConnection.getDBConnection();

        String type;
        String sql = "SELECT type FROM student_type WHERE id = '" + id + "'";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            type = rs.getString("type");
            return type;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static LinkedList<Integer> marks(int studentID){
        Connection conn = DBConnection.getDBConnection();

        LinkedList<Integer> marks = new LinkedList<>();

        String sql = "SELECT value FROM mark WHERE student = '" + studentID + "'";
        try {
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()){
                marks.add(Integer.parseInt(rs.getString("value")));
            }
            return marks;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
