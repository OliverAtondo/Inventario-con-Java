import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.lang.*;
import javax.swing.table.DefaultTableModel;
//import org.json.*;
import java.sql.*;
import java.time.chrono.ThaiBuddhistChronology;

class MenuPrincipal extends JFrame implements ActionListener, KeyListener{

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

	public Color [] pinta = {dos, tres, dos};

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public double screenX = screenSize.getWidth();
	public double screenY = screenSize.getHeight();

	public JLabel n;

	public MenuSuperior ms;

	public JLabel lineaCentral [] = new JLabel[3];
	public JLabel datoCentral[] = new JLabel[3];
	public String etiquetas[] = {"Objetos","Ficha de Expedicion","Datos del Objeto"};

	public JLabel imagen, idlbl, modelo, nomlbl, estadolbl, ultimoM, prestado, compleme, categ;

	public JPanel catalogo, prestamos;
	public MapaChido mapa;
	public JScrollPane impresiones, elpdf, muestra;
	public JButton salirBi;

	public JButton [] botonesManiquies = new JButton[6000];

	public JToggleButton mostrarMapa = new JToggleButton("Mostrar Ubicacion");
	public JScrollPane scrollBotonesProductos;
	int alturaDelPanelProductos;

		public	String [] ids = new String[6000];
		public	String [] modeloss = new String[6000];
		public	String [] nombress = new String[6000];
		public	String [] estadoss = new String[6000];
		public	String [] ultimates = new String[6000];
		public	String [] prestadoss = new String[6000];
		public	String [] complee = new String[6000];
		public	String [] categs = new String[6000];

	int i = 0;
		public ArrayList<String> listaObjetos = new ArrayList<String>();
		public JTextArea objetosLista = new JTextArea();


		public JTextField objetosAPrestarFocus = new JTextField();

		public JTable table;

		public String[] columnNames = {"ID", "Nombre", "Modelo", "Grupo"};


		public DefaultTableModel dtm= new DefaultTableModel(null, columnNames);


	public MenuPrincipal()
	{
	this.setResizable(false);
	this.setVisible(true);
	this.setBounds(0,0,(int)screenX,(int)screenY);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setVisible(true);
	this.setLayout(null);
	this.getContentPane().setBackground( Color.white );

	this.addWindowListener( new WindowAdapter() {
		public void windowOpened( WindowEvent e ){
			objetosAPrestarFocus.requestFocus();
		}
	});

				ms = new MenuSuperior("INTRACLAMPS", (int)screenY/32 , Color.white);
				ms.setBounds(0,0,(int)screenX,(int)screenY/8);
				ms.setVisible(true);
				add(ms);

				ms.chgcuenta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						abrirSesion();
					}
				});

				ms.registrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						Registros r = new Registros();
				}
				});

				ms.eliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						VentanaEliminarProducto bb = new VentanaEliminarProducto();
				}
				});

				ms.cata.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						catalogo.removeAll();
						catalogo.revalidate();
        				catalogo.repaint();
						visualizarInfo("SELECT * from objeto");
						scrollBotonesProductos.setVisible(true);
						salirBi.setVisible(true);
				}
				});

				catalogo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        		catalogo.removeAll();
        		catalogo.revalidate();
        		catalogo.repaint();
				catalogo.setBackground(Color.white);
				catalogo.setOpaque(true);;
        		catalogo.revalidate();
        		catalogo.repaint();

        		scrollBotonesProductos = new JScrollPane(catalogo);
       			scrollBotonesProductos.setBounds(0,(int)screenY/8,2*(int)screenX/3,(int)screenY-110);
        		scrollBotonesProductos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        		scrollBotonesProductos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollBotonesProductos.setVisible(false);

				this.add(scrollBotonesProductos);

				for(int i = 0; i < lineaCentral.length; i++){
				lineaCentral[i] = new JLabel();
				lineaCentral[i].setBounds(((i+1)*(int)screenX/3)-2,((int)screenY/8)+10,4,(7*(int)screenY/8)-15);
				lineaCentral[i].setBackground(Color.black);
				lineaCentral[i].setOpaque(true);
				lineaCentral[i].setBorder(null);
				datoCentral[i] = new JLabel(etiquetas[i]);
				datoCentral[i].setBounds((i*(int)screenX/3),((int)screenY/8),(int)screenX/3,(int)screenY/8);
				datoCentral[i].setFont(new Font("BebasNeue-Regular.ttf", Font.BOLD, 30));
				datoCentral[i].setForeground(Color.white);
				datoCentral[i].setHorizontalAlignment(SwingConstants.CENTER);
				datoCentral[i].setBackground(pinta[i]);
				datoCentral[i].setOpaque(true);
				datoCentral[i].setBorder(null);
				this.add(lineaCentral[i]);
				this.add(datoCentral[i]);
				}


				lineaCentral[0].setVisible(false);
				datoCentral[0].setBounds((0*(int)screenX/3),((int)screenY/8),2*(int)screenX/3,(int)screenY/8);
				datoCentral[1].setVisible(false);
				datoCentral[0].setBackground(cuatro);
				lineaCentral[1].setBounds(((2)*(int)screenX/3)-2,((int)screenY/8)+10,4,(7*(int)screenY/8)-15);
				repaint();

				categ = new JLabel("CATEGORIA");
				categ.setBounds((2*(int)screenX/3) + (1*(int)screenX/64),(9*(int)screenY/32),19*(int)screenX/64,(int)screenY/30);
				categ.setBackground(dos);
				categ.setVisible(true);
				categ.setOpaque(true);
				categ.setFont(new Font("BebasNeue-Regular.ttf", Font.BOLD, (int)screenY/32));
				categ.setHorizontalAlignment(SwingConstants.CENTER);
				categ.setVerticalAlignment(SwingConstants.CENTER);
				categ.setForeground(Color.white);
				add(categ);

				imagen = new JLabel();
				imagen.setBounds((2*(int)screenX/3) + (1*(int)screenX/64),(9*(int)screenY/32),19*(int)screenX/64,(int)screenY/3);
				imagen.setBackground(cinco);
				imagen.setVisible(true);
				imagen.setOpaque(true);
				add(imagen);

				mapa = new MapaChido(1);
				mapa.setBounds((2*(int)screenX/3) + (1*(int)screenX/64),(21*(int)screenY/32),19*(int)screenX/64,(int)screenY/3);
				mapa.setVisible(false);
				add(mapa);

				idlbl = new JLabel("ID: ");
				idlbl.setBounds((2*(int)screenX/3) + (1*(int)screenX/64),(21*(int)screenY/32),19*(int)screenX/64,(int)screenY/32);
				idlbl.setBackground(cinco);
				idlbl.setVisible(true);
				idlbl.setOpaque(true);
				idlbl.setFont(new Font("BebasNeue-Regular.ttf", Font.BOLD, (int)screenY/32));
				add(idlbl);

				nomlbl = new JLabel("Nombre: ");
				nomlbl.setBounds((2*(int)screenX/3) + (1*(int)screenX/64),(22*(int)screenY/32),19*(int)screenX/64,(int)screenY/32);
				nomlbl.setBackground(cuatro);
				nomlbl.setVisible(true);
				nomlbl.setOpaque(true);
				nomlbl.setFont(new Font("BebasNeue-Regular.ttf", Font.BOLD, (int)screenY/32));
				add(nomlbl);

				modelo = new JLabel("Modelo: ");
				modelo.setBounds((2*(int)screenX/3) + (1*(int)screenX/64),(23*(int)screenY/32),19*(int)screenX/64,(int)screenY/32);
				modelo.setBackground(cinco);
				modelo.setVisible(true);
				modelo.setOpaque(true);
				modelo.setFont(new Font("BebasNeue-Regular.ttf", Font.BOLD, (int)screenY/32));
				add(modelo);

				estadolbl = new JLabel("Estado: ");
				estadolbl.setBounds((2*(int)screenX/3) + (1*(int)screenX/64),(24*(int)screenY/32),19*(int)screenX/64,(int)screenY/32);
				estadolbl.setBackground(cuatro);
				estadolbl.setVisible(true);
				estadolbl.setOpaque(true);
				estadolbl.setFont(new Font("BebasNeue-Regular.ttf", Font.BOLD, (int)screenY/32));
				add(estadolbl);

				ultimoM = new JLabel("Ult. Mante.: ");
				ultimoM.setBounds((2*(int)screenX/3) + (1*(int)screenX/64),(25*(int)screenY/32),19*(int)screenX/64,(int)screenY/32);
				ultimoM.setBackground(cinco);
				ultimoM.setVisible(true);
				ultimoM.setOpaque(true);
				ultimoM.setFont(new Font("BebasNeue-Regular.ttf", Font.BOLD, (int)screenY/32));
				add(ultimoM);

				prestado = new JLabel("Prestado: ");
				prestado.setBounds((2*(int)screenX/3) + (1*(int)screenX/64),(26*(int)screenY/32),19*(int)screenX/64,(int)screenY/32);
				prestado.setBackground(cuatro);
				prestado.setVisible(true);
				prestado.setOpaque(true);
				prestado.setFont(new Font("BebasNeue-Regular.ttf", Font.BOLD, (int)screenY/32));
				add(prestado);

				compleme = new JLabel("Grupo: ");
				compleme.setBounds((2*(int)screenX/3) + (1*(int)screenX/64),(27*(int)screenY/32),19*(int)screenX/64,(int)screenY/32);
				compleme.setBackground(cinco);
				compleme.setVisible(true);
				compleme.setOpaque(true);
				compleme.setFont(new Font("BebasNeue-Regular.ttf", Font.BOLD, (int)screenY/32));
				add(compleme);

				salirBi = new JButton("Salir de Catalogo");
				salirBi.setBounds((2*(int)screenX/3) + (1*(int)screenX/64),(29*(int)screenY/32),19*(int)screenX/64,(int)screenY/32);
				salirBi.setBackground(cuatro);
				salirBi.setVisible(false);
				salirBi.setOpaque(true);
				salirBi.setFont(new Font("BebasNeue-Regular.ttf", Font.BOLD, (int)screenY/32));
				salirBi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						cerrarPanel();
				}
				});
				add(salirBi);

				this.invalidate();
				this.revalidate();
				this.repaint();
				this.setTitle("VERSION BETA INVENTARIO");
			}

			public void actionPerformed(ActionEvent event)
			{
				System.out.println("ERROR DE BOTONES TACTIL FUNCIONAL");
			}

			public void keyReleased(KeyEvent e){
				/*if (identificadorTEXT.getText().length()==4){
					visualizarInfo("call seleccionarObjeto("+identificadorTEXT.getText()+");");
				}
				System.out.println("tecla suelta");*/
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
			ids[i] = "ID: "+rs.getString("id");
			modeloss[i] = "Modelo: "+rs.getString("modelo");
			nombress[i] = "Nombre: "+rs.getString("nombre");
			estadoss[i] = "Estado: "+rs.getString("estado");
			ultimates[i] = "Ult. Mante.: "+rs.getString("ultimoMantenimiento");
			prestadoss[i] = "Prestado: "+rs.getString("prestado");
			complee[i] = "Grupo: "+rs.getString("complementos");
			categs[i] = rs.getString("categoria");

			System.out.println(ids[i] +" "+modeloss[i] +" "+nombress[i] +" "+estadoss[i] +" "+ultimates[i] +" "+prestadoss[i] +" "+complee[i] +" "+categs[i]);
		// imagen, idlbl, modelo, nomlbl, estadolbl, ultimoM, prestado, compleme, categ
            botonesManiquies[i] = new JButton("<html>"+rs.getString("nombre")+"<br>"+rs.getString("id")+"</br><html>");
            botonesManiquies[i].setPreferredSize(new Dimension(175,100));
            botonesManiquies[i].setIcon(new ImageIcon((new ImageIcon("./Icons/man.png").getImage()).getScaledInstance(17,50,java.awt.Image.SCALE_SMOOTH)));
            botonesManiquies[i].setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 15));
            botonesManiquies[i].setHorizontalTextPosition(JButton.RIGHT);
            botonesManiquies[i].setVerticalTextPosition(JButton.TOP);
            botonesManiquies[i].setHorizontalAlignment(SwingConstants.LEFT);
            botonesManiquies[i].setContentAreaFilled(false);
            botonesManiquies[i].setBorderPainted(false);
            botonesManiquies[i].setOpaque(true);
            botonesManiquies[i].setBackground(cuatro);
            botonesManiquies[i].setForeground(Color.white);
            botonesManiquies[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (int j = 0; j < botonesManiquies.length+1; j++){
						// imagen, idlbl, modelo, nomlbl, estadolbl, ultimoM, prestado, compleme, categ
					if (e.getSource() == botonesManiquies[j])
					{
						categ.setText(categs[j]);
						idlbl.setText(ids[j]);
						modelo.setText(modeloss[j]);
						compleme.setText(complee[j]);
						nomlbl.setText(nombress[j]);
						estadolbl.setText(estadoss[j]);
						ultimoM.setText(ultimates[j]);
						prestado.setText(prestadoss[j]);
						System.out.println("AJUA SI JALA ESTE PEDO");
						break;
					}
					}
				}});
		  	catalogo.add(botonesManiquies[i]);
            alturaDelPanelProductos = alturaDelPanelProductos + 90;
            System.out.println(botonesManiquies[i].getText());
            catalogo.setPreferredSize(new Dimension(600,alturaDelPanelProductos/2));
			catalogo.setBackground(Color.white);
			catalogo.setBorder(null);
        i = i + 1;
      }con.close(); i = 0;
    }catch(Exception e){ System.out.println("F");}
      };

	public void actualizarTabla(String id)
      {
		ArrayList<String> listado = new ArrayList<>();
      try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con=DriverManager.getConnection(
      "jdbc:mysql://localhost:3306/mydb","root","66xkzHUb3.");
      Statement stmt=con.createStatement();
      ResultSet rs=stmt.executeQuery("select * from objeto where objeto.id = "+id+";");
      while(rs.next()){
			Object[] newRow={rs.getString("id"), rs.getString("nombre"), rs.getString("modelo"), rs.getString("complementos")}; 
			dtm.addRow(newRow);
      }
	  con.close();
    }catch(Exception e){
	System.out.println("F");
	}
      };

		public void abrirSesion(){
			this.dispose();
			InicioSesion ses = new InicioSesion();
		}

		public void cerrarPanel(){
			scrollBotonesProductos.setVisible(false);
			salirBi.setVisible(false);
						categ.setText("CATEGORIA");
						idlbl.setText("ID: ");
						modelo.setText("Modelo: ");
						compleme.setText("Grupo: ");
						nomlbl.setText("Nombre: ");
						estadolbl.setText("Estado: ");
						ultimoM.setText("Ult. Mante.: ");
						prestado.setText("Prestado: ");
		}
}
