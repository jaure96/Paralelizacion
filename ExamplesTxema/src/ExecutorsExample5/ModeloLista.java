package ExecutorsExample5;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class ModeloLista extends AbstractListModel<File>{
	
	List<File> lista;

	public ModeloLista(){
		lista = new ArrayList<>();
	}
	
	public synchronized void add (List<File> lista){
		//synchronized(this){
			int indice0;
			indice0 = this.lista.size()-1;
			this.lista.addAll(lista);
			for (File f: this.lista) System.out.print(f.getName()+" ");
			System.out.println(this.lista.size()+" ");
			this.fireContentsChanged(this, indice0, this.lista.size()-1);
		//}
		
	}

	@Override
	public int getSize() {
		//synchronized (this){
			return lista.size();
		//}
	}

	@Override
	public File getElementAt(int index) {
		//synchronized(this){
			return lista.get(index);
		//}
		
	}
	

}
