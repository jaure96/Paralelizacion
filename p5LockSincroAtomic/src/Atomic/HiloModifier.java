package Atomic;

public class HiloModifier extends Thread {

	Cuenta cuenta;
	
	public HiloModifier(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	public void run(){
		cuenta.sumarValor();
	}
}
