package org.liceolapaz.des.dgm;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;

import org.liceolapaz.des.dgm.Tablero;

import org.liceolapaz.des.dgm.Boton;

public class Boton extends JButton {
	
	private Tablero tablero;
	private int fila;
	private int columna;
	private boolean pulsado;
	private int valor;
	private int anterior;

	public Boton(Tablero tablero, int fila, int columna, int valor) {
		super();
		this.tablero = tablero;
		this.fila = fila;
		this.columna = columna;
		this.valor = valor;
		setBackground(Color.WHITE);
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!Boton.this.pulsado) {
					pulsar();
				}
			}
		});
	}

	public void taparBoton() {
		setBackground(Color.WHITE);
		setText("");
	}

	protected void pulsar() {	
		setText(String.valueOf(valor));
		setBackground(Color.CYAN);
		tablero.comprobarPareja(this);
	}
	
	
	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	
	

}
