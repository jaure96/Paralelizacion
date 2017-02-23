package ExecutorsExample4;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



public class NumPrimosCompletionService {
	
		final int LIMITE = 100000;
		final int NUMHILOS = 10;
		
		ExecutorService executor;
		CompletionService<Integer> completionService;
		
		
		public NumPrimosCompletionService(){
			executor = Executors.newFixedThreadPool(NUMHILOS);
			completionService = new ExecutorCompletionService<Integer>(executor);
			
		}
		public void hallarNumerosPrimos(){
			for (int i= 2; i<LIMITE; i++){
				completionService.submit(new PrimoTask(i));
				
			}
			
			for (int i=2; i<LIMITE; i++){
				Integer valor;
				Future<Integer> future;
				try {
					future = completionService.take();
					if ((valor = future.get())!=null){
						System.out.println(valor +" es primo");
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
		}
		
		public static void main(String[] args) {
			
			NumPrimosCompletionService ejercicio = new NumPrimosCompletionService();
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
