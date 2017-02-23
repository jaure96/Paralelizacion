import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class RunableGastador implements Runnable {

	final int MAX = 1000;
	Cuenta cuenta;
	ExecutorService executor;
	
	
	public RunableGastador(Cuenta cuenta, ExecutorService executor){
		this.cuenta = cuenta;
		this.executor = executor;
	}

	@Override
	public void run() {
		for (int kont = 0; kont < MAX; kont++) {
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					cuenta.meter(-1);					
				}
			});
		}		
	}
}
