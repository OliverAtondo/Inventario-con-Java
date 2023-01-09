import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

class Registros extends JDialog implements ActionListener{


    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public double screenX = screenSize.getWidth();
	public double screenY = screenSize.getHeight();

/******************Paleta de Colores*********************/

Color uno = new Color(0,93,164);
Color dos = new Color(0,133,191);
Color tres = new Color(0,172,212);
Color cuatro = new Color(0,186,223);
Color cinco = new Color(0,211,235);
Color seis = new Color(73,226,241);
Color siete = new Color(138,237,246);
Color ocho = new Color(197,246,251);
Color nueve = new Color(228,251,253);

Color finalizar = new Color(243,91,104);

JPanel principal = new JPanel(null);

JButton materiaPrima, materialProduccion, productoTerminado, agregar, cerrar;
JLabel nombre, modelo, identificador, estado, ubicacion, combo;
JTextField jtfNombre, jtfModelo, jtfIdentificador;

String categoria;

public JComboBox estados, grupos, salas;

private boolean materialProduccionBln, materialProductoTerminadobln;

	public Registros()
	{
						this.setLayout(null);
						principal.setLayout(null);
						principal.setBounds(0,0,640,480);
						principal.setBackground(nueve);
                        principal.setBorder( BorderFactory.createLineBorder(Color.black) );

				cerrar = new JButton("X");
				cerrar.setBounds(590,0,50,40);
				cerrar.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 12));
				cerrar.setForeground(Color.white);
				cerrar.setContentAreaFilled(false);
				cerrar.setBorderPainted(false);
				cerrar.setBackground(Color.red);
				cerrar.setOpaque(true);
				cerrar.addActionListener(this);

				materiaPrima = new JButton("Maniqui");
				materiaPrima.setBounds(80,50,160,40);
				materiaPrima.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 12));
				materiaPrima.setContentAreaFilled(false);
				materiaPrima.setBorderPainted(false);
				materiaPrima.setBackground(seis);
				materiaPrima.setOpaque(true);
			  	materiaPrima.addActionListener(this);

				materialProduccion = new JButton("Instrumento");
				materialProduccion.setBounds(240,50,160,40);
				materialProduccion.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 12));
				materialProduccion.setContentAreaFilled(false);
				materialProduccion.setBorderPainted(false);
				materialProduccion.setBackground(seis);
				materialProduccion.setOpaque(true);
				materialProduccion.addActionListener(this);

				productoTerminado = new JButton("Complementos");
				productoTerminado.setBounds(400,50,160,40);
				productoTerminado.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 12));
				productoTerminado.setContentAreaFilled(false);
				productoTerminado.setBorderPainted(false);
				productoTerminado.setBackground(seis);
				productoTerminado.setOpaque(true);
				productoTerminado.addActionListener(this);

				agregar = new JButton("Agregar");
				agregar.setBounds(280,400,80,40);
				agregar.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 12));
				agregar.setContentAreaFilled(false);
				agregar.setBorderPainted(false);
				agregar.setBackground(seis);
				agregar.setOpaque(true);
				agregar.addActionListener(this);
				agregar.setEnabled(false);

				nombre = new JLabel("Nombre: ");
				nombre.setBounds(80,120,100,40);
				nombre.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 15));

				jtfNombre = new JTextField();
				jtfNombre.setBounds(140,120,420,40);
				jtfNombre.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 15));

                modelo = new JLabel("Modelo: ");
				modelo.setBounds(80,170,100,40);
				modelo.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 15));

				jtfModelo = new JTextField();
				jtfModelo.setBounds(140,170,420,40);
				jtfModelo.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 15));

                identificador = new JLabel("ID: ");
				identificador.setBounds(80,220,100,40);
				identificador.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 15));

				jtfIdentificador = new JTextField();
				jtfIdentificador.setBounds(140,220,420,40);
				jtfIdentificador.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 15));

                estado = new JLabel("Estado: ");
				estado.setBounds(80,270,100,40);
				estado.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 15));

                String combosa[] = {"Nuevo","Semi-Nuevo","Usado", "Muy Usado"};
                estados = new JComboBox(combosa);
                estados.setBounds(140,270,150,40);
				estados.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 15));

                ubicacion = new JLabel("Ubicacion: ");
				ubicacion.setBounds(300,270,100,40);
				ubicacion.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 15));
				ubicacion.setHorizontalAlignment(SwingConstants.RIGHT);

                String combosb[] = {"Sala 1","Sala 2","Sala 3", "Sala 4", "Sala 5", "Sala 6", "Sala 7"};
                salas = new JComboBox(combosb);
                salas.setBounds(400,270,150,40);
				salas.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 15));

                combo = new JLabel("Grupo/Complemento: ");
				combo.setBounds(80,320,150,40);
				combo.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 15));

				String combosc[] = {"BLS", "ACLS", "AUSCULTACION", "EXAMEN 1"};
                grupos = new JComboBox(combosc);
                grupos.setBounds(230,320,150,40);
				grupos.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 15));

				principal.add(agregar);
				principal.add(nombre);
				this.add(jtfNombre);
				this.add(modelo);
                this.add(jtfModelo);
                this.add(identificador);
                this.add(jtfIdentificador);
                this.add(estado);
                this.add(ubicacion);
                this.add(combo);
                this.add(estados);
                this.add(salas);
				this.add(grupos);
				principal.add(materiaPrima);
				principal.add(materialProduccion);
				principal.add(productoTerminado);
				//principal.add(cerrar);

				this.add(principal);

				this.setModal(true);
				this.setAlwaysOnTop(true);
				this.setTitle("AGREGAR PRODUCTO");
				this.setBounds(500,200,680,500);
				//this.setUndecorated(true);
				this.setVisible(true);
                this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
				getRootPane().setBorder( BorderFactory.createLineBorder(Color.black) );

			}

			public void actionPerformed(ActionEvent event)
			{
				if(event.getSource()==agregar)
				{

					if(materialProduccionBln)
					{
						System.out.println("Se agrego un Instrumento");
						categoria = "Instrumento";
						subirDatos("call mydb.registrarObjeto("+jtfIdentificador.getText()+", '"+jtfNombre.getText()+"', '"+jtfModelo.getText()+"', '"+String.valueOf(estados.getSelectedItem())+"', null, 1, '"+"No prestado"+"', '"+String.valueOf(grupos.getSelectedItem())+"', '"+categoria+"');");
						System.out.println("jalo 2");
					}
					else if(materialProductoTerminadobln)
					{
						System.out.println("Se modifico un grupo");
						subirDatos("call actualizarGrupoObjeto("+jtfIdentificador.getText()+",'"+String.valueOf(grupos.getSelectedItem())+"');");
						System.out.println("call actualizarGrupoObjeto("+jtfIdentificador.getText()+",'"+String.valueOf(grupos.getSelectedItem())+"');");
					}
					else{

						System.out.println("Se agrego un maniqui");
						categoria = "Maniqui";
						subirDatos("call mydb.registrarObjeto("+jtfIdentificador.getText()+", '"+jtfNombre.getText()+"', '"+jtfModelo.getText()+"', '"+String.valueOf(estados.getSelectedItem())+"', null, 1, '"+"No prestado"+"', '"+String.valueOf(grupos.getSelectedItem())+"', '"+categoria+"');");
						System.out.println("jalo 1");
					}
					this.dispose();

				}
				else if(event.getSource()==cerrar)
				{
					this.dispose();
				}
				else if(event.getSource()==materiaPrima)
				{
					materiaPrima.setBackground(seis);
					materialProduccion.setBackground(cuatro);
					productoTerminado.setBackground(cuatro);

					jtfIdentificador.setEnabled(true);
					jtfModelo.setEnabled(true);
					jtfNombre.setEnabled(true);
					estados.setEnabled(true);
					grupos.setEnabled(true);
					salas.setEnabled(true);

					agregar.setEnabled(true);

				}
				else if(event.getSource()==materialProduccion)
				{
					materiaPrima.setBackground(cuatro);
					materialProduccion.setBackground(seis);
					productoTerminado.setBackground(cuatro);

					materialProduccionBln = true;
					materialProductoTerminadobln = false;

					jtfIdentificador.setEnabled(true);
					jtfModelo.setEnabled(true);
					jtfNombre.setEnabled(true);
					estados.setEnabled(true);
					grupos.setEnabled(true);
					salas.setEnabled(true);

					agregar.setEnabled(true);

				}
				else if(event.getSource()==productoTerminado)
				{
					materiaPrima.setBackground(cuatro);
					materialProduccion.setBackground(cuatro);
					productoTerminado.setBackground(seis);

					materialProductoTerminadobln = true;
					materialProduccionBln = false;

					jtfIdentificador.setEnabled(true);
					jtfModelo.setEnabled(false);
					jtfNombre.setEnabled(false);
					estados.setEnabled(false);
					grupos.setEnabled(true);
					salas.setEnabled(false);

					agregar.setEnabled(true);

				}

			}

		public void subirDatos(String query)
		{
		try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/mydb","root","66xkzHUb3.");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(query);
		con.close();
		System.out.println("[Exito]");
	}catch(Exception e){ System.out.println(e);}
		};

}
