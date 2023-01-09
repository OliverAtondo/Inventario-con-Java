import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

class MapaChido extends JPanel{

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

    public JLabel bg = new JLabel(new ImageIcon((new ImageIcon("./Icons/Mapa.png").getImage()).getScaledInstance(19*(int)screenX/64,(int)screenY/3,java.awt.Image.SCALE_SMOOTH)));
    public JLabel zonaL = new JLabel();

	public MapaChido(int zona)
	{
        setBorder(null);
        setLayout(null);

        zonaL.setBackground(new Color(255,0,0,150));
        bg.setVisible(true);

        add(bg);
        add(zonaL);
        switch (zona){
            case 1:
            zonaL.setBounds(10,10,40,40);
            zonaL.setVisible(true);
            this.invalidate();
			this.revalidate();
			this.repaint();
            break;
            case 2:
            zonaL.setBounds(55,44,40,40);
            zonaL.setVisible(true);
            this.invalidate();
			this.revalidate();
			this.repaint();
            break;
            default:
            break;
        }
    }
}