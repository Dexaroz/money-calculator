package software.ulpgc.apps.swing;

import software.ulpgc.control.Command;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SwingTopMenu extends JPanel {

    public SwingTopMenu(String title, URL imageUrl) {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.DARK_GRAY));
        this.setBackground(Color.WHITE);

        // App name and Logo
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.X_AXIS));
        JLabel logoLabel = new JLabel();
        JLabel nameLabel = new JLabel(title);

        logoLabel.setIcon(new ImageIcon(scaleImage(imageUrl)));

        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        logoPanel.add(logoLabel);
        logoPanel.add(Box.createRigidArea(new Dimension(2,0)));
        logoPanel.add(nameLabel);

        logoPanel.setBackground(Color.WHITE);

        // Menu panel
        this.add((JPanel) SwingTopHorizontalMenu(), BorderLayout.CENTER);

        this.add(logoPanel, BorderLayout.WEST);
    }

    private Image scaleImage(URL imageUrl){
        if (imageUrl != null){
            ImageIcon originalIcon = new ImageIcon(imageUrl);
            return originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        }
        return null;
    }

    private Component SwingTopHorizontalMenu(){
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 30));

        menuPanel.setOpaque(true);
        menuPanel.setBackground(Color.WHITE);

        menuPanel.add(createButton("Home"));
        menuPanel.add(createButton("Home"));
        menuPanel.add(createButton("Home"));
        menuPanel.add(createButton("Home"));
        return menuPanel;
    }

    private JButton createButton(String name){
        JButton button = new JButton(name);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setForeground(Color.GREEN);
        return button;
    }
}
