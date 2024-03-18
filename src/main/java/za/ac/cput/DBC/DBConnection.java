package za.ac.cput.DBC;

import java.sql.*;

public class DBConnection {

    public static Connection derbyConnection() throws SQLException {

        String dCon = "jdbc:derby://localhost:1527/University";
        String name = "username";
        String pass = "password";

        Connection connection = DriverManager.getConnection(dCon, name, pass);
        return connection;

    }
}
