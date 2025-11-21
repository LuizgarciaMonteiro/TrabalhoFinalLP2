package Atores;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario {


    protected String nome;
    protected String cpf;
    protected String email;
    protected String senha;

    protected List<Chamado> chamados;


    public Usuario(String n, String c, String m, String s) {

        this.nome = n;
        this.cpf = c;
        this.email = m;
        this.senha = s;

        this.chamados = new ArrayList<>();
    }

    public void vizualizarChamados() {

        if (this.chamados.isEmpty()) {
            System.out.println("Nenhum chamado criado até o momento!");
        } else {
            for (Chamado c : this.chamados) {
                System.out.println(c);
            }
        }
    }

    public boolean validarAcesso(String senha) {
        if (senha.equals(this.senha)) {
                return true;
        } else {
            return false;
        }
    }

    public void recuperarAcesso() {
        Scanner entrada = new Scanner(System.in);

        String novaSenha;

        System.out.print("Digite seu e-mail: ");
        String email = entrada.nextLine();

        System.out.print("Digite seu cpf: ");
        String cpf = entrada.nextLine();

        if (email.equals(this.email) &&  cpf.equals(this.cpf)) {
            System.out.print("Digite sua nova senha: ");
            this.senha = entrada.nextLine();
            System.out.print("Senha atualizada com sucesso!");
        }
        else {
            System.out.print("Dados inválidos. Senha não atualizada!");
        }

    }

    public String getEmail() {
        return this.email;
    }

}
