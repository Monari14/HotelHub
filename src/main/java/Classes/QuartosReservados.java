// Importações necessárias
package Classes;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuartosReservados {

    private int hospedeId, quartoId;
    private String data_entrada, data_saida;
    private Double valor;

    public int getHospedeId() {
        return hospedeId;
    }

    public void setHospedeId(int hospedeId) {
        this.hospedeId = hospedeId;
    }

    public int getQuartoId() {
        return quartoId;
    }

    public void setQuartoId(int quartoId) {
        this.quartoId = quartoId;
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
    public void inserirQuartoReservado(int hospedeId, int quartoId, double valor, String data_entrada, String data_saida) {
        Connection conn = Database.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO quartosreservados (hospede, quarto, valor, data_entrada, data_saida) VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setInt(1, hospedeId);  // Alterado para passar o ID do hóspede
            stmt.setInt(2, quartoId);   // Alterado para passar o ID do quarto
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

    // Método para deletar um quarto reservado pelo ID do quarto
    public void deletarQuartoReservado(int quartoId) {
        Connection conn = Database.getConnection();
        try {
            // Query SQL para deletar um registro com base no ID do quarto
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM quartosreservados WHERE quarto = ?"
            );
            stmt.setInt(1, quartoId);  // Alterado para passar o ID do quarto

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Quarto reservado deletado com sucesso.");
            } else {
                System.out.println("Nenhum quarto reservado encontrado com o ID especificado.");
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
    public int getHospedeId(String nome, String cpf) {
    int hospedeId = -1;
    Connection conn = Database.getConnection();
    try {
        // Query para buscar o ID do hóspede baseado no nome e CPF
        PreparedStatement stmt = conn.prepareStatement(
                "SELECT id_hospede FROM hospedes WHERE nome = ? AND cpf = ?"
        );
        stmt.setString(1, nome);
        stmt.setString(2, cpf);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            hospedeId = rs.getInt("id_hospede");
        }

    } catch (SQLException ex) {
        Logger.getLogger(Hospedes.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Hospedes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return hospedeId;
}

}
