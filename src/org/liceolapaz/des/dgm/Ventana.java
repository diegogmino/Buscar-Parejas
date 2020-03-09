package org.liceolapaz.des.dgm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.liceolapaz.des.dgm.Tablero;

public class Ventana extends JFrame {
	
	private Tablero tablero;
	
	public JLabel intentosNumero;
	public JLabel parejasNumero;
	JPanel panel = null;
	int tiempoSegundos = 0;
	Timer contador = null;
	JLabel tiempo = null;
	private Dialogo mensaje = new Dialogo(this);

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
				crearTablero();
				
			}
		});
		
		this.add(botonPortada);
		
		URL url = getClass().getResource("/icono.PNG");
		setIconImage(new ImageIcon(url).getImage());
	}
	

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	private void crearTablero() {
		
		Font fuenteNumeros = new Font("Arial", Font.BOLD, 30);
		
		crearMenu();
		int filas = 3, columnas = 4;
		getRootPane().setBorder(null);
		setResizable(true);
		setLayout(new BorderLayout());
		this.tablero = new Tablero(this, filas, columnas);
		panel = new JPanel();
		
		panel.setLayout(new GridLayout());
		
		JLabel intentos = new JLabel("Intentos", SwingConstants.CENTER);
		intentos.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		intentosNumero = new JLabel(tablero.getIntentos()+"", SwingConstants.CENTER);
		intentosNumero.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
		intentosNumero.setFont(fuenteNumeros);
		
		
		JLabel parejas = new JLabel("Parejas", SwingConstants.CENTER);
		parejas.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		parejasNumero = new JLabel(tablero.getNumeroParejas()+"", SwingConstants.CENTER);
		parejasNumero.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
		parejasNumero.setFont(fuenteNumeros);
		
		JLabel reloj = new JLabel();
		reloj.setIcon(new ImageIcon(getClass().getResource("/reloj.PNG")));
		reloj.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		tiempo = new JLabel(tiempo+"", SwingConstants.CENTER);
		TimerTask timerTask = new TimerTask() {
			
			@Override
			public void run() {
				tiempoSegundos++;
				tiempo.setText(tiempoSegundos+"");
				revalidate();
				
			}
		};
		contador = new Timer();
		contador.scheduleAtFixedRate(timerTask, 0, 1000);
		tiempo.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
		tiempo.setFont(fuenteNumeros);
		
		panel.add(intentos);
		panel.add(intentosNumero);
		panel.add(parejas);
		panel.add(parejasNumero);
		panel.add(reloj);
		panel.add(tiempo);
		
		add(this.tablero, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		
		
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
				
				nuevaPartida();
				
				
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
			
				mensaje.setVisible(true);				
				
			}
		});
		
		menuOpciones.add(cambiarDificultad);
		
		
		
		menuBar.add(menuOpciones);
		setJMenuBar(menuBar);
		
	}

	
	
	
public JLabel getIntentosNumero() {
		return intentosNumero;
	}


	public void setIntentosNumero(int intentosNumero) {
		this.intentosNumero.setText(intentosNumero+"");
	}


	public JLabel getParejasNumero() {
		return parejasNumero;
	}


	public void setParejasNumero(int parejasNumero) {
		this.parejasNumero.setText(parejasNumero+"");
	}

	
	
	
	public int getTiempoSegundos() {
		return tiempoSegundos;
	}


	public void setTiempoSegundos(int tiempoSegundos) {
		this.tiempoSegundos = tiempoSegundos;
	}


	public void nuevaPartida() {
		
		contador.cancel();
		tablero.reiniciarValores();
		setParejasNumero(tablero.getFilas()*tablero.getColumnas()/2);
		setIntentosNumero(0);
		remove(tablero);
		remove(tiempo);
		setTiempoSegundos(0);
		nuevoTablero(tablero.getFilas(), tablero.getColumnas());
	}
	
	
	
public void nuevoTablero(int filas, int columnas) {
	
	remove(panel);
	remove(tablero);
	remove(tiempo);
	setTiempoSegundos(0);
	contador.cancel();
	Font fuenteNumeros = new Font("Arial", Font.BOLD, 30);
	
	crearMenu();
	getRootPane().setBorder(null);
	setResizable(true);
	setLayout(new BorderLayout());
	this.tablero = new Tablero(this, filas, columnas);
	panel = new JPanel();
	
	panel.setLayout(new GridLayout());
	
	JLabel intentos = new JLabel("Intentos", SwingConstants.CENTER);
	intentos.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	
	intentosNumero = new JLabel(tablero.getIntentos()+"", SwingConstants.CENTER);
	intentosNumero.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
	intentosNumero.setFont(fuenteNumeros);
	
	
	JLabel parejas = new JLabel("Parejas", SwingConstants.CENTER);
	parejas.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	
	parejasNumero = new JLabel(tablero.getNumeroParejas()+"", SwingConstants.CENTER);
	parejasNumero.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
	parejasNumero.setFont(fuenteNumeros);
	
	JLabel reloj = new JLabel();
	reloj.setIcon(new ImageIcon(getClass().getResource("/reloj.PNG")));
	reloj.setHorizontalAlignment(SwingConstants.CENTER);
	
	tiempo = new JLabel(tiempo+"", SwingConstants.CENTER);
	tiempo = new JLabel(tiempo+"", SwingConstants.CENTER);
	TimerTask timerTask = new TimerTask() {
		
		@Override
		public void run() {
			tiempoSegundos++;
			tiempo.setText(tiempoSegundos+"");
			revalidate();
			
		}
	};
	contador = new Timer();
	contador.scheduleAtFixedRate(timerTask, 0, 1000);
	tiempo.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
	tiempo.setFont(fuenteNumeros);
	
	panel.add(intentos);
	panel.add(intentosNumero);
	panel.add(parejas);
	panel.add(parejasNumero);
	panel.add(reloj);
	panel.add(tiempo);
	
	add(this.tablero, BorderLayout.CENTER);
	add(panel, BorderLayout.SOUTH);
	
	
	revalidate();
	repaint();
}

}
