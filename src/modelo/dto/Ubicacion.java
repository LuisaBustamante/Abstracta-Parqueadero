package modelo.dto;
public class Ubicacion {
    private int Columna;
    private int Fila;
    public Ubicacion(int Columna, int Fila) {
        this.Columna = Columna;
        this.Fila = Fila;
    }
    /**
     * @return the Columna
     */
    public int getColumna() {
        return Columna;
    }
    /**
     * @param Columna the Columna to set
     */
    public void setColumna(int Columna) {
        this.Columna = Columna;
    }
    /**
     * @return the Fila
     */
    public int getFila() {
        return Fila;
    }
    /**
     * @param Fila the Fila to set
     */
    public void setFila(int Fila) {
        this.Fila = Fila;
    }
}
