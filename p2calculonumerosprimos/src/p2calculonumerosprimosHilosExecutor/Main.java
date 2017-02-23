package p2calculonumerosprimosHilosExecutor;

import java.util.ArrayList;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
	
	int rangoDeBusqueda, bloques, numeroProcesadores;
	ExecutorService executor;
	CompletionService<ArrayList<Integer>> completionService;
	
	public Main(int rangoDeBusqueda){
		executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		completionService = new ExecutorCompletionService<ArrayList<Integer>>(executor);
		this.rangoDeBusqueda = rangoDeBusqueda;
		this.numeroProcesadores = Runtime.getRuntime().availableProcessors();
		this.bloques = rangoDeBusqueda/numeroProcesadores;
	}
		
	public static void main(String[] args){
		Main myMain = new Main(10000000);
		int inicio = 0;
		int fin = myMain.bloques;
		int encontrados = 0;	
		
		try {
			long tiempoInicio = System.currentTimeMillis();
			for (int i = 0; i < myMain.numeroProcesadores; i++) {
				myMain.completionService.submit(new HiloNumerosPrimosFinder(inicio, fin));
				inicio += myMain.bloques;
				fin += myMain.bloques;
			}
			
			for (int i = 0; i < myMain.numeroProcesadores; i++) {
				Future<ArrayList<Integer>> future;
				
				future = myMain.completionService.take();
				encontrados += future.get().size();				
				myMain.executor.shutdown();
			}
			long tiempoFin = System.currentTimeMillis();
			System.out.println("Encontrados: " +encontrados);
			System.out.println("Tiempo empleado: "+(tiempoFin - tiempoInicio));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
