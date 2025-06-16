package software.ulpgc.apps.swing.topMenu;

import software.ulpgc.arquitecture.control.Command;
import software.ulpgc.arquitecture.view.TopMenuComponent;
import software.ulpgc.arquitecture.view.VisualComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SwingTopMenuComponent extends JPanel implements VisualComponent, TopMenuComponent {

    private static final Color BACKGROUND_COLOR = new Color(17, 21, 24);
    private static final Color TEXT_COLOR = new Color(229, 229, 231);
    private static final Font TITLE_FONT = new Font("Arial Black", Font.BOLD, 15);

    private final SwingTopLogoName logoName;
    private final SwingTopHorizontalMenu horizontalMenu;
    private final Map<String, JButton> buttons;

    public SwingTopMenuComponent(String title, URL imageUrl) {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(44, 44, 46)));
        this.setBackground(BACKGROUND_COLOR);

        this.buttons = new HashMap<>();

        logoName = new SwingTopLogoName(title, imageUrl);
        horizontalMenu = new SwingTopHorizontalMenu();

        this.add(horizontalMenu.getComponent(), BorderLayout.CENTER);
        this.add(logoName.getComponent(), BorderLayout.WEST);
    }

    private JButton createButton(String name){
        JButton button = new JButton(name);
        button.setMargin(new Insets(1,1,1,1));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setForeground(TEXT_COLOR);
        button.setFont(TITLE_FONT);
        return button;
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    @Override
    public void addButton(String label, Command command) {
        if (!buttons.containsKey(label)){
            JButton button = createButton(label);
            button.addActionListener(e -> {
                try {
                    command.execute();
                } catch (IOException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
            buttons.put(label, button);
            horizontalMenu.add(button);
        }
    }

    @Override
    public void setButtonAction(String label, Command command) {
        JButton button = buttons.get(label);
        if (button != null) {
            for (ActionListener al : button.getActionListeners()) {
                button.removeActionListener(al);
            }
            button.addActionListener(e -> {
                try {
                    command.execute();
                } catch (IOException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
    }
}
