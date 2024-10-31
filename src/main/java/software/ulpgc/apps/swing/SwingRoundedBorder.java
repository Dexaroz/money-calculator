package software.ulpgc.apps.swing;

import javax.swing.border.Border;
import java.awt.*;

public class SwingRoundedBorder implements Border {
    private int radiusBorder;

    public SwingRoundedBorder(int radiusBorder) {
        this.radiusBorder = radiusBorder;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radiusBorder + 5, radiusBorder + 5, radiusBorder + 5, radiusBorder + 5);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(new Color(44, 44, 46));
        g.drawRoundRect(x, y, width - 1, height - 1, radiusBorder+20, radiusBorder+20);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}
