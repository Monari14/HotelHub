package Classes;

public class ReservaPagamento {

    private static String nome, cpf, email, quemCadastrou, quarto, servico, dataEntrada, dataSaida, metodoPagamento, numeroQuarto;
    private static double valorFinal;
    private static int idade;

    // Setter para o nome
    public static void setNome(String nome) {
        ReservaPagamento.nome = nome;
    }

    // Getter para o nome
    public static String getNome() {
        return nome;
    }

    // Setter para a idade
    public static void setIdade(int idade) {
        ReservaPagamento.idade = idade;
    }

    // Getter para a idade
    public static int getIdade() {
        return idade;
    }

    // Setter para o CPF
    public static void setCpf(String cpf) {
        ReservaPagamento.cpf = cpf;
    }

    // Getter para o CPF
    public static String getCpf() {
        return cpf;
    }

    // Setter para o e-mail
    public static void setEmail(String email) {
        ReservaPagamento.email = email;
    }

    // Getter para o e-mail
    public static String getEmail() {
        return email;
    }

    // Setter para quem cadastrou
    public static void setQuemCadastrou(String quemCadastrou) {
        ReservaPagamento.quemCadastrou = quemCadastrou;
    }

    // Getter para quem cadastrou
    public static String getQuemCadastrou() {
        return quemCadastrou;
    }

    // Setter para o quarto
    public static void setQuarto(String quarto) {
        ReservaPagamento.quarto = quarto;
    }

    // Getter para o quarto
    public static String getQuarto() {
        return quarto;
    }

    // Setter para o serviço
    public static void setServico(String servico) {
        ReservaPagamento.servico = servico;
    }

    // Getter para o serviço
    public static String getServico() {
        return servico;
    }

    // Setter para a data de entrada
    public static void setDataEntrada(String dataEntrada) {
        ReservaPagamento.dataEntrada = dataEntrada;
    }

    // Getter para a data de entrada
    public static String getDataEntrada() {
        return dataEntrada;
    }

    // Setter para a data de saída
    public static void setDataSaida(String dataSaida) {
        ReservaPagamento.dataSaida = dataSaida;
    }

    // Getter para a data de saída
    public static String getDataSaida() {
        return dataSaida;
    }

    // Setter para o método de pagamento
    public static void setMetodoPagamento(String metodoPagamento) {
        ReservaPagamento.metodoPagamento = metodoPagamento;
    }

    // Getter para o método de pagamento
    public static String getMetodoPagamento() {
        return metodoPagamento;
    }

    // Setter para o número do quarto
    public static void setNumeroQuarto(String numeroQuarto) {
        ReservaPagamento.numeroQuarto = numeroQuarto;
    }

    // Getter para o número do quarto
    public static String getNumeroQuarto() {
        return numeroQuarto;
    }

    // Setter para o valor final
    public static void setValorFinal(double valorFinal) {
        ReservaPagamento.valorFinal = valorFinal;
    }

    // Getter para o valor final
    public static double getValorFinal() {
        return valorFinal;
    }
}
