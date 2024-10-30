package software.ulpgc.apps.swing;

import software.ulpgc.view.TopMenuComponent;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SwingTopMenuComponent extends JPanel implements TopMenuComponent {

    public SwingTopMenuComponent(String title, URL imageUrl) {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(44, 44, 46)));
        this.setBackground(new Color(17, 21, 24));

        this.setPreferredSize(new Dimension(800, 50));

        SwingTopLogoName logoName = new SwingTopLogoName(title, imageUrl);
        SwingTopHorizontalMenu horizontalMenu = new SwingTopHorizontalMenu();

        this.add(horizontalMenu.getComponent(), BorderLayout.CENTER);
        this.add(logoName.getComponent(), BorderLayout.WEST);
    }

    @Override
    public JComponent getComponent() {
        return this;
    }
}
