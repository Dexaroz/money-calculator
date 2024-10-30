package software.ulpgc.apps.swing;

import software.ulpgc.view.VisualComponent;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SwingTopMenuComponent extends JPanel implements VisualComponent {
    private final SwingTopHorizontalMenu horizontalMenu;

    public SwingTopMenuComponent(String title, URL imageUrl) {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(44, 44, 46)));
        this.setBackground(new Color(17, 21, 24));

        SwingTopLogoName logoName = new SwingTopLogoName(title, imageUrl);
        horizontalMenu = new SwingTopHorizontalMenu();

        this.add(horizontalMenu.getComponent(), BorderLayout.CENTER);
        this.add(logoName.getComponent(), BorderLayout.WEST);
    }

    public SwingTopHorizontalMenu getHorizontalMenu(){
        return horizontalMenu;
    }

    @Override
    public JComponent getComponent() {
        return this;
    }
}
