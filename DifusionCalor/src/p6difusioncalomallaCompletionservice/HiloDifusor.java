package p6difusioncalomallaCompletionservice;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Lazkano on 16/03/2017.
 */


public class HiloDifusor implements Callable<Malla> {

    int inicioFila;
    int finalFila;
    Malla nuevaMalla, mallaInicial;
    double coeficienteDifusionX, coeficienteDifusionY;
    List<PuntoCalor> focosCalor;

    public HiloDifusor(int inicioFila, int finalFila, Malla nuevaMalla, Malla mallaInicial, double coeficienteDifusionX, double coeficienteDifusionY, List<PuntoCalor> focosCalor) {
        this.inicioFila = inicioFila;
        this.finalFila = finalFila;
        this.nuevaMalla = nuevaMalla;
        this.mallaInicial = mallaInicial;
        this.coeficienteDifusionX = coeficienteDifusionX;
        this.coeficienteDifusionY = coeficienteDifusionY;
        this.focosCalor = focosCalor;
    }


    @Override
    public Malla call() throws Exception {
        return difundir();
    }

    public Malla difundir() {
        for (int i = 1; i < mallaInicial.getDimensionX() - 1; i++) {
            for (int j = inicioFila; j < finalFila; j++) {
                PuntoCalor punto = new PuntoCalor(i, j, 0);
                if (esFocoCalor(punto)) {
                    nuevaMalla.setValor(i, j, punto.getTemperatura());
                } else {
                    double temperatura = mallaInicial.getValor(i, j) +
                            coeficienteDifusionX * ((mallaInicial.getValor(i - 1, j) + mallaInicial.getValor(i + 1, j)) - (2 * mallaInicial.getValor(i, j))) +
                            coeficienteDifusionY * ((mallaInicial.getValor(i, j - 1) + mallaInicial.getValor(i, j + 1)) - (2 * mallaInicial.getValor(i, j)));

                    nuevaMalla.setValor(i, j, temperatura);
                }
            }
        }
        return nuevaMalla;
    }

    private boolean esFocoCalor(PuntoCalor punto) {
        for (PuntoCalor puntoCalor : focosCalor) {
            if (puntoCalor.getX() == punto.getX() && puntoCalor.getY() == punto.getY()) {
                punto.setTemperatura(puntoCalor.getTemperatura());
                return true;
            }

        }
        return false;
    }
}
