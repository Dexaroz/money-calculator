package software.ulpgc.apps.swing;

import software.ulpgc.view.VisualComponent;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SwingTopHorizontalMenu extends JPanel implements VisualComponent {

    private static final Color BACKGROUND_COLOR = new Color(17, 21, 24);

    private final Map<String, JButton> commandsButton;

    public SwingTopHorizontalMenu() {
        this.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        this.commandsButton = new HashMap<>();

        this.setOpaque(true);
        this.setBackground(BACKGROUND_COLOR);
    }

    @Override
    public JComponent getComponent() {
        return this;
    }
}
