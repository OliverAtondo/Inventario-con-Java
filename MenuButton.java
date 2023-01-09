import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class MenuButton extends JToggleButton {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public double screenX = screenSize.getWidth();
	public double screenY = screenSize.getHeight();

    JPopupMenu popup;
    Color dos = new Color(0,133,191);

    public MenuButton(String name, JPopupMenu menu) {
        this.popup = menu;
        popup.setPopupSize((int)screenX/6,(int)screenY/8);
        this.setText (name);
        this.setFont(new Font("BebasNeue-Regular.ttf", Font.PLAIN, (int)screenY/32));
        this.setForeground(Color.white);
        this.setBackground(null);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorder(null);
        this.setHorizontalAlignment(SwingConstants.CENTER);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                JToggleButton b = MenuButton.this;
                if (b.isSelected()) {
                    popup.show(b, 0, b.getBounds().height);
                    b.setBackground(dos);
                    b.setOpaque(true);
                    b.setContentAreaFilled(true);
                } else if (!b.isSelected()) {
                    popup.setVisible(false);
                    b.setOpaque(false);
                    b.setContentAreaFilled(false);
                }
            }
        });
        popup.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                MenuButton.this.setSelected(false);
                setOpaque(false);
                setContentAreaFilled(false);
            }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {}
        });
    }
}
