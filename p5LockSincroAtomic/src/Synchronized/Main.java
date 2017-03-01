package Synchronized;

public class Main {
	
	public static int SIZE = 100000;
	
	public static void main(String[] args) {
		
		Cuenta cuenta = new Cuenta();		
		Thread []myThreadList = new Thread[SIZE];
		
		long inicio = System.currentTimeMillis();
		
		for(int kont=0; kont<SIZE; kont++){
			myThreadList[kont]= new HiloModifier(cuenta);
			myThreadList[kont].start();
		}
		
		for(int kont=0; kont<SIZE; kont++){
			try {
				myThreadList[kont].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long fin = System.currentTimeMillis();
		System.out.println("Tiempo empleado : "+(fin-inicio)+" ms");
		System.out.println("Valor cuenta: "+cuenta.getValor());
	}

}
