package p1multiplicacionmatricesHilosRunnable;

public class Principal {

	static final int DIMENSIONX = 500;
	static final int DIMENSIONY = 500;
	static int matrizA[][];
	static int matrizB[][];
	static long resultado[][];

	static Thread[] threads;

	static MultiplicadorMatrices multiplicador;

	public Principal() {
		threads = new Thread[Runtime.getRuntime().availableProcessors()];
		matrizA = new GeneradorMatriz(DIMENSIONX, DIMENSIONY).getMatriz();
		matrizB = new GeneradorMatriz(DIMENSIONY, DIMENSIONX).getMatriz();
		resultado = new long[matrizA.length][matrizA[0].length];
		multiplicador = new MultiplicadorMatrices(matrizA, matrizB, resultado);
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
		Principal programa = new Principal();
		HiloMultiplicador hiloMultiplicador;
		int indexStart = DIMENSIONY / 80;
		int i = 0;
		for (int j = 0; j < 80; j++) {
			hiloMultiplicador = new HiloMultiplicador(matrizA, matrizB, resultado, multiplicador, indexStart * i, indexStart * (1 + i));
			Thread th = new Thread(hiloMultiplicador);
			threads[i] = th;
			threads[i].start();
		}


		long instanteInicial = System.currentTimeMillis();
		try {
			for (int j = 0; j < 80; j++) {
				threads[i].join();

			}

		} catch (
				InterruptedException e)

		{

		}

		long instanteFinal = System.currentTimeMillis();
		//programa.visualizarMatrices();
		System.out.println("Tiempo utilizado:  " + (instanteFinal - instanteInicial));

	}


}
