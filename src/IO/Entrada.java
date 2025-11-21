package IO;

import Atores.Chamado;
import Atores.Cliente;
import Atores.Sistema;
import Atores.Suporte;

import java.util.Scanner;

public class Entrada {
    Scanner entrada;

    public Entrada() {
        this.entrada = new Scanner(System.in);
    }

    /* Métodos para leitura dos dados do programa */

    public String lerString(String s) {
        System.out.print(s);

        String x = this.entrada.nextLine();
        return x;
    }

    public int lerInt(String s) {
        System.out.print(s);

        String x = this.entrada.nextLine();
        return Integer.parseInt(x);
    }

    /* Menus do sistema: */

    public int menuPrincipal() {
        int opcao;

            System.out.println("--- MENU PRICIPAL ---\n\n" +
                    "1. CRIAR CADASTRO COMO CLIENTE \n" +
                    "2. CRIAR CADASTRO COMO SUPORTE \n" +
                    "3. LOGIN CLIENTE\n" +
                    "4. LOGIN SUPORTE\n" +
                    "0. SAIR");
            opcao = this.lerInt("Escolha a opção: ");
            return opcao;
    }

    public void menuCliente(Sistema s, Cliente c) {

        String opcoes = "1. VER MEUS CHAMADOS \n" +
                "2. CRIAR NOVO CHAMADO \n" +
                "3. CANCELAR CHAMADO \n" +
                "4. REABRIR CHAMADO \n" +
                "5. SAIR \n";

        System.out.println(opcoes);
        int opcao = this.lerInt("Escolha uma opção: ");

        while (opcao != 5) {
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
                    System.out.println("Reabrir chamados");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

            System.out.println(opcoes);

            opcao = this.lerInt("Escolha uma opção: ");
        }
    }

    public void menuSuporte(Sistema s, Suporte sup) {

        String opcoes = "1. VER MEUS CHAMADOS EM ANDAMENTO \n" +
                "2. ASSUMIR CHAMADO \n" +
                "3. RESOLVER CHAMADO \n" +
                "4. SAIR \n";

        System.out.println(opcoes);
        int opcao = this.lerInt("Escolha uma opção: ");

        while (opcao != 4) {
            switch (opcao) {
                case 1:
                    sup.vizualizarChamados();
                    break;
                case 2:
                   // this.cadastrarChamado(s,sup);
                    break;
                case 3:
                    System.out.println("Cancelando novos chamados");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

            System.out.println(opcoes);

            opcao = this.lerInt("Escolha uma opção: ");
        }
    }


    /* Cadastro dos usuários do sistema */

    public void cadastrarChamado(Sistema s, Cliente c) {

        String titulo = this.lerString("Digite o titulo breve para o chamado: ");
        int numero = s.gerarNumeroChamado();
        String descricao = this.lerString("Digite o descricao completa do chamado: ");
        Chamado chamado = c.criarChamado(numero, titulo, descricao);
        s.inserirChamado(chamado);

    }
    public void cancelarChamado(Sistema s, Cliente c) {
        c.vizualizarChamados();
        int numero = this.lerInt("Digite o número do chamado: #");
        s.cancelarChamado(numero);
    }

    public void cadastrarCliente(Sistema s) {
        String nome = this.lerString("Nome do cliente: ");
        String cpf = this.lerString("CPF do cliente: ");
        String email = this.lerString("Email do cliente: ");
        String senha = this.lerString("Senha do cliente: ");

        Cliente c = new Cliente(nome, cpf, email, senha);
        s.inserirCliente(c);

        System.out.println("CADASTRO REALIZADO COM SUCESSO!");

    }

    public void cadastrarSuporte(Sistema s) {
        String nome = this.lerString("Nome do suporte: ");
        String cpf = this.lerString("CPF do suporte: ");
        String email = this.lerString("Email do suporte: ");
        String senha = this.lerString("Senha do suporte: ");


        Suporte sup = new Suporte(nome, cpf, email, senha);
        s.inserirSuporte(sup);

        System.out.println("CADASTRO REALIZADO COM SUCESSO!");


    }

    /* Logins dos usuários do sistema */

    public void loginCliente(Sistema s) {
        String login = this.lerString("Digite seu email: ");
        String senha = this.lerString("Digite sua senha: ");

        Cliente c = s.getCliente(login);

        if (c != null) {
            if (c.validarAcesso(senha)){
                System.out.println("Login de cliente realizado com sucesso!");
                this.menuCliente(s, c);
            } else {
                System.out.println("Erro: Email e/ou Senha incorretos! ");
            }
        } else {
            System.out.println("Erro: Email e/ou Senha incorretos! ");
        }
    }

    public void loginSuporte(Sistema s) {
        String login = this.lerString("Digite seu email: ");
        String senha = this.lerString("Digite sua senha: ");

        Suporte sup = s.getSuporte(login);

        if (sup != null) {
            if (sup.validarAcesso(senha)){
                System.out.println("Login de suporte realizado com sucesso!");
            } else {
                System.out.println("Erro: Email e/ou Senha incorretos! ");
            }
        } else {
            System.out.println("Erro: Email e/ou Senha incorretos! ");
        }
    }





}
