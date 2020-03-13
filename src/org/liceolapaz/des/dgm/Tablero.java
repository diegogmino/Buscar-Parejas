package org.liceolapaz.des.dgm;


import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.liceolapaz.des.dgm.Ventana;

import org.liceolapaz.des.dgm.Boton;


public class Tablero extends JPanel {
	
	private Ventana ventana;
	private int filas;
	private int columnas;
	private int pulsados;
	public Boton[][] botones;
	private Boton botonPulsado1 = null, botonPulsado2 = null;
	private Boton boton;
	private Dialogo mensaje;
	private int numeroParejas;
	private int intentos = 0;

	public Tablero(Ventana ventana, int filas, int columnas) {
		super();
		this.ventana = ventana;
		this.filas = filas;
		this.columnas = columnas;
		this.pulsados = 0;
		numeroParejas = (filas*columnas)/2;
		setLayout(new GridLayout(filas, columnas));
		setBorder(new LineBorder(Color.ORANGE, 5));
		crearBotones();
		comprobarPareja(boton);
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	private void crearBotones() {
		this.botones = new Boton[this.filas][this.columnas];
		for (int fila = 0; fila < this.filas; fila++) {
			for (int columna = 0; columna < this.columnas; columna++) {
				this.botones[fila][columna] = new Boton(this, fila, columna, 0);
				add(this.botones[fila][columna]);
			}
		}
		
		crearParejas();
		crearParejas();
		
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	private void crearParejas() {
		Random random = new Random();
		int valor = 1;
		while (valor <= (this.filas * this.columnas)/2) {
			int fila = random.nextInt(this.filas);
			int columna = random.nextInt(this.columnas);
			if (this.botones[fila][columna].getValor() == 0) {
				this.botones[fila][columna].setValor(valor);
				valor++;
			}
		}
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public void comprobarPareja(Boton boton) {
		if (botonPulsado1 == null) {
			botonPulsado1 = boton;
		} else {
			botonPulsado2 = boton;
			
			if (botonPulsado1.getValor() == botonPulsado2.getValor()) {
				
				intentos++;
				numeroParejas--;
				
				if (numeroParejas == 0) {
					JOptionPane.showMessageDialog(null, "¡Enhorabuena! Has ganado en " + intentos + " intento(s)", "Has ganado", JOptionPane.INFORMATION_MESSAGE);
					
					if (ventana.pulsado) {
						String usuario = JOptionPane.showInputDialog("Escriba su nombre de usuario:");
						ventana.almacenarResultados(usuario);
						
					} else {
						
						int opcion = JOptionPane.showConfirmDialog(null, "¿Quieres jugar otra partida?", "Fin de la partida", JOptionPane.YES_NO_OPTION);
						if (JOptionPane.YES_NO_OPTION == opcion) {
							ventana.nuevaPartida();
						} else {
							System.exit(0);
						}
						
					}
					
					
					
					
				} else {
					JOptionPane.showMessageDialog(null, "Los números son pareja", "Bien hecho", JOptionPane.INFORMATION_MESSAGE);
				}
				
				botonPulsado1.setEnabled(false);
				botonPulsado1.pulsado = true;
				botonPulsado2.setEnabled(false);
				botonPulsado2.pulsado = true;
				botonPulsado1 = null;
				botonPulsado2 = null;
				
				ventana.intentosNumero.setText(Integer.toString(intentos));
				ventana.parejasNumero.setText(Integer.toString(numeroParejas));
				
				
			} else {
				
				intentos++;
				
				JOptionPane.showMessageDialog(null, "Los números no hacen pareja", "Sigue buscando", JOptionPane.INFORMATION_MESSAGE);
				
				botonPulsado1.taparBoton();
				botonPulsado2.taparBoton();
				
				botonPulsado1.setEnabled(true);
				botonPulsado2.setEnabled(true);
				
				botonPulsado1 = null;
				botonPulsado2 = null;
				
				ventana.intentosNumero.setText(Integer.toString(intentos));
				
			}
		}
		
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public int getIntentos() {
		return intentos;
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}

	public int getNumeroParejas() {
		return numeroParejas;
	}

	public void setNumeroParejas(int numeroParejas) {
		this.numeroParejas = numeroParejas;
	}

	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}

	public void reiniciarValores() {
		
		intentos = 0;
		numeroParejas = (filas*columnas)/2;
		
	}

	
	
	

}
