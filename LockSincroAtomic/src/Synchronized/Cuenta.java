package Synchronized;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cuenta {
	
	int valor;
	
	public Cuenta() {
		valor = 0;
	}
	
	public synchronized void sumarValor(){
		try {
			this.valor++;
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
