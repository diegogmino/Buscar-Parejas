package org.liceolapaz.des.dgm;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.liceolapaz.des.dgm.Tablero;

import org.liceolapaz.des.dgm.Boton;

public class Boton extends JButton {
	
	private Tablero tablero;
	private int fila;
	private int columna;
	private int estado;
	private boolean pulsado;

	public Boton(Tablero tablero, int fila, int columna) {
		super();
		this.tablero = tablero;
		this.fila = fila;
		this.columna = columna;
		this.pulsado = false;
		setBackground(Color.WHITE);
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!Boton.this.pulsado) {
					
				}
			}
		});
	}
	

}
