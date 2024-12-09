package cadastros;

import Classes.Usuarios;
import Database.Database;
import Sexao.Sexsao;
import adm.WinAdmLogado;
import home.HotelHubInitial;
import home.HotelHubLogado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import logins.LoginUser;

public class CadastroUser extends javax.swing.JFrame {

    private boolean deuOk = false;

    // Constructor to initialize the window and set closing behavior
    private HotelHubInitial hotelHubInitial; // Referência para HotelHubInitial

    public CadastroUser(HotelHubInitial hotelHubInitial) {
        this.hotelHubInitial = hotelHubInitial;
        initComponents(); // Inicializa os componentes da janela
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        edtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        edtIdade = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btCadastro = new javax.swing.JButton();
        edtSenha = new javax.swing.JPasswordField();
        edtCPF = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        user = new javax.swing.JMenuItem();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nome:");

        edtNome.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Idade:");

        edtIdade.setForeground(new java.awt.Color(0, 0, 0));

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("CPF:");

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Senha");

        btCadastro.setBackground(new java.awt.Color(255, 153, 0));
        btCadastro.setForeground(new java.awt.Color(0, 0, 0));
        btCadastro.setText("Cadastrar");
        btCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastroActionPerformed(evt);
            }
        });

        edtSenha.setForeground(new java.awt.Color(0, 0, 0));

        edtCPF.setForeground(new java.awt.Color(0, 0, 0));
        try {
            edtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\WESLEYLUCASMOREIRA\\Documents\\mini hotel.jpg")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6))
        );

        jMenu1.setText("Usuário");

        user.setText("Login de Usuários");
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        jMenu1.add(user);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(edtNome))
                        .addComponent(btCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(edtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(edtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(edtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(edtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(edtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(btCadastro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastroActionPerformed
        cadastrar();
    }//GEN-LAST:event_btCadastroActionPerformed

    private void cadastrar() {
        String nome = edtNome.getText().trim();  // Get the name input from the user
        String idadeS = edtIdade.getText().trim();  // Get the age input from the user
        String cpf = edtCPF.getText().trim();  // Get the CPF input from the user
        String senha = new String(edtSenha.getPassword()).trim();  // Get the password input from the user

        // Validate the input fields
        if (!validateInputs(nome, idadeS, cpf, senha)) {
            return;  // Exit if validation fails
        }

        // Check if the CPF is already registered
        if (usuarioExist(cpf)) {
            JOptionPane.showMessageDialog(this, "O CPF " + cpf + " já está cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            JFrame a = new HotelHubInitial(); // Open the user registration window.
            a.setVisible(true);
            a.setLocationRelativeTo(null);
            JFrame j = new CadastroUser(null); // Open the user registration window.
            j.setVisible(true);
            j.setLocationRelativeTo(null);
            return;
        }

        // Register the user
        try {
            int idade = Integer.parseInt(idadeS);  // Convert age input to integer
            Usuarios u = new Usuarios(nome, idade, cpf, senha);

            // Insert user into the database
            if (u.inserirUser(nome, idade, cpf, senha)) {
                JOptionPane.showMessageDialog(this, "Funcionário " + nome + " cadastrado com sucesso.");
                // Authenticate the user using CPF and password
                Object[] authResult = autenticarUsuario(cpf, senha);
                boolean autenticado = (boolean) authResult[0];  // True if authenticated
                boolean isAdm = (boolean) authResult[1];  // True if the user is an administrator

                // If authenticated, proceed to the correct screen
                if (autenticado) {
                    Sexsao.setUsuarioLogado(cpf);  // Set the logged-in user
                    this.dispose();  // Close the login window

                    JFrame j;
                    // Redirect based on user role (Admin or regular user)
                    if (isAdm) {
                        j = new WinAdmLogado();  // Admin screen
                    } else {
                        j = new HotelHubLogado();  // Regular user screen
                    }
                    j.setVisible(true);
                    j.setLocationRelativeTo(null);  // Center the window
                    deuOk = true;
                    if (hotelHubInitial != null) {
                        hotelHubInitial.dispose();
                        dispose();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar o funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao converter idade.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Object[] autenticarUsuario(String usuario, String senha) {
        Connection conn = Database.getConnection();  // Get database connection
        Object[] resultado = {false, false};  // First value: authentication status, second: is_adm status

        try {
            // SQL query to check if the user exists and if the password matches
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT is_adm FROM usuarios WHERE (nome = ? OR cpf = ?) AND senha = ?");
            stmt.setString(1, usuario);  // Set username
            stmt.setString(2, usuario);  // Set CPF
            stmt.setString(3, senha);    // Set password

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                resultado[0] = true;  // User authenticated
                resultado[1] = rs.getBoolean("is_adm");  // Set user role (admin or not)
            }
        } catch (SQLException ex) {
            ex.printStackTrace();  // Print exception if any error occurs
        } finally {
            try {
                if (conn != null) {
                    conn.close();  // Close the database connection
                }
            } catch (SQLException e) {
                e.printStackTrace();  // Print exception if closing fails
            }
        }

        return resultado;  // Return the authentication result
    }

    private boolean validateInputs(String nome, String idadeS, String cpf, String senha) {
        // Verifica campos vazios
        if (nome == null || nome.isBlank() || idadeS == null || idadeS.isBlank()
                || cpf == null || cpf.isBlank() || senha == null || senha.isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Valida nome (mínimo 3 caracteres, só letras e espaços)
        if (nome.length() < 3 || !nome.matches("[a-zA-ZÀ-ÿ\\s]+")) {
            JOptionPane.showMessageDialog(this, "Insira um nome válido (mínimo 3 caracteres, apenas letras).", "Atenção", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Valida idade (número positivo, maior ou igual a 18)
        if (!idadeS.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "A idade deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        int idade = Integer.parseInt(idadeS);
        if (idade < 18) {
            JOptionPane.showMessageDialog(this, "A idade deve ser 18 anos ou mais.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Remove pontos e hífen do CPF para validação
        String cpfNumerico = cpf.replace(".", "").replace("-", "");

        // Valida CPF (11 dígitos e formato válido)
        if (!cpfNumerico.matches("\\d{11}")) {
            JOptionPane.showMessageDialog(this, "O CPF deve conter exatamente 11 dígitos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!isValidCPF(cpfNumerico)) {
            JOptionPane.showMessageDialog(this, "O CPF inserido é inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Valida senha (mínimo 6 caracteres, sem espaços)
        if (senha.length() < 6) {
            JOptionPane.showMessageDialog(this, "A senha deve conter no mínimo 6 caracteres.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (senha.contains(" ")) {
            JOptionPane.showMessageDialog(this, "A senha não pode conter espaços.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

// Valida CPF com cálculo dos dígitos verificadores
    private boolean isValidCPF(String cpf) {
        int[] pesos1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesos2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * pesos1[i];
            }
            int digito1 = (soma % 11 < 2) ? 0 : 11 - (soma % 11);

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * pesos2[i];
            }
            int digito2 = (soma % 11 < 2) ? 0 : 11 - (soma % 11);

            return digito1 == Character.getNumericValue(cpf.charAt(9))
                    && digito2 == Character.getNumericValue(cpf.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }


    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
        this.dispose();  // Close the current window
        LoginUser j = new LoginUser(this);  // Open the login window
        j.setVisible(true);
        j.setLocationRelativeTo(null);  // Center the window
    }//GEN-LAST:event_userActionPerformed

    // Method to check if a user already exists in the database by CPF
    private static boolean usuarioExist(String cpf) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            conn = Database.getConnection();  // Get database connection
            // Check if the CPF is already registered in the database
            stmt = conn.prepareStatement("SELECT COUNT(*) FROM usuarios WHERE cpf = ?");
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();

            if (rs.next()) {
                existe = rs.getInt(1) > 0;  // If the count is greater than 0, the CPF already exists
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();  // Close the result set
                }
                if (stmt != null) {
                    stmt.close();  // Close the statement
                }
                if (conn != null) {
                    conn.close();  // Close the database connection
                }
            } catch (SQLException e) {
                e.printStackTrace();  // Print exception if closing fails
            }
        }

        return existe;  // Return whether the user exists or not
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroUser(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCadastro;
    private javax.swing.JFormattedTextField edtCPF;
    private javax.swing.JTextField edtIdade;
    private javax.swing.JTextField edtNome;
    private javax.swing.JPasswordField edtSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem user;
    // End of variables declaration//GEN-END:variables
}
