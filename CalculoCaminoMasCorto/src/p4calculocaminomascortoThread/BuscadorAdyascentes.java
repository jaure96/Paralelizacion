package p4calculocaminomascortoThread;

import java.util.List;

/**
 * Created by Lazkano on 11/03/2017.
 */
public class BuscadorAdyascentes implements Runnable {

    int dimension;
    Nodo nodo;
    List<Nodo> adyacentes;
    int[][] espacio;

    public BuscadorAdyascentes(Nodo nodo, int dimension, List<Nodo> adyacentes, int[][] espacio) {
        this.nodo = nodo;
        this.dimension = dimension;
        this.adyacentes = adyacentes;
        this.espacio = espacio;
    }

    @Override
    public void run() {
        Punto punto = nodo.getPunto();
        int coordenadaX = punto.getCoordenadaX();
        int coordenadaY = punto.getCoordenadaY();
        int limiteInferiorX = (coordenadaX == 0) ? 0 : coordenadaX - 1;
        int limiteSuperiorX = (coordenadaX == dimension - 1) ? dimension - 1 : coordenadaX + 1;
        int limiteInferiorY = (coordenadaY == 0) ? 0 : coordenadaY - 1;
        int limiteSuperiorY = (coordenadaY == dimension - 1) ? dimension - 1 : coordenadaY + 1;

        for (int i = limiteInferiorX; i <= limiteSuperiorX; i++) {
            for (int j = limiteInferiorY; j <= limiteSuperiorY; j++) {
                if (espacio[i][j] != 1) {
                    Punto newPunto = new Punto(i, j);
                    if (!punto.equals(newPunto)) {
                        this.adyacentes.add(new Nodo(newPunto, 0, nodo.getRecorrido() + 1));
                    }
                }
            }
        }

    }
}
