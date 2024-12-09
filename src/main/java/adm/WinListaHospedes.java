package adm;

import Database.Database;
import Sexao.Sexsao;
import home.HotelHubInitial;
import home.HotelHubLogado;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class WinListaHospedes extends javax.swing.JFrame {

    // DefaultTableModel to define the table structure for displaying employee data
    private DefaultTableModel tabelaHospedes = new DefaultTableModel(new Object[]{"ID", "Nome", "E-mail", "CPF", "Idade"}, 0);

    // Constructor to initialize the components and list employees
    public WinListaHospedes() {
        initComponents();  // Initialize GUI components
        listaHospedes();  // Load the employee data into the table
        setTitle("Lista de Hóspedes");  // Set the window title
        setLocationRelativeTo(null);  // Center the window on the screen

        // Listener for changes in the table
        tabelaHospedes.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {  // Check if the table data was updated
                    int row = e.getFirstRow();  // Get the updated row index

                    // Get the updated values from the row
                    int id = Integer.parseInt(tabelaHospedes.getValueAt(row, 0).toString());
                    String nome = tabelaHospedes.getValueAt(row, 1).toString();
                    String cpf = tabelaHospedes.getValueAt(row, 3).toString();
                    String email = tabelaHospedes.getValueAt(row, 2).toString();
                    String idade = tabelaHospedes.getValueAt(row, 4).toString();

                    String idadeNumerica = idade.replaceAll("[^0-9]", "");
                    int idad = Integer.parseInt(idadeNumerica);

                    atualizarPelaTabelaH(id, nome, email, cpf, idad);
                    listaHospedes();
                }
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F5) {
                    listaHospedes();
                }
            }
        });

        this.setFocusable(true);
        this.requestFocusInWindow();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        hospedes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel4.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("file:/C:/Users/monari/Documents/NetBeansProjects/HotelHub-aaaa/images/loguilho-hotilho.png")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("LISTA DE HÓSPEDES");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 391, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );

        hospedes.setModel(tabelaHospedes);
        jScrollPane2.setViewportView(hospedes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private static void atualizarPelaTabelaH(int id, String nome, String email, String cpf, int idade) {
        try (Connection conn = Database.getConnection()) {
            // SQL query to update room reservation
            String query = "UPDATE hospedes SET nome = ?, email = ?, cpf = ?, idade = ? WHERE id_hospede = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            // Set the updated values in the prepared statement
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, cpf);
            stmt.setInt(4, idade);
            stmt.setInt(5, id);  // Set the reservation ID

            stmt.executeUpdate();  // Execute the update query
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar no banco de dados: " + ex.getMessage());  // Show error if something goes wrong
        }
    }

    // Method to retrieve employee data from the database and populate the table
    public void listaHospedes() {
        // Create database connection
        Connection conn = Database.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // SQL query to select employee data
            String sql = "SELECT id_hospede, nome, email, cpf, idade FROM hospedes";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Get the table model to update it
            DefaultTableModel model = (DefaultTableModel) hospedes.getModel();

            model.setRowCount(0);  // Clear existing rows in the table

            // Iterate through the result set and add data to the table
            while (rs.next()) {
                int id = rs.getInt("id_hospede");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                int idade = rs.getInt("idade");

                model.addRow(new Object[]{id, nome, email, cpf, idade});  // Add a new row with employee data
            }
        } catch (Exception e) {
            e.printStackTrace();  // Print any exception to the console
        } finally {
            // Close database resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();  // Print any exception to the console
            }
        }
    }

    // Method to handle the disconnect action
    public void desconect() {
        // Show a confirmation dialog asking if the user wants to disconnect
        int resposta = JOptionPane.showConfirmDialog(rootPane, "Você realmente deseja desconectar " + Sexsao.getNomePorCpf() + "?", "Desconectar", JOptionPane.YES_NO_OPTION);

        // If the user confirms, show a success message and open the login screen
        if (resposta == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(rootPane, "Desconectado com sucesso!");  // Success message
            JFrame j = new HotelHubInitial();  // Open the initial login screen
            j.setVisible(true);
            j.setLocationRelativeTo(null);  // Center the window on the screen
        } else {
            // If the user cancels, reopen the logged-in screen
            JFrame j = new HotelHubLogado();
            j.setVisible(true);
            j.setLocationRelativeTo(null);  // Center the window on the screen
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WinListaHospedes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WinListaHospedes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WinListaHospedes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WinListaHospedes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WinListaHospedes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable hospedes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
