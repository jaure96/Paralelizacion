package p4calculocaminomascortoExecutors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ListaRutas {
	List<Ruta> rutas;
	Integer minima = 0;
	ExecutorService executorService = Executors.newSingleThreadExecutor();
	
	public ListaRutas(){
		rutas = new ArrayList<>();
	}
	public void add(Ruta ruta){
		rutas.add(0,ruta);
	}
	public boolean vacia() {
		
		return rutas.size() == 0;
	}
	public Ruta take() {
		
		synchronized (minima) {
            return rutas.remove(minima.intValue());
        }
	}

	public void addRutas(Ruta ruta, List<Nodo> adyacentes) {
		executorService.submit(new AddRutasRunnable(adyacentes, ruta));
		
	}
	private boolean explorado(Nodo nodo) {
		for (Ruta ruta : rutas){
			if (ruta.contiene(nodo)){
				return true;
			}
		}
		return false;
	}
	
	
	private class AddRutasRunnable implements Runnable {

	    List<Nodo> adyacentes;
	    Ruta ruta;

	    public AddRutasRunnable(List<Nodo> adyacentes, Ruta ruta) {
	        this.adyacentes = adyacentes;
	        this.ruta = ruta;
	    }

	    @Override
	    public void run() {
	        synchronized (minima) {
	            int added = 0;
	            for (Nodo nodo : adyacentes) {
	                if (!explorado(nodo)) {
	                    Ruta newRuta = new Ruta(ruta);
	                    newRuta.add(nodo);
	                    rutas.add(newRuta);
	                    added++;
	                }
	            }
	            if (true) {
	                int minValor = Integer.MAX_VALUE;
	                for (int i = 0; i < rutas.size(); i++) {
	                    int valor = (rutas.get(i).getDistancia() + rutas.get(i).getRecorrido());
	                    if (minValor > valor) {
	                        minValor = valor;
	                        minima = i;
	                    }

	                }
	            }
	        }

	    }
	}


	public void acabar() {
		executorService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
	}
}
