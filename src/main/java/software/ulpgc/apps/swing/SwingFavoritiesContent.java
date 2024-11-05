package software.ulpgc.apps.swing;

import software.ulpgc.model.ExchangeRecord;
import software.ulpgc.view.VisualComponent;

import javax.swing.*;
import java.awt.*;

public class SwingFavoritiesContent extends JPanel implements VisualComponent {

    private static SwingFavoritiesContent instance;

    public static SwingFavoritiesContent getInstance(){
        return (instance == null) ? instance = new SwingFavoritiesContent() : instance;
    }

    private SwingFavoritiesContent() {
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(17, 21, 24));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        this.add(getTitlePanel(gridBagConstraints), gridBagConstraints);
    }

    private Component getTitlePanel(GridBagConstraints gridBagConstraints){
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.insets = new Insets(5,0,40,0);
        JLabel tittleLabel = new JLabel("Favorities");
        tittleLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
        tittleLabel.setForeground(Color.WHITE);

        return tittleLabel;
    }

    @Override
    public Object getComponent() {
        return this;
    }
}

