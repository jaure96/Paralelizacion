import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class RunableAhorrador implements Runnable{
	
	final int MAX = 1000;
	Cuenta cuenta;
	ExecutorService executor;
	
	
	public RunableAhorrador(Cuenta cuenta, ExecutorService executor){
		this.cuenta = cuenta;
		this.executor = executor;
	}

	@Override
	public void run() {
		for (int kont = 0; kont < MAX; kont++) {
			executor.execute(()-> cuenta.meter(1));
		}		
	}

}
