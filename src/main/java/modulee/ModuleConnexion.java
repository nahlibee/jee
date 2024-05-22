package modulee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ModuleConnexion {
    public static Connection conn;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/profvacatairebd", "root", "");
            System.out.println("Connection successfully...");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions
            throw e;
        }
    }
}
