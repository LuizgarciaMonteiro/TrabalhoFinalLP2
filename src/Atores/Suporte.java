package Atores;


import java.util.Formatter;

public class Suporte extends Usuario {


    public Suporte(String n, String c, String m, String s) {
        super(n, c, m, s);
    }

    public boolean vizualizarChamados() {
        boolean achou = false;
        System.out.println("\nCHAMADOS:\n");
        for (Chamado c : this.chamados) {
            if (c.chamadoEmAndamento()) {
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


    public void assumirChamado(Chamado c) {
        this.chamados.add(c);
    }

    public void salvarEmArquivo(Formatter f) {
        f.format("2\n");
        super.salvarEmArquivo(f);
    }


}
