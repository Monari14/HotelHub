// Importações necessárias
package Classes;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuartosReservados {

    private String hospede, quarto, data_entrada, data_saida;
    private Double valor;

    public String getHospede() {
        return hospede;
    }

    public void setHospede(String hospede) {
        this.hospede = hospede;
    }

    public String getQuarto() {
        return quarto;
    }

    public void setQuarto(String quarto) {
        this.quarto = quarto;
    }

    public String getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(String data_entrada) {
        this.data_entrada = data_entrada;
    }

    public String getData_saida() {
        return data_saida;
    }

    public void setData_saida(String data_saida) {
        this.data_saida = data_saida;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    // Método para inserir um quarto reservado no banco de dados
    public void inserirQuartoReservado(String hospede, String quarto, double valor, String data_entrada, String data_saida) {
        Connection conn = Database.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO quartosreservados (hospede, quarto, valor, data_entrada, data_saida) VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setString(1, hospede);
            stmt.setString(2, quarto);
            stmt.setDouble(3, valor);
            stmt.setString(4, data_entrada);
            stmt.setString(5, data_saida);

            stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(QuartosReservados.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(QuartosReservados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Método para deletar um quarto reservado pelo número do quarto
    public void deletarQuartoReservado(String numeroQuarto) {
        Connection conn = Database.getConnection();
        try {
            // Query SQL para deletar um registro com base no número do quarto
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM quartosreservados WHERE quarto = ?"
            );
            stmt.setString(1, numeroQuarto);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Quarto reservado deletado com sucesso.");
            } else {
                System.out.println("Nenhum quarto reservado encontrado com o número especificado.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuartosReservados.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(QuartosReservados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
