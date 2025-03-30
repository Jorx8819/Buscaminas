package ilerna.buscaminas;

import java.util.Random;

/**
 * WARNING: Do NOT modify this code.
 */
// <editor-fold defaultstate="collapsed" desc="Generated Code"> 
public class Buscaminas {

    static final int MINAS_FACIL = 10;
    static final int MINAS_MEDIO = 25;
    static final int MINAS_DIFICIL = 40;
    private final int FILAS = 10;
    private final int COLUMNAS = 10;
    private final boolean[][] minas;
    private final int[][] contadorMinas;
    private int numeroMinas = 0;

    /**
     * Constructor de la clase Buscaminas
     *
     * @param nivel el nivel del juego
     */
    public Buscaminas(int nivel) {
        // Número de minas en función del nivel
        if (nivel == Juego.NIVEL_INICIAL) {
            this.numeroMinas = MINAS_FACIL;
        } else if (nivel == Juego.NIVEL_MEDIO) {
            this.numeroMinas = MINAS_MEDIO;
        } else if (nivel == Juego.NIVEL_DIFICIL) {
            this.numeroMinas = MINAS_DIFICIL;
        }

        minas = new boolean[FILAS][COLUMNAS];
        contadorMinas = new int[FILAS][COLUMNAS];
        generarMinas();
        calcularContadorMinas();
    }

    private void generarMinas() {
        Random rand = new Random();
        int minasColocadas = 0;
        while (minasColocadas < this.numeroMinas) {
            int fila = rand.nextInt(FILAS);
            int columna = rand.nextInt(COLUMNAS);
            if (!minas[fila][columna]) {
                minas[fila][columna] = true;
                minasColocadas++;
            }
        }
    }

    private void calcularContadorMinas() {
        // Recorre cada celda para buscar las minas
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                if (minas[fila][columna]) {
                    // Incrementa el contador en las celdas vecinas
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            int nuevaFila = fila + i;
                            int nuevaColumna = columna + j;
                            // Verifica que esté dentro de los límites del tablero
                            if (nuevaFila >= 0 && nuevaFila < FILAS && nuevaColumna >= 0 && nuevaColumna < COLUMNAS) {
                                contadorMinas[nuevaFila][nuevaColumna]++;
                            }
                        }
                    }
                    // Evita contar la mina en sí misma
                    contadorMinas[fila][columna]--;
                }
            }
        }
        // Opcional: Asegura que las celdas sin minas cercanas tengan un valor de 0
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                if (!minas[fila][columna] && contadorMinas[fila][columna] == 0) {
                    contadorMinas[fila][columna] = 0;
                }
            }
        }
    }

    public int[][] getContadorMinas() {
        return contadorMinas;
    }

    public boolean[][] getMinas() {
        return minas;
    }

    public void mostrarTableroConMinas() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (minas[i][j]) {
                    System.out.print("M ");
                } else {
                    System.out.print(contadorMinas[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
// </editor-fold> 