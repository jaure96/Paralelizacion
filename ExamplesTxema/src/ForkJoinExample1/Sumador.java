package ForkJoinExample1;

import java.util.concurrent.RecursiveTask;

public class Sumador extends RecursiveTask<Integer> {
	
	private int[] numeros;
	private int indiceInicio;
	private int indiceFin;
	
	public Sumador(int[] numbers, int startIndex, int endIndex) {
		this.numeros = numbers;
		this.indiceInicio = startIndex;
		this.indiceFin = endIndex;
	}
	@Override
	protected Integer compute() {
		int tamano = (indiceFin - indiceInicio) + 1;
		if (tamano > 2) {
			Sumador trozoInferior = new Sumador(numeros, indiceInicio, indiceInicio + (tamano / 2) - 1);
			trozoInferior.fork();
			Sumador trozoSuperior = new Sumador(numeros, indiceInicio + (tamano / 2), indiceFin);
			trozoSuperior.fork();
			return trozoInferior.join()+trozoSuperior.join();
		} else {
			return numeros[indiceInicio]+ numeros[indiceFin];
		}
	}
	

}
