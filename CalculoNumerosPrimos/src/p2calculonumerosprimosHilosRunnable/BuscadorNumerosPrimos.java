package p2calculonumerosprimosHilosRunnable;

import java.util.ArrayList;
import java.util.List;

public class BuscadorNumerosPrimos {

	List<Integer> numerosPrimos;
	final static int NUMBERS = 100000000;

	public BuscadorNumerosPrimos() {
		numerosPrimos = new ArrayList<>();
	}

	public ArrayList<Integer> buscarNumerosPrimos(int limiteMin, int limiteMax) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = limiteMin; i < limiteMax; i++) {
			if (esPrimo(i)) {
				list.add(i);
			}
		}
		return list;

	}

	private boolean esPrimo(int valor) {
		for (int i = 2; i < (Math.sqrt(valor) + 1); i++) {
			if (valor % i == 0) {
				return false;
			}
		}
		return true;
	}

	public void mostrarResultados(boolean all) {
		if (all) {
			for (int i = 0; i < numerosPrimos.size(); i++) {
				if (i % 20 == 0) {
					System.out.println();
				} else {
					System.out.print(numerosPrimos.get(i) + " ");
				}
			}
		}
		System.out.println();
		System.out.println("Numeros primos encontrados: " + numerosPrimos.size());
	}

	public List<Integer> getNumerosPrimos() {
		return numerosPrimos;
	}

	public void setNumerosPrimos(List<Integer> numerosPrimos) {
		this.numerosPrimos = numerosPrimos;
	}

	public static void main(String[] args) {

		Integer processors = 40;
		BuscadorNumerosPrimos programa = new BuscadorNumerosPrimos();
		Thread[] threads = new Thread[processors];
		for (int i = 0; i < processors; i++) {
			threads[i] = new Thread(new HiloPrimos(i * NUMBERS / processors, (i + 1) * NUMBERS / processors, programa, i));
			threads[i].start();
		}

		long inicio = System.currentTimeMillis();
		try {
			for (int i = 0; i < processors; i++) {
				threads[i].join();
			}

		} catch (InterruptedException e) {

		}

		long fin = System.currentTimeMillis();

		programa.mostrarResultados(false);
		System.out.println("Tiempo empleado: " + (fin - inicio));
	}

}
