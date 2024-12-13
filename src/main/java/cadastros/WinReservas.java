package cadastros;

import Classes.Hospedes;
import Classes.Quartos;
import Classes.QuartosReservados;
import Classes.ReservaPagamento;
import Classes.Reservas;
import Database.Database;
import Sexao.Sexsao;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class WinReservas extends javax.swing.JFrame {

    private DefaultTableModel tabelaReservas = new DefaultTableModel(new Object[]{"ID", "Hóspede", "Quarto", "Serviços", "Entrada", "Saída", "Total", "Pagamento"}, 0);

    public WinReservas() {
        initComponents();
        setTitle("Reservar Quarto");  // Set the window title
        listaQuartos();  // List available rooms
        listaReservas();  // List current reservations
        listaServicos();  // List available services
        setLocationRelativeTo(null);  // Center the window on the screen

        // Listener for changes in the table
        tabelaReservas.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {  // Check if the table data was updated
                    int row = e.getFirstRow();  // Get the updated row index

                    // Get the updated values from the row
                    int id = Integer.parseInt(tabelaReservas.getValueAt(row, 0).toString());  // ID (first column)
                    String hospede = tabelaReservas.getValueAt(row, 1).toString();  // Tipo (second column)
                    String quarto = tabelaReservas.getValueAt(row, 2).toString();  // Tipo (second column)
                    String servicos = tabelaReservas.getValueAt(row, 3).toString();  // Tipo (second column)
                    String entrada = tabelaReservas.getValueAt(row, 4).toString();  // Tipo (second column)
                    String saida = tabelaReservas.getValueAt(row, 5).toString();  // Tipo (second column)
                    double total = Double.parseDouble(tabelaReservas.getValueAt(row, 6).toString()); // Tipo (second column) by flarom parse String mathiack feat monari n bertua
                    String pagamento = tabelaReservas.getValueAt(row, 7).toString();  // Tipo (second column)

                    // Update the database with the modified row data
                    atualizarPelaTabelaR(id, hospede, servicos, entrada, saida, pagamento);
                    listaReservas();
                }
            }
        });

        // Add functionality to delete selected reservations with the Delete key
        JTreservas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {  // Verifica se a tecla Delete foi pressionada
                    int selectedRow = JTreservas.getSelectedRow();  // Obtém a linha selecionada
                    int resposta = JOptionPane.showConfirmDialog(rootPane, "Você realmente deseja excluir?", "Excluir", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {

                        if (selectedRow != -1) {  // Verifica se uma linha foi selecionada
                            // Obtém o ID da reserva selecionada
                            int id = Integer.parseInt(JTreservas.getValueAt(selectedRow, 0).toString());

                            excluirPelaTabelaR(id);  // Exclui a reserva pela ID

                            var q = new Quartos();
                            String quarto = tabelaReservas.getValueAt(selectedRow, 2).toString();  // Tipo (segunda coluna)
                            String[] quartoDados = quarto.split(" - ");
                            String numeroQuarto = quartoDados[1].replace("N°", "").trim();  // Número do quarto

                            // Obtém o ID do quarto usando o número do quarto
                            int quartoId = q.getIdByNumero(numeroQuarto);  // Método que você já tem na classe Quartos

                            // Atualiza a disponibilidade do quarto
                            q.atualizarDisponibilidade(numeroQuarto, "Disponível");

                            // Deleta o quarto reservado usando o ID do quarto
                            var qr = new QuartosReservados();
                            qr.deletarQuartoReservado(quartoId);  // Usando o ID do quarto, não o número

                            listaQuartos();  // Atualiza a lista de quartos
                            listaReservas();
                            // Remove a linha da tabela de reservas
                            DefaultTableModel model = (DefaultTableModel) JTreservas.getModel();
                            model.removeRow(selectedRow);

                            // Exibe uma mensagem de sucesso
                            JOptionPane.showMessageDialog(null, "Reserva excluída!");
                        } else {
                            // Se nenhuma linha for selecionada, exibe uma mensagem
                            JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir.");
                        }
                    }
                }
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F5) {
                    listaReservas();
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDataSaida = new javax.swing.JFormattedTextField();
        txtDataEntrada = new javax.swing.JFormattedTextField();
        comboQuartos = new javax.swing.JComboBox<>();
        comboServicos = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        edtNome = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        edtIdade = new javax.swing.JTextField();
        edtEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        edtCPF = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        comboPagamentos = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btReservar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTreservas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("RESERVAS");

        jLabel13.setIcon(new javax.swing.ImageIcon("C:\\Users\\monari\\Documents\\NetBeansProjects\\HotelHub-aaaa\\images\\loguilho-hotilho.png")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(47, 47, 47))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(153, 51, 255));

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Entrada:");

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Saída:");

        try {
            txtDataSaida.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtDataEntrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        comboQuartos.setForeground(new java.awt.Color(0, 0, 0));
        comboQuartos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboServicos.setForeground(new java.awt.Color(0, 0, 0));
        comboServicos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Quarto:");

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Serviços:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Nome:");

        edtNome.setForeground(new java.awt.Color(0, 0, 0));

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Idade:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Email:");

        edtIdade.setForeground(new java.awt.Color(0, 0, 0));

        edtEmail.setForeground(new java.awt.Color(0, 0, 0));

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("CPF:");

        edtCPF.setForeground(new java.awt.Color(0, 0, 0));
        try {
            edtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Método de Pagamento:");

        comboPagamentos.setForeground(new java.awt.Color(0, 0, 0));
        comboPagamentos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dinheiro", "Pix" }));

        jLabel1.setBackground(new java.awt.Color(181, 184, 182));
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Formato: dd/mm/aaaa");

        btReservar.setBackground(new java.awt.Color(247, 151, 29));
        btReservar.setForeground(new java.awt.Color(0, 0, 0));
        btReservar.setText("Pagar");
        btReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReservarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel6)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(edtNome)
                                .addComponent(comboQuartos, javax.swing.GroupLayout.Alignment.LEADING, 0, 226, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(edtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(edtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                .addComponent(edtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(comboServicos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboPagamentos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1)
                    .addComponent(btReservar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(edtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(edtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(edtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboQuartos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboServicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(comboPagamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(btReservar)
                .addContainerGap())
        );

        JTreservas.setModel(tabelaReservas);
        jScrollPane2.setViewportView(JTreservas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Method to update room reservation data in the database
    private static void atualizarPelaTabelaR(int id, String nomeHospede, String servicos, String entrada, String saida, String pagamento) {
        try (Connection conn = Database.getConnection()) {
            var r = new Reservas();
            int hospedeId = r.obterIdHospedePorNome(nomeHospede);

            if (hospedeId == -1) {
                JOptionPane.showMessageDialog(null, "Hóspede não encontrado!");
                return;
            }

            // SQL query to update room reservation
            String query = "UPDATE reservas SET hospede_id = ?, servico = ?, data_entrada = ?, data_saida = ?, metodo_pagamento = ? WHERE id_reserva = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            // Set the updated values in the prepared statement
            stmt.setInt(1, hospedeId);      // Set the hospede_id (integer value)
            stmt.setString(2, servicos);     // Set the services
            stmt.setString(3, entrada);     // Set the entry date
            stmt.setString(4, saida);       // Set the departure date
            stmt.setString(5, pagamento);   // Set the payment method
            stmt.setInt(6, id);             // Set the reservation ID

            // Execute the update query
            int rowsAffected = stmt.executeUpdate();

            // Check if the update was successful
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Reserva atualizada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma reserva foi atualizada.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não é possível alterar este campo!" + ex.getMessage());
        }
    }

    // Function to delete a reservation from the database
    private static void excluirPelaTabelaR(int id) {
        try (Connection conn = Database.getConnection()) {  // Get connection to the database
            String query = "DELETE FROM reservas WHERE id_reserva = ?";  // SQL query to delete reservation
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);  // Set the ID value in the query

            int rowsAffected = stmt.executeUpdate();  // Execute the query and check how many rows were affected

            if (rowsAffected > 0) {
            } else {
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao excluir do banco de dados: " + ex.getMessage());  // Show error message if something goes wrong
        }
    }


    private void btReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReservarActionPerformed
        String nome = edtNome.getText().trim();
        String idadeS = edtIdade.getText().trim();
        String cpf = edtCPF.getText().trim();
        String email = edtEmail.getText().trim();
        String quemCadastrou = Sexsao.getUsuarioLogado();

        String quarto = (String) comboQuartos.getSelectedItem();
        String servico = (String) comboServicos.getSelectedItem();
        String dataEntrada = txtDataEntrada.getText().trim();
        String dataSaida = txtDataSaida.getText().trim();
        String metodo_pagamento = (String) comboPagamentos.getSelectedItem();

// Verificar campos obrigatórios
        if (nome.isEmpty() || quarto == null || dataEntrada.isEmpty() || dataSaida.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Todos os campos obrigatórios devem ser preenchidos.");
            return;
        }

// Verificar idade
        int idad;
        try {
            idad = Integer.parseInt(idadeS);
            if (idad < 18 || idad > 120) {
                JOptionPane.showMessageDialog(rootPane, "A idade deve ser um número entre 18 e 120 anos.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "Idade inválida. Insira um número inteiro.");
            return;
        }

        try {
            // Formatando e validando as datas
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataEntradaLocal = LocalDate.parse(dataEntrada, formatter);
            LocalDate dataSaidaLocal = LocalDate.parse(dataSaida, formatter);

            if (dataSaidaLocal.isBefore(dataEntradaLocal)) {
                JOptionPane.showMessageDialog(rootPane, "A data de saída não pode ser anterior à data de entrada.");
                return;
            }

            // Extraindo dados do quarto e serviço
            String[] quartoDados = quarto.split(" - ");
            String valorQuarto = quartoDados[2].replace("R$", "").trim();

            String numeroQuarto = quartoDados[1].replace("N°", "").trim();

            String[] servicoDados = servico.split(" - ");
            String valorServico = servicoDados[1].replace("R$", "").trim();

            double valorQuartoD = Double.parseDouble(valorQuarto);
            double valorServicoD = Double.parseDouble(valorServico);

            // Calculando valores e verificando disponibilidade
            int diasReservados = (int) ChronoUnit.DAYS.between(dataEntradaLocal, dataSaidaLocal);
            double valorFinal = (diasReservados * valorQuartoD) + valorServicoD;

            if (reservaExist(numeroQuarto)) {
                JOptionPane.showMessageDialog(rootPane, "O quarto N°" + numeroQuarto + " já está reservado.");
                return;
            }

            // Criando o objeto de reserva
            var rp = new ReservaPagamento();
            rp.setNome(nome);
            rp.setIdade(idad);
            rp.setCpf(cpf);
            rp.setEmail(email);
            rp.setQuemCadastrou(quemCadastrou);
            rp.setQuarto(quarto);
            rp.setServico(servico);
            rp.setDataEntrada(dataEntrada);
            rp.setDataSaida(dataSaida);
            rp.setMetodoPagamento(metodo_pagamento);
            rp.setValorFinal(valorFinal);
            rp.setNumeroQuarto(numeroQuarto);

            this.dispose();
            JFrame j = new WinPagamentos(rp);
            j.setVisible(true);
            j.setLocationRelativeTo(null);

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(rootPane, "Formato de data inválido. Use o formato dd/MM/yyyy.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btReservarActionPerformed

    public void all() {
        var rp = new ReservaPagamento();

        // Inserindo hóspede
        var hospede = new Hospedes(rp.getNome(), rp.getEmail(), rp.getCpf(), rp.getIdade(), rp.getQuemCadastrou());
        int hospedeId = hospede.getHospedeId(rp.getNome(), rp.getCpf()); // Usando o novo método para pegar o ID

        // Se o hóspede não foi encontrado, insere o hóspede no banco
        if (hospedeId == -1) {
            hospede.inserirHospede(rp.getNome(), rp.getEmail(), rp.getCpf(), rp.getIdade(), rp.getQuemCadastrou());
            hospedeId = hospede.getHospedeId(rp.getNome(), rp.getCpf()); // Agora que o hóspede foi inserido, buscamos o ID
        }

        // Obtendo o ID do quarto
        var qt = new Quartos();
        int quartoId = qt.getIdByNumero(rp.getNumeroQuarto()); // Obter o ID do quarto a partir do número

        // Inserindo reserva
        var reserva = new Reservas(hospedeId, quartoId, rp.getServico(), rp.getDataEntrada(), rp.getDataSaida(), rp.getValorFinal(), rp.getMetodoPagamento());
        reserva.inserirReserva(hospedeId, quartoId, rp.getServico(), rp.getDataEntrada(), rp.getDataSaida(), rp.getValorFinal(), rp.getMetodoPagamento());

        // Atualizando disponibilidade do quarto
        qt.atualizarDisponibilidade(rp.getNumeroQuarto(), "Indisponível");

        // Inserindo quarto reservado
        var qr = new QuartosReservados();
        qr.setData_entrada(rp.getDataEntrada());
        qr.setData_saida(rp.getDataSaida());
        qr.setValor(rp.getValorFinal());

        // Inserir o quarto reservado usando os IDs
        qr.inserirQuartoReservado(hospedeId, quartoId, rp.getValorFinal(), rp.getDataEntrada(), rp.getDataSaida());

        // Atualizando a lista de reservas e quartos na interface
        listaReservas();
        listaQuartos();
        cleanTextQ();
    }

    // Function to clear the text fields after reservation
    public void cleanTextQ() {
        edtNome.setText("");  // Clear name field
        edtCPF.setText("");  // Clear CPF field
        edtIdade.setText("");  // Clear age field
        edtEmail.setText("");  // Clear email field

        txtDataEntrada.setText("");  // Clear entry date field
        txtDataSaida.setText("");  // Clear exit date field
    }

    // Function to check if the reservation exists for a given room
    private boolean reservaExist(String quarto) {
        boolean existe = false;
        try (Connection conn = Database.getConnection()) {
            // Query to check if the room is already reserved
            String query = "SELECT COUNT(*) FROM quartos WHERE disponivel = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, quarto);  // Set room availability to the parameter
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                existe = rs.getInt(1) > 0;  // If count is greater than 0, the room is reserved
            }
        } catch (SQLException ex) {
            Logger.getLogger(WinReservas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }

    public void listaReservas() {
        try (Connection conn = Database.getConnection()) {
            // Query para buscar as reservas e os detalhes do quarto com um JOIN
            String query = "SELECT r.id_reserva, r.hospede_id, q.numero, r.servico, r.data_entrada, r.data_saida, r.total, r.metodo_pagamento "
                    + "FROM reservas r "
                    + "JOIN quartos q ON r.quarto_id = q.id_quarto";  // Fazendo o JOIN entre reservas e quartos
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            // Clear the table before displaying new data
            tabelaReservas.setRowCount(0);

            while (rs.next()) {
                // Get data from the database result
                int id = rs.getInt("id_reserva");
                String hospede = rs.getString("hospede_id");
                String numeroQuarto = rs.getString("numero");  // O número do quarto é recuperado diretamente da tabela 'quartos'
                String servicos = rs.getString("servico");
                String dataEntrada = rs.getString("data_entrada");
                String dataSaida = rs.getString("data_saida");
                double total = rs.getDouble("total");
                String metodo_pagamento = rs.getString("metodo_pagamento");
                var r = new Reservas();

                // Add the data to the table model (which displays it in the JTable)
                tabelaReservas.addRow(new Object[]{
                    id,
                    r.getNomeHospedeById(Integer.parseInt(hospede)), // Presumo que o método getNomeHospedeById esteja correto
                    "N°" + numeroQuarto, // O número do quarto agora vem diretamente da consulta SQL
                    servicos,
                    dataEntrada,
                    dataSaida,
                    total,
                    metodo_pagamento
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(WinReservas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao carregar as reservas: " + ex.getMessage());
        }
    }

    // Function to list all available rooms and display them in a combo box
    public void listaQuartos() {
        try (Connection conn = Database.getConnection()) {
            String query = "SELECT * FROM quartos WHERE disponivel = 'Disponível'";  // SQL query to get available rooms
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();  // Combo box model to hold room data

            // Loop through result set and add rooms to the combo box
            while (rs.next()) {
                String tipo = rs.getString("tipo");
                String numeroQuarto = rs.getString("numero");
                String preco = rs.getString("preco");
                model.addElement(tipo + " - N°" + numeroQuarto + " - R$ " + preco);  // Add formatted room info
            }

            comboQuartos.setModel(model);  // Set the combo box model
        } catch (SQLException ex) {
            Logger.getLogger(WinReservas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao carregar os quartos: " + ex.getMessage());
        }
    }

    // Function to list all available services and display them in a combo box
    private void listaServicos() {
        try (Connection conn = Database.getConnection()) {
            String query = "SELECT * FROM servicos";  // SQL query to get all services
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();  // Combo box model to hold service data

            // Loop through result set and add services to the combo box
            while (rs.next()) {
                String nomeServico = rs.getString("tipo");
                String precoServico = rs.getString("preco");
                model.addElement(nomeServico + " - R$ " + precoServico);  // Add formatted service info
            }

            comboServicos.setModel(model);  // Set the combo box model
        } catch (SQLException ex) {
            Logger.getLogger(WinReservas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao carregar os serviços: " + ex.getMessage());
        }
    }

    // Function to check if a guest already exists in the database based on their details
    private boolean hospedeExist(String nome, String email, String cpf) {
        boolean existe = false;
        try (Connection conn = Database.getConnection()) {
            String query = "SELECT COUNT(*) FROM hospedes WHERE nome = ? OR email = ? OR cpf = ?";  // SQL query to check if guest exists
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                existe = rs.getInt(1) > 0;  // If count is greater than 0, the guest already exists
            }
        } catch (SQLException ex) {
            Logger.getLogger(WinReservas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }

    // Function to validate the email format
    public boolean isEmailValido(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.matches(regex, email);  // Check if the email matches the regex pattern
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WinReservas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTreservas;
    private javax.swing.JButton btReservar;
    private javax.swing.JComboBox<String> comboPagamentos;
    private javax.swing.JComboBox<String> comboQuartos;
    private javax.swing.JComboBox<String> comboServicos;
    private javax.swing.JFormattedTextField edtCPF;
    private javax.swing.JTextField edtEmail;
    private javax.swing.JTextField edtIdade;
    private javax.swing.JTextField edtNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JFormattedTextField txtDataEntrada;
    private javax.swing.JFormattedTextField txtDataSaida;
    // End of variables declaration//GEN-END:variables
}
