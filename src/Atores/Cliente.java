package Atores;

import java.util.Formatter;

/**
 * repreesenta um cliente
 * a classe herda de usuario
 */
public class Cliente extends Usuario  {

    /**
     * Constrói uma nova instância da classe cliente, chamando o contrutor sa super classe(usuario).
     * @param n o nome do cliente
     * @param c o cpf do cliente
     * @param m o email do cliente
     * @param s a senha do cliente
     */
    public Cliente(String n, String c, String m, String s) {

        /* como nao há atributos nesta subclasse, basta chamar o construtor da super classe(usuario)*/
        super(n, c, m, s);

    }

    /**
     * imprime uma lista com todos os chamados do cliente que não foram cancelados.
     * @return um boolean indicando se algum chamado foi impressso na tela.
     */
    public boolean vizualizarChamados() {
        boolean achou = false;
        System.out.println("\nCHAMADOS:\n");
        for (Chamado c : this.chamados) {
            if (!c.chamadoCancelado()) {
                System.out.println(c);
                achou = true;
            }
        }

        if (!achou) {
            System.out.println("\nNenhum chamado criado pelo usuário até o momento!");
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * imprime uma lista com todos os chamados do cliente que foram finalizados.
     * @return um boolean indicando se se algum chamado foi impresso na tela.
     */
    public boolean vizualizarChamadosFinalizados() {
        boolean achou = false;
        System.out.println("\nCHAMADOS:\n");
        for (Chamado c : this.chamados) {
            if (c.chamadoFinalizado()) {
                System.out.println(c);
                achou = true;
            }
        }

        if (!achou) {
            System.out.println("\nNenhum chamado criado pelo usuário até o momento!");
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Cria um chamado feito por este cliente, dado o numero, titulo e descricao.
     * O novo chamado é adicionado a lista de chamados deste cliente
     * @param numero para numero do chamado
     * @param titulo para o titulo do chamado
     * @param descricao para a descricao do chamado
     */
    public Chamado criarChamado(int numero, String titulo, String descricao) {
        Chamado c = new Chamado(numero, titulo, 1, this);
        c.adicionarInteracao(descricao, this, 1);
        this.chamados.add(c);
        return c;
    }
    /**
     * Como a classe cliente implementa herda de usuario que por sua vez implementa a interface
     * salvavel, é preciso implementar este metodo.
     * Define como os dados de um cliente serão salvos em um arquivo texto.
     * @param f um objeto da classe Formatter
     */
    public void salvarEmArquivo(Formatter f) {
        f.format("1\n");
        super.salvarEmArquivo(f);
    }

}

