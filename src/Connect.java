// Connect.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connect {
    private static final String URL = "jdbc:mysql://localhost:3306/libfinal";
    private static final String USER = "root";
    private static final String PASSWORD = "YOURDBPASSWORD";
    private static final Logger LOGGER = Logger.getLogger(Connect.class.getName());

    // Private constructor to prevent instantiation
    public Connect() {}

    public static Connection getConnection() {
        Connection con = null;
        try {
            // Optional: Explicitly load the MySQL driver
            // Class.forName("com.mysql.cj.jdbc.Driver");
            
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            LOGGER.log(Level.INFO, "Database connection established.");
        } catch (SQLException /*| ClassNotFoundException*/ ex) {
            LOGGER.log(Level.SEVERE, "Failed to create the database connection.", ex);
            // Optionally, rethrow the exception or handle it as needed
        }
        return con;
    }

    // Example main method to test the connection
    public static void main(String[] args) {
        try (Connection connection = Connect.getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Failed to establish connection.");
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "An error occurred while closing the connection.", ex);
        }
    }
}
