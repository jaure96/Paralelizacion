package ExecutorsExample2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NumPrimosExecutor {
	final int LIMITE = 100000;
	final int NUMHILOS = 4;
	
	ExecutorService executor;
	
	public NumPrimosExecutor(){
		
		//executor = Executors.newFixedThreadPool(NUMHILOS);
		executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	}
	public void hallarNumerosPrimos() throws InterruptedException{
		for (int i= 2; i<LIMITE; i++){
			executor.execute(new PrimoTask(i));
		}
		executor.shutdown();
		executor.awaitTermination(30, TimeUnit.SECONDS);
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		NumPrimosExecutor ejercicio = new NumPrimosExecutor();
		long inicio = System.currentTimeMillis();
		ejercicio.hallarNumerosPrimos();
		
		long fin = System.currentTimeMillis();
		System.out.println (" ha tardado: "+(fin -inicio)+" milisegundos");
	}
	
	private class PrimoTask implements Runnable{
		int valor;
		public PrimoTask(int valor){
			this.valor = valor;
		}
		@Override
		public void run() {
			if (esPrimo(valor)){
				System.out.println(valor + "es primo");
			}
			
		}
		private boolean esPrimo(int valor) {
			for (int i= 2; i<valor/2; i++){
				if (valor%i ==0) return false;
			}
			return true;
		}
	}

}
