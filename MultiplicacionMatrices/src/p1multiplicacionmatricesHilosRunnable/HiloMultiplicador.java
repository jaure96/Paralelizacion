package p1multiplicacionmatricesHilosRunnable;


public class HiloMultiplicador implements Runnable {

	private int[][] matrizA;
	private int[][] matrizB;
	private long[][] resultado;
	private int indexStart;
	private int indexEnd;
	private MultiplicadorMatrices multiplicadorMatrices;

	public HiloMultiplicador(int[][] matrizA, int[][] matrizB, long[][] resultado,
							 MultiplicadorMatrices multiplicadorMatrices, int indexStart, int indexEnd) {
		this.matrizA = matrizA;
		this.matrizB = matrizB;
		this.resultado = resultado;
		this.indexStart = indexStart;
		this.indexEnd = indexEnd;
		this.multiplicadorMatrices = multiplicadorMatrices;

	}

	@Override
	public void run() {
		multiplicadorMatrices.multiplicar(indexStart, indexEnd);

	}
}
