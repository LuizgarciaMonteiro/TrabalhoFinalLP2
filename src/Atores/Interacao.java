package Atores;

import Persistencia.Salvavel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

public class Interacao implements Salvavel {

    private String descricao;
    private Usuario usuario;
    private Date data;

    public Interacao(String d, Usuario u) {

        this.descricao = d;
        this.usuario = u;
        this.data = new Date();
    }

    public Interacao(String d, Usuario u, Date data) {

        this.descricao = d;
        this.usuario = u;
        this.data = data;
    }

    public String toString() {
        SimpleDateFormat d = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss"); // Define the desired format
        String dataFormatada = d.format(this.data);

        return this.descricao + " (adicionada por " + usuario.getNome() + " em " + dataFormatada + ")";
    }

    public boolean equals(Interacao i) {
        return this.usuario.equals(i.getUsuario()) && this.data.equals(i.getData());
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Date getData() {
        return data;
    }


    public void salvarEmArquivo(Formatter f) {
        f.format("%s\n", this.descricao);
        f.format("%s\n", this.usuario.getEmail());

        SimpleDateFormat d = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss"); // Define the desired format
        String dataFormatada = d.format(this.data);

        f.format("%s\n", dataFormatada);
    }

}
