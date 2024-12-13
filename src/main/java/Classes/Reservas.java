package Classes;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reservas {

    private int hospedeId, quartoId;
    private String servico, data_entrada, data_saida, metodo_pagamento;
    private Double total;

    // Constructor to initialize Reservas attributes
    public Reservas(int hospedeId, int quartoId, String servico, String data_entrada, String data_saida, Double total, String metodo_pagamento) {
        this.hospedeId = hospedeId;
        this.quartoId = quartoId;
        this.servico = servico;
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
        this.total = total;
        this.metodo_pagamento = metodo_pagamento;
    }

    public Reservas() {
        return;
    }

    public String getNumeroQuartoById(int quartoId) {
        String numeroQ = null;
        Connection conn = Database.getConnection(); // Obtém a conexão com o banco de dados

        try {
            // SQL query para obter o número do quarto
            String query = "SELECT numero FROM quartos WHERE id_quarto = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, quartoId); // Define o parâmetro quartoId

            // Executa a consulta e armazena o resultado
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                numeroQ = rs.getString("numero");
            } else {
                // Se não encontrar resultados, você pode lançar uma exceção ou atribuir um valor padrão
                Logger.getLogger(Quartos.class.getName()).log(Level.WARNING, "Quarto com ID {0} não encontrado.", quartoId);
                numeroQ = "Desconhecido";
            }

        } catch (SQLException ex) {
            // Loga o erro caso haja uma exceção
            Logger.getLogger(Quartos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Fecha a conexão com o banco de dados
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Quartos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return numeroQ; // Retorna o número do quarto ou "Desconhecido" se não encontrado
    }

    public int obterIdHospedePorNome(String nomeHospede) {
        try (Connection conn = Database.getConnection()) {
            // Debug: Verifique o nome que está sendo passado para a consulta
            System.out.println("Nome do hóspede sendo procurado: " + nomeHospede);

            String query = "SELECT id_hospede FROM hospedes WHERE LOWER(TRIM(nome)) = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nomeHospede.trim().toLowerCase());  // Trim spaces and convert to lowercase

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_hospede");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public String getNomeHospedeById(int hospedeId) {
        String nome = null;
        Connection conn = Database.getConnection(); // Obtém a conexão com o banco de dados

        try {
            // SQL query para obter o nome do hóspede a partir do hospedeId
            String query = "SELECT nome FROM hospedes WHERE id_hospede = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, hospedeId); // Define o parâmetro hospedeId

            // Executa a consulta e armazena o resultado
            var rs = stmt.executeQuery();
            if (rs.next()) {
                nome = rs.getString("nome"); // Obtém o nome do hóspede
            }

        } catch (SQLException ex) {
            // Loga o erro caso haja uma exceção
            Logger.getLogger(Reservas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Fecha a conexão com o banco de dados
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Reservas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return nome; // Retorna o nome do hóspede ou null caso não encontrado
    }

    // Method to insert a reservation into the database
    public void inserirReserva(int hospedeId, int quartoId, String servico, String data_entrada, String data_saida, Double total, String metodo_pagamento) {
        Connection conn = Database.getConnection(); // Get database connection
        try {
            // SQL query to insert data into reservas table
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO reservas (hospede_id, quarto_id, servico, data_entrada, data_saida, total, metodo_pagamento) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            stmt.setInt(1, hospedeId); // Set 'hospedeId' parameter
            stmt.setInt(2, quartoId); // Set 'quartoId' parameter
            stmt.setString(3, servico); // Set 'servico' parameter
            stmt.setString(4, data_entrada); // Set 'data_entrada' parameter
            stmt.setString(5, data_saida); // Set 'data_saida' parameter
            stmt.setDouble(6, total); // Set 'total' parameter
            stmt.setString(7, metodo_pagamento); // Set 'metodo_pagamento' parameter

            stmt.execute(); // Execute the query

        } catch (SQLException ex) {
            // Log the error in case of an exception
            Logger.getLogger(Reservas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close the database connection
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Reservas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
