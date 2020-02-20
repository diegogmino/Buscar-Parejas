package org.liceolapaz.des.dgm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


public class Ventana extends JFrame {
	
	private JFrame inicial = new JFrame();
	
	public Ventana() {
		super();
		
		JLabel textoTitulo = new JLabel();
		JLabel textoDescripcion = new JLabel();
		JLabel textoAutor = new JLabel();
		Font fuenteTitulo = new Font("Arial", Font.BOLD, 30);
		Font fuenteDescripcion = new Font("Calibri", 5, 20);
		Font fuenteAutor = new Font("Calibri", Font.BOLD, 20);
		ImageIcon imagenPortada = new ImageIcon(getClass().getResource("/icono.PNG"));
		JButton botonPortada = new JButton();
		
		setTitle("Buscar Parejas - Diego García Miño");
		setSize(700, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
		// Valores para el texto del título
		textoTitulo.setText("Buscar Parejas");
		textoTitulo.setBounds(240, 40, 300, 100);
		textoTitulo.setFont(fuenteTitulo);
		this.add(textoTitulo);
		// Valores para el texto de descripcion
		textoDescripcion.setText("Pulse en la imagen para empezar a jugar");
		textoDescripcion.setBounds(180, 370, 350, 100);
		textoDescripcion.setFont(fuenteDescripcion);
		this.add(textoDescripcion);
		// Valores texto autor
		textoAutor.setText("Autor: Diego García Miño");
		textoAutor.setBounds(240, 430, 350, 100);
		textoAutor.setFont(fuenteAutor);
		this.add(textoAutor);
		// Valores para el botón de iniciar partida
		botonPortada.setIcon(imagenPortada);
		botonPortada.setBounds(225, 140, 250, 250);
		botonPortada.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				// Llamamos al método para borrar la pantalla
				borrar();
				
			}

			private void borrar() {
				// Borramos componentes
				remove(botonPortada);
				remove(textoAutor);
				remove(textoDescripcion);
				remove(textoTitulo);
				revalidate();
				repaint();
				// Cambiamos el color del borde a rojo
				getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
				
			}
		});
		
		this.add(botonPortada);
		
		URL url = getClass().getResource("/icono.PNG");
		setIconImage(new ImageIcon(url).getImage());
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	protected void juego() {
		
		
		
		
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	private void nuevaPantalla() {
	
	
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	private void crearMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Archivo");
		menu.setMnemonic(KeyEvent.VK_A);
		
		// Creamos el boton de nuevo archivo
			JMenuItem nuevoArchivo = new JMenuItem("Nuevo");
			nuevoArchivo.setMnemonic(KeyEvent.VK_N);
			nuevoArchivo.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
			menu.add(nuevoArchivo);
			
		menuBar.add(menu);
		setJMenuBar(menuBar);	
	}

}
