package software.ulpgc.apps.swing;

import software.ulpgc.view.VisualComponent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class SwingTopLogoName extends JPanel implements VisualComponent {

    private static final Color BACKGROUND_COLOR = new Color(17, 21, 24);
    private static final Color TEXT_COLOR = new Color(229, 229, 231);
    private static final Font TITLE_FONT = new Font("Arial Black", Font.BOLD, 16);
    private static final int LOGO_WIDTH = 50;
    private static final int LOGO_HEIGHT = 50;

    public SwingTopLogoName(String title, URL imageUrl) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBackground(BACKGROUND_COLOR);

        this.setBorder(new EmptyBorder(0,10,0,0));

        this.add(getLogoLabel(imageUrl));
        this.add(Box.createRigidArea(new Dimension(2,0)));
        this.add(getNameLabel(title));
    }

    private Component getLogoLabel(URL imageUrl){
        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(new ImageIcon(scaleImage(imageUrl)));

        return logoLabel;
    }

    private Component getNameLabel(String title){
        JLabel nameLabel = new JLabel(title);
        nameLabel.setFont(TITLE_FONT);
        nameLabel.setForeground(TEXT_COLOR);

        return nameLabel;
    }

    private Image scaleImage(URL imageUrl){
        if (imageUrl != null){
            ImageIcon originalIcon = new ImageIcon(imageUrl);
            return originalIcon.getImage().getScaledInstance(LOGO_WIDTH, LOGO_HEIGHT, Image.SCALE_SMOOTH);
        }
        return null;
    }

    @Override
    public JComponent getComponent() {
        return this;
    }
}
