import Atores.Sistema;
import IO.Entrada;

class Main {
    public static void main(String[] args) {

        Entrada e = new Entrada();
        Sistema s = new Sistema();

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
                    System.out.println("Opção invalida, tente novamente");
                    break;
            }
            opcao = e.menuPrincipal();
        }
        System.out.println("Até breve.");
    }
}