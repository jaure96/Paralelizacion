package ForkJoinExample1;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Principal {
	final int DIMENSION = 1000;
	final int LIMITE = 100;
	int numeros[];
	Random random;
	
	public Principal(){
		numeros = new int [DIMENSION];
		random = new Random();
	}
	public void llenar(){
		for (int i = 0; i<DIMENSION; i++){
			numeros[i] = random.nextInt(LIMITE);
		}
	}
	public void obtenerSuma(){
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		Integer suma = pool.invoke(new Sumador(numeros, 0, numeros.length - 1));
		System.out.println(suma);
	}
	public static void main(String[] args) {
		Principal ejercicio = new Principal();
		ejercicio.llenar();
		ejercicio.obtenerSuma();
		
	}

}
