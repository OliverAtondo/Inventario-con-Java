import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
//import org.json.*;

class NuevoUsuario extends JFrame implements ActionListener{

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public double screenX = screenSize.getWidth();
	public double screenY = screenSize.getHeight();

	Color uno = new Color(0,93,164);
	Color dos = new Color(0,133,191);
	Color tres = new Color(0,172,212);
	Color cuatro = new Color(0,186,223);
	Color cinco = new Color(0,211,235);
	Color seis = new Color(73,226,241);
	Color siete = new Color(138,237,246);
	Color ocho = new Color(197,246,251);
	Color nueve = new Color(228,251,253);

	public JLabel barra;

	public JLabel titulo, instruccion, nombre;
    public JTextField nom, idem, apell, coorr, passs, passs2;
	public JButton salir, registrar;

	public NuevoUsuario()
	{
		this.setTitle("VERSION BETA INVENTARIO");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			this.setUndecorated(
				true);
			this.setVisible(true);
			this.setBounds((int)screenX/8,(int)screenY/8,3*(int)screenX/4,3*(int)screenY/4);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setVisible(true);
			this.setLayout(null);
			this.getContentPane().setBackground( Color.white );

			    titulo = new JLabel("REGISTRO");
				titulo.setBounds(0,0,3*(int)screenX/4,(int)screenY/16);
				titulo.setVisible(true);
				titulo.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, (int)screenY/16));
				titulo.setHorizontalAlignment(SwingConstants.CENTER);
				titulo.setBackground(uno);
				titulo.setForeground(Color.white);
				titulo.setOpaque(true);
				this.add(titulo);

                instruccion = new JLabel("Bienvenido. Ingrese el identificador (matricula o numero de control) y nombre de usuario.");
				instruccion.setBounds((int)screenX/64,(int)screenY/10,(int)screenX,30);
				instruccion.setVisible(true);
				instruccion.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 20));
				instruccion.setHorizontalAlignment(SwingConstants.LEFT);
				this.add(instruccion);

                nombre = new JLabel("<html> <style> div{text-align: RIGHT;} </style> <div> Nombre(s): <br><br> Apellidos: <br><br> Correo: <br><br> Identificador: <br><br> Contraseña: <br><br> Nuevamente: </div> </html>");
				nombre.setBounds((int)screenX/64,(int)screenY/8,(int)screenX/14,(int)screenY/3);
				nombre.setVisible(true);
				nombre.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, (int)screenY/50));
				nombre.setHorizontalAlignment(SwingConstants.RIGHT);
				nombre.setVerticalAlignment(SwingConstants.TOP);
				nombre.setForeground(Color.black);
				this.add(nombre);

                nom = new JTextField();
				nom.setBounds((int)screenX/10,((int)screenY/8),(int)screenX/4,(int)screenY/30);
				nom.setVisible(true);
				nom.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, (int)screenY/60));
				nom.setHorizontalAlignment(SwingConstants.LEFT);
				this.add(nom);

				apell = new JTextField();
				apell.setBounds((int)screenX/10,11*(int)screenY/64,(int)screenX/4,(int)screenY/30);
				apell.setVisible(true);
				apell.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, (int)screenY/60));
				apell.setHorizontalAlignment(SwingConstants.LEFT);
				this.add(apell);

				coorr = new JTextField();
				coorr.setBounds((int)screenX/10,14*(int)screenY/64,(int)screenX/4,(int)screenY/30);
				coorr.setVisible(true);
				coorr.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, (int)screenY/60));
				coorr.setHorizontalAlignment(SwingConstants.LEFT);
				this.add(coorr);

				idem = new JTextField();
				idem.setBounds((int)screenX/10,35*(int)screenY/128,(int)screenX/4,(int)screenY/30);
				idem.setVisible(true);
				idem.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, (int)screenY/60));
				idem.setHorizontalAlignment(SwingConstants.LEFT);
				this.add(idem);

				passs = new JTextField();
				passs.setBounds((int)screenX/10,42*(int)screenY/128,(int)screenX/4,(int)screenY/30);
				passs.setVisible(true);
				passs.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, (int)screenY/60));
				passs.setHorizontalAlignment(SwingConstants.LEFT);
				this.add(passs);

				passs2 = new JTextField();
				passs2.setBounds((int)screenX/10,48*(int)screenY/128,(int)screenX/4,(int)screenY/30);
				passs2.setVisible(true);
				passs2.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, (int)screenY/60));
				passs2.setHorizontalAlignment(SwingConstants.LEFT);
				this.add(passs2);

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
				if(passs2.getText() != null && passs.getText() != null && nom.getText() != null && apell.getText() != null && coorr.getText() != null){
				if(passs.getText().equals(passs2.getText())){
					new Mailer().sendEmail(coorr.getText(),"CLAMPS INVETARIO", new TemplatesCorreo().enviarBienvenida(nom.getText()));
					subirDatos("call registrarUsuario(\""+idem.getText()+"\", \""+nom.getText()+"\", \""+apell.getText()+"\", now(), \""+passs.getText()+"\", \""+coorr.getText()+"\");");
                	abrirSesion();
				} else {
					instruccion.setText("Las contraseñas no son iguales");
				}
			} else {
				instruccion.setText("Debes llenar todos los campos.");
			}
			 }});
			 this.add(registrar);

				this.invalidate();
				this.revalidate();
				this.repaint();
			}

			public void actionPerformed(ActionEvent event)
			{
				System.out.println("ERROR DE BOTONES TACTIL FUNCIONAL");
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

	public void abrirSesion(){
		this.dispose();
		InicioSesion ses = new InicioSesion();
	}
}