package ilerna.buscaminas;

import ilerna.logminas.Log;

/**
 * WARNING: Do NOT modify this code.
 */
// <editor-fold defaultstate="collapsed" desc="Generated Code"> 
public class Juego {

    static final int NIVEL_INICIAL = 1; // 10 minas
    static final int NIVEL_MEDIO = 2; // 25 minas
    static final int NIVEL_DIFICIL = 3; // 40 minas

    private int puntuacion;
    private final int nivel;
    private String estadoJuego;
    private final Buscaminas buscaminas;
    private final Tablero tablero;

    public Juego(int nivel, Tablero tablero) {
        this.puntuacion = 0;
        this.nivel = nivel;
        this.tablero = tablero;
        this.buscaminas = tablero.getBuscaminas();
        this.estadoJuego = "Inicio";
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public int getNivel() {
        return nivel;
    }

    public String getEstadoJuego() {
        return estadoJuego;
    }

    /**
     * Actualiza la puntuación en la interfaz
     *
     * @param puntos
     */
    public void incrementarPuntuacion(int puntos) {
        puntuacion += puntos;
        tablero.actualizarPuntuacion();  // Actualiza la puntuación en la interfaz
    }

    /**
     * Descubre la celda que indicamos
     *
     * @param fila número de fila
     * @param columna número de columna
     */
    public void descubrirCelda(int fila, int columna) {
        // Verifica si la celda contiene una mina
        if (buscaminas.getMinas()[fila][columna]) {
            derrotaJuego();  // Termina el juego si el jugador descubre una mina
            tablero.mostrarMina(fila, columna);
            this.estadoJuego = "Derrota";
        } else {
            int minasAlrededor = buscaminas.getContadorMinas()[fila][columna];
            tablero.mostrarNumero(fila, columna, minasAlrededor);
            if (minasAlrededor == 0) {
                // Descubre automáticamente celdas adyacentes si no hay minas cercanas
                tablero.descubrirAdyacentes(fila, columna);
            }
            incrementarPuntuacion(10);  // Aumenta la puntuación
            this.estadoJuego = "En partida";
            verificarVictoria();  // Comprueba si se han descubierto todas las celdas seguras
        }
    }

    /**
     * Verifica si se ganó la partida
     */
    private void verificarVictoria() {
        if (tablero.todasLasCeldasDescubiertas()) {
            tablero.mostrarMensajeVictoria(); // Llama al método en Tablero para mostrar el mensaje de victoria
            this.estadoJuego = "Victoria";
        }
    }

    private void derrotaJuego() {
        tablero.mostrarMensajeDerrota();
    }

    public String generarLog(String nombre) {
        String nivelTexto = "Fácil";
        if (this.nivel == NIVEL_MEDIO) {
            nivelTexto = "Medio";
        } else if (this.nivel == NIVEL_DIFICIL) {
            nivelTexto = "Difícil";
        }
        // Creamos la clase Log
        Log log = new Log(nombre, nivelTexto, puntuacion, tablero.getEstadoTablero(), estadoJuego);

        return log.toString();
    }
    
    public String encrypt(String nombre, String texto) throws Exception{
        Log log = new Log(nombre, "Fácil", puntuacion, tablero.getEstadoTablero(), estadoJuego);
        return log.encriptarLog();
    }
}
// </editor-fold> 
