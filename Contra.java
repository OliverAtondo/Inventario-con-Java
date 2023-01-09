import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.io.*;

class Contra extends JDialog implements ActionListener{

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

Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
public double screenX = screenSize.getWidth();
public double screenY = screenSize.getHeight();

Color finalizar = new Color(243,91,104);

JPanel principal = new JPanel(null);

JButton agregar, cerrar;

JLabel instruccion;
JTextArea contras;
JButton olvide;

public Boolean bandera = true;
public Boolean accesss = false;

public Boolean reinicio = false;

public String asdcontra, correocc;

	public Contra(String contrasenaa, String corrr)
	{
        asdcontra = contrasenaa;
        correocc = corrr;

				this.setLayout(null);
				principal.setLayout(null);
				principal.setBounds(0,0,(int)screenX/4,(int)screenY/4);
				principal.setBackground(Color.gray);

				cerrar = new JButton("X");
				cerrar.setBounds(((int)screenX/4)-50,0,50,40);
				cerrar.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 12));
				cerrar.setForeground(Color.white);
				cerrar.setContentAreaFilled(false);
				cerrar.setBorderPainted(false);
				cerrar.setBackground(Color.red);
				cerrar.setOpaque(true);
				cerrar.addActionListener(this);

                olvide = new JButton("¿Olvidaste tu contraseña?");
				olvide.setBounds(0,10*(int)screenY/64,(int)screenX/4,(int)screenY/32);
				olvide.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 12));
                olvide.setForeground(Color.WHITE);
                olvide.setContentAreaFilled(false);
				olvide.setBorderPainted(false);
                olvide.setVisible(false);
                olvide.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						String newPassw = genPassword();
						new Mailer().sendEmail(corrr,"CLAMPS INVETARIO", new TemplatesCorreo().retornarContra(newPassw));
						cambiarcontra(corrr,newPassw);
				    }
                    });

				agregar = new JButton("Acceder");
				agregar.setBounds(3*(int)screenX/32,13*(int)screenY/64,(int)screenX/16,(int)screenY/32);
				agregar.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 12));
				agregar.setContentAreaFilled(false);
				agregar.setBorderPainted(false);
				agregar.setBackground(seis);
				agregar.setOpaque(true);
				agregar.addActionListener(this);

                instruccion = new JLabel("INGRESE SU CONTRASEÑA", SwingConstants.CENTER);
                instruccion.setBounds(0,3*(int)screenY/64,(int)screenX/4,(int)screenY/32);
				instruccion.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, (int)screenY/64));

                contras = new JTextArea();
                contras.setBounds((int)screenX/16,4*(int)screenY/32,(int)screenX/8,(int)screenY/32);
				contras.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, (int)screenY/64));

                principal.add(cerrar);
                principal.add(olvide);
                principal.add(agregar);
                principal.add(instruccion);
                principal.add(contras);


				this.add(principal);

				this.setModal(true);
				this.setAlwaysOnTop(true);
				this.setTitle("Eliminar");
				this.setBounds(3*(int)screenX/8,3*(int)screenY/8,(int)screenX/4,(int)screenY/4);
				this.setUndecorated(true);
				this.setVisible(true);
			}

			public void actionPerformed(ActionEvent event)
			{
                if(event.getSource()==agregar && contras.getText() != null)
				{
                    if(asdcontra.equals(contras.getText())){
                        accesss = true;
                        bandera = false;
                        this.dispose();
                    }else{
                        instruccion.setText("Contraseña incorrecta");
                        olvide.setVisible(true);
                    }
                }
				else if(event.getSource()==cerrar)
				{
                    accesss = false;
                    bandera = false;
					this.dispose();
				}
			}

			public String genPassword(){
				String chars = "0123456789qwertyuiopasdfghjklñzxcvbnm!@.QWERTYUIOPASDFGHJKLÑZXCVBNM";
				String passwordRtn = "";
	
				for(int i = 0; i <= 10; i++){
					int randomn = (int)Math.floor(Math.random() * chars.length());
					passwordRtn += chars.substring(randomn, randomn+1);
				}
	
				return passwordRtn;
	
			}

			public void cambiarcontra(String correov, String pass)
			{
			try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/mydb","root","66xkzHUb3.");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("call cambiarPassword(\""+correov+"\",\""+pass+"\");");
			while(rs.next()){
				System.out.println("contraseña cambiada");
			}
			con.close();
		  }catch(Exception e){
		  System.out.println(e);
		  }
		  accesss = false;
		  reinicio = true;
          bandera = false;
		  this.dispose();
		}
}