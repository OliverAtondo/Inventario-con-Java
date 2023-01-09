import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.font.TextAttribute;

class Intro extends JFrame{

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public double screenX = screenSize.getWidth();
	public double screenY = screenSize.getHeight();

    public JLabel titulo, sistemas, author, copy, version;

	public Intro()
	{
                copy = new JLabel("LICENCIA APACHE 2.0, AGOSTO-DICIEMBRE,2022. UABC. C. L. A. M. P. S.");
				copy.setBounds(10,((int)screenY/2)-50,(int)screenX/2,50);
				copy.setFont(new Font("BebasNeue-Regular.ttf", Font.BOLD, 12));
                copy.setForeground(Color.white);
                copy.setVisible(true);
                copy.setHorizontalAlignment(SwingConstants.LEFT);
				this.add(copy);

                author = new JLabel("UABC");
				author.setBounds(10,0,(int)screenX/2,50);
				author.setFont(new Font("BebasNeue-Regular.ttf", Font.BOLD, 45));
                author.setForeground(Color.green);
                author.setVisible(true);
                author.setHorizontalAlignment(SwingConstants.LEFT);
				this.add(author);

                version = new JLabel("BETA 1.0.2");
				version.setBounds(10,0,((int)screenX/2)-20,50);
				version.setFont(new Font("BebasNeue-Regular.ttf", Font.BOLD, 18));
                version.setForeground(Color.white);
                version.setVisible(true);
                version.setHorizontalAlignment(SwingConstants.RIGHT);
				this.add(version);

                sistemas = new JLabel("SISTEMAS");
                Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
                attributes.put(TextAttribute.TRACKING, 4);
                Font font2 = sistemas.getFont().deriveFont(attributes);
                sistemas.setFont(font2);
                sistemas.setFont(sistemas.getFont().deriveFont(attributes));
				sistemas.setBounds(10+(int)screenX/128,(int)screenY/8,(int)screenX/2,(int)screenY/2);
				sistemas.setVisible(true);
                sistemas.setForeground(Color.white);
				//sistemas.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 30));
				sistemas.setHorizontalAlignment(SwingConstants.CENTER);
				this.add(sistemas);

        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/Cream Cake.ttf"));
            titulo = new JLabel("Clamps");
            titulo.setFont(font.deriveFont(Font.BOLD, (int)screenX/8));
            titulo.setBounds(0,0,(int)screenX,(int)screenY);
            titulo.setForeground(Color.white);
            titulo.setVisible(true);
            titulo.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(titulo);
        } catch (FontFormatException | IOException ex) {
            ex.printStackTrace();
        }
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(
            true);
        this.setVisible(true);
	    this.setBounds((int)screenX/4,(int)screenY/4,(int)screenX/2,(int)screenY/2);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setVisible(true);
	    this.setLayout(null);
        this.getContentPane().setBackground( Color.black );

        try {
            Thread.sleep(4000);
        } catch (Exception e) {
            copy.setText("Hubo un error al crear el objeto.");
        }
        try {
            this.dispose();
        } catch (Exception e) {
            copy.setText("Hubo un error al crear el objeto.");
        }
			}

}