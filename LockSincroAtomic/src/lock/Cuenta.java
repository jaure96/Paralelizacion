package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cuenta {
	
	int valor;
	public int getValor() {
		return valor;
	}


	public void setValor(int valor) {
		this.valor = valor;
	}


	public Lock getLock() {
		return lock;
	}


	public void setLock(Lock lock) {
		this.lock = lock;
	}


	Lock lock;
	
	public Cuenta() {
		valor = 0;
		lock = new ReentrantLock();
	}


	public void sumarValor(){
		lock.lock();
		this.valor++;
		lock.unlock();
	}
	
	

}
