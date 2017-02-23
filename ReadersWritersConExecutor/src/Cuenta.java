
public class Cuenta {
	
	int saldo;
	
	public Cuenta(int saldoInicial){
		this.saldo = saldoInicial;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	public void meter(int cantidad){
		saldo = saldo + cantidad;
		System.out.println("Cantidad: "+cantidad);
	}
	
}
