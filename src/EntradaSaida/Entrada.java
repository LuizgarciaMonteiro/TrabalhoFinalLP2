package EntradaSaida;

import Atores.*;

import java.security.PrivateKey;
import java.util.Scanner;

public class Entrada {
    private Scanner entrada;

    public Entrada() {
        this.entrada = new Scanner(System.in);
    }

    /* Métodos para leitura dos dados do programa */

    /**
     * lê uma string digitada no teclado
     * @param s o texto mostrado para o usuario. (Ex. digite sua senha:)
     */
    public String lerString(String s) {
        System.out.print(s);

        return this.entrada.nextLine();
    }
    /**
     * lê um inteiro digitada no teclado.
     * Lança uma excessão se o valor digitado não for um numero inteiro válido.
     * @param s o texto mostrado para o usuario. (Ex. digite sua senha:)
     */
    public int lerInt(String s) throws NumberFormatException {
        System.out.print(s);

        try {
            String x = this.entrada.nextLine();
            /* Tenta converter a String lida para inteiro*/
            return Integer.parseInt(x);
        }
        catch (NumberFormatException e) {
            /* Se não for possivel converter, lança uma exceção informando que numero não é válido.*/
            throw new NumberFormatException("\nNúmero inválido.");
        }

    }

    /* Menus do sistema: */

    public static void limparTela() {
        System.out.println("\n---------------------------------------------------------");
    }

    /**
     * Exibe o menu principal do sistema e retorna a opção escolhida pelo usuário.
     */
    public int menuPrincipal() {
        int opcao;

            limparTela();
            System.out.println("\n" +
                    "1. CRIAR CADASTRO COMO CLIENTE \n" +
                    "2. CRIAR CADASTRO COMO SUPORTE \n" +
                    "3. LOGIN CLIENTE\n" +
                    "4. LOGIN SUPORTE\n" +
                    "0. SAIR");

            try {
                opcao = this.lerInt("\nEscolha a opção: ");
            }
            catch (NumberFormatException e) {
                /* Se o usuário não digitar um número válido, a opção será incorreta. */
                opcao = 5;
            }
            return opcao;
    }

    /**
     * Exibe o menu de um cliente logado no sistema.
     * @param s um objeto da classe sistema.
     * @param c o cliente que esta logado no sistema.
     */
    public void menuCliente(Sistema s, Cliente c) {

        String opcoes = "\n1. VER MEUS CHAMADOS \n" +
                "2. CRIAR NOVO CHAMADO \n" +
                "3. CANCELAR CHAMADO \n" +
                "4. REABRIR CHAMADO \n" +
                "0. SAIR \n";

        limparTela();
        System.out.println(opcoes);
        int opcao;

        try {
            opcao = this.lerInt("\nEscolha uma opção: ");
        }
        catch (NumberFormatException e) {
            opcao = 5;
        }

        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    c.vizualizarChamados();
                    break;
                case 2:
                    this.cadastrarChamado(s, c);
                    break;
                case 3:
                    this.cancelarChamado(s, c);
                    break;
                case 4:
                    this.reabrirChamado(s, c);
                    break;
                default:
                    System.out.println("\nOpção inválida!");
                    break;
            }

            limparTela();
            System.out.println(opcoes);

            try {
                opcao = this.lerInt("\nEscolha uma opção: ");
            }
            catch (NumberFormatException e) {
                opcao = 5;
            }
        }
    }

    /**
     * Exibe o menu de um atendente logado no sistema.
     * @param s um objeto da classe sistema.
     * @param sup o suporte que esta logado no sistema.
     */
    public void menuSuporte(Sistema s, Suporte sup) {

        String opcoes = "\n1. VER MEUS CHAMADOS EM ANDAMENTO \n" +
                "2. ASSUMIR CHAMADO \n" +
                "3. RESOLVER CHAMADO \n" +
                "0. SAIR \n";

        limparTela();
        System.out.println(opcoes);
        int opcao;

        try {
            opcao = this.lerInt("\nEscolha uma opção: ");
        }
        catch (NumberFormatException e) {
            opcao = 4;
        }

        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    sup.vizualizarChamados();
                    break;
                case 2:
                    this.assumirChamado(s,sup);
                    break;
                case 3:
                    this.resolverChamado(s, sup);
                    break;
                default:
                    System.out.println("\nOpção inválida!");
                    break;
            }

            limparTela();
            System.out.println(opcoes);

            try {
                opcao = this.lerInt("\nEscolha uma opção: ");
            }
            catch (NumberFormatException e) {
                opcao = 4;
            }
        }
    }


    /* CADASTRO DOS DADOS DO SISTEMA */

    /**
     * lê os dados do cliente e chama os métodos apropriados para cadastra-lo no sistema
     * @param s um objeto da classe sistema.
     */
    public void cadastrarCliente(Sistema s) {
        String email = this.lerString("\nEmail do cliente: ");

        if (s.emailExistente(email)) {
            System.out.println("\nEmail já existente! Cadastro não realizado!");
        }
        else {
            String nome = this.lerString("Nome do cliente: ");
            String cpf = this.lerString("CPF do cliente: ");
            String senha = this.lerString("Senha do cliente: ");

            Cliente c = new Cliente(nome, cpf, email, senha);
            s.inserirCliente(c);

            System.out.println("\nCADASTRO REALIZADO COM SUCESSO!");
        }
    }

    /**
     * lê os dados do suporte e chama os métodos apropriados para cadastra-lo no sistema
     * @param s um objeto da classe sistema.
     */
    public void cadastrarSuporte(Sistema s) {
        String email = this.lerString("\nEmail do suporte: ");

        if (s.emailExistente(email)) {
            System.out.println("\nEmail já existente! Cadastro não realizado!");
        }
        else {
            String nome = this.lerString("Nome do suporte: ");
            String cpf = this.lerString("CPF do suporte: ");
            String senha = this.lerString("Senha do suporte: ");


            Suporte sup = new Suporte(nome, cpf, email, senha);
            s.inserirSuporte(sup);

            System.out.println("\nCADASTRO REALIZADO COM SUCESSO!");
        }
    }

    /**
     * lê os dados do chamado e chama os métodos apropriados para cadastra-lo no sistema
     * @param s um objeto da classe sistema.
     */
    public void cadastrarChamado(Sistema s, Cliente c) {

        String titulo = this.lerString("\nDigite o titulo breve para o chamado: ");
        int numero = s.gerarNumeroChamado();
        String descricao = this.lerString("Digite o descricao completa do chamado: ");
        Chamado chamado = c.criarChamado(numero, titulo, descricao);
        s.inserirChamado(chamado);

    }

    /* FUNCIONALIDADES DO CLIENTE */

    /**
     * lê os dados necessarios para cancelar chamado de um cliente  e chama os métodos apropriados.
     * @param s um objeto da classe sistema.
     * @param c o cliente que esta logado no sistema.
     */
    public void cancelarChamado(Sistema s, Cliente c) {
        if (c.vizualizarChamados()) {
            try {
                int numero = this.lerInt("\nDigite o número do chamado: #");
                s.cancelarChamado(numero);
            }
            catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * lê os dados necessarios para reabrir um chamado do cliente  e chama os métodos apropriados.
     * @param s um objeto da classe sistema.
     * @param c o cliente que esta logado no sistema.
     */
    public void reabrirChamado(Sistema s, Cliente c) {
        if (c.vizualizarChamadosFinalizados()) {
            try {
                int numero = this.lerInt("\nDigite o número do chamado: #");
                String descricao = this.lerString("Digite o motivo: ");
                s.reabrirChamado(numero, c, descricao);
            }
            catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    /* FUNCIONALIDADES DO SUPORTE */

    /**
     * lê os dados necessarios para um atendente assumir um chamado e chama os métodos apropriados.
     * @param s um objeto da classe sistema.
     * @param sup o atendente que vai assumir este chamado.
     */
    public void assumirChamado(Sistema s, Suporte sup) {
        if (s.vizualizarChamadosAbertos()) {
            try {
                int numero = this.lerInt("\nDigite o número do chamado: #");
                s.assumirChamado(numero, sup);
            }
            catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * lê os dados necessarios para um atendente resolver um chamado e chama os métodos apropriados.
     * @param s um objeto da classe sistema.
     * @param sup o atendente que vai assumir este chamado.
     */
    public void resolverChamado(Sistema s, Suporte sup) {
        if (sup.vizualizarChamados()) {
            try {
                int numero = this.lerInt("\nDigite o número do chamado: #");
                String descricao = this.lerString("Digite a solução: ");
                s.resolverChamado(numero, sup, descricao);
            }
            catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    /* LOGINS DOS USUÁRIOS DO SITEMA */

    /**
     * lê os dados necessarios para um cliente se logar no sistema.
     * Se o login for bem sucedido, chama o menu do cliente
     * @param s um objeto da classe sistema.
     */
    public void loginCliente(Sistema s) {
        String login = this.lerString("\nDigite seu email: ");
        String senha = this.lerString("Digite sua senha: ");

        Cliente c = s.getCliente(login);

        if (c != null) {
            if (c.validarAcesso(senha)){
                System.out.println("\nBem vindo, " + c +"!");
                this.menuCliente(s, c);
            } else {
                System.out.println("\nErro: Email e/ou Senha incorretos! ");
            }
        } else {
            System.out.println("\nErro: Email e/ou Senha incorretos! ");
        }
    }

    /**
     * lê os dados necessarios para um atendente se logar no sistema.
     * Se o login for bem sucedido, chama o menu do suporte.
     * @param s um objeto da classe sistema.
     */
    public void loginSuporte(Sistema s) {
        String login = this.lerString("\nDigite seu email: ");
        String senha = this.lerString("Digite sua senha: ");

        Suporte sup = s.getSuporte(login);

        if (sup != null) {
            if (sup.validarAcesso(senha)){
                System.out.println("\nBem vindo, " + sup +"!");
                this.menuSuporte(s, sup);
            } else {
                System.out.println("\nErro: Email e/ou Senha incorretos! ");
            }
        } else {
            System.out.println("\nErro: Email e/ou Senha incorretos! ");
        }
    }

    public void encerrarSistema() {
        System.out.println("Obrigado, volte sempre!");
        if (this.entrada != null) {
            this.entrada.close();
        }
    }
}
