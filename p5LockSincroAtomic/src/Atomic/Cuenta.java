package Atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Cuenta {
	
	AtomicInteger valor;
	
	public Cuenta() {
		valor = new AtomicInteger(0);
	}
	
	public synchronized void sumarValor(){
		try {
			this.valor.incrementAndGet();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AtomicInteger getValor() {
		return valor;
	}

	public void setValor(AtomicInteger valor) {
		this.valor = valor;
	}
	
	
	


	
}
