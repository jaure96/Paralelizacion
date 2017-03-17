package p6difusioncalomallaCompletionservice;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.*;

public class DifusorCalor extends Observable {

    private static final int TAMAÑO_FILA = 10;
    final public static int NUMHILOS = 6;

    List<Malla> secuencia;
    double coeficienteDifusionX;
    double coeficienteDifusionY;
    Malla mallaInicial, nuevaMalla;
    List<PuntoCalor> focosCalor;
    CompletionService<Malla> completionService;
    ExecutorService executor;

    public DifusorCalor(double cfX, double cfY, Malla malla, List<PuntoCalor> focosCalor) {
        secuencia = new ArrayList<>();
        this.coeficienteDifusionX = cfX;
        this.coeficienteDifusionY = cfY;
        this.mallaInicial = malla;
        this.focosCalor = focosCalor;
        for (PuntoCalor punto : this.focosCalor) {
            mallaInicial.setValor(punto.getX(), punto.getY(), punto.getTemperatura());
        }
        executor = Executors.newFixedThreadPool(NUMHILOS);
        completionService = new ExecutorCompletionService<>(executor);
        secuencia.add(mallaInicial);
    }


    public void difundir() {
        int dimensionX = mallaInicial.getDimensionX();
        int dimensionY = mallaInicial.getDimensionY();

        nuevaMalla = new Malla(dimensionX, dimensionY);
        int numThreads = dimensionY / TAMAÑO_FILA;

        inicializarBordes(nuevaMalla);

        for (int i = 0; i < numThreads; i++) {
            if (i == 0) {
                completionService.submit(new HiloDifusor((TAMAÑO_FILA * i) + 1, TAMAÑO_FILA * (i + 1), nuevaMalla, mallaInicial, coeficienteDifusionX, coeficienteDifusionY, focosCalor));
            } else if (i < numThreads - 1) {
                completionService.submit(new HiloDifusor(TAMAÑO_FILA * i, TAMAÑO_FILA * (i + 1), nuevaMalla, mallaInicial, coeficienteDifusionX, coeficienteDifusionY, focosCalor));
            } else {
                completionService.submit(new HiloDifusor(TAMAÑO_FILA * i, TAMAÑO_FILA * (i + 1) - 1, nuevaMalla, mallaInicial, coeficienteDifusionX, coeficienteDifusionY, focosCalor));
            }
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                Malla malla = completionService.take().get();
                this.nuevaMalla = malla;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }


    }

    private void inicializarBordes(Malla malla) {
        int dimensionX = malla.getDimensionX();
        int dimensionY = malla.getDimensionY();
        for (int i = 0; i < dimensionX; i++) {
            malla.setValor(i, 0, 10);
            malla.setValor(i, dimensionY - 1, 10);
        }
        for (int i = 0; i < dimensionY; i++) {
            malla.setValor(0, i, 10);
            malla.setValor(dimensionX - 1, i, 10);
        }

    }

    public Malla getNuevaMalla() {

        return nuevaMalla;
    }

    public void cambiar() {
        this.setChanged();
        this.notifyObservers(nuevaMalla);
        mallaInicial = nuevaMalla;

    }

    public Malla getMallaInicial() {

        return mallaInicial;
    }

    public ExecutorService getExecutor() {
        return executor;
    }


    public void setExecutor(ExecutorService executor) {
        this.executor = executor;
    }


}
