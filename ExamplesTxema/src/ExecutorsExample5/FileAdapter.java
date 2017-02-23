package ExecutorsExample5;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.ListCellRenderer;

public class FileAdapter implements ListCellRenderer<File>{

	@Override
	public Component getListCellRendererComponent(JList<? extends File> list, File value, int index, boolean isSelected,
			boolean cellHasFocus) {
		System.out.println("he pasado por aqui "+value.getName() );
		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,crearPanelIzda(value),crearPanelDcha(value));
		
		return panel;
	}

	private Component crearPanelIzda(File value) {
		//JLabel imagen = new JLabel(new ImageIcon(value.getAbsolutePath()));
		JLabel imagen = new JLabel("imagen");
		return imagen;
	}

	private Component crearPanelDcha(File value) {
		JPanel panel = new JPanel (new GridLayout (2,1,0,10));
		JLabel lbNombre = new JLabel(value.getName());
		lbNombre.setFont(new Font("Arial",Font.BOLD,18));
		JLabel lbPath = new JLabel(value.getPath());
		panel.add(lbNombre);
		panel.add(lbPath);
		
		return panel;
	}

}
