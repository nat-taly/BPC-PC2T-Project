package cz.vut.db.init;

import cz.vut.db.conn.DBConnection;
import cz.vut.db.crud.Insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Create {
    private static void init_table(String command){
        Connection conn = DBConnection.getDBConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(command);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void init_tables(){
        init_table("CREATE TABLE IF NOT EXISTS student_type(id INTEGER PRIMARY KEY AUTOINCREMENT, type VARCHAR(45) UNIQUE);");
        init_table("CREATE TABLE IF NOT EXISTS student(id INTEGER PRIMARY KEY NOT NULL,name VARCHAR(45),surname VARCHAR(45), birth_date DATE, student_type INTEGER, FOREIGN KEY(student_type) REFERENCES student_type(id));");
        init_table("CREATE TABLE IF NOT EXISTS mark(id INTEGER PRIMARY KEY AUTOINCREMENT, value VARCHAR(45), student INTEGER NOT NULL , CONSTRAINT fk_student FOREIGN KEY(student) REFERENCES student(id) ON DELETE CASCADE);");
        Insert.init_data("INSERT INTO student_type(type) values ('Humanitarian');");
        Insert.init_data("INSERT INTO student_type(type) values ('Technical');");
        Insert.init_data("INSERT INTO student_type(type) values ('Tech&Hum');");
    }
}
