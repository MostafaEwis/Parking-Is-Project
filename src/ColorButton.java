import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ColorButton extends JButton {
    String colorName;
    static int chosenColor = 9;
    private int id;

    ColorButton(String colorName, Color color, int x, int y, int id, PopUpInput parent) {
        this.colorName = colorName;
        this.setFocusable(false);
        updateBorder();
        this.id = id;
        this.setSize(30, 30);
        this.setBackground(color);
        this.setLocation(x, y);
        this.addActionListener(e -> {
            parent.setColorName(this.colorName);
            chosenColor = this.id;
            parent.refreshColors();
        });
    }

    public void updateBorder() {
        super.setBorder(this.id == chosenColor ? BorderFactory.createLineBorder(Color.black, 3) : null);
    }
}
