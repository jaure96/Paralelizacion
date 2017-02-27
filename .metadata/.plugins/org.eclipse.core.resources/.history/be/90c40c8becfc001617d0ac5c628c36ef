package p2calculonumerosprimosHilosRunnable;

import java.util.ArrayList;

/**
 * Created by joanes on 2/15/17.
 */
public class HiloPrimos implements Runnable {

	private Integer indexStart;
	private Integer indexEnd;
	private BuscadorNumerosPrimos buscadorNumerosPrimos;
	private ArrayList<Integer> integers;
	private Integer id;

	public HiloPrimos(Integer indexStart, Integer indexEnd, BuscadorNumerosPrimos buscadorNumerosPrimos, Integer id) {
		this.indexStart = indexStart;
		this.indexEnd = indexEnd;
		this.buscadorNumerosPrimos = buscadorNumerosPrimos;
		this.id = id;
	}

	@Override
	public void run() {
		integers = buscadorNumerosPrimos.buscarNumerosPrimos(indexStart, indexEnd);
		buscadorNumerosPrimos.getNumerosPrimos().addAll(integers);

	}
}
