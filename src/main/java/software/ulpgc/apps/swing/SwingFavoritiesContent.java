package software.ulpgc.apps.swing;

import software.ulpgc.view.VisualComponent;

import javax.swing.*;
import java.awt.*;

public class SwingFavoritiesContent extends JPanel implements VisualComponent {

    public SwingFavoritiesContent() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(17, 21, 24));

        JLabel labelPrueba = new JLabel("JUAN");
        this.add(labelPrueba);
    }

    @Override
    public Object getComponent() {
        return this;
    }
}

