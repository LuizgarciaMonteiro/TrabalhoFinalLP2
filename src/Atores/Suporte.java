package Atores;


import java.util.Formatter;
import java.util.List;


/**
 * repreesenta um suporte
 * a classe herda de usuario
 */
public class Suporte extends Usuario {

    /**
     * Constrói uma nova instância da classe suporte, chamando o contrutor sa super classe(usuario).
     * @param n o nome do suporte
     * @param c o cpf do suporte
     * @param m o email do suporte
     * @param s a senha do suporte
     */
    public Suporte(String n, String c, String m, String s) {
        super(n, c, m, s);
    }

    /**
     * imprime uma lista com todos os chamados do atendente.
     * @param chamados lista com todos os chamados do sistema.
     * @return um boolean indicando se algum chamado foi impressso na tela.
     */
    public boolean vizualizarChamados(List<Chamado> chamados) {
        boolean achou = false;
        System.out.println("\nCHAMADOS:\n");
        for (Chamado c : chamados) {
            if (c.possuiResponsavel() && this.equals(c.getResponsavel()) && c.chamadoEmAndamento()) {
                System.out.println(c);
                achou = true;
            }
        }

        if (!achou) {
            System.out.println("\nNenhum chamado em andamento até o momento!");
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Como a classe suporte implementa herda de usuario que por sua vez implementa a interface
     * salvavel, é preciso implementar este metodo.
     * Define como os dados de um suporte serão salvos em um arquivo texto.
     * @param f um objeto da classe Formatter
     */
    public void salvarEmArquivo(Formatter f) {
        f.format("2\n");
        super.salvarEmArquivo(f);
    }


}
