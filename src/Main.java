import Atores.Sistema;
import EntradaSaida.Entrada;
import Persistencia.LeituraEscritaArquivos;

class Main {
    public static void main(String[] args) {

        Sistema s = new Sistema();
        LeituraEscritaArquivos.lerArquivo(s);

        Entrada e = new Entrada();

        System.out.println("\n********************[ BEM VINDO ]********************\n");

        System.out.println(s);

        int opcao = e.menuPrincipal();

        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    e.cadastrarCliente(s);
                    break;
                case 2:
                    e.cadastrarSuporte(s);
                    break;
                case 3:
                    e.loginCliente(s);
                    break;
                case 4:
                    e.loginSuporte(s);
                    break;

                default:
                    System.out.println("\nOpção invalida, tente novamente");
                    break;
            }

            opcao = e.menuPrincipal();
        }
        e.encerrarSistema();
        LeituraEscritaArquivos.salvarArquivo(s);

    }
}