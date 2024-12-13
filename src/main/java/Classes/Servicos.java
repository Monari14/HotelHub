package Classes;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servicos {

    private String tipo, preco;

    // Constructor to initialize Servicos attributes
    public Servicos(String tipo, String preco) {
        this.tipo = tipo;
        this.preco = preco;
    }

    // Method to insert a new service into the database
    public void inserirServicos(String tipo, double preco) {
        Connection conn = Database.getConnection(); // Get database connection
        try {
            // Check if it's the first service
            if (isFirstService(conn)) {
                // Insert "Nenhum" service first with price 0.0
                inserirNenhum(conn);
            }

            // Insert the custom service
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO servicos (tipo, preco) VALUES (?, ?)");
            stmt.setString(1, tipo); // Set 'tipo' parameter
            stmt.setDouble(2, preco); // Set 'preco' parameter

            stmt.execute(); // Execute the query

        } catch (SQLException ex) {
            // Log the error in case of an exception
            Logger.getLogger(Servicos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close the database connection
            closeConnection(conn);
        }
    }

    // Helper method to insert the "Nenhum" service first
    private void inserirNenhum(Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO servicos (tipo, preco) VALUES (?, ?)");
        stmt.setString(1, "Nenhum"); // Insert "Nenhum"
        stmt.setDouble(2, 0.0); // Set price to 0.0
        stmt.execute(); // Execute the insertion
    }

    // Helper method to check if this is the first service being inserted
    private boolean isFirstService(Connection conn) {
        boolean isFirst = false;

        try {
            // SQL query to count the services in the table
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM servicos");
            var resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count == 0) {
                    isFirst = true; // It's the first service
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Servicos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isFirst;
    }

    // Helper method to close the connection
    private void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Servicos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
