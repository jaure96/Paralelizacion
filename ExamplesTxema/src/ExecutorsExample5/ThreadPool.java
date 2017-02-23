package ExecutorsExample5;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPool {
	final int NUMHILOS = 8;
	CompletionService<List<File>> pool;
	ExecutorService executor;
	int numTareas;
	ModeloLista modelo;
	
	public ThreadPool (ModeloLista modelo){
		executor = Executors.newFixedThreadPool(NUMHILOS);
		pool = new ExecutorCompletionService<>(executor);
		numTareas = 0;
		this.modelo = modelo;
	}
	public void submit(Callable<List<File>> tarea){
		pool.submit(tarea);
		synchronized(this){
			numTareas++;
		}
	}
	public void getFiles(){
		ActualizadorFicheros hilo = new ActualizadorFicheros();
		hilo.start();
	}
	public void shutdown(){
		executor.shutdown();
	}
	public class ActualizadorFicheros extends Thread{

		@Override
		public void run() {
		
			for (int i  = 0; i<numTareas; i++){
				try {
					System.out.println("recogiendo ficheros");
					Future<List<File>> future = pool.take();
					
					List<File> lista = future.get();
					if (lista.size()>0){
						modelo.add(lista);
						System.out.println("he recogido ficheros");
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			ThreadPool.this.shutdown();
		}
		
	}
}
