package p1multiplicacionMatricesExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Principal {

	static final int DIMENSIONX = 3000;
	static final int DIMENSIONY = 3000;
	static int matrizA[][];
	static int matrizB[][];
	static long resultado[][];
	static ExecutorService pool;

	MultiplicadorMatrices multiplicador;

	public Principal() {
		matrizA = new GeneradorMatriz(DIMENSIONX, DIMENSIONY).getMatriz();
		matrizB = new GeneradorMatriz(DIMENSIONY, DIMENSIONX).getMatriz();
	}

	private void visualizarMatriz(int[][] matriz, String texto) {
		System.out.println(texto);
		int dimX = matriz.length;
		int dimY = matriz[0].length;
		for (int i = 0; i < dimX; i++) {
			for (int j = 0; j < dimY; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
	}

	private void visualizarMatrizLong(long[][] matriz, String texto) {
		System.out.println(texto);
		int dimX = matriz.length;
		int dimY = matriz[0].length;
		for (int i = 0; i < dimX; i++) {
			for (int j = 0; j < dimY; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void visualizarMatrices() {
		visualizarMatriz(matrizA, "Matriz A: ");
		visualizarMatriz(matrizB, "Matriz : ");
		visualizarMatrizLong(resultado, "Resultado: ");
	}

	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Principal programa = new Principal();
		long instanteInicial = System.currentTimeMillis();
		int numProcesadores = Runtime.getRuntime().availableProcessors();
		pool = Executors.newFixedThreadPool(numProcesadores);
		resultado = new long[DIMENSIONX][DIMENSIONY];
		
		for (int i = 0; i < numProcesadores; i++) {
			if (i == numProcesadores - 1){
				MultiplicadorMatrices m = new MultiplicadorMatrices(matrizA, matrizB, resultado,
						(DIMENSIONX / numProcesadores) * i, DIMENSIONX);
				pool.execute(m);
			}else{
				MultiplicadorMatrices m = new MultiplicadorMatrices(matrizA, matrizB, resultado,
						(DIMENSIONX / numProcesadores) * i, (DIMENSIONX / numProcesadores) * (i + 1));
				pool.execute(m);
			}
		}

		pool.shutdown();

		try {
			pool.awaitTermination(5, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (pool.isTerminated()) {
			long instanteFinal = System.currentTimeMillis();
			System.out.println("Tiempo utilizado:  " + (instanteFinal - instanteInicial));
			//programa.visualizarMatrices();
		}
	}

}
