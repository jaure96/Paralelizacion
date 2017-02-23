package p1multiplicacionmatricesHilosRunnable;

public class MultiplicadorMatrices {

	private final int[][] matrizA;
	private final int[][] matrizB;
	private long[][] resultado;


	public MultiplicadorMatrices(int[][] matrizA, int[][] matrizB, long[][] resultado) {
		this.matrizA = matrizA;
		this.matrizB = matrizB;
		this.resultado = resultado;
	}

	public void multiplicar(int indexStart, int indexEnd) {


		for (int i = 0; i < this.resultado.length; i++) {
			for (int j = indexStart; j < indexEnd; j++) {
				int dimensionZ = matrizB.length;
				int suma = 0;
				for (int k = 0; k < dimensionZ; k++) {
					suma += (matrizA[i][k] * matrizB[k][j]);
				}
				resultado[i][j] = suma;
			}
		}

	}
}
