package org.liceolapaz.des.dgm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ventana extends JFrame {
	
	private Tablero tablero;
	
	public JLabel intentosNumero;
	public JLabel parejasNumero;
	JPanel panel = null;
	int tiempoSegundos = 0;
	Timer contador = null;
	JLabel tiempo = null;
	private Dialogo mensaje = new Dialogo(this);
	public boolean pulsado = false;
	private File archivo;
	String rutaFicheroResultados = "Resultados.txt";

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
		// Llamamos a crear menú
		crearMenu();
		int filas = 3, columnas = 4;
		getRootPane().setBorder(null);
		setResizable(true);
		setLayout(new BorderLayout());
		this.tablero = new Tablero(this, filas, columnas);
		panel = new JPanel();
		
		panel.setLayout(new GridLayout());
		// Label intentos
		JLabel intentos = new JLabel("Intentos", SwingConstants.CENTER);
		intentos.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		// Label para el número de intentos
		intentosNumero = new JLabel(Integer.toString(tablero.getIntentos()), SwingConstants.CENTER);
		intentosNumero.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
		intentosNumero.setFont(fuenteNumeros);
		
		// Label de parejas
		JLabel parejas = new JLabel("Parejas", SwingConstants.CENTER);
		parejas.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		// Label apara el número de parejas
		parejasNumero = new JLabel(Integer.toString(tablero.getNumeroParejas()), SwingConstants.CENTER);
		parejasNumero.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
		parejasNumero.setFont(fuenteNumeros);
		// Icono del reloj
		JLabel reloj = new JLabel();
		reloj.setIcon(new ImageIcon(getClass().getResource("/reloj.PNG")));
		reloj.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Label para el tiempo del reloj
		tiempo = new JLabel(tiempo+"", SwingConstants.CENTER);
		TimerTask timerTask = new TimerTask() {
			
			@Override
			public void run() {
				tiempoSegundos++;
				tiempo.setText(Integer.toString(tiempoSegundos));
				revalidate();
				
			}
		};
		contador = new Timer();
		contador.scheduleAtFixedRate(timerTask, 0, 1000);
		tiempo.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
		tiempo.setFont(fuenteNumeros);
		// Añadimos todos los label
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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	public void crearMenu() {
		// Método para crear el menú
		JMenuBar menuBar = new JMenuBar();
		JMenu menuPartida = new JMenu("Partida");
		menuPartida.setMnemonic(KeyEvent.VK_P);	
		// Nueva partida
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
		// Guardar partida
		JMenuItem guardarPartida = new JMenuItem("Guardar Partida");
		guardarPartida.setIcon(new ImageIcon(getClass().getResource("/GuardarPartida.png")));
		guardarPartida.setMnemonic(KeyEvent.VK_G);
		guardarPartida.setAccelerator(KeyStroke.getKeyStroke("ctrl G"));
		guardarPartida.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				guardarInfo();
				
			}
		});
		
		menuPartida.add(guardarPartida);
		// Cargar partida
		JMenuItem cargarPartida = new JMenuItem("Cargar Partida");
		cargarPartida.setIcon(new ImageIcon(getClass().getResource("/CargarPartida.png")));
		cargarPartida.setMnemonic(KeyEvent.VK_C);
		cargarPartida.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
		cargarPartida.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				cargar();
				
			}
		});
		
		menuPartida.add(cargarPartida);
		// Salir
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
		
		// Pestaña de opciones
		JMenu menuOpciones = new JMenu("Opciones");
		menuPartida.setMnemonic(KeyEvent.VK_O);
		// Checkbox de almacenar resultados
		JCheckBoxMenuItem almacenarResultados = new JCheckBoxMenuItem("Almacenar Resultados");
		almacenarResultados.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				pulsado = true;
				
			}
		});
		menuOpciones.add(almacenarResultados);
		// Cambiar dificultad
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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	protected void cargar() {
		// Método de cargar partida
		String ruta = "";
		JFileChooser filechooser = new JFileChooser();
		
		int seleccion = filechooser.showOpenDialog(null);
		
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			
			File fichero = filechooser.getSelectedFile();
			ruta = fichero.getAbsolutePath();
			
			if (ruta.endsWith("txt")) {
				
				if (ruta != null) {
					
					leer(fichero);
						
				}
				
			} else {
				// Mensaje de error
				JOptionPane.showMessageDialog(null, "Escoja un archivo válido");
				// Devolvemos la ruta a null
				ruta = null;
				return;
			}
		}
	
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	private void leer(File fichero) {
	
		FileReader fr = null;
		
		try {
			fr = new FileReader(fichero);
		} catch (FileNotFoundException e) {}
		
		BufferedReader br = new BufferedReader(fr);
		
		cabecera(br);
		
		partida(br);
		
		try {
			br.close();
		} catch (IOException e) {}
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void cabecera(BufferedReader br) {
		// Método de leer la primera línea del archivo
		String info = "";
		
		try {
			info = br.readLine();
		} catch (IOException e) {}
		
		String[] infoCortada = info.split(";");
		
		// Guardamos la información en distintas variables 
		int filasCargadas = Integer.parseInt(infoCortada[0]);
		int columnasCargadas = Integer.parseInt(infoCortada[1]);
		int intentosCargados = Integer.parseInt(infoCortada[2]);
		int parejasCargadas = Integer.parseInt(infoCortada[3]);
		int tiempoCargado = Integer.parseInt(infoCortada[4]);
		String dificultadCargada = infoCortada[5];
		boolean pulsadoCargado = Boolean.parseBoolean(infoCortada[6]);
		// Le pasamos la informacióna un método de crear un nuevo tablero
		tableroCargado(filasCargadas, columnasCargadas, intentosCargados, parejasCargadas, tiempoCargado, dificultadCargada,
		pulsadoCargado);
		
	}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	private void partida(BufferedReader br) {
		// Método de leer la información de cada uno de los botones
		String datosCasilla = "";
		
		try {
					
			while((datosCasilla = br.readLine()) != null) {
						
				String[] infoCortada = datosCasilla.split(";");
					// Guardamos información en distintas variables	
				int X = Integer.parseInt(infoCortada[0]);
				int Y = Integer.parseInt(infoCortada[1]);
				int valor = Integer.parseInt(infoCortada[2]);
				boolean estado = Boolean.parseBoolean(infoCortada[3]);
						
				tablero.botones[X][Y].setValor(valor);
				
				if(estado == true) {
					
					tablero.botones[X][Y].setText(String.valueOf(valor));
					tablero.botones[X][Y].setBackground(Color.CYAN);
					tablero.botones[X][Y].setEnabled(false);

				}
			}
					
		} catch (IOException e) {}
		
		
		
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void tableroCargado(int filasCargadas, int columnasCargadas, int intentosCargados, int parejasCargadas, int tiempoCargado,
			String dificultadCargada, boolean pulsadoCargado) {
		// Método de cargar un tablero con las variables que le pasamos
		remove(panel);
		remove(tablero);
		remove(tiempo);
		setTiempoSegundos(tiempoCargado);
		contador.cancel();
		Font fuenteNumeros = new Font("Arial", Font.BOLD, 30);
	
		crearMenu();
		getRootPane().setBorder(null);
		setResizable(true);
		setLayout(new BorderLayout());
		this.tablero = new Tablero(this, filasCargadas, columnasCargadas);
		panel = new JPanel();
	
		panel.setLayout(new GridLayout());
	
		JLabel intentos = new JLabel("Intentos", SwingConstants.CENTER);
		intentos.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	
		intentosNumero = new JLabel(Integer.toString(intentosCargados), SwingConstants.CENTER);
		intentosNumero.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
		intentosNumero.setFont(fuenteNumeros);
	
	
		JLabel parejas = new JLabel("Parejas", SwingConstants.CENTER);
		parejas.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	
		parejasNumero = new JLabel(Integer.toString(parejasCargadas), SwingConstants.CENTER);
		parejasNumero.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
		parejasNumero.setFont(fuenteNumeros);
	
		JLabel reloj = new JLabel();
		reloj.setIcon(new ImageIcon(getClass().getResource("/reloj.PNG")));
		reloj.setHorizontalAlignment(SwingConstants.CENTER);
	
		tiempo = new JLabel(tiempo+"", SwingConstants.CENTER);
		tiempoSegundos = tiempoCargado;
		TimerTask timerTask = new TimerTask() {
		
			@Override
			public void run() {
				tiempoSegundos++;
				tiempo.setText(Integer.toString(tiempoSegundos));
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


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	protected void guardarInfo() {
		// Método para crear la cabecera para luego guardarla
		String datos = "";
		JFileChooser filechooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de texto (.txt)", "txt");
		String ruta = "";
	
		datos = Integer.toString(tablero.getFilas()) + ";" + Integer.toString(tablero.getColumnas()) + ";" + Integer.toString(tablero.getIntentos()) 
		+ ";" + Integer.toString(tablero.getNumeroParejas()) + ";" + Integer.toString(tiempoSegundos) + ";" + mensaje.dificultad + ";" + Boolean.toString(pulsado);
	
	
		filechooser.setFileFilter(filter);
		// Llamamos al filechooser para abrir una ventana
		int seleccion = filechooser.showSaveDialog(null);
	
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			// Guardamos el archivo seleccionado en una variable
			File fichero = filechooser.getSelectedFile();
			// Obtenemos la ruta 
			ruta = fichero.getAbsolutePath();
			// Si la ruta termina en txt guardamos
			if (ruta.endsWith("txt")) {
			
				if (ruta != null) {
					archivo = new File(ruta);
				}
			} else {
				// Mensaje de error
				JOptionPane.showMessageDialog(null, "Especifique la extensión del archivo");
				// Devolvemos la ruta a null
				ruta = null;
				return;
			}
		
		}
		// Llamamos a los métodos de guardar
		guardarFichero(datos, archivo);
		guardarTablero();
	
	
		}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	protected void guardarTablero() {
		// Método para guardar la información de cada botón
		String datos = "";
	
		for (int fila = 0; fila < tablero.getFilas(); fila++) {
			for (int columna = 0; columna < tablero.getColumnas(); columna++) {
			
				datos = Integer.toString(fila) + ";" + Integer.toString(columna) + ";" + Integer.toString(tablero.botones[fila][columna].getValor()) + ";" + Boolean.toString(tablero.botones[fila][columna].pulsado);
				// Llamamos al método de guardar fichero
				guardarFichero(datos, archivo);
			
			}
		}
	
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void guardarFichero(String datos, File archivo) {
		// Guardamos la información 
		try {
		
			FileWriter fw = new FileWriter(archivo,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
		
			pw.println(datos);
			pw.close();
		
		} catch (IOException e) {}
	
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public void almacenarResultados(String usuario) {
		// Método del checkbox de almacenar resultados
		File directorioResultados = new File(rutaFicheroResultados);
		// Variable de fecha
		Date date = new Date(); 
		DateFormat fechaHora = new SimpleDateFormat("HH:mm:ss dd/MM/yy");
		
		String resultados = "Usuario: " + usuario + ", Tiempo: " + Integer.toString(tiempoSegundos) + ", Dificultad: " + mensaje.dificultad 
				+ ", Fecha y hora: " + fechaHora.format(date);
		
		try {
			
			FileWriter fw = new FileWriter(directorioResultados,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
		
			pw.println(resultados);
			pw.close();
		
		} catch (IOException e) {}
		
	}	
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void nuevaPartida() {
		// Método de nueva partida
		contador.cancel();
		tablero.reiniciarValores();
		setParejasNumero(tablero.getFilas()*tablero.getColumnas()/2);
		setIntentosNumero(0);
		remove(tablero);
		remove(tiempo);
		setTiempoSegundos(0);
		nuevoTablero(tablero.getFilas(), tablero.getColumnas());
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void nuevoTablero(int filas, int columnas) {
		// Método para crear el tablero inicial
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
	
		intentosNumero = new JLabel(Integer.toString(tablero.getIntentos()), SwingConstants.CENTER);
		intentosNumero.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
		intentosNumero.setFont(fuenteNumeros);
	
	
		JLabel parejas = new JLabel("Parejas", SwingConstants.CENTER);
		parejas.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	
		parejasNumero = new JLabel(Integer.toString(tablero.getNumeroParejas()), SwingConstants.CENTER);
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
				tiempo.setText(Integer.toString(tiempoSegundos));
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
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public JLabel getIntentosNumero() {
		return intentosNumero;
	}


	public void setIntentosNumero(int intentosNumero) {
		this.intentosNumero.setText(Integer.toString(intentosNumero));
	}


	public JLabel getParejasNumero() {
		return parejasNumero;
	}


	public void setParejasNumero(int parejasNumero) {
		this.parejasNumero.setText(Integer.toString(parejasNumero));
	}
	
	public int getTiempoSegundos() {
		return tiempoSegundos;
	}

	public void setTiempoSegundos(int tiempoSegundos) {
		this.tiempoSegundos = tiempoSegundos;
	}


	


}
