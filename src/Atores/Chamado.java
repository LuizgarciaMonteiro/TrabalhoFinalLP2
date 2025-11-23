package Atores;

import Persistencia.Salvavel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

public class Chamado implements Salvavel {

    private int numero;
    private String titulo;
    private int status;
    private Cliente solicitante;
    private Suporte responsavel;

    private List<Interacao> interacoes;

    /*public Chamado(int n, String t, Cliente solicitante, Interacao i) {

        this.numero = n;
        this.titulo = t;
        this.status = 1;
        this.solicitante = solicitante;
        this.interacoes = new ArrayList<>();
        this.interacoes.add(i);
    }*/

    /**
     * Constrói uma nova instância da classe chamado, dado o número do chamado,
     * o título do chamado, o status do chamado e o cliente que abriu o chamado.
     * @param n o número do chamado
     * @param t o titulo do chamado
     * @param status (1: para aberto, 2: em andamento, 3: finalizado e 4: cancelado) do chamado.
     * @param solicitante seria o cliente que abriu o chamado.
     *
     */
    public Chamado(int n, String t, int status, Cliente solicitante) {
        this.numero = n;
        this.titulo = t;
        this.status = status;
        this.solicitante = solicitante;
        this.responsavel = null;
        this.interacoes = new ArrayList<>();
    }

    /**
     * Retorna uma string representando um chamado com suas informacões principais,
     * retornando o (# numero do chamado, Titulo do chamado, numero de interçao e as interações que ja,
     * ocorreram neste chamado).
     */
    public String toString() {
        String chamado = "Chamado #" + this.numero + "\n";
        chamado += "Titulo: " + this.titulo + "\n";
        int cont = 1;
        for (Interacao i : this.interacoes) {
            chamado += "Descrição #" + cont + ": "  + i.toString() + "\n";
            cont += 1;
        }
        chamado += "Status atual: ";
        switch (this.status) {
            case 1:
                chamado += "Aberto" + "\n";
                break;
            case 2:
                chamado += "Em andamento" + "\n";
                break;
            case 3:
                chamado += "Finalizado" + "\n";
                break;
            case 4:
                chamado += "Cancelado" + "\n";

        }
        return chamado;
    }

    /**
     * Compara se uma instância se um numero de um chamado é igual ao de outro chamado.
     * @return  se os dois chamados são os mesmo número.
     */
    public boolean equals(Chamado c) {
        return this.numero == c.getNumero();
    }


    public int getNumero() {
        return this.numero;
    }

    /**
     * Altera um status do chamado para o valor recebido como parâmetro.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * retorna se o status do chamado é: Aberto
     * @return um booleano informando se o chamado está aberto.
     */
    public boolean chamadoAberto() {
        return this.status == 1;
    }

    /**
     * retorna se o status do chamado está em: Andamento
     * @return um booleano informando se o chamado está em andamento.
     */
    public boolean chamadoEmAndamento() {
        return this.status == 2;
    }

    /**
     * retorna se o status do chamado está: finalizado
     * @return um booleano informando se o chamado está finalizado.
     */
    public boolean chamadoFinalizado() {
        return this.status == 3;
    }

    /**
     * retorna se o status do chamado está: cancelado
     * @return um booleano informando se o chamado está cancelado.
     */
    public boolean chamadoCancelado() {
        return this.status == 4;
    }

    /**
     * altera o status do chamado para cancelado.
     * cancela o chamado do sistema.
     */
    public void cancelarChamado() {
        this.status = 4;
    }

    /**
     * metodo que insere uma nova interação no chamado
     * @param descricao para descrição do chamado, ex: resolucao ou retorno.
     * @param u seria o usuario que realizou a interação.
     * @param status é como o chamado está: Aberto, em andamento ou fechado ou cancelado
     */
    public void adicionarInteracao(String descricao, Usuario u, int status) {

        this.status = status;
        this.interacoes.add(new Interacao(descricao, u));
    }

    /**
     * metodo que insere uma nova interação no chamado
     * @param descricao para descrição do chamado, ex: resolucao ou retorno.
     * @param u seria o usuario que realizou a interação.
     * @param d para adicionar a data em que a intereção foi realizada.
     */
    public void adicionarInteracao(String descricao, Usuario u, Date d) {
        this.interacoes.add(new Interacao(descricao, u, d));
    }

    /**
     * metodo que atribui um responsavel ao chamado
     * @param s recebe um objeto da classe suporte com o atendente que vai solucionar o chamado.
     */
    public void adicionarResponsavel(Suporte s) {
        this.responsavel = s;
    }


    /**
     * Como a classe chamado implementa a interface Salvavel, é preciso implementar este metodo.
     * Define como os dados de um chamado serão salvos em um arquivo texto.
     * @param f um objeto da classe Formatter
     *
     */
    public void salvarEmArquivo(Formatter f) {
        f.format("3\n");
        f.format("%d\n", this.numero);
        f.format("%s\n", this.titulo);
        f.format("%d\n", this.status);
        f.format("%s\n", this.solicitante.getEmail());

        if (this.responsavel != null) {
            f.format("%s\n", this.responsavel.getEmail());
        }
        else {
            /* indica no arquivo texto que nenhum atendente assumiu o chamado. */
            f.format("null\n");
        }

        /* indica no arquivo quantas interacoes o chamado teve */
        f.format("%d\n", this.interacoes.size());

        for (Interacao i : this.interacoes) {
            i.salvarEmArquivo(f);
        }
    }

}
