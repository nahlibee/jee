package modulee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModuleInsertTest {

    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost/profvacatairebd";
        String username = "root";
        String password = "";

        // Module data
        String nom_module = "Test Module";
        String abrev_module = "TM";

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected to the database.");

                // SQL query to insert a module
                String sql = "INSERT INTO module (nom_module, abrev_module) VALUES (?, ?)";

                // Create a PreparedStatement
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, nom_module);
                    statement.setString(2, abrev_module);

                    // Execute the insert statement
                    int rowsAffected = statement.executeUpdate();

                    // Check if the insertion was successful
                    if (rowsAffected > 0) {
                        System.out.println("Module inserted successfully.");
                    } else {
                        System.out.println("Failed to insert module.");
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
