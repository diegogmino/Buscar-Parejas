package org.liceolapaz.des.dgm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;


public class Ventana extends JFrame {
	
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
		this.add(botonPortada);
		
		crearMenu();
		
		URL url = getClass().getResource("/icono.PNG");
		setIconImage(new ImageIcon(url).getImage());
	}

	private void crearMenu() {
		// TODO Auto-generated method stub
		
	}

}
