package Persistencia;

import Atores.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;

/* LEITURA ESCRITA DOS DADOS EM AQUIVO TEXTO */

public class LeituraEscritaArquivos {

    public static String lerString(Scanner arquivo) {
        String x = arquivo.nextLine();
        return x;
    }

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
            if (f != null) {
                try {
                    f.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar arquivo em disco.");
                }
            }
        }
    }

    public static void lerDadosClienteArq(Sistema s, Scanner arquivo) {
        String email = lerString(arquivo);
        String nome = lerString(arquivo);
        String cpf = lerString(arquivo);
        String senha = lerString(arquivo);

        Cliente c = new Cliente(nome, cpf, email, senha);
        s.inserirCliente(c);
    }

    public static void lerDadosSuporteArq(Sistema s, Scanner arquivo) {
        String email = lerString(arquivo);
        String nome = lerString(arquivo);
        String cpf = lerString(arquivo);
        String senha = lerString(arquivo);

        Suporte sup = new Suporte(nome, cpf, email, senha);
        s.inserirSuporte(sup);
    }

    public static void lerDadosChamadoArq(Sistema s, Scanner arquivo) {
        int numero = lerInt(arquivo);
        String titulo = lerString(arquivo);
        int status = lerInt(arquivo);

        String emailSolicitante = lerString(arquivo);
        String emailResponsavel = lerString(arquivo);

        Cliente cli = s.getCliente(emailSolicitante);
        Suporte sup = s.getSuporte(emailResponsavel);

        Chamado c = new Chamado(numero, titulo, status, cli);

        cli.inserirChamado(c);
        if (sup != null) {
            c.adicionarResponsavel(sup);
            sup.inserirChamado(c);
        }

        int nInteracoes = lerInt(arquivo);
        for (int i = 0; i < nInteracoes; i++) {
            String descricao = lerString(arquivo);
            String email = lerString(arquivo);
            Usuario u = s.getUsuario(email);

            try {
                String data = lerString(arquivo);
                DateFormat dataFormatada = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");

                if (cli != null) {

                }
                c.adicionarInteracao(descricao, u, (Date)dataFormatada.parse(data));

            } catch (ParseException e) {
                System.out.println("Erro ao ler a data da interação no arquivo.");
            }

        }

        s.inserirChamado(c);

    }

    /* MÉTODOS PARA SALVAR DADOS EM ARQUIVO */

    public static void salvarArquivo(Sistema s) {
        FileWriter arquivo = null;
        Formatter f = null;
        try {
            arquivo = new FileWriter("dados.txt");
            f = new Formatter(arquivo);

            for (Salvavel sal : s.getSalvaveis()) { // Polimorfismo (cada objeto por ser um usuario ou chamado
                sal.salvarEmArquivo(f);
            }

            f.format("0\n"); // indica fim dos dados no arquivo

        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo em disco.");
        }
        finally {
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
