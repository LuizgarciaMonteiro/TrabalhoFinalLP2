package Persistencia;

import java.util.Formatter;

/**
 * Interface que define as funcionalidades padrões de todas as classes que serão salvas em arqruivo.
 */
public interface Salvavel {
    public void salvarEmArquivo(Formatter f);
}
