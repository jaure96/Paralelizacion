import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
	
	final int NUMHILOS = 10;
	ExecutorService executor, ahorrador, gastador;
	Cuenta cuenta;

	public void iniciarReaderWriters(){
		
		gastador = Executors.newFixedThreadPool(4);
		ahorrador = Executors.newFixedThreadPool(4);
		executor = Executors.newSingleThreadExecutor();
		cuenta = new Cuenta(0);
		
		for (int kont = 0; kont < NUMHILOS; kont++) {
			gastador.execute(new RunableAhorrador(cuenta, executor));
			gastador.execute(new RunableGastador(cuenta, executor));
		}
		try {
			ahorrador.shutdown();
			gastador.shutdown();
			ahorrador.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
			gastador.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
			executor.shutdown();
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		}catch (Exception e){
			e.printStackTrace();
		}


	}
	public static void main(String[] args) {
		Main main = new Main();
		main.iniciarReaderWriters();
	}

}
