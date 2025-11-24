package Atores;

import Persistencia.Salvavel;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa o sistema e armazena as listas com todos os clientes, atendentes e chamados ja criados.
 * também há uma lista para todos os objetos que podem ser salvos em arquivo.
 */
public class Sistema {

    private List<Cliente> clientes;
    private List<Suporte> atendentes;
    private List<Chamado> chamados;
    private List<Salvavel> salvaveis;
    private int nChamadoAtual;

    /**
     * Constrói uma nova instância de Sistema e inicializa as listas armazenadas.
     */
    public Sistema() {
        this.clientes = new ArrayList<>();
        this.atendentes = new ArrayList<>();
        this.chamados = new ArrayList<>();
        this.salvaveis = new ArrayList<>();
        this.nChamadoAtual = 0;
    }

    /**
     * Retorna uma string representando o Sistema.
     * @return Uma string com o numero de clientes, atendentes e chamados ja cadastrados.
     */
    public String toString() {
        String retorno = "Clientes cadastrados: " + this.clientes.size() + "\n";
        retorno += "Atendentes cadastrados: " + this.atendentes.size() + "\n";
        retorno += "Chamados cadastrados: " + this.chamados.size() + "\n";

        return retorno;
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

    public Usuario getUsuario(String email) {
        Cliente c = this.getCliente(email);
        if (c != null) {
            return c;
        }
        else {
            return this.getSuporte(email);
        }
    }

    /**
     * Insere um cliente na lista de cliente e de objetos salvaveis em arquivo.
     */
    public void inserirCliente(Cliente c) {
        this.clientes.add(c);
        this.salvaveis.add(c);
    }

    /**
     * Insere um suporte na lista de suporte e de objetos salvaveis em arquivo.
     */
    public void inserirSuporte(Suporte p) {
        this.atendentes.add(p);
        this.salvaveis.add(p);
    }

    /**
     * Insere um chamado na lista de chamado e de objetos salvaveis em arquivo.
     */
    public void inserirChamado(Chamado c) {
        this.chamados.add(c);
        this.salvaveis.add(c);
    }

    /**
     * Gera um numero unico para identificar o chamado.
     * @return um numero unico para identificar um chamado no sistema.
     */
    public int gerarNumeroChamado() {
        this.nChamadoAtual += 1;
        return this.nChamadoAtual;
    }

    /**
     * imprime uma lista com todos os chamados do sistema que ainda não tiverem um suporte responsavel.
     * @return um boolean indicando se algum chamado foi impressso na tela.
     */
    public boolean vizualizarChamadosAbertos() {
        boolean achou = false;
        System.out.println("\nCHAMADOS:\n");
        for (Chamado c : this.chamados) {
            if (c.chamadoAberto()) {
                System.out.println(c);
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("\nNenhum chamado encontrado!");
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Cancela o chamado de um cliente no sistema.
     * @param numero o identificados do chamado.
     * @param cliente que abriu o chamado.
     */
    public void cancelarChamado(int numero, Cliente cliente) {
        boolean achou = false;
        for ( Chamado c : cliente.getChamados()) {
            if (numero == c.getNumero() && !c.chamadoCancelado()) {
                c.cancelarChamado();
                achou = true;
            }
        }
        if (achou) {
            System.out.println("\nChamado cancelado com sucesso.");
        }else {
        System.out.println("\nChamado não encontrado ou já cancelado.");
        }
    }

    /**
     * Altera o responsavel por resolver este chamado.
     * @param numero identificador do chamado.
     * @param sup o atendente que vai assumir este chamado.
     */
    public void assumirChamado(int numero, Suporte sup) {
        boolean achou = false;
        for ( Chamado c : this.chamados) {
            if (numero == c.getNumero() && c.chamadoAberto()) {
                c.setStatus(2);
                sup.assumirChamado(c);
                c.adicionarResponsavel(sup);
                achou = true;
            }
        }
        if (achou) {
            System.out.println("\nChamado alterado com sucesso.");
        }else {
            System.out.println("\nChamado não encontrado ou já cancelado.");
        }
    }

    /**
     * reabre um chamado de um cliente que não aceitou sua solução.
     * @param numero o identificados do chamado.
     * @param c o cliente que abriu o chamado.
     * @param descricao o motivo da reabertura do chamado.
     */
    public void reabrirChamado(int numero, Cliente c, String descricao) {
        boolean achou = false;
        for ( Chamado chamado : c.getChamados()) {
            if (numero == chamado.getNumero() && chamado.chamadoFinalizado()) {
                chamado.adicionarInteracao(descricao, c, 2);
                achou = true;
            }
        }
        if (achou) {
            System.out.println("\nChamado reaberto com sucesso.");
        }else {
            System.out.println("\nChamado não encontrado ou já cancelado.");
        }
    }

    /**
     * Muda os dados de um chamado que foi resolvido.
     * @param numero indentificados do chamado.
     * @param sup o atendente que resolveu este chamado.
     * @param descricao a solução do chamado.
     */
    public void resolverChamado(int numero, Suporte sup, String descricao) {
        boolean achou = false;
        for ( Chamado c : sup.getChamados()) {
            if (numero == c.getNumero() && c.chamadoEmAndamento()) {
                c.adicionarInteracao(descricao, sup, 3);
                achou = true;
            }
        }
        if (achou) {
            System.out.println("\nChamado resolvido com sucesso.");
        }else {
            System.out.println("\nChamado não encontrado ou já cancelado.");
        }
    }

    /**
     * Verifica se algum usuario cadastrado no sistema possui este email.
     * @param email o email a ser buscado.
     * @return um booleano indicando se a algum usuario no sistema com esse email.
     */
    public boolean emailExistente(String email) {
        for (Cliente c : this.clientes) {
            if (c.getEmail().equals(email)) {
                return true;
            }
        }
        for (Suporte sup : this.atendentes) {
            if (sup.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public List<Salvavel> getSalvaveis() {
        return this.salvaveis;
    }


}
