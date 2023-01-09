import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

class VentanaEliminarProducto extends JDialog implements ActionListener{

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

JButton agregar, cerrar;

String strNombre, strCosto, strPrecio, strCantidad, strEtiqueta, strFoto;

JToggleButton []  botonesManiquies = new JToggleButton [6000];
//JButton []  botonesInstrumentos = new JButton [6000];

public String [] nombresito = new String [6000];
public String [] eliminador = new String [6000];

Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
public double screenX = screenSize.getWidth();
public double screenY = screenSize.getHeight();


public JPanel panelBotonesDeProductos;

public ArrayList<String> eliminator = new ArrayList<String>();

public int conteoEli = 0;

int alturaDelPanelProductos;
int i = 0;

	public VentanaEliminarProducto()
	{

				this.setLayout(null);
				principal.setLayout(null);
				principal.setBounds(0,0,640,480);
				principal.setBackground(Color.white);

				cerrar = new JButton("X");
				cerrar.setBounds(590,0,50,40);
				cerrar.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 12));
				cerrar.setForeground(Color.white);
				cerrar.setContentAreaFilled(false);
				cerrar.setBorderPainted(false);
				cerrar.setBackground(Color.red);
				cerrar.setOpaque(true);
				cerrar.addActionListener(this);

				agregar = new JButton("Eliminar");
				agregar.setBounds(280,400,80,40);
				agregar.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 12));
				agregar.setContentAreaFilled(false);
				agregar.setBorderPainted(false);
				agregar.setBackground(seis);
				agregar.setOpaque(true);
				agregar.addActionListener(this);
				agregar.setEnabled(false);

        panelBotonesDeProductos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotonesDeProductos.removeAll();
        panelBotonesDeProductos.revalidate();
        panelBotonesDeProductos.repaint();

        panelBotonesDeProductos.setPreferredSize(new Dimension(600,alturaDelPanelProductos/2));
        panelBotonesDeProductos.revalidate();
        panelBotonesDeProductos.repaint();

        JScrollPane scrollBotonesProductos = new JScrollPane(panelBotonesDeProductos);
        scrollBotonesProductos.setBounds(40,70,585,310);
        scrollBotonesProductos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollBotonesProductos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

				principal.add(agregar);

				//this.add();
				this.add(scrollBotonesProductos);
				//principal.add(cerrar);

        visualizarInfo("SELECT * from objeto");

				this.add(principal);

				this.setModal(true);
				this.setAlwaysOnTop(true);
				this.setTitle("Eliminar");
				this.setBounds(500,200,700,500);
				//this.setUndecorated(true);
        this.setResizable(false);
				this.setVisible(true);

			}

			public void actionPerformed(ActionEvent event)
			{

				if(event.getSource()==agregar && eliminator.size() != 0)
				{
          for(int j = 0;j<eliminator.size();j++){
            subirDatos("call eliminarObjeto("+eliminator.get(j)+");");
				  }
        this.dispose();
        }
				else if(event.getSource()==cerrar)
				{
          System.out.println(eliminator.size());
					this.dispose();
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

      public void visualizarInfo(String query)
      {
      try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con=DriverManager.getConnection(
      "jdbc:mysql://localhost:3306/mydb","root","66xkzHUb3.");
      Statement stmt=con.createStatement();
      ResultSet rs=stmt.executeQuery(query);
      while(rs.next()){
            botonesManiquies[i] = new JToggleButton("<html>"+rs.getString("nombre")+"<br>"+rs.getString("id")+"</br><html>");
            botonesManiquies[i].setPreferredSize(new Dimension(175,100));
            botonesManiquies[i].setIcon(new ImageIcon((new ImageIcon("./Icons/man.png").getImage()).getScaledInstance(17,50,java.awt.Image.SCALE_SMOOTH)));
            botonesManiquies[i].setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 15));
            botonesManiquies[i].setHorizontalTextPosition(JButton.RIGHT);
            botonesManiquies[i].setVerticalTextPosition(JButton.TOP);
            botonesManiquies[i].setHorizontalAlignment(SwingConstants.LEFT);
            botonesManiquies[i].setContentAreaFilled(false);
            botonesManiquies[i].setBorderPainted(false);
            botonesManiquies[i].setOpaque(true);
            nombresito[i] = rs.getString("id");
            botonesManiquies[i].setBackground(cuatro);
            botonesManiquies[i].setForeground(Color.white);
            botonesManiquies[i].addItemListener(new ItemListener() {
              @Override
              public void itemStateChanged(ItemEvent itemEvent) {
                for(int j = 0; j < botonesManiquies.length;j++){
                  int estado = itemEvent.getStateChange();
                  if(estado == ItemEvent.SELECTED && itemEvent.getSource()==botonesManiquies[j]){
                    botonesManiquies[j].setBackground(Color.black);
                    eliminator.add(nombresito[j]);
                    conteoEli++;
                  } else if (estado != ItemEvent.SELECTED && itemEvent.getSource()==botonesManiquies[j]){
                    botonesManiquies[j].setBackground(cuatro);
                    eliminator.remove(nombresito[j]);
                    conteoEli--;
                  }
                }
                if(conteoEli == 0){
                  agregar.setEnabled(false);
                } else {
                  agregar.setEnabled(true);
                }
              }
          });
            panelBotonesDeProductos.add(botonesManiquies[i]);
            alturaDelPanelProductos = alturaDelPanelProductos + 90;
            System.out.println(botonesManiquies[i].getText());
            panelBotonesDeProductos.setPreferredSize(new Dimension(600,alturaDelPanelProductos/2));
        i = i + 1;
      }con.close();
    }catch(Exception e){ System.out.println(" ");}
      };

}
