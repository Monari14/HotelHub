package cadastros;

import ClassesCadastros.CadastroHospedes;
import Database.Database;
import Sexao.Sexsao;
import home.HomeLogado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class CadastroHospede extends javax.swing.JFrame {

    public CadastroHospede() {
        initComponents();
        setTitle("Cadastro de Hóspedes!");
        formatarCampoCPF(edtCPF);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                JFrame j = new HomeLogado();
                j.setVisible(true);
                j.setLocationRelativeTo(null);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        edtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        edtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        edtCPF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        edtIdade = new javax.swing.JTextField();
        btCadastro = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        quarto = new javax.swing.JMenuItem();
        voltar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nome:");

        jLabel2.setText("Email:");

        jLabel3.setText("CPF:");

        jLabel4.setText("Idade:");

        btCadastro.setText("Cadastrar");
        btCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastroActionPerformed(evt);
            }
        });

        jMenu1.setText("Cadastros");

        quarto.setText("Cadastro de Quartos");
        quarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quartoActionPerformed(evt);
            }
        });
        jMenu1.add(quarto);

        voltar.setText("Voltar");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });
        jMenu1.add(voltar);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(120, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btCadastro)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(edtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(edtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(edtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btCadastro)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastroActionPerformed
        String nome = edtNome.getText();
        String idadeS = edtIdade.getText();
        String cpf = edtCPF.getText();
        String email = edtEmail.getText();
        String quemCadastrou = Sexsao.getUsuarioLogado();
        // Validação do email
        if (!isEmailValido(email)) {
            JOptionPane.showMessageDialog(this, "Email inválidilho!");
            return;
        } else {
            var c = new CadastroHospedes(nome, email, cpf, idadeS, quemCadastrou);
            if (!nome.isEmpty() && !email.isEmpty() && !cpf.isEmpty() && !idadeS.isEmpty()) {
                if (hospedeExist(nome, email, cpf)) {
                    JOptionPane.showMessageDialog(rootPane, "Hóspede " + nome + " já está Cadastrado!");
                    return;
                } else {
                    int idade = Integer.parseInt(idadeS);
                    c.inserirHospede(nome, email, cpf, idade, quemCadastrou);
                    JOptionPane.showMessageDialog(rootPane, "Hóspede " + nome + " foi Adicionado com Sucesso!");
                }
            }
        }
    }//GEN-LAST:event_btCadastroActionPerformed

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        this.dispose();
        JFrame j = new HomeLogado();
        j.setVisible(true);
        j.setLocationRelativeTo(null);
    }//GEN-LAST:event_voltarActionPerformed

    private void quartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quartoActionPerformed
        this.dispose();
        JFrame j = new CadastroQuarto();
        j.setVisible(true);
        j.setLocationRelativeTo(null);
    }//GEN-LAST:event_quartoActionPerformed
    private static boolean hospedeExist(String nome, String email, String cpf) {
        Connection conn = Database.getConnection();
        boolean existe = false;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM hospedes WHERE nome = ? OR email = ? OR cpf = ?");
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, cpf);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroHospedes.class.getName()).log(Level.SEVERE, null, ex);
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

    private boolean isEmailValido(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return Pattern.matches(emailRegex, email);
    }

    private void formatarCampoCPF(JTextField campo) {
        try {
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.setPlaceholderCharacter('_');

            // Criamos um JFormattedTextField temporário com a máscara
            JFormattedTextField formattedField = new JFormattedTextField(cpfMask);
            formattedField.setText(campo.getText());  // Copia o texto existente (se houver)

            // Substituímos o campo original pelo campo formatado
            formattedField.setColumns(campo.getColumns());
            formattedField.setBounds(campo.getBounds());
            formattedField.setFont(campo.getFont());

            // Remove o JTextField atual e adiciona o JFormattedTextField formatado no seu lugar
            getContentPane().remove(campo);
            getContentPane().add(formattedField);
            getContentPane().revalidate();
            getContentPane().repaint();

            // Atualiza a referência para o novo campo formatado
            this.edtCPF = formattedField;

        } catch (ParseException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao aplicar formatação ao CPF.");
        }
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroHospede.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroHospede.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroHospede.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroHospede.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroHospede().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCadastro;
    private javax.swing.JTextField edtCPF;
    private javax.swing.JTextField edtEmail;
    private javax.swing.JTextField edtIdade;
    private javax.swing.JTextField edtNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem quarto;
    private javax.swing.JMenuItem voltar;
    // End of variables declaration//GEN-END:variables
}
