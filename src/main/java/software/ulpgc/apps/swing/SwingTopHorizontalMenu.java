package software.ulpgc.apps.swing;

import software.ulpgc.view.TopHorizontalMenu;

import javax.swing.*;
import java.awt.*;

public class SwingTopHorizontalMenu extends JPanel implements TopHorizontalMenu {

    public SwingTopHorizontalMenu() {
        this.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 15));

        this.setOpaque(true);
        this.setBackground(new Color(17, 21, 24));

        this.add(createButton("Converter"));
        this.add(createButton("History"));
        this.add(createButton("Favorities"));
    }

    private JButton createButton(String name){
        JButton button = new JButton(name);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setForeground(new Color(229, 229, 231));
        button.setFont(new Font("Arial", Font.TYPE1_FONT,15));
        return button;
    }

    @Override
    public JComponent getComponent() {
        return this;
    }
}
