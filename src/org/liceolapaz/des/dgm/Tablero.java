package org.liceolapaz.des.dgm;


import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.liceolapaz.des.dgm.Ventana;

import org.liceolapaz.des.dgm.Boton;

public class Tablero extends JPanel {
	
	private Ventana ventana;
	private int filas;
	private int columnas;
	private int turno;
	private int pulsados;
	private Boton[][] botones;

	public Tablero(Ventana ventana, int filas, int columnas) {
		super();
		this.ventana = ventana;
		this.filas = filas;
		this.columnas = columnas;
		this.pulsados = 0;
		setLayout(new GridLayout(filas, columnas));
		setBorder(new LineBorder(Color.ORANGE, 5));
		crearBotones();
		//generarTurno();
	}
	
	private void crearBotones() {
		int valor = 0;
		this.botones = new Boton[this.filas][this.columnas];
		for (int fila = 0; fila < this.filas; fila++) {
			for (int columna = 0; columna < this.columnas; columna++) {
				this.botones[fila][columna] = new Boton(this, fila, columna);
				add(this.botones[fila][columna]);
			}
		}
	}
	
	

}
