package util;

import java.sql.*;

public class ConnectionUtil {
    Connection connection = null;
    public static Connection connectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("URL of the database");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't connect to DB");
            return null;
        }
    }
}
