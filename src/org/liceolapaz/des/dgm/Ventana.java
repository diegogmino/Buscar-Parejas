package org.liceolapaz.des.dgm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.liceolapaz.des.dgm.Tablero;

public class Ventana extends JFrame {
	
	private Tablero tablero;
	private int filas = 3, columnas = 4;
	
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
				// getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
				crearTablero();
			}
		});
		
		this.add(botonPortada);
		
		URL url = getClass().getResource("/icono.PNG");
		setIconImage(new ImageIcon(url).getImage());
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	private void crearTablero() {
		
		crearMenu();
		getRootPane().setBorder(null);
		setResizable(true);
		setLayout(new BorderLayout());
		this.tablero = new Tablero(this, filas, columnas);
		add(this.tablero, BorderLayout.CENTER);
		revalidate();
		
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	public void crearMenu() {
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menuPartida = new JMenu("Partida");
		menuPartida.setMnemonic(KeyEvent.VK_P);	
		
		JMenuItem nuevaPartida = new JMenuItem("Nueva partida");
		nuevaPartida.setIcon(new ImageIcon(getClass().getResource("/NuevaPartida.png")));
		nuevaPartida.setMnemonic(KeyEvent.VK_N);
		nuevaPartida.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
		nuevaPartida.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		menuPartida.add(nuevaPartida);
		
		JMenuItem guardarPartida = new JMenuItem("Guardar Partida");
		guardarPartida.setIcon(new ImageIcon(getClass().getResource("/GuardarPartida.png")));
		guardarPartida.setMnemonic(KeyEvent.VK_G);
		guardarPartida.setAccelerator(KeyStroke.getKeyStroke("ctrl G"));
		guardarPartida.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		menuPartida.add(guardarPartida);
		
		JMenuItem cargarPartida = new JMenuItem("Cargar Partida");
		cargarPartida.setIcon(new ImageIcon(getClass().getResource("/CargarPartida.png")));
		cargarPartida.setMnemonic(KeyEvent.VK_C);
		cargarPartida.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
		cargarPartida.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		menuPartida.add(cargarPartida);
		
		JMenuItem salir = new JMenuItem("Salir");
		salir.setIcon(new ImageIcon(getClass().getResource("/salir.png")));
		salir.setMnemonic(KeyEvent.VK_S);
		salir.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		salir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		menuPartida.add(salir);
		menuBar.add(menuPartida);
		
		
		JMenu menuOpciones = new JMenu("Opciones");
		menuPartida.setMnemonic(KeyEvent.VK_O);
		
		JCheckBoxMenuItem almacenarResultados = new JCheckBoxMenuItem("Almacenar Resultados");
		
		menuOpciones.add(almacenarResultados);
		
		JMenuItem cambiarDificultad = new JMenuItem("Cambiar dificultad");
		cambiarDificultad.setMnemonic(KeyEvent.VK_D);
		cambiarDificultad.setAccelerator(KeyStroke.getKeyStroke("ctrl D"));
		cambiarDificultad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		menuOpciones.add(cambiarDificultad);
		
		
		
		menuBar.add(menuOpciones);
		setJMenuBar(menuBar);
		
	}

}
