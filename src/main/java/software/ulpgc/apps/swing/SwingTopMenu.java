package software.ulpgc.apps.swing;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SwingTopMenu extends JPanel {

    public SwingTopMenu(String title, URL imageUrl) {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.DARK_GRAY));
        this.setBackground(new Color(255,255,255));

        // App name and Logo
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.X_AXIS));
        JLabel logoLabel = new JLabel();
        JLabel nameLabel = new JLabel(title);

        logoLabel.setIcon(new ImageIcon(scaleImage(imageUrl)));

        nameLabel.setFont(new Font("Impact", Font.BOLD, 16));

        logoPanel.add(logoLabel);
        logoPanel.add(Box.createRigidArea(new Dimension(2,0)));
        logoPanel.add(nameLabel);

        logoPanel.setBackground(new Color(255,255,255));

        this.add(logoPanel, BorderLayout.WEST);
    }

    private Image scaleImage(URL imageUrl){
        if (imageUrl != null){
            ImageIcon originalIcon = new ImageIcon(imageUrl);
            return originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        }
        return null;
    }
}
