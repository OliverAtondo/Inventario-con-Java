import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
//import org.json.*;
import java.sql.*;

class VentanaPrograma extends JFrame implements ActionListener, KeyListener{

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public double screenX = screenSize.getWidth();
	public double screenY = screenSize.getHeight();

	public JLabel n;
	public JLabel menu, actual;
	public JTextField identificadorTEXT;
	public JButton subir;

	public JLabel lineaCentral,nombreLbl,idLbl,modeloLbl,estadoLbl,ubicacionLbl, prestadoLbl;
	public JTextField nombreIn,idIn,modeloIn,estadoIn,ubicacionIn, prestadoIn;
	public JButton registrar,ver;
	public JLabel nombreLbl2,idLbl2,modeloLbl2,estadoLbl2,ubicacionLbl2, prestadoLbl2, ultimoMatenimiento, grupoComplementos;

	public VentanaPrograma()
	{
	visualizarInfo("call seleccionarObjeto(19212369);");

	this.setTitle("VERSION BETA INVENTARIO");
	this.setBounds(100,100,(int)screenX,(int)screenY);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setVisible(true);
	this.setLayout(null);

				menu = new JLabel("INVENTARIO MEDICO");
				menu.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 80));
				menu.setBounds(0,0,(int)screenX,(int)screenY/8);
				menu.setBackground(Color.blue);
				menu.setForeground(Color.white);
				menu.setOpaque(true);
				menu.setBorder(null);
				menu.setHorizontalAlignment(SwingConstants.CENTER);
				this.add(menu);

				lineaCentral = new JLabel();
				lineaCentral.setBounds(((int)screenX/2)-2,((int)screenY/8)+10,4,(7*(int)screenY/8)-15);
				lineaCentral.setBackground(Color.black);
				lineaCentral.setOpaque(true);
				lineaCentral.setBorder(null);
				this.add(lineaCentral);

				nombreLbl = new JLabel("Nombre");
				nombreLbl.setBounds(10,((int)screenY/8)+10,((int)screenX/4)-10,((int)screenY/16));
				nombreLbl.setVisible(true);
				nombreLbl.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 30));
				nombreLbl.setHorizontalAlignment(SwingConstants.CENTER);
				this.add(nombreLbl);

				idLbl = new JLabel("Identificador");
				idLbl.setBounds(((int)screenX/4)-10,((int)screenY/8)+10,((int)screenX/4)-10,((int)screenY/16));
				idLbl.setVisible(true);
				idLbl.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 30));
				idLbl.setHorizontalAlignment(SwingConstants.CENTER);
				this.add(idLbl);

				modeloLbl = new JLabel("Modelo");
				modeloLbl.setBounds(10,(2*(int)screenY/8)+10,((int)screenX/4)-10,((int)screenY/16));
				modeloLbl.setVisible(true);
				modeloLbl.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 30));
				modeloLbl.setHorizontalAlignment(SwingConstants.CENTER);
				this.add(modeloLbl);

				estadoLbl = new JLabel("Estado");
				estadoLbl.setBounds(((int)screenX/4)-10,(2*(int)screenY/8)+10,((int)screenX/4)-10,((int)screenY/16));
				estadoLbl.setVisible(true);
				estadoLbl.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 30));
				estadoLbl.setHorizontalAlignment(SwingConstants.CENTER);
				this.add(estadoLbl);

				ubicacionLbl = new JLabel("Ubicacion");
				ubicacionLbl.setBounds(10,(3*(int)screenY/8)+10,((int)screenX/4)-10,((int)screenY/16));
				ubicacionLbl.setVisible(true);
				ubicacionLbl.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 30));
				ubicacionLbl.setHorizontalAlignment(SwingConstants.CENTER);
				this.add(ubicacionLbl);

				prestadoLbl = new JLabel("Prestado");
				prestadoLbl.setBounds(((int)screenX/4)-10,(3*(int)screenY/8)+10,((int)screenX/4)-10,((int)screenY/16));
				prestadoLbl.setVisible(true);
				prestadoLbl.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 30));
				prestadoLbl.setHorizontalAlignment(SwingConstants.CENTER);
				this.add(prestadoLbl);

				//Text

				nombreIn = new JTextField();
				nombreIn.setBounds(10,3*(int)screenY/16,((int)screenX/4)-10,((int)screenY/16));
				nombreIn.setVisible(true);
				nombreIn.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 30));
				nombreIn.setHorizontalAlignment(SwingConstants.CENTER);
				this.add(nombreIn);

				idIn = new JTextField();
				idIn.setBounds(((int)screenX/4)-10,3*(int)screenY/16,((int)screenX/4)-10,((int)screenY/16));
				idIn.setVisible(true);
				idIn.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 30));
				idIn.setHorizontalAlignment(SwingConstants.CENTER);
				this.add(idIn);

				modeloIn = new JTextField();
				modeloIn.setBounds(10,5*(int)screenY/16,((int)screenX/4)-10,((int)screenY/16));
				modeloIn.setVisible(true);
				modeloIn.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 30));
				modeloIn.setHorizontalAlignment(SwingConstants.CENTER);
				this.add(modeloIn);

				estadoIn = new JTextField();
				estadoIn.setBounds(((int)screenX/4)-10,5*(int)screenY/16,((int)screenX/4)-10,((int)screenY/16));
				estadoIn.setVisible(true);
				estadoIn.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 30));
				estadoIn.setHorizontalAlignment(SwingConstants.CENTER);
				this.add(estadoIn);

				ubicacionIn = new JTextField();
				ubicacionIn.setBounds(10,7*(int)screenY/16,((int)screenX/4)-10,((int)screenY/16));
				ubicacionIn.setVisible(true);
				ubicacionIn.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 30));
				ubicacionIn.setHorizontalAlignment(SwingConstants.CENTER);
				this.add(ubicacionIn);

				prestadoIn = new JTextField();
				prestadoIn.setBounds(((int)screenX/4)-10,(7*(int)screenY/16),((int)screenX/4)-10,((int)screenY/16));
				prestadoIn.setVisible(true);
				prestadoIn.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 30));
				prestadoIn.setHorizontalAlignment(SwingConstants.CENTER);
				this.add(prestadoIn);

				//RESULTADOS
				nombreLbl2 = new JLabel("Nombre");
				nombreLbl2.setBounds(2*((int)screenX/4)-10,((int)screenY/8)+10,((int)screenX/4)-10,((int)screenY/16));
				nombreLbl2.setVisible(true);
				nombreLbl2.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 30));
				nombreLbl2.setHorizontalAlignment(SwingConstants.CENTER);
				this.add(nombreLbl2);

				idLbl2 = new JLabel("Identificador");
				idLbl2.setBounds(3*((int)screenX/4)-10,((int)screenY/8)+10,((int)screenX/4)-10,((int)screenY/16));
				idLbl2.setVisible(true);
				idLbl2.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 30));
				idLbl2.setHorizontalAlignment(SwingConstants.CENTER);
				this.add(idLbl2);

				modeloLbl2 = new JLabel("Modelo");
				modeloLbl2.setBounds(2*((int)screenX/4)-10,(2*(int)screenY/8)+10,((int)screenX/4)-10,((int)screenY/16));
				modeloLbl2.setVisible(true);
				modeloLbl2.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 30));
				modeloLbl2.setHorizontalAlignment(SwingConstants.CENTER);
				this.add(modeloLbl2);

				estadoLbl2 = new JLabel("Estado");
				estadoLbl2.setBounds(3*((int)screenX/4)-10,(2*(int)screenY/8)+10,((int)screenX/4)-10,((int)screenY/16));
				estadoLbl2.setVisible(true);
				estadoLbl2.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 30));
				estadoLbl2.setHorizontalAlignment(SwingConstants.CENTER);
				this.add(estadoLbl2);

				ubicacionLbl2 = new JLabel("Ubicacion");
				ubicacionLbl2.setBounds(2*((int)screenX/4)-10,(3*(int)screenY/8)+10,((int)screenX/4)-10,((int)screenY/16));
				ubicacionLbl2.setVisible(true);
				ubicacionLbl2.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 30));
				ubicacionLbl2.setHorizontalAlignment(SwingConstants.CENTER);
				this.add(ubicacionLbl2);

				identificadorTEXT = new JTextField();
				identificadorTEXT.setBounds(3*((int)screenX/4)-300,3*(int)screenY/16,((int)screenX/4)-10,((int)screenY/16));
				identificadorTEXT.setVisible(true);
				identificadorTEXT.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 30));
				identificadorTEXT.setHorizontalAlignment(SwingConstants.CENTER);
				this.add(identificadorTEXT);

				registrar = new JButton("Registrar");
				registrar.setBounds(((int)screenX/8),(9*(int)screenY/16),((int)screenX/4)-10,40);
				registrar.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 24));
				registrar.setForeground(Color.white);
				registrar.setBackground(Color.black);
				registrar.setOpaque(true);
				registrar.setContentAreaFilled(true);
				registrar.setBorder(null);
				registrar.setHorizontalAlignment(SwingConstants.CENTER);
				registrar.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				subirDatos("call registrarObjeto("+idIn.getText()+",\""+nombreIn.getText()+"\", \""+modeloIn.getText()+"\", \""+estadoIn.getText()+"\", null, "+ubicacionIn.getText()+", "+"\"No prestado\""+", null);");
			 }});
			 this.add(registrar);

				this.invalidate();
				this.revalidate();
				this.repaint();
				identificadorTEXT.addKeyListener(this);
				addKeyListener(this);
				setFocusable(true);
        		setFocusTraversalKeysEnabled(false);
			}

			public void actionPerformed(ActionEvent event)
			{
				System.out.println("ERROR DE BOTONES TACTIL FUNCIONAL");
			}

			public void keyReleased(KeyEvent e){
				if (identificadorTEXT.getText().length()==4){
					visualizarInfo("call seleccionarObjeto("+identificadorTEXT.getText()+");");
				}
				System.out.println("tecla suelta");
			}

			public void keyPressed(KeyEvent e){
				System.out.println("tecla presionada");
			}

			public void keyTyped(KeyEvent e){
				System.out.println("tecla presionandose");
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

		public void visualizarInfo(String query)
		{
		try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/mydb","root","66xkzHUb3.");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(query);
		while(rs.next()){
			nombreLbl2.setText(rs.getString("nombre"));
			idLbl2.setText(rs.getString("id"));
			modeloLbl2.setText(rs.getString("modelo"));
			estadoLbl2.setText(rs.getString("estado"));
			ubicacionLbl2.setText(rs.getString("ubicacion"));
		con.close();
		}
	}catch(Exception e){ System.out.println(" ");}
		};

}