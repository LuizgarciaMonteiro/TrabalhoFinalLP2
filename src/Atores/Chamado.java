package Atores;

import java.util.ArrayList;
import java.util.List;

public class Chamado {

    private int numero;
    private String titulo;
    private int status;

    private List<Interacao> interacoes;

    public Chamado(int n, String t, Interacao i) {

        this.numero = n;
        this.titulo = t;
        this.status = 1;
        this.interacoes = new ArrayList<>();
        this.interacoes.add(i);
    }

    public String toString() {
        String chamado = "Chamado #" + this.numero + "\n";
        chamado += "Titulo: " + this.titulo + "\n";
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

        }
        chamado += "Número de interações: " + this.interacoes.size() + "\n";
        return chamado;
    }

    public int getNumero() {
        return this.numero;
    }


    public void atribuirResponsavel(Suporte atendente) {

    }

    public void atualizarChamado(Interacao descricao) {

    }
}
