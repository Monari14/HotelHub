package cadastros;

import Classes.Hospedes;
import Classes.Quartos;
import Classes.QuartosReservados;
import Classes.Reservas;
import DataBase.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class WinReservas extends javax.swing.JFrame {

    private DefaultTableModel tabelaReservas = new DefaultTableModel(new Object[]{"Hóspede", "Quarto", "Serviços", "Entrada", "Saída", "Total"}, 0);

    public WinReservas() {
        initComponents();
        setTitle("Reserva de Quartos!");
        listaHospedes();
        listaQuartos();
        listaReservas();
        listaServicos();
        setLocationRelativeTo(null);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btReservar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboQuartos = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        comboServicos = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDataEntrada = new javax.swing.JTextField();
        txtDataSaida = new javax.swing.JTextField();
        comboHospedes = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        reservas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Procurar hospede:");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Escolher Quarto:");

        btReservar.setBackground(new java.awt.Color(247, 151, 29));
        btReservar.setForeground(new java.awt.Color(0, 0, 0));
        btReservar.setText("Reservar");
        btReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReservarActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("RESERVAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(28, 28, 28))))
        );

        comboQuartos.setForeground(new java.awt.Color(0, 0, 0));
        comboQuartos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Serviços:");

        comboServicos.setForeground(new java.awt.Color(0, 0, 0));
        comboServicos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(153, 51, 255));

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Data de Entrada:");

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Data de Saída:");

        txtDataEntrada.setForeground(new java.awt.Color(0, 0, 0));
        txtDataEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataEntradaActionPerformed(evt);
            }
        });

        txtDataSaida.setForeground(new java.awt.Color(0, 0, 0));
        txtDataSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataSaidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDataSaida))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        comboHospedes.setForeground(new java.awt.Color(0, 0, 0));
        comboHospedes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        reservas.setModel(tabelaReservas);
        jScrollPane2.setViewportView(reservas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboQuartos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comboHospedes, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btReservar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(comboHospedes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(comboQuartos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(comboServicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btReservar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtDataEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataEntradaActionPerformed
        try {
            javax.swing.text.MaskFormatter dateMask = new javax.swing.text.MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('_');
            javax.swing.JFormattedTextField formattedField = new javax.swing.JFormattedTextField(dateMask);

            formattedField.setText(txtDataEntrada.getText());
            txtDataEntrada.setText(formattedField.getText());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtDataEntradaActionPerformed

    private void txtDataSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataSaidaActionPerformed
        try {
            javax.swing.text.MaskFormatter dateMask = new javax.swing.text.MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('_');
            javax.swing.JFormattedTextField formattedField = new javax.swing.JFormattedTextField(dateMask);

            formattedField.setText(txtDataSaida.getText());
            txtDataSaida.setText(formattedField.getText());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtDataSaidaActionPerformed

    private void btReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReservarActionPerformed
        String hospede = (String) comboHospedes.getSelectedItem(); // tem que ver se vai ser tipo busca ou combobox palavrachavepraachaessalinha: buceta
        String quarto = (String) comboQuartos.getSelectedItem();
        String servico = (String) comboServicos.getSelectedItem();
        String dataEntrada = txtDataEntrada.getText();
        String dataSaida = txtDataSaida.getText();

        // Separar os dados do combobox
        String[] hospedeDados = hospede.split(" - ");
        String nomeHospede = hospedeDados[1].trim();

        String[] quartoDados = quarto.split(" - ");
        String valorQuarto = quartoDados[1].replace("R$", "").trim();
        String numeroQuarto = quartoDados[2].replace("N°", "").trim();

        String[] servicoDados = servico.split(" - ");
        String valorServico = servicoDados[1].replace("R$", "").trim();

        double valorQuartoD = Double.parseDouble(valorQuarto);
        double valorServicoD = Double.parseDouble(valorServico);
        double total = valorQuartoD + valorServicoD;

        var r = new Reservas(hospede, quarto, servico, dataEntrada, dataSaida, total);

        if (!hospede.isEmpty() && !quarto.isEmpty() && !dataEntrada.isEmpty() && !dataSaida.isEmpty()) {
            if (reservaExist(quarto)) {
                JOptionPane.showMessageDialog(rootPane, "Por algum motivo o quarto N°" + quarto + " ta reservado já.");
                return;
            } else {
                r.inserirReserva(hospede, quarto, servico, dataEntrada, dataSaida, total);
                JOptionPane.showMessageDialog(rootPane, "Hóspede " + hospede + " reservou o quarto N°" + quarto + " com Sucesso!");
                listaReservas();
                var qr = new QuartosReservados(nomeHospede, numeroQuarto, dataEntrada, dataSaida, total);
                qr.inserirQuartoReservado(nomeHospede, numeroQuarto, total, dataEntrada, dataSaida);
                var q = new Quartos();
                q.atualizarDisponibilidade(numeroQuarto, "Indisponível");
            }
        }
    }//GEN-LAST:event_btReservarActionPerformed

    private static boolean reservaExist(String quarto) {
        Connection conn = Database.getConnection();
        boolean existe = false;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM quartos WHERE disponivel = ?");
            stmt.setString(1, quarto);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Hospedes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return existe;
    }

    public void listaReservas() {
        Connection conn = Database.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            String sql = "SELECT hospede, quarto, servico, data_entrada, data_saida, total FROM reservas";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) reservas.getModel();

            model.setRowCount(0);

            while (rs.next()) {
                String hospede = rs.getString("hospede");
                String quarto = rs.getString("quarto");
                String servico = rs.getString("servico");
                String data_entrada = rs.getString("data_entrada");
                String data_saida = rs.getString("data_saida");
                String total = rs.getString("total");

                model.addRow(new Object[]{hospede, quarto, servico, data_entrada, data_saida, "R$" + total});
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
                e.printStackTrace();
            }
        }
    }

    public void listaHospedes() {
        Connection conn = Database.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT id_hospede, nome FROM hospedes";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboHospedes.getModel();

            model.removeAllElements();

            while (rs.next()) {
                int id_hospede = rs.getInt("id_hospede");
                String nome = rs.getString("nome");
                model.addElement(id_hospede + " - " + nome);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
                e.printStackTrace();
            }
        }
    }

    public void listaQuartos() {
        Connection conn = Database.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT id_quarto, tipo, numero, preco, disponivel FROM quartos";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboQuartos.getModel();

            model.removeAllElements();

            while (rs.next()) {
                int id = rs.getInt("id_quarto");
                String tipo = rs.getString("tipo");
                String numero = rs.getString("numero");
                String preco = rs.getString("preco");
                String disponivel = rs.getString("disponivel");
                if (!disponivel.equals("Indisponível")) {
                    model.addElement(tipo + " - R$" + preco + " - N°" + numero);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
                e.printStackTrace();
            }
        }
    }

    public void listaServicos() {
        Connection conn = Database.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT id_servico, tipo, preco FROM servicos";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboServicos.getModel();

            model.removeAllElements();

            while (rs.next()) {
                int id = rs.getInt("id_servico");
                String tipo = rs.getString("tipo");
                String preco = rs.getString("preco");
                model.addElement(tipo + " - R$" + preco);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WinReservas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btReservar;
    private javax.swing.JComboBox<String> comboHospedes;
    private javax.swing.JComboBox<String> comboQuartos;
    private javax.swing.JComboBox<String> comboServicos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable reservas;
    private javax.swing.JTextField txtDataEntrada;
    private javax.swing.JTextField txtDataSaida;
    // End of variables declaration//GEN-END:variables
}
