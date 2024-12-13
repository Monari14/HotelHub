package cadastros;

import Classes.Quartos;
import Classes.Servicos;
import Database.Database;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class WinQuartosServicos extends javax.swing.JFrame {

    // DefaultTableModels to hold room and service data for display
    private DefaultTableModel tabelaQuartos = new DefaultTableModel(new Object[]{"ID", "Tipo", "Número", "Preço", "Disponibilidade"}, 0);
    private DefaultTableModel tabelaServicos = new DefaultTableModel(new Object[]{"ID", "Tipo", "Preço"}, 0);

    public WinQuartosServicos() {
        initComponents();
        listaQuartos();  // List all rooms
        listaServicos();  // List all services
        setTitle("Quartos e Serviços");  // Set the title for the window
        setLocationRelativeTo(null);  // Center the window on the screen
        // Seleção de linhas nas tabelas
        JTquartos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                // Desmarcar outras tabelas
                JTservicos.clearSelection();
            }
        });
        JTservicos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                // Desmarcar outras tabelas
                JTquartos.clearSelection();
            }
        });

        // Listener for changes in the table
        tabelaQuartos.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {  // Check if the table data was updated
                    int row = e.getFirstRow();  // Get the updated row index

                    // Get the updated values from the row
                    int id = Integer.parseInt(tabelaQuartos.getValueAt(row, 0).toString());  // ID (first column)
                    String tipo = tabelaQuartos.getValueAt(row, 1).toString();  // Tipo (second column)
                    String numero = tabelaQuartos.getValueAt(row, 2).toString().replace("N°", "");  // Room number (remove "N°")
                    String precoStr = tabelaQuartos.getValueAt(row, 3).toString().replace("R$", "").trim();  // Price (remove "R$")

                    double preco = 0.0;
                    try {
                        preco = Double.parseDouble(precoStr);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Preço inválido: " + precoStr);
                        return;
                    }

                    // Update the database with the modified row data
                    atualizarPelaTabelaQ(id, tipo, preco);
                    JOptionPane.showMessageDialog(null, "Quarto atualizado com sucesso!");
                    listaQuartos();
                }
            }
        });
        JTquartos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    int selectedRow = JTquartos.getSelectedRow(); // Obter a linha selecionada
                    if (selectedRow != -1) {
                        int resposta = JOptionPane.showConfirmDialog(rootPane, "Você realmente deseja excluir?", "Excluir", JOptionPane.YES_NO_OPTION);
                        if (resposta == JOptionPane.YES_OPTION) {
                            int id = Integer.parseInt(JTquartos.getValueAt(selectedRow, 0).toString()); // ID na primeira coluna
                            String disponibilidade = JTquartos.getValueAt(selectedRow, 4).toString(); // Disponibilidade na coluna 5

                            // if indisponível dont exclui
                            if (disponibilidade.equalsIgnoreCase("Indisponível")) {
                                JOptionPane.showMessageDialog(null, "Este quarto está reservado!");
                                return;
                            }

                            excluirPelaTabelaQ(id);

                            DefaultTableModel model = (DefaultTableModel) JTquartos.getModel();
                            model.removeRow(selectedRow);

                            listaQuartos();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir.");
                    }
                }
            }
        });

        // Listener for changes in the table
        tabelaServicos.addTableModelListener(
                new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e
            ) {
                if (e.getType() == TableModelEvent.UPDATE) {  // Check if the table data was updated
                    int row = e.getFirstRow();  // Get the updated row index

                    // Get the updated values from the row
                    int id = Integer.parseInt(tabelaServicos.getValueAt(row, 0).toString());  // ID (first column)
                    String tipo = tabelaServicos.getValueAt(row, 1).toString();  // Tipo (second column)
                    String precoStr = tabelaServicos.getValueAt(row, 2).toString().replace("R$", "").trim();  // Price (remove "R$")

                    double preco = 0.0;
                    try {
                        preco = Double.parseDouble(precoStr);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Preço inválido: " + precoStr);
                        return;
                    }

                    // Update the database with the modified row data
                    atualizarPelaTabelaS(id, tipo, preco);
                    JOptionPane.showMessageDialog(null, "Serviço atualizado com sucesso!");
                    listaServicos();
                }
            }
        }
        );

        JTservicos.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e
            ) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    int selectedRow = JTservicos.getSelectedRow(); // Get the selected row
                    int resposta = JOptionPane.showConfirmDialog(rootPane, "Você realmente deseja excluir?", "Excluir", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        if (selectedRow != -1) {
                            // Get the ID of the selected row (assuming the ID is in the first column)
                            int id = Integer.parseInt(JTservicos.getValueAt(selectedRow, 0).toString()); // ID in the first column

                            // Delete the item from the database
                            excluirPelaTabelaS(id);

                            // Remove the row from the table
                            DefaultTableModel model = (DefaultTableModel) JTservicos.getModel();
                            model.removeRow(selectedRow);

                            // Show a success message or update the interface
                            listaServicos();
                        } else {
                            JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir.");
                        }
                    }
                }
            }
        }
        );
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F5) {
                    listaQuartos();
                    listaServicos();
                }
            }
        });

        this.setFocusable(true);
        this.requestFocusInWindow();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTservicos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTquartos = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        edtTipoS = new javax.swing.JTextField();
        btnAdicionarQuartos = new javax.swing.JButton();
        edtValorS = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        edtTipo = new javax.swing.JTextField();
        btnAdicionarServicos = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        edtNumero = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        edtPreco = new javax.swing.JTextField();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(700, 400));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ADICIONAR QUARTO E SERVIÇOS");

        jLabel6.setIcon(new javax.swing.JLabel() {
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(6, 6, 6))
        );

        JTservicos.setModel(tabelaServicos);
        jScrollPane3.setViewportView(JTservicos);

        JTquartos.setModel(tabelaQuartos);
        jScrollPane2.setViewportView(JTquartos);

        jLabel7.setText("Tipo:");

        btnAdicionarQuartos.setBackground(new java.awt.Color(255, 153, 0));
        btnAdicionarQuartos.setForeground(new java.awt.Color(0, 0, 0));
        btnAdicionarQuartos.setText("Adicionar Quartos");
        btnAdicionarQuartos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarQuartosActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo:");

        jLabel8.setText("Valor:");

        btnAdicionarServicos.setBackground(new java.awt.Color(255, 153, 0));
        btnAdicionarServicos.setForeground(new java.awt.Color(0, 0, 0));
        btnAdicionarServicos.setText("Adicionar Serviços");
        btnAdicionarServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarServicosActionPerformed(evt);
            }
        });

        jLabel3.setText("Numero:");

        jLabel5.setText("Valor:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edtTipoS, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(edtValorS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(edtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel3)
                                .addGap(26, 26, 26)
                                .addComponent(edtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(26, 26, 26)
                                .addComponent(edtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnAdicionarQuartos, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdicionarServicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(edtValorS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(edtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel7)
                        .addComponent(edtTipoS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(edtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(edtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionarQuartos)
                    .addComponent(btnAdicionarServicos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Method to update room reservation data in the database
    private static void atualizarPelaTabelaQ(int id, String tipo, double preco) {
        try (Connection conn = Database.getConnection()) {
            // SQL query to update room reservation
            String query = "UPDATE quartos SET tipo = ?, preco = ? WHERE id_quarto = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            // Set the updated values in the prepared statement
            stmt.setString(1, tipo);      // Set the tipo (room type)
            stmt.setDouble(2, preco);     // Set the room price
            stmt.setInt(3, id);           // Set the room ID

            int rowsAffected = stmt.executeUpdate();  // Execute the update query
            if (rowsAffected > 0) {
            } else {
            } //se true atualizou

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar no banco de dados: " + ex.getMessage());
        }
    }

    private static void excluirPelaTabelaQ(int id) {
        try (Connection conn = Database.getConnection()) {
            // SQL query to delete data based on the room ID
            String query = "DELETE FROM quartos WHERE id_quarto = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);  // Set the room ID

            int rowsAffected = stmt.executeUpdate();  // Execute the delete query
            if (rowsAffected > 0) {
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum dado foi encontrado com o ID fornecido.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao excluir do banco de dados: " + ex.getMessage());
        }
    }

    // Method to update room reservation data in the database
    private static void atualizarPelaTabelaS(int id, String tipo, double preco) {
        try (Connection conn = Database.getConnection()) {
            // SQL query to update service data
            String query = "UPDATE servicos SET tipo = ?, preco = ? WHERE id_servico = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            // Set the updated values in the prepared statement
            stmt.setString(1, tipo);      // Set the service type
            stmt.setDouble(2, preco);     // Set the service price
            stmt.setInt(3, id);           // Set the service ID

            int rowsAffected = stmt.executeUpdate();  // Execute the update query
            if (rowsAffected > 0) {
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum dado foi encontrado com o ID fornecido.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar no banco de dados: " + ex.getMessage());
        }
    }

    // Method to delete data from the database based on selected ID
    private static void excluirPelaTabelaS(int id) {
        try (Connection conn = Database.getConnection()) {
            // SQL query to retrieve the service details based on the ID
            String selectQuery = "SELECT tipo, preco FROM servicos WHERE id_servico = ?";
            PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
            selectStmt.setInt(1, id);

            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                String tipo = rs.getString("tipo"); // Assuming 'tipo' column exists
                double preco = rs.getDouble("preco"); // Assuming 'preco' column exists

                // Check if the row is "Nenhum" and price is "0"
                if ("Nenhum".equals(tipo) && preco == 0.0) {
                    JOptionPane.showMessageDialog(null, "Nao e possível excluir este item.");
                    return; // Exit the method without deleting the row
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum dado encontrado com o ID fornecido.");
                return; // Exit the method if no data is found
            }

            // SQL query to delete the data based on the service ID
            String deleteQuery = "DELETE FROM servicos WHERE id_servico = ?";
            PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
            deleteStmt.setInt(1, id);

            int rowsAffected = deleteStmt.executeUpdate(); // Execute the delete query
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Serviço excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir o serviço.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao excluir do banco de dados: " + ex.getMessage());
        }
    }


    private void btnAdicionarQuartosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarQuartosActionPerformed
        String tipo = edtTipo.getText();  // Get room type
        String numero = edtNumero.getText();  // Get room number
        String preco = edtPreco.getText();  // Get room price
        String disponivel = "Disponível";  // Room availability is initially "Available"

        // Create a new Quartos object and set its properties
        var c = new Quartos();
        c.setTipo(tipo);
        c.setNumero(numero);
        c.setPreco(preco);
        c.setDisponivel(disponivel);

        // Validate inputs
        if (!tipo.isEmpty() && !numero.isEmpty() && !preco.isEmpty() && !disponivel.isEmpty()) {
            if (quartoExist(numero)) {
                JOptionPane.showMessageDialog(rootPane, "O quarto N°" + numero + " já está Cadastrado!");  // Notify if room exists
                return;
            } else {
                // Convert price to double and insert the room into the database
                double precoD = Double.parseDouble(preco);
                c.inserirQuarto(tipo, numero, precoD, disponivel);
                JOptionPane.showMessageDialog(rootPane, "Quarto N°" + numero + " foi Adicionado com Sucesso!");  // Success message
                listaQuartos();  // Refresh the room list
                cleanTextQ();  // Clean input fields
            }
        }

    }//GEN-LAST:event_btnAdicionarQuartosActionPerformed

    private void btnAdicionarServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarServicosActionPerformed
        String tipo = edtTipoS.getText();  // Get service type
        String precoS = edtValorS.getText();  // Get service price

        var s = new Servicos(tipo, precoS);

        // Validate inputs
        if (!tipo.isEmpty() && !precoS.isEmpty()) {
            if (servicoExist(tipo)) {
                JOptionPane.showMessageDialog(rootPane, "O serviço: " + tipo + " já está Cadastrado!");  // Notify if service exists
                return;
            } else {
                // Convert price to double and insert the service into the database
                double precoD = Double.parseDouble(precoS);
                s.inserirServicos(tipo, precoD);
                JOptionPane.showMessageDialog(rootPane, "Serviço: " + tipo + " foi Adicionado com Sucesso!");  // Success message
                listaServicos();  // Refresh the service list
                cleanTextS();  // Clean input fields
            }
        }

    }//GEN-LAST:event_btnAdicionarServicosActionPerformed

    // Method to clean room input fields
    private void cleanTextQ() {
        edtTipo.setText("");
        edtPreco.setText("");
        edtNumero.setText("");
    }

    // Method to clean service input fields
    private void cleanTextS() {
        edtTipoS.setText("");
        edtValorS.setText("");
    }

    // Method to list all rooms from the database
    public void listaQuartos() {
        Connection conn = Database.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // SQL query to get all rooms
            String sql = "SELECT id_quarto, tipo, numero, preco, disponivel FROM quartos";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Clear the table before adding new rows
            DefaultTableModel model = (DefaultTableModel) JTquartos.getModel();
            model.setRowCount(0);

            // Iterate through the result set and add each room to the table
            while (rs.next()) {
                int id = rs.getInt("id_quarto");
                String tipo = rs.getString("tipo");
                String numero = rs.getString("numero");
                double preco = rs.getDouble("preco");
                String disponivel = rs.getString("disponivel");

                model.addRow(new Object[]{id, tipo, numero, "R$" + preco, disponivel});
            }

            // Custom TableCellRenderer for changing row colors based on availability
            JTquartos.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    // Get the 'disponivel' value for the current row
                    String disponivel = (String) table.getValueAt(row, 4);

                    // Set background color based on the availability
                    if ("Disponível".equals(disponivel)) {
                        comp.setBackground(new Color(204, 255, 204)); // Light green
                    } else if ("Indisponível".equals(disponivel)) {
                        comp.setBackground(new Color(255, 204, 204)); // Light red
                    } else {
                        comp.setBackground(Color.WHITE); // Default color
                    }

                    return comp;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
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

    // Method to check if a room already exists in the database
    private static boolean quartoExist(String numero) {
        Connection conn = Database.getConnection();
        boolean existe = false;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM quartos WHERE numero = ?");
            stmt.setString(1, numero);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                existe = rs.getInt(1) > 0;  // Check if the room exists
            }
        } catch (SQLException ex) {
            Logger.getLogger(Quartos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return existe;  // Return true if the room exists, false otherwise
    }

    // Method to list all services from the database
    public void listaServicos() {
        Connection conn = Database.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // SQL query to get all services
            String sql = "SELECT id_servico, tipo, preco FROM servicos";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Clear the table before adding new rows
            DefaultTableModel model = (DefaultTableModel) JTservicos.getModel();
            model.setRowCount(0);

            // Iterate through the result set and add each service to the table
            while (rs.next()) {
                int id = rs.getInt("id_servico");
                String tipo = rs.getString("tipo");
                double preco = rs.getDouble("preco");

                model.addRow(new Object[]{id, tipo, "R$" + preco});
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
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

    // Method to check if a service already exists in the database
    private static boolean servicoExist(String tipo) {
        Connection conn = Database.getConnection();
        boolean existe = false;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM servicos WHERE tipo = ?");
            stmt.setString(1, tipo);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                existe = rs.getInt(1) > 0;  // Check if the service exists
            }
        } catch (SQLException ex) {
            Logger.getLogger(Quartos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return existe;  // Return true if the service exists, false otherwise
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WinQuartosServicos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTquartos;
    private javax.swing.JTable JTservicos;
    private javax.swing.JButton btnAdicionarQuartos;
    private javax.swing.JButton btnAdicionarServicos;
    private javax.swing.JTextField edtNumero;
    private javax.swing.JTextField edtPreco;
    private javax.swing.JTextField edtTipo;
    private javax.swing.JTextField edtTipoS;
    private javax.swing.JTextField edtValorS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

}
