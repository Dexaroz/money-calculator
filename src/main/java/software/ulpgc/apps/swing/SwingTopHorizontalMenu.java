package software.ulpgc.apps.swing;

import software.ulpgc.view.VisualComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SwingTopHorizontalMenu extends JPanel implements VisualComponent {
    private final Map<String, JButton> commandsButton;

    public SwingTopHorizontalMenu() {
        this.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        this.commandsButton = new HashMap<>();

        this.setOpaque(true);
        this.setBackground(new Color(17, 21, 24));

        this.add(createButton("Converter"));
        this.add(createButton("History"));
        this.add(createButton("Favorities"));
    }

    private JButton createButton(String name){
        JButton button = new JButton(name);
        button.setMargin(new Insets(1,1,1,1));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setForeground(new Color(229, 229, 231));
        button.setFont(new Font("Arial", Font.TYPE1_FONT,15));
        commandsButton.put(name, button);
        return button;
    }

    public void setButtonAction(String buttonName, ActionListener action){
        JButton button = commandsButton.get(buttonName);
        if (buttonName != null){
            button.addActionListener(action);
        }
    }

    @Override
    public JComponent getComponent() {
        return this;
    }
}
