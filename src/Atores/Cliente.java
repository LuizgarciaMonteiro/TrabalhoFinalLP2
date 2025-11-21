package Atores;

import java.util.Date;

public class Cliente extends Usuario  {


    public Cliente(String n, String c, String m, String s) {
        super(n, c, m, s);

    }



    public Chamado criarChamado(int numero, String titulo, String descricao) {
        Interacao i = new Interacao(descricao, this, new Date());
        Chamado c = new Chamado(numero, titulo, i);
        this.chamados.add(c);
        return c;
    }

}

