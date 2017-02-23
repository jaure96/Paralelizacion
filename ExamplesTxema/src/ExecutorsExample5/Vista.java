package ExecutorsExample5;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Vista extends JFrame implements ActionListener{
	ModeloLista modelo;
	JTextField tfDirectorio;
	File fileSeleccionado;
	ThreadPool pool;
	JList<File> lista;
	
	public Vista(){
		super("Visor de imagenes");
		modelo = new ModeloLista();
		
		pool = new ThreadPool (modelo);
		this.setLocation(100,100);
		this.setSize(500, 600);
		this.setContentPane(crearPanelVentana());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private Container crearPanelVentana() {
		JPanel panel = new JPanel (new BorderLayout(0,20));
		panel.add(crearPanelSeleccionDirectorio(),BorderLayout.NORTH);
		panel.add(crearPanelListaFicheros(),BorderLayout.CENTER);
		return panel;
	}

	private Component crearPanelListaFicheros() {
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		lista = new JList<>(modelo);
		lista.setCellRenderer(new FileAdapter());
		panel.setViewportView(lista);
		return panel;
	}

	private Component crearPanelSeleccionDirectorio() {
		JPanel panel = new JPanel(new FlowLayout(20));
		tfDirectorio = new JTextField(40);
		JButton botonSeleccion = new JButton("...");
		botonSeleccion.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();	
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int opcion = fileChooser.showOpenDialog(Vista.this);
				if (opcion == JFileChooser.APPROVE_OPTION){
					fileSeleccionado = fileChooser.getSelectedFile();
					tfDirectorio.setText(fileSeleccionado.getPath());
				}
			}
			
		});
		JButton botonBuscar = new JButton ("Buscar");
		botonBuscar.addActionListener(this);
		panel.add(tfDirectorio);
		panel.add(botonSeleccion);
		panel.add(botonBuscar);
		return panel;
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Vista programa = new Vista();
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		pool.submit(new Buscador(fileSeleccionado,modelo,pool));
		pool.getFiles();
		System.out.println("He salido del action performed");
		
	}

	

}
