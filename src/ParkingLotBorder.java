import javax.swing.*;
import java.awt.*;

public class ParkingLotBorder extends JPanel {
    private int index,pWidth,pHeight;
    private String placement;

    ParkingLotBorder(int pWidth, int pHeight, int index, String placement) {
        this.index = index;
        this.pHeight = pHeight;
        this.pWidth = pWidth;
        this.placement = placement;
        drawParkingLotBorder();
        this.setOpaque(false);
        this.setBackground(Color.black);

    }
    private void drawParkingLotBorder(){
        int totalLotsLength, lotLength;
        switch (placement.toLowerCase()) {
            case "top":
                totalLotsLength = (int) (pWidth * Constants.TOP_PARKING_PERCENTAGE);
                lotLength = totalLotsLength / Constants.TOP_PARKING_LOTS_NUMBER;
                this.setSize(Constants.LOT_BORDER_BROADNESS, Constants.LOT_BORDER_LENGTH + 5);
                this.setLocation(lotLength * (index) + (int) (pWidth * Constants.TOP_PARKING_MARGIN_PERCENTAGE), -5);
                break;
            case "right":
                totalLotsLength = (int) (pHeight * Constants.LEFT_RIGHT_PARKING_PERCENTAGE);
                lotLength = totalLotsLength / Constants.PARKING_LOTS_NUMBER;

                this.setSize(Constants.LOT_BORDER_LENGTH + 5, Constants.LOT_BORDER_BROADNESS);
                this.setLocation(pWidth - Constants.LOT_BORDER_LENGTH - 15, lotLength * (index) + (int) (pHeight * Constants.LEFT_RIGHT_PARKING_MARGIN));
                break;
            case "left":
                totalLotsLength = (int) (pHeight * Constants.LEFT_RIGHT_PARKING_PERCENTAGE);
                lotLength = totalLotsLength / Constants.PARKING_LOTS_NUMBER;

                this.setSize(Constants.LOT_BORDER_LENGTH + 5, Constants.LOT_BORDER_BROADNESS);
                this.setLocation(-5, lotLength * (index) + (int) (pHeight * Constants.LEFT_RIGHT_PARKING_MARGIN));
                break;
            case "middle":
                totalLotsLength = (int) (pHeight * Constants.LEFT_RIGHT_PARKING_PERCENTAGE);
                lotLength = totalLotsLength / Constants.PARKING_LOTS_NUMBER;

                this.setSize(Constants.LOT_BORDER_LENGTH * 2, Constants.LOT_BORDER_BROADNESS);
                this.setLocation((pWidth / 2) - Constants.LOT_BORDER_LENGTH, lotLength * (index) + (int) (pHeight * Constants.LEFT_RIGHT_PARKING_MARGIN));
                break;
            case "middlebreak":
                totalLotsLength = (int) (pHeight * Constants.LEFT_RIGHT_PARKING_PERCENTAGE);

                this.setSize(Constants.LOT_BORDER_BROADNESS, totalLotsLength + Constants.LOT_BORDER_BROADNESS);
                this.setLocation((pWidth / 2) - (Constants.LOT_BORDER_BROADNESS / 2), (int) (pHeight * Constants.LEFT_RIGHT_PARKING_MARGIN));
                break;
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        Dimension arcs = new Dimension(15, 15);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Draws the rounded opaque panel with borders.
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint background
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);//paint border
        super.paintComponent(g);
    }
}
