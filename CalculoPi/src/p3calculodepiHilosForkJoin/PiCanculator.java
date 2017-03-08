package p3calculodepiHilosForkJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;


public class PiCanculator extends RecursiveTask<Double>{
	
	double numeroIntervalos;
	double anchuraIntervalo;
	long inicio;
	long fin;
	
	public PiCanculator(double anchuraIntervalo, long inicio, long fin, double numeroIntervalos){
		this.numeroIntervalos = numeroIntervalos;
		this.anchuraIntervalo = anchuraIntervalo;
		this.inicio = inicio;
		this.fin = fin;	
	}
	@Override
	protected Double compute() {
		double valorPi = 0;
		double x;
		List<RecursiveTask<Double>> tareas = new ArrayList<>();
		
		if(numeroIntervalos >= 25000){
			numeroIntervalos /= 2;
			RecursiveTask<Double> nuevaTarea = new PiCanculator(anchuraIntervalo, inicio, fin, numeroIntervalos);
			inicio += 25000;
			fin += 25000;
			tareas.add(nuevaTarea);
			nuevaTarea.fork();
		}else{			
			for (long i = inicio; i<fin; i++){
				x= (i+0.5)*anchuraIntervalo;
				valorPi += 4/(1+(x*x));
			}	
			valorPi *= Main.ANCHURAINTERVALO;
		}	
		for (RecursiveTask<Double> tarea: tareas){
			valorPi+= tarea.join();
		}
		return valorPi;
	}	
}
