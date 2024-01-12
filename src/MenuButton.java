import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton {
    MenuButton(String label, int x, int y, int fontBoldness, int fontSize){
        this.setText(label);
        this.setFocusable(false);
        this.setFont(new Font("Arial", fontBoldness, fontSize));
        this.setSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        this.setForeground(Color.white);
        this.setBorder(new RoundedBorder(20, Constants.PARKING_BLUE));
        this.setBackground(Constants.PARKING_BLUE);
        this.setOpaque(false);
        this.setLocation(x, y);
    }
    protected void paintComponent(Graphics g) {
        Dimension arcs = new Dimension(20, 20);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        //Draws the rounded opaque panel with borders.
        graphics.setColor(this.getBackground());
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint background
        graphics.setColor(this.getBackground());
        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint border
        super.paintComponent(g);
    }
}
