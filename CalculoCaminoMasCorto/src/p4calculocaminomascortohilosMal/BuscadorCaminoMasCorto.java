package p4calculocaminomascortohilosMal;

import java.util.ArrayList;
import java.util.List;

public class BuscadorCaminoMasCorto {

    ListaRutas listaRutas;
    Espacio espacio;
    Punto origen, destino;
    Ruta rutaMasCorta;

    public BuscadorCaminoMasCorto(Espacio espacio) {
        listaRutas = new ListaRutas();
        this.espacio = espacio;
        this.origen = espacio.getOrigen();
        this.destino = espacio.getDestino();

        listaRutas.add(new Ruta(origen, this.manhattan(origen, destino), 0));
    }

    private int manhattan(Punto punto1, Punto punto2) {

        return Math.abs(punto1.getCoordenadaX() - punto2.getCoordenadaX()) + Math.abs(punto1.getCoordenadaY() - punto2.getCoordenadaY());
    }

    public Ruta buscarCaminoMasCorto() {
        while (!listaRutas.vacia()) {
            Ruta ruta = listaRutas.take();

//            System.out.println("Tamaño lista rutas: "+listaRutas.rutas.size());

            Nodo nodo = ruta.getLastNodo();
            if (nodo.getPunto().equals(destino)) {
                rutaMasCorta = ruta;
                break;
            }
//			List<Nodo> adyacentes = espacio.getNodosAdyacentes (nodo);
            List<Nodo> adyacentes = new ArrayList<>();
            Thread thread = new Thread(new BuscadorAdyascentes(nodo, espacio.DIMENSION, adyacentes, espacio.getEspacio()));
            thread.start();
            try{
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            for (Nodo n : adyacentes) {
                n.setDistancia(this.manhattan(n.getPunto(), destino));
            }
            listaRutas.addRutas(ruta, adyacentes);
        }

        return rutaMasCorta;
    }

}