package Atores;

import Persistencia.Salvavel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

/**
 * Classe que representa uma interação do chamado.
 * As interações podem ser feitas por um cliente ou por um atendente do suporte.
 * As interações podem ser salvas em arquivo, por isso a classe implementa a interface Salvavel
 */
public class Interacao implements Salvavel {

    private String descricao;
    private Usuario usuario;
    private Date data;

    /**
     * Construtor que recebe uma descrição e um usuário.
     * A data de criacão será a data do sistema daquele momento.
     */
    public Interacao(String d, Usuario u) {

        this.descricao = d;
        this.usuario = u;
        this.data = new Date();
    }

    /**
     * Construtor que recebe uma descrição, um usuário e a data que sera salva em arquivo.
     */
    public Interacao(String d, Usuario u, Date data) {

        this.descricao = d;
        this.usuario = u;
        this.data = data;
    }

    /**
     * Retorna uma string representando um interação.
     * @return Uma string com as informações desta interação.
     */
    public String toString() {
        /* formatando a data para o padrão (dia/mês/ano hora:minuto:segundo) */
        SimpleDateFormat d = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss"); // Define the desired format
        String dataFormatada = d.format(this.data);

        return this.descricao + " (adicionada por " + usuario.getNome() + " em " + dataFormatada + ")";
        /* Ex: Sem água (adicionada por Luiz Henrique em 24/11/2025 13:09:00) */
    }

    /**
     * Compara se uma instância de interação é igual a outra instância da mesma classe.
     * @return Um booleano indicando se as duas interações foram criadas pelo mesmo usuário no mesmo instante.
     */
    public boolean equals(Interacao i) {
        return this.usuario.equals(i.getUsuario()) && this.data.equals(i.getData());
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Date getData() {
        return data;
    }

    /**
     * Como a classe Interacao implementa a interface Salvavel, é preciso implementar este metodo.
     * Define como os dados de uma interação serão salvos em um arquivo texto.
     * @param f um objeto da classe Formatter
     */
    public void salvarEmArquivo(Formatter f) {
        f.format("%s\n", this.descricao);
        f.format("%s\n", this.usuario.getEmail());

        /* formatando a data para o padrão (dia/mês/ano hora:minuto:segundo) */
        SimpleDateFormat d = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss"); // Define the desired format
        String dataFormatada = d.format(this.data);

        f.format("%s\n", dataFormatada);
    }

}
