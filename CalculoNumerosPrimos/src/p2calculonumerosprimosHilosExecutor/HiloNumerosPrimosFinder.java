package p2calculonumerosprimosHilosExecutor;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class HiloNumerosPrimosFinder implements Callable<ArrayList<Integer>> {
	
	int inicio, fin;
	
	public HiloNumerosPrimosFinder(int inicio, int fin) {
		this.inicio = inicio;
		this.fin = fin;
	}

	@Override
	public ArrayList<Integer> call() throws Exception {
		ArrayList<Integer> listaDePrimos = new ArrayList<Integer>();
		for (int i = inicio; i<fin; i++){
			if (esPrimo(i)){
				listaDePrimos.add(i);
			}
		}
		return listaDePrimos;
	}
	
	private boolean esPrimo(int valor){
		for (int i = 2; i<(Math.sqrt(valor)+1);i++){
			if (valor%i ==0 ){
				return false;
			}
		}
		return true;
	}

}
