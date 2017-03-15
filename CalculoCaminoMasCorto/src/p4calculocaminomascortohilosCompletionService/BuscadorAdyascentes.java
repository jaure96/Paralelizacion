package p4calculocaminomascortohilosCompletionService;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by Lazkano on 11/03/2017.
 */
public class BuscadorAdyascentes implements Callable<Map<Ruta, List<Nodo>>> {

    int dimension;
    Nodo nodo;
    int[][] espacio;
    Ruta ruta;

    public BuscadorAdyascentes(Ruta ruta, Nodo nodo, int dimension, int[][] espacio) {
        this.nodo = nodo;
        this.dimension = dimension;
        this.espacio = espacio;
        this.ruta = ruta;
    }

    @Override
    public Map<Ruta, List<Nodo>> call() {
        List<Nodo> adyascentes = new ArrayList<>();
        Map<Ruta, List<Nodo>> map = new HashMap<>();

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
                        adyascentes.add(new Nodo(newPunto, 0, nodo.getRecorrido() + 1));
                    }
                }
            }
        }
        map.put(ruta, adyascentes);
        return map;
    }
}
