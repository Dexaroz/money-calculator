package software.ulpgc.apps.swing;

import software.ulpgc.view.VisualComponent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SwingFavoritiesContent extends JPanel implements VisualComponent {

    public SwingFavoritiesContent() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(17, 21, 24));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setOpaque(false);
        topPanel.setBorder(new EmptyBorder(20,0,0,0));

        JLabel tittleLabel = new JLabel("Favorities");
        tittleLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
        tittleLabel.setForeground(Color.WHITE);
        topPanel.add(tittleLabel);
        this.add(topPanel, BorderLayout.NORTH);
    }

    @Override
    public Object getComponent() {
        return this;
    }
}

