import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class MenuSuperior extends JPanel implements ActionListener{

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public double screenX = screenSize.getWidth();
	public double screenY = screenSize.getHeight();

/******************Paleta de Colores*********************/

Color uno = new Color(0,93,164);
Color siete = new Color(138,237,246);

JLabel titulo;
JButton utilidades, infostatic;

int n;

public JMenuItem salir, chgcuenta, registrar, eliminar, modificar, cata;
public MenuButton cuentaUsuario;
	public MenuSuperior(String titulopl, int fontH)
	 {
		 this.n = fontH;
		 titulo= new JLabel(titulopl);
		 this.add(titulo);
		 this.setVisible(true);
		 this.setPreferredSize(new Dimension((int)screenX,(int)screenY/8));
	 }

	 public MenuSuperior(String titulopl, int fontH, Color color)
		{
			this.setLayout(null);
			final JPopupMenu popupmenu = new JPopupMenu("CLIENTES");
			JMenuItem cut = new JMenuItem("REGISTRAR");
			JMenuItem copy = new JMenuItem("ELIMINAR");
			JMenuItem paste = new JMenuItem("MODIFICAR");
			popupmenu.add(cut); popupmenu.add(copy); popupmenu.add(paste);
			MenuButton buzon = new MenuButton("CLIENTES", popupmenu);
			buzon.setBounds(2*(int)screenX/6,0,(int)screenX/6,(int)screenY/8);

			this.n = fontH;
			titulo= new JLabel(titulopl);

			final JPopupMenu inven = new JPopupMenu("Inventario");
			cata = new JMenuItem("CATALOGO");
			registrar = new JMenuItem("REGISTRAR");
			eliminar = new JMenuItem("ELIMINAR");
			modificar = new JMenuItem("MODIFICAR");
			inven.add(cata); inven.add(registrar); inven.add(eliminar);  inven.add(modificar);
			MenuButton inventario = new MenuButton("INVENTARIO", inven);
			inventario.setBounds(3*(int)screenX/6,0,(int)screenX/6,(int)screenY/8);

			infostatic = new JButton("DOCUMENTOS");
			infostatic.setBounds(4*(int)screenX/6,0,(int)screenX/6,(int)screenY/8);
			infostatic.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, n));
			infostatic.setForeground(Color.white);
			infostatic.setBackground(null);
			infostatic.setOpaque(false);
			infostatic.setContentAreaFilled(false);
			infostatic.setBorder(null);
			infostatic.setHorizontalAlignment(SwingConstants.CENTER);
			infostatic.addActionListener(this);

			final JPopupMenu opcionesUsuario = new JPopupMenu("USUARIO");
			JMenuItem conf = new JMenuItem("Configuracion");
			chgcuenta = new JMenuItem("Cambiar de Cuenta");
			salir = new JMenuItem("Salir");
			opcionesUsuario.add(conf); opcionesUsuario.add(chgcuenta); opcionesUsuario.add(salir);
			cuentaUsuario = new MenuButton("nombreU", opcionesUsuario);
			cuentaUsuario.setBounds(5*(int)screenX/6,0,(int)screenX/6,(int)screenY/8);
			opcionesUsuario.setPopupSize((int)screenX/6,(int)screenY/8);

			salir.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ev) {
            System.exit(0);
    }
});

			this.add(buzon);
			this.add(titulo);
			this.add(inventario);
			this.add(infostatic);
			this.add(cuentaUsuario);
			this.setVisible(true);
			this.setPreferredSize(new Dimension((int)screenX,(int)screenY/8));
		}

   @Override
   public void paintComponent(Graphics g) {
		 titulo.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, 2*n));
		 titulo.setBounds(20,0,(int)screenX/3,(int)screenY/8);
		 titulo.setOpaque(false);
		 titulo.setForeground(Color.white);
		 titulo.setVerticalAlignment(SwingConstants.CENTER);
		 this.setPreferredSize(new Dimension((int)screenX,(int)screenY/8));
		 this.setBackground(uno);
		 this.setVisible(true);

		 super.paintComponent(g);
   }

			public void actionPerformed(ActionEvent event)
			{
				JLabel label = new JLabel("Esta App sigue en desarrollo.");
				label.setFont(new Font("Arial", Font.BOLD, 20));

				if(event.getSource() == utilidades){

				if(JOptionPane.showConfirmDialog(null, label,"En desarrollo.",JOptionPane.CLOSED_OPTION) == JOptionPane.CLOSED_OPTION)
				{
					System.out.println("Entendido");
				}

		  	}

			}

}
