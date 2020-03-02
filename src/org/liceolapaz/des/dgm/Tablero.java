package org.liceolapaz.des.dgm;


import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;

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
	private Boton[][] botones;
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
				} else {
					JOptionPane.showMessageDialog(null, "Los números son pareja", "Bien hecho", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
				botonPulsado1.setEnabled(false);
				botonPulsado2.setEnabled(false);
				botonPulsado1 = null;
				botonPulsado2 = null;
				
			} else {
				
				intentos++;
				
				JOptionPane.showMessageDialog(null, "Los números no hacen pareja", "Sigue buscando", JOptionPane.INFORMATION_MESSAGE);
				
				botonPulsado1.taparBoton();
				botonPulsado2.taparBoton();
				
				botonPulsado1 = null;
				botonPulsado2 = null;
			}
		}
		
		
		
	}
	
	
	
	

}
