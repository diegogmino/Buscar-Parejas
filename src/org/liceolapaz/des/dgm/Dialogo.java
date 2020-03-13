package org.liceolapaz.des.dgm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Dialogo extends JDialog {
	
	JTextField numeroFilas = null;
	JTextField numeroColumnas = null;
	public String dificultad = "Facil";
	
	public Dialogo(Ventana ventana) {
		super();
		dificultad(ventana);
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	public void dificultad(Ventana ventana) {
		// Diálogo de nivel de dificultad
		setResizable(false);
		setTitle("Nivel de dificultad");
		setSize(400, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		URL url = getClass().getResource("/icono.PNG");
		setIconImage(new ImageIcon(url).getImage());
		// Creamos el panel
		JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        {
            JButton aceptar = new JButton("Aceptar");
            aceptar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if ((Integer.parseInt(numeroFilas.getText()) * Integer.parseInt(numeroColumnas.getText())) %2 == 0) {
						
						ventana.nuevoTablero(Integer.parseInt(numeroFilas.getText()), Integer.parseInt(numeroColumnas.getText()));
						dispose();
						
					} else {
						// Comprobación de que el número de casillas introducida sea par
						JOptionPane.showMessageDialog(null, "El número de casillas debe ser par", "Error", JOptionPane.ERROR_MESSAGE);
						
					}
					
					
				}
            });
            
            aceptar.setActionCommand("Aceptar");
            panel.add(aceptar);
            getRootPane().setDefaultButton(aceptar);
        }
        {
            JButton cancelar = new JButton("Cancelar");
            cancelar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    // aquí van las acciones al hacer click en Vale
                    // envía el diálogo al recolector de basura de Java
                    dispose();
                }
            });
            cancelar.setActionCommand("Cancelar");
            panel.add(cancelar);
		
	}
     
       JPanel filasColumnas = new JPanel();
       filasColumnas.setLayout(null);
       getContentPane().add(filasColumnas);
       // Filas
       JLabel filas = new JLabel("Filas");
       filas.setBounds(30, 30, 30, 10);
       filasColumnas.add(filas);
         
       numeroFilas = new JTextField("");
       numeroFilas.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
       numeroFilas.setBounds(180, 26, 190, 20);
       filasColumnas.add(numeroFilas);
       // Columnas
       JLabel columnas = new JLabel("Columnas");
       columnas.setBounds(30, 80, 70, 30);
       filasColumnas.add(columnas);
        
       numeroColumnas = new JTextField("");
       numeroColumnas.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
       numeroColumnas.setBounds(180, 80, 190, 20);
       filasColumnas.add(numeroColumnas);
        
       // Panel para poner las distintas dificultades 
       JPanel dificultades = new JPanel();
       dificultades.setLayout(new FlowLayout(FlowLayout.CENTER));
       getContentPane().add(dificultades, BorderLayout.NORTH);
       
       JRadioButton facil = new JRadioButton("Facil");
       facil.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (facil.isSelected()) {
				numeroFilas.setText("3");
				numeroColumnas.setText("4");
				dificultad = "Fácil";
				
			}
			
		}
	});
       dificultades.add(facil);
        
       JRadioButton medio = new JRadioButton("Medio");
       medio.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			if (medio.isSelected()) {
				numeroFilas.setText("4");
				numeroColumnas.setText("5");
				dificultad = "Medio";
				
			}
			
		}
	});
       dificultades.add(medio);
        
       JRadioButton dificil = new JRadioButton("Difícil");
       dificil.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (dificil.isSelected()) {
				numeroFilas.setText("6");
				numeroColumnas.setText("6");
				dificultad = "Dificil";
				
			}
		}
	});
       dificultades.add(dificil);
        
       JRadioButton personalizado = new JRadioButton("Personalizado");
       personalizado.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (personalizado.isSelected()) {
				numeroFilas.setText("");
				numeroColumnas.setText("");
				dificultad = "Personalizado";
			}
			
		}

		
	});
       dificultades.add(personalizado);
       // Creamos un grupo de botones para que solo se pueda seleccionar uno de ellos
       ButtonGroup grupoBotones = new ButtonGroup();
       grupoBotones.add(facil);
       grupoBotones.add(medio);
       grupoBotones.add(dificil);
       grupoBotones.add(personalizado);   
        
	}

	
	
}
