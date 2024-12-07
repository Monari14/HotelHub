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
                    atualizarPelaTabelaR(id, hospede, quarto, servicos, entrada, saida, total, pagamento);
                }
            }
        });

        // Add functionality to delete selected reservations with the Delete key
        JTreservas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {  // Check if the Delete key was pressed
                    int selectedRow = JTreservas.getSelectedRow();  // Get selected row
                    int resposta = JOptionPane.showConfirmDialog(rootPane, "Você realmente deseja excluir?", "Excluir", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {

                        if (selectedRow != -1) {  // Check if a row is selected
                            // Get the ID of the selected reservation
                            int id = Integer.parseInt(JTreservas.getValueAt(selectedRow, 0).toString());

                            excluirPelaTabelaR(id);
                            var q = new Quartos();
                            String quarto = tabelaReservas.getValueAt(selectedRow, 2).toString();  // Tipo (second column)
                            String[] quartoDados = quarto.split(" - ");
                            String numeroQuarto = quartoDados[1].replace("N°", "").trim();
                            q.atualizarDisponibilidade(numeroQuarto, "Disponível");

                            var qr = new QuartosReservados();
                            qr.deletarQuartoReservado(numeroQuarto);
                            listaQuartos();
                            // Remove the selected row from the table
                            DefaultTableModel model = (DefaultTableModel) JTreservas.getModel();
                            model.removeRow(selectedRow);

                            // Show a message indicating successful deletion
                            JOptionPane.showMessageDialog(null, "Reserva excluída!");
                        } else {
                            // If no row is selected, show a message
                            JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir.");
                        }
                    }
                }
            }
        }
        );
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
        btReservar.setText("Reservar");
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
                                .addComponent(comboQuartos, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(edtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(edtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private static void atualizarPelaTabelaR(int id, String hospede, String quarto, String servicos, String entrada, String saida, double total, String pagamento) {
        try (Connection conn = Database.getConnection()) {
            // SQL query to update room reservation
            String query = "UPDATE reservas SET hospede = ?, quarto = ?, servico = ?, data_entrada = ?, data_saida = ?, total = ?, metodo_pagamento = ? WHERE id_reserva = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            // Set the updated values in the prepared statement
            stmt.setString(1, hospede);      // Set the tipo (room type)
            stmt.setString(2, quarto);    // Set the room number
            stmt.setString(3, servicos);    // Set the room number
            stmt.setString(4, entrada);    // Set the room number
            stmt.setString(5, saida);    // Set the room number
            stmt.setDouble(6, total);     // Set the room price
            stmt.setString(7, pagamento);    // Set the room number
            stmt.setInt(8, id);           // Set the room ID

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
        if (nome.isEmpty() || quarto == null || dataEntrada.isEmpty() || dataSaida.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Todos os campos obrigatórios devem ser preenchidos.");
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

            if (!isEmailValido(email)) {
                JOptionPane.showMessageDialog(this, "Email inválido!");
                return;
            }

            if (hospedeExist(nome, email, cpf)) {
                JOptionPane.showMessageDialog(rootPane, "Hóspede " + nome + " já está cadastrado.");
                return;
            }
            int idade = Integer.parseInt(idadeS);

            var rp = new ReservaPagamento();
            rp.setNome(nome);
            rp.setIdade(idade);
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

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao processar valores numéricos: " + e.getMessage());
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
        hospede.inserirHospede(rp.getNome(), rp.getEmail(), rp.getCpf(), rp.getIdade(), rp.getQuemCadastrou());

        // Inserindo reserva
        var reserva = new Reservas(rp.getNome(), rp.getQuarto(), rp.getServico(), rp.getDataEntrada(), rp.getDataSaida(), rp.getValorFinal(), rp.getMetodoPagamento());
        reserva.inserirReserva(rp.getNome(), rp.getQuarto(), rp.getServico(), rp.getDataEntrada(), rp.getDataSaida(), rp.getValorFinal(), rp.getMetodoPagamento());

        // Atualizando disponibilidade do quarto
        var qt = new Quartos();
        qt.atualizarDisponibilidade(rp.getNumeroQuarto(), "Indisponível");

        // Inserindo quarto reservado
        var qr = new QuartosReservados(); //nome, numeroQuarto, dataEntrada, dataSaida, valorFinal
        qr.setHospede(rp.getNome());
        qr.setQuarto(rp.getNumeroQuarto());
        qr.setData_entrada(rp.getDataEntrada());
        qr.setData_saida(rp.getDataSaida());
        qr.setValor(rp.getValorFinal());
        qr.inserirQuartoReservado(rp.getNome(), rp.getNumeroQuarto(), rp.getValorFinal(), rp.getDataEntrada(), rp.getDataSaida());
        
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

    // Function to list all current reservations and display them in a table
    public void listaReservas() {
        try (Connection conn = Database.getConnection()) {
            String query = "SELECT * FROM reservas";  // SQL query to fetch all reservations
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            // Clear the table before displaying new data
            tabelaReservas.setRowCount(0);

            while (rs.next()) {
                // Get data from the database result
                int id = rs.getInt("id_reserva");
                String hospede = rs.getString("hospede");
                String quarto = rs.getString("quarto");
                String servicos = rs.getString("servico");
                String dataEntrada = rs.getString("data_entrada");
                String dataSaida = rs.getString("data_saida");
                double total = rs.getDouble("total");
                String metodo_pagamento = rs.getString("metodo_pagamento");

                // Add the data to the table model (which displays it in the JTable)
                tabelaReservas.addRow(new Object[]{id, hospede, quarto, servicos, dataEntrada, dataSaida, total, metodo_pagamento});
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
