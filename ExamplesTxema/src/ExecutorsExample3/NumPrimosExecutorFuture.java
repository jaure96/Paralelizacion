package ExecutorsExample3;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class NumPrimosExecutorFuture {
	final int LIMITE = 100000;
	final int NUMHILOS = 10;
	
	ExecutorService executor;
	ArrayList<Future<Integer>> lista;
	
	public NumPrimosExecutorFuture(){
		executor = Executors.newFixedThreadPool(NUMHILOS);
		lista = new ArrayList<Future<Integer>>();
	}
	public void hallarNumerosPrimos(){
		for (int i= 2; i<LIMITE; i++){
			Future<Integer> future = executor.submit(new PrimoTask(i));
			lista.add(future);
		}
		
		for (Future<Integer> item: lista){
			Integer valor;
			try {
				valor = item.get();
				if (valor!=null){
					System.out.println(valor+ " es primo");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		executor.shutdown();
		try {
			executor.awaitTermination(3,TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		NumPrimosExecutorFuture ejercicio = new NumPrimosExecutorFuture();
		long inicio = System.currentTimeMillis();
		ejercicio.hallarNumerosPrimos();
		long fin = System.currentTimeMillis();
		System.out.println (" ha tardado: "+(fin -inicio)+" milisegundos");
	}
	
	private class PrimoTask implements Callable<Integer>{
		int valor;
		public PrimoTask(int valor){
			this.valor = valor;
		}
		
		private boolean esPrimo(int valor) {
			for (int i= 2; i<valor/2; i++){
				if (valor%i ==0) return false;
			}
			return true;
		}
		@Override
		public Integer call() throws Exception {
			if (esPrimo(valor)){
				return valor;
			}
			return null;
		}
	}

}
