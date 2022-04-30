package cz.vut.db.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private final static String sqlPath = "jdbc:sqlite:/Users/natalievychodilova/IdeaProjects/PC2T-Project/database.db";
    private static volatile Connection dbConnection;

    private DBConnection() {
    }

    public static Connection getDBConnection() {
        if (dbConnection == null) {
            Class var0 = DBConnection.class;
            synchronized(DBConnection.class) {
                if (dbConnection == null) {
                    try {
                        Class.forName("org.sqlite.JDBC");
                        dbConnection = DriverManager.getConnection(sqlPath);
                    } catch (ClassNotFoundException | SQLException var3) {
                        var3.printStackTrace();
                    }
                }
            }
        }
        return dbConnection;
    }

    public static void closeConnection() {
        try {
            dbConnection.close();
        } catch (SQLException var1) {
            var1.printStackTrace();
        }
    }
}
