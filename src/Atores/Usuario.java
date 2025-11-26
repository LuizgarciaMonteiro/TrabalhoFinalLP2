package Atores;

import Persistencia.Salvavel;

import java.util.Formatter;
import java.util.List;

/**
 * Classe que representa um usuário do sistema.
 * Os usuário do sistema possuem nome, cpf, email e senha.
 * Os dados dos usuários podem ser salvos em arquivo, por isso a classe implementa a interface Salvavel
 */
public abstract class Usuario implements Salvavel {

    /** Atributos para salvar nome, cpf, email e senha do usuário */
    protected String nome;
    protected String cpf;
    protected String email;
    protected String senha;

    /**
     * Constrói uma nova instância de Usuario com os dados fornecidos.
     * @param n o nome do usuário
     * @param c o cpf do usuário
     * @param m o email do usuário
     * @param s a senha do usuário
     */
    public Usuario(String n, String c, String m, String s) {

        this.nome = n;
        this.cpf = c;
        this.email = m;
        this.senha = s;
    }

    /**
     * Retorna uma string representando um usuário.
     * @return Uma string com o nome do usuário
     */
    public String toString() {
        return this.nome;
    }

    /**
     * Compara se uma instância de Usuario é igual a outra instância da mesma classe.
     * @return Um booleano indicando se os usuários possuem o mesmo email
     */
    public boolean equals(Usuario u) {
        return this.email.equals(u.getEmail());
    }

    public String getEmail() {
        return this.email;
    }

    public String getNome() {
        return this.nome;
    }

    /**
     * Valida o acesso de um usuário no sistema.
     * @param senha a senha do usuário
     * @return Um booleano informando se os dados foram validados corretamente
     */
    public boolean validarAcesso(String senha) {
        if (senha.equals(this.senha)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo abstrato que deve ser implementado em toda classe que herdar de usuário.
     * Exibe os chamados do usuário.
     * @return Um booleano indicando que pelo menos um chamado foi exibido.
     */
    public abstract boolean vizualizarChamados(List<Chamado> chamados);

    /**
     * Como a classe Usuário implementa a interface Salvavel, é preciso implementar este metodo.
     * Define como os dados de um usuário serão salvos em um arquivo texto.
     * @param f um objeto da classe Formatter
     */
    public void salvarEmArquivo(Formatter f) {
        f.format("%s\n", this.email);
        f.format("%s\n", this.nome);
        f.format("%s\n", this.cpf);
        f.format("%s\n", this.senha);
    }

}
