package p1multiplicacionMatricesExecutor;

public class MultiplicadorMatrices implements Runnable {

	final int[][] matrizA;
	final int[][] matrizB;
	int desde, hasta;
	long resultado[][];

	public MultiplicadorMatrices(int[][] matrizA, int[][] matrizB, long resultado[][], int desde, int hasta) {
		this.matrizA = matrizA;
		this.matrizB = matrizB;
		this.resultado = resultado;
		this.desde = desde;
		this.hasta = hasta;
	}

	public void multiplicar() {
		int dimensionY = matrizB[0].length;
		for (int i = desde; i < hasta; i++) {
			for (int j = 0; j < dimensionY; j++) {
				int dimensionZ = matrizB.length;
				int suma = 0;
				for (int k = 0; k < dimensionZ; k++) {
					suma += (matrizA[i][k] * matrizB[k][j]);
				}
				resultado[i][j] = suma;
			}
		}
	}

	@Override
	public void run() {
		this.multiplicar();
	}
}
