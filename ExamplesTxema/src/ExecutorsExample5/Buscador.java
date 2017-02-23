package ExecutorsExample5;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Buscador implements Callable<List<File>>{
	final String EXTENSION =".png";
	
	ModeloLista modelo;
	File path;
	ThreadPool pool;
	
	public Buscador (File path, ModeloLista modelo, ThreadPool pool){
		this.path = path;
		this.modelo = modelo;
		this.pool = pool;
	}

	

	@Override
	public List<File> call() throws Exception {
		List<File> listaImagenes = new ArrayList<>();
		File [] listaFicheros = path.listFiles();
		System.out.println("Buscando");
		for (File f: listaFicheros){
			if (f.isDirectory()){
				pool.submit(new Buscador(f,modelo,pool));
			}else{
				if (f.getName().contains(EXTENSION)){
				   listaImagenes.add(f);
				}
			}
			
		}
		return listaImagenes;
	}

}
