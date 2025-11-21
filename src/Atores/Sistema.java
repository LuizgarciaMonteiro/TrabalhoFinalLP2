package Atores;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {

    private List<Cliente> clientes;
    private List<Suporte> atendentes;
    private List<Chamado> chamados;
    private int nChamadoAtual;

    public Sistema() {
        this.clientes = new ArrayList<>();
        this.atendentes = new ArrayList<>();
        this.chamados = new ArrayList<>();
        this.nChamadoAtual = 0;
    }

    public Cliente getCliente(String email){
        for (Cliente c : this.clientes) {
            if (email.equals(c.getEmail())) {
                return c;
            }
        }
        return null;
    }


    public Suporte getSuporte(String email) {
        for (Suporte sup : this.atendentes) {
            if (email.equals(sup.getEmail())) {
                return sup;
            }
        }
        return null;
    }


    public void inserirCliente(Cliente c) {
        this.clientes.add(c);
    }

    public void inserirSuporte(Suporte p) {
        this.atendentes.add(p);
    }

    public void inserirChamado(Chamado c) {this.chamados.add(c);}

    public int gerarNumeroChamado() {
        this.nChamadoAtual += 1;
        return this.nChamadoAtual;
    }
    public void cancelarChamado(int numero) {

        int pos = -1;
        for (int i = 0; i < this.chamados.size(); i++) {
            if (this.chamados.get(i).getNumero() == numero) {
                pos = i;
            }
        }
        if (pos >= 0) {
            this.chamados.remove(pos);
        } else {
            System.out.println("Chamado inexistente!");
        }
    }

}
