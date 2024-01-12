import javax.swing.border.Border;
import java.awt.*;

public class RoundedBorder implements Border {

    private int radius;
    private Color borderColor;

    RoundedBorder(int radius, Color borderColor) {
        this.radius = radius; this.borderColor = borderColor;
    }


    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }


    public boolean isBorderOpaque() {
        return true;
    }


    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(this.borderColor);
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}