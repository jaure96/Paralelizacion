package ForkJoinExample4;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class CalculadorTamanoDirectorio extends RecursiveTask<Long>{
	File ficheroInicial;
	public CalculadorTamanoDirectorio(File f){
		this.ficheroInicial = f;
	}
	@Override
	protected Long compute() {
		long size = 0;
		List<RecursiveTask<Long>> tareas = new ArrayList<>();
		File [] listaFicheros = ficheroInicial.listFiles();
		
		for (File f: listaFicheros){
			if (f.isDirectory()){
				RecursiveTask<Long> nuevaTarea = new CalculadorTamanoDirectorio(f);
				tareas.add(nuevaTarea);
				nuevaTarea.fork();
			}else{
				size+= f.length();
			}
		}
		for (RecursiveTask<Long> tarea: tareas){
			size+= tarea.join();
		}
		return size;
	}

	
}
