import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.Paint;

class InicioSesion extends JFrame implements ActionListener{

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public double screenX = screenSize.getWidth();
	public double screenY = screenSize.getHeight();

	public JLabel titulo, instruccion, consejo;
	public JButton salir, agregar;
	public JButton [] usuarios = new JButton[20];
	public String [] nombres = new String[50];
	public JPanel botonesUsuarios;

	public MenuPrincipal menu;

	public Contra contra;

	public int i = 0;

	public String nombreUsuario;

	public InicioSesion()
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

				botonesUsuarios = new JPanel();
				botonesUsuarios.setBounds(2*(int)screenX/64,(int)screenY/6,11*(int)screenX/16,(int)screenY/2);
				botonesUsuarios.setLayout(new FlowLayout(FlowLayout.CENTER));

				add(botonesUsuarios);

				visualizarInfo("select * from usuarios");

				agregar = new JButton(new CircleIcon());
				agregar.setText("Agregar Usuario");
				agregar.setVisible(true);
				agregar.setBackground(Color.white);
				agregar.setPreferredSize(new Dimension(150,150));
				agregar.setVisible(true);
				agregar.setBackground(Color.yellow);
				agregar.setOpaque(false);
				agregar.setContentAreaFilled(false);
				agregar.setBorder(null);
				agregar.setVerticalAlignment(SwingConstants.BOTTOM);
				agregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						abrirRegistro();
					}});

				botonesUsuarios.add(agregar);

				instruccion = new JLabel("Seleccione su usuario. El usuario es el operador de los préstamos y de los cambios realizados durante su turno.");
				instruccion.setBounds((int)screenX/64,(int)screenY/10,(int)screenX,30);
				instruccion.setVisible(true);
				instruccion.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 20));
				instruccion.setHorizontalAlignment(SwingConstants.LEFT);
				this.add(instruccion);

				titulo = new JLabel("INICIO DE SESION");
				titulo.setBounds(10,10,(int)screenX/2,50);
				titulo.setVisible(true);
				titulo.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 45));
				titulo.setHorizontalAlignment(SwingConstants.LEFT);
				this.add(titulo);

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

		public void visualizarInfo(String query)
		{
		try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/mydb","root","66xkzHUb3.");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(query);
		while(rs.next()){
			usuarios[i] = new JButton(new CircleIcon());
			usuarios[i].setIcon(new ImageIcon((new ImageIcon("./Icons/User.png").getImage()).getScaledInstance(130,130,java.awt.Image.SCALE_SMOOTH)));
			usuarios[i].setText(rs.getString("nombre"));
			usuarios[i].setPreferredSize(new Dimension(150,150));
			usuarios[i].setVisible(true);
			usuarios[i].setBackground(Color.yellow);
			usuarios[i].setOpaque(false);
			usuarios[i].setContentAreaFilled(false);
			usuarios[i].setBorder(null);
			usuarios[i].setHorizontalAlignment(SwingConstants.CENTER);
			usuarios[i].setForeground(Color.black);
			usuarios[i].setVerticalAlignment(SwingConstants.BOTTOM);
			usuarios[i].setVerticalTextPosition(JButton.BOTTOM);
    		usuarios[i].setHorizontalTextPosition(JButton.CENTER);
			nombres[i]=rs.getString("nombre");
			String contrasu = rs.getString("contraseña");
			String correooa = rs.getString("correo");
			System.out.println(nombres[i]+i+contrasu);
			usuarios[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (int j = 0; j < usuarios.length+1; j++){
					if (e.getSource() == usuarios[j])
					{
						contra = new Contra(contrasu, correooa);
						while(contra.bandera){

						}
						if(contra.accesss){
							abrirMenu(usuarios[j].getText());
						}else if(contra.reinicio){
							InicioSesion nuevamente = new InicioSesion();
							cerraresta();
						}
						break;
					}
					}
				}});
			botonesUsuarios.add(usuarios[i]);
			i = i + 1;
		}con.close();
	}catch(Exception e){ System.out.println(" ");}
		};

		private static class CircleIcon implements Icon{
			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
			Graphics2D g2d = (Graphics2D) g;
			Paint op = g2d.getPaint();
			g2d.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setPaint(Color.blue);
			g2d.fillOval(15, 5, 130, 130);
			g2d.setPaint(op);
			}
			@Override
			public int getIconWidth() {
				return 10;
			}
			@Override
			public int getIconHeight() {
				return 10;
			}
		}

	public void abrirMenu(String a){
		this.dispose();
		menu = new MenuPrincipal();
		menu.ms.cuentaUsuario.setText(a);
	}
	public void abrirRegistro(){
		this.dispose();
		NuevoUsuario nu = new NuevoUsuario();
	}

	public void cerraresta(){
		this.dispose();
	}
}