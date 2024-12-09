package cadastros;

import Classes.Quartos;
import Classes.Reservas;
import Database.Database;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class WinQuartosReservados extends javax.swing.JFrame {

    // DefaultTableModel to hold the list of reserved rooms
    private DefaultTableModel tabelaQuartosReservados = new DefaultTableModel(new Object[]{"ID", "Hóspede", "Quarto", "Valor", "Entrada", "Saída"}, 0);

    // Constructor: Initialize the window and load the reserved rooms
    public WinQuartosReservados() {
        initComponents();
        setTitle("Quartos Reservados");
        listaQuartosReservados();  // Populate the table with reserved rooms
        setLocationRelativeTo(null);  // Center the window

        // Listener for changes in the table
        tabelaQuartosReservados.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {  // Verifica se os dados da tabela foram atualizados
                    int row = e.getFirstRow();  // Obtém o índice da linha atualizada

                    // Obtém os valores atualizados da linha
                    int id = Integer.parseInt(tabelaQuartosReservados.getValueAt(row, 0).toString());  // ID (primeira coluna)
                    String hospede = tabelaQuartosReservados.getValueAt(row, 1).toString();  // Nome do hóspede (segunda coluna)
                    String quarto = tabelaQuartosReservados.getValueAt(row, 2).toString().replace("N°", "");  // Número do quarto (remove "N°")
                    String valor = tabelaQuartosReservados.getValueAt(row, 3).toString().replace("R$", "");  // Preço (remove "R$")
                    String entrada = tabelaQuartosReservados.getValueAt(row, 4).toString();  // Data de check-in
                    String saida = tabelaQuartosReservados.getValueAt(row, 5).toString();  // Data de check-out

                    atualizarPelaTabelaQR(id, entrada, saida);
                    listaQuartosReservados();
                }
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F5) {
                    listaQuartosReservados();
                }
            }
        });

        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        JTQuarRes = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        JTQuarRes.setModel(tabelaQuartosReservados);
        jScrollPane2.setViewportView(JTQuarRes);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("QUARTOS RESERVADOS");

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\monari\\Documents\\NetBeansProjects\\HotelHub-aaaa\\images\\loguilho-hotilho.png")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 302, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(32, 32, 32))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 263, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(71, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Method to update room reservation data in the database
    private static void atualizarPelaTabelaQR(int id, String dataEntrada, String dataSaida) {
        try (Connection conn = Database.getConnection()) {
            // SQL query to update room reservation
            String query = "UPDATE quartosreservados SET data_entrada = ?, data_saida = ? WHERE id_quartoReservado = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            // Set the updated values in the prepared statement
            stmt.setString(1, dataEntrada);
            stmt.setString(2, dataSaida);
            stmt.setInt(3, id);  // Set the reservation ID

            stmt.executeUpdate();  // Execute the update query
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar no banco de dados: " + ex.getMessage());  // Show error if something goes wrong
        }
    }

    // EXCLUSÕES NA TABELA E DELETES NO BANCO
    private static void excluirPelaTabelaQR(int id) {
        try (Connection conn = Database.getConnection()) {  // Obtém conexão com o banco
            String query = "DELETE FROM quartosreservados WHERE id_quartoReservado = ?";  // SQL para excluir com base no id_sabor
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);  // Define o valor do placeholder (id)

            int rowsAffected = stmt.executeUpdate();  // Executa a query e retorna o número de linhas afetadas

            if (rowsAffected > 0) {
            } else {
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao excluir do banco de dados: " + ex.getMessage());
        }
    }

    private static void excluirPelaTabelaF(String numeroQuarto) {
        try (Connection conn = Database.getConnection()) {  // Obtém conexão com o banco
            String query = "DELETE FROM reservas WHERE quarto LIKE ?";  // SQL para excluir com base no número do quarto
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%N°" + numeroQuarto + "%");  // Define o valor do placeholder (número do quarto)

            int rowsAffected = stmt.executeUpdate();  // Executa a query e retorna o número de linhas afetadas

            if (rowsAffected > 0) {
                System.out.println("Reserva excluída com sucesso.");
            } else {
                System.out.println("Nenhuma reserva encontrada para o quarto " + numeroQuarto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao excluir do banco de dados: " + ex.getMessage());
        }
    }

    // Method to populate the table with reserved rooms data from the database
    public void listaQuartosReservados() {
        Connection conn = Database.getConnection();
        try {
            // SQL query with JOIN to fetch room number from 'quartos' table
            String sql = "SELECT qr.id_quartoReservado, qr.hospede, q.numero AS numero_quarto, "
                    + "qr.valor, qr.data_entrada, qr.data_saida "
                    + "FROM quartosreservados qr "
                    + "JOIN quartos q ON qr.quarto = q.id_quarto"; // Assuming 'quarto' in 'quartosreservados' is the foreign key to 'id_quarto' in 'quartos'

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();  // Execute the query

            tabelaQuartosReservados.setRowCount(0);  // Clear the table before populating it

            // Iterate over the result set and add rows to the table
            while (rs.next()) {
                int id_QR = rs.getInt("id_quartoReservado");
                String hospede = rs.getString("hospede");
                int numeroQuarto = rs.getInt("numero_quarto"); // Get the room number
                double valor = rs.getDouble("valor");
                String dataEntrada = rs.getString("data_entrada");
                String dataSaida = rs.getString("data_saida");

                var r = new Reservas();
                int hpdint = Integer.parseInt(hospede);
                String nomeHospede = r.getNomeHospedeById(hpdint);

                // Add the row to the table
                tabelaQuartosReservados.addRow(new Object[]{id_QR, nomeHospede, "N°" + numeroQuarto, "R$" + valor, dataEntrada, dataSaida});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new WinQuartosReservados().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTQuarRes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
