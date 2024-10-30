package software.ulpgc.apps.swing;

import software.ulpgc.view.TopLogoName;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class SwingTopLogoName extends JPanel implements TopLogoName {

    public SwingTopLogoName(String title, URL imageUrl) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBackground(new Color(17, 21, 24));
        JLabel logoLabel = new JLabel();
        JLabel nameLabel = new JLabel(title);

        this.setBorder(new EmptyBorder(0,10,0,0));
        logoLabel.setIcon(new ImageIcon(scaleImage(imageUrl)));
        nameLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
        nameLabel.setForeground(new Color(229, 229, 231));

        this.add(logoLabel);
        this.add(Box.createRigidArea(new Dimension(2,0)));
        this.add(nameLabel);
    }

    private Image scaleImage(URL imageUrl){
        if (imageUrl != null){
            ImageIcon originalIcon = new ImageIcon(imageUrl);
            return originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        }
        return null;
    }

    @Override
    public JComponent getComponent() {
        return this;
    }
}
