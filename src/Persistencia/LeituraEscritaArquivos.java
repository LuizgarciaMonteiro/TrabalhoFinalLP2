package Persistencia;

import Atores.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Classe auxiliar com metodos estaticos para leitura e escrita dos dados do sistema em arquivo.
 */
public class LeituraEscritaArquivos {

    /**
     * lê uma string salva no arquivo.
     * @param arquivo ja aberto para leitura.
     */
    public static String lerString(Scanner arquivo) {
        String x = arquivo.nextLine();
        return x;
    }

    /**
     * lê um inteiro salvo no arquivo.
     * Lança uma excessão se o valor lido não for um numero inteiro válido.
     * @param arquivo ja aberto para leitura.
     */
    public static int lerInt(Scanner arquivo) throws NumberFormatException {
        try {
            String x = arquivo.nextLine();
            return Integer.parseInt(x);
        }
        catch (NumberFormatException e) {
            throw new NumberFormatException("\nNúmero inválido encontrado durante leitura do arquivo em disco.");
        }
    }


    /* MÉTOS PARA LEITURA DOS DADOS SALVO EM ARQUIVO */

    /**
     * metodo principal para leitura do arquivo texto.
     * Abre o arquivo, faz os tratamentos de erros necessarios e chama os metodos apropriados dependendo do
     * tipo de objeto que estiver sendo lido (Um cliente, um atendente ou um chamado).
     * @param s o objeto com os dados do sistema.
     */
    public static void lerArquivo(Sistema s) {
        FileReader f = null;

        try {
            f = new FileReader("dados.txt");
            Scanner arquivo = new Scanner(f);

            int tipo = lerInt(arquivo);
            while (tipo != 0) {
                switch (tipo) {
                    case 1:
                        lerDadosClienteArq(s, arquivo);
                        break;
                    case 2:
                        lerDadosSuporteArq(s, arquivo);
                        break;
                    case 3:
                        lerDadosChamadoArq(s, arquivo);
                        break;
                }
                tipo = lerInt(arquivo);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\nArquivo de dados não encontrado. Nenhum dado carregado.");
        }
        finally {
            /* Se o arquivo chegou a ser aberto, vamos fecha-lo aqui */
            if (f != null) {
                try {
                    f.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar arquivo em disco.");
                }
            }
        }
    }

    /**
     * metodo auxiliar para leitura dos dados de um cliente que foram salvos em arquivos.
     * @param s o objeto com os dados do sistema.
     * @param arquivo ja aberto para leitura.
     */
    public static void lerDadosClienteArq(Sistema s, Scanner arquivo) {
        String email = lerString(arquivo);
        String nome = lerString(arquivo);
        String cpf = lerString(arquivo);
        String senha = lerString(arquivo);

        Cliente c = new Cliente(nome, cpf, email, senha);
        /* Após a criação do objeto com os dados do cliente, ele é adicionado na lista de clientes e de
        * itens salvaveis do sistema. */
        s.inserirCliente(c);
    }

    /**
     * metodo auxiliar para leitura dos dados de um suporte que foram salvos em arquivos.
     * @param s o objeto com os dados do sistema.
     * @param arquivo ja aberto para leitura.
     */
    public static void lerDadosSuporteArq(Sistema s, Scanner arquivo) {
        String email = lerString(arquivo);
        String nome = lerString(arquivo);
        String cpf = lerString(arquivo);
        String senha = lerString(arquivo);

        Suporte sup = new Suporte(nome, cpf, email, senha);
        /* Após a criação do objeto com os dados do suporte, ele é adicionado na lista de suportes e de
         * itens salvaveis do sistema. */
        s.inserirSuporte(sup);
    }

    /**
     * metodo auxiliar para leitura dos dados de um chamado que foram salvos em arquivos.
     * @param s o objeto com os dados do sistema.
     * @param arquivo ja aberto para leitura.
     */
    public static void lerDadosChamadoArq(Sistema s, Scanner arquivo) {
        int numero = lerInt(arquivo);
        String titulo = lerString(arquivo);
        int status = lerInt(arquivo);

        String emailSolicitante = lerString(arquivo);
        String emailResponsavel = lerString(arquivo);

        /* Após a leitura do email do cliente que abriu o chamado e do atendente reposnsavel,
         * precisamos encontrar seus respectivos objetos no sistema. */
        Cliente cli = s.getCliente(emailSolicitante);
        Suporte sup = s.getSuporte(emailResponsavel);

        /* O chamado é criado, mas ainda sem as interações */
        Chamado c = new Chamado(numero, titulo, status, cli);
        c.adicionarResponsavel(sup);

        /* Leitura da lista de interacoes deste chamado  */
        int nInteracoes = lerInt(arquivo);
        for (int i = 0; i < nInteracoes; i++) {
            String descricao = lerString(arquivo);
            String email = lerString(arquivo);
            Usuario u = s.getUsuario(email);

            try {
                /* lendo do arquivo uma data formatada no padrão (dia/mês/ano hora:minuto:segundo) */
                String data = lerString(arquivo);
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); // Define the desired format
                Date dataFormatada = (Date)formato.parse(data);

                c.adicionarInteracao(descricao, u, dataFormatada);

            } catch (ParseException e) {
                System.out.println("Erro ao ler a data da interação no arquivo.");
            }

        }
        /* Após a criação do objeto com os dados do chamado, ele é adicionado na lista de chamados e de
         * itens salvaveis do sistema. */
        s.inserirChamado(c);

    }

    /* MÉTODOS PARA SALVAR DADOS EM ARQUIVO */

    /**
     * metodo principal para salvar os dados no arquivo texto.
     * Abre o arquivo, faz os tratamentos de erros necessarios e chama os metodos apropriados dependendo do
     * tipo de objeto que estiver sendo escrito (Um cliente, um atendente ou um chamado).
     * A interface salvavel e o polimorfismo permitem que isso seja feito de forma mais elegante.
     * @param s o objeto com os dados do sistema.
     */
    public static void salvarArquivo(Sistema s) {
        FileWriter arquivo = null;
        Formatter f = null;
        try {
            arquivo = new FileWriter("dados.txt");
            f = new Formatter(arquivo);

            for (Cliente c : s.getClientes()) c.salvarEmArquivo(f);
            for (Suporte sup : s.getAtendentes()) sup.salvarEmArquivo(f);
            for (Chamado c : s.getChamados()) c.salvarEmArquivo(f);

            /* indica fim dos dados no arquivo */
            f.format("0\n");

        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo em disco.");
        }
        finally {
            /* Se o arquivo chegou a ser aberto, vamos fecha-lo aqui */
            if  (arquivo != null) {
                try {
                    arquivo.close();
                    f.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar arquivo em disco.");
                }
            }
        }
    }
}
