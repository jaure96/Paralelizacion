package ExecutorsExample1;

public class NumPrimosSecuencial {
	final int LIMITE = 100000;
	
	public NumPrimosSecuencial(){
		
	}
	public void hallarNumerosPrimos(){
		for (int i= 2; i<LIMITE; i++){
			if(esPrimo(i)){
				System.out.println(i+" es primo");
			}
		}
	}
	private boolean esPrimo(int valor) {
		for (int i= 2; i<valor/2; i++){
			if (valor%i ==0) return false;
		}
		return true;
	}
	public static void main(String[] args) {
		
		NumPrimosSecuencial ejercicio = new NumPrimosSecuencial();
		long inicio = System.currentTimeMillis();
		ejercicio.hallarNumerosPrimos();
		long fin = System.currentTimeMillis();
		System.out.println (" ha tardado: "+(fin -inicio)+" milisegundos");
	}

}
