package Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cuenta {
	
	int valor;
	Semaphore semaphore;
	
	public Cuenta() {
		valor = 0;
		semaphore = new Semaphore(1);
	}
	
	public void sumarValor(){
		try {
			semaphore.acquire();
			this.valor++;
			semaphore.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int getValor() {
		return valor;
	}


	public void setValor(int valor) {
		this.valor = valor;
	}


	
}
