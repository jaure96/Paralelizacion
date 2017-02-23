package ForkJoinExample4;

import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Principal {

	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool(4);
		File f = new File (args[0]);
		
		long size = pool.invoke(new CalculadorTamanoDirectorio(f));
		pool.shutdown();
		System.out.println("El tamaño de "+ f.getAbsolutePath()+" es: "+ size);

	}

}
