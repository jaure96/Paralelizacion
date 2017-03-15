
package p4calculocaminomascortohilosCompletionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class BuscadorCaminoMasCorto {

    ListaRutas listaRutas;
    Espacio espacio;
    Punto origen, destino;
    Ruta rutaMasCorta;

    ExecutorService executor;
    CompletionService< Map<Ruta, List<Nodo>>> completionService;

    public BuscadorCaminoMasCorto(Espacio espacio) {
        listaRutas = new ListaRutas();
        this.espacio = espacio;
        this.origen = espacio.getOrigen();
        this.destino = espacio.getDestino();

        executor = Executors.newFixedThreadPool(4);
        completionService = new ExecutorCompletionService<>(executor);

        listaRutas.add(new Ruta(origen, this.manhattan(origen, destino), 0));
    }

    private int manhattan(Punto punto1, Punto punto2) {

        return Math.abs(punto1.getCoordenadaX() - punto2.getCoordenadaX()) + Math.abs(punto1.getCoordenadaY() - punto2.getCoordenadaY());
    }

    public Ruta buscarCaminoMasCorto() {
        Ruta ruta = null;
        int kont;

        idLoop:
        while (!listaRutas.vacia()) {
            for (kont = 0; kont < listaRutas.getRutas().size(); kont++) {
                ruta = listaRutas.take();
                Nodo nodo = ruta.getLastNodo();

                if (nodo.getPunto().equals(destino)) {
                    rutaMasCorta = ruta;
                    break idLoop;
                }
                completionService.submit(new BuscadorAdyascentes(ruta, nodo, Espacio.DIMENSION, espacio.getEspacio()));
            }

//            List<Nodo> adyacentes = new ArrayList<>();

            for (int i = 0; i < kont; i++) {
                Map<Ruta, List<Nodo>> map;
                Future<Map<Ruta, List<Nodo>>> future;
                try {
                    future = completionService.take();
                    if ((map = future.get()) != null) {
                        map.forEach((rut,nodos)->{
                            for(Nodo n: nodos){
                                n.setDistancia(this.manhattan(n.getPunto(), destino));
                            }
                        });
                        map.forEach((rut,nodos)->{
                            listaRutas.addRutas(rut, nodos);
                        });

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

//            for (Nodo n : adyacentes) {
//                n.setDistancia(this.manhattan(n.getPunto(), destino));
//            }
//            listaRutas.addRutas(ruta, adyacentes);

//            Ruta r = new Ruta();
//            for (Nodo n: adyacentes){
//                r.add(n);
//            }
//            listaRutas.add(r);
        }
        executor.shutdown();
        return rutaMasCorta;
    }

}
