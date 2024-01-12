import javax.swing.*;
import java.awt.*;

public class ParkingLotButton extends JButton {
    private int index, pWidth, pHeight;
    private String state, placement;


    public void setState(String state) {
        this.state = state;
    }


    public void setCarColorName(String carColorName) {
        this.carColorName = carColorName;
    }

    private Parking parent;
    private String carColorName;
    ParkingLotButton(int pWidth, int pHeight, int index, String placement, String state, String color, Parking parent) {
        this.index = index;
        this.parent = parent;
        this.pHeight = pHeight;
        this.pWidth = pWidth;
        this.placement = placement;
        this.state = state;
        this.carColorName = color;
        this.setOpaque(false);
        this.setForeground(Color.white);
        drawButton();
        setButtonAttributes();


        this.setFocusable(false);
    }

    public void setButtonAttributes() {
        switch (this.state.toLowerCase()) {
            case "reserved":
                this.setText("<html>Reserved lot</html>");
                this.setBorder(new RoundedBorder(10, Constants.PARKING_BLUE));
                this.setBackground(Constants.PARKING_BLUE);
                this.setIcon(null);
                this.addActionListener(e -> parkingAtReservedRoutine());
                break;
            case "parked":
                this.setText("");
                ImageIcon car = new ImageIcon(this.getClass().getResource("assets/cars/"+this.carColorName+"Car.png"));
                switch (this.placement) {
                    case "right", "middleleft":
                        car.setImage(ImageUtils.rotateImage(car.getImage(), 90));
                        break;
                    case "left", "middleright":
                        car.setImage(ImageUtils.rotateImage(car.getImage(), 270));
                        break;
                }
                car.setImage(car.getImage().getScaledInstance((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.8), Image.SCALE_DEFAULT));
                this.setBackground(Constants.MAIN_BACKGROUND_COLOR);
                this.setBorder(new RoundedBorder(10, Constants.MAIN_BACKGROUND_COLOR));
                this.setIcon(car);
                this.addActionListener(e -> moveCarRoutine());
                break;
            case "normal":
                this.setText("");
                this.setBackground(Constants.MAIN_BACKGROUND_COLOR);
                this.setIcon(null);
                this.setBorder(new RoundedBorder(10, Constants.MAIN_BACKGROUND_COLOR));
                this.addActionListener(e -> parkingAtNormalRoutine());
                break;
        }
    }

    public void drawButton() {
        int totalButtonsLength, buttonLength;
        switch (this.placement.toLowerCase()) {
            case "top":
                totalButtonsLength = (int) (this.pWidth * Constants.TOP_PARKING_PERCENTAGE);
                buttonLength = totalButtonsLength / Constants.TOP_PARKING_LOTS_NUMBER;
                this.setSize(buttonLength - 20, Constants.LOT_BORDER_LENGTH - 20);
                this.setLocation(buttonLength * (index) + (int) (this.pWidth * Constants.TOP_PARKING_MARGIN_PERCENTAGE) + (buttonLength / 2) - ((buttonLength - 20) / 2) + 5, 5);

                break;
            case "right":
                totalButtonsLength = (int) (this.pHeight * Constants.LEFT_RIGHT_PARKING_PERCENTAGE);
                buttonLength = totalButtonsLength / Constants.PARKING_LOTS_NUMBER;
                this.setSize(Constants.LOT_BORDER_LENGTH - 20, buttonLength - 20);
                this.setLocation(this.pWidth - Constants.LOT_BORDER_LENGTH, buttonLength * (index - 8) + (int) (this.pHeight * Constants.LEFT_RIGHT_PARKING_MARGIN) + (buttonLength / 2) - ((buttonLength - 20) / 2) + 5);
                break;
            case "left":
                totalButtonsLength = (int) (this.pHeight * Constants.LEFT_RIGHT_PARKING_PERCENTAGE);
                buttonLength = totalButtonsLength / Constants.PARKING_LOTS_NUMBER;
                this.setSize(Constants.LOT_BORDER_LENGTH - 20, buttonLength - 20);
                this.setLocation(5, buttonLength * (index - 13) + (int) (pHeight * Constants.LEFT_RIGHT_PARKING_MARGIN) + (buttonLength / 2) - ((buttonLength - 20) / 2) + 5);
                break;
            case "middleright":
                totalButtonsLength = (int) (this.pHeight * Constants.LEFT_RIGHT_PARKING_PERCENTAGE);
                buttonLength = totalButtonsLength / Constants.PARKING_LOTS_NUMBER;
                this.setSize(Constants.LOT_BORDER_LENGTH - 20, buttonLength - 20);
                this.setLocation(pWidth / 2 + 10, buttonLength * (index - 18) + (int) (pHeight * Constants.LEFT_RIGHT_PARKING_MARGIN) + (buttonLength / 2) - ((buttonLength - 20) / 2) + 5);
                break;
            case "middleleft":
                totalButtonsLength = (int) (this.pHeight * Constants.LEFT_RIGHT_PARKING_PERCENTAGE);
                buttonLength = totalButtonsLength / Constants.PARKING_LOTS_NUMBER;
                this.setSize(Constants.LOT_BORDER_LENGTH - 20, buttonLength - 20);
                this.setLocation(pWidth / 2 - Constants.LOT_BORDER_LENGTH + 10, buttonLength * (index - 23) + (int) (pHeight * Constants.LEFT_RIGHT_PARKING_MARGIN) + (buttonLength / 2) - ((buttonLength - 20) / 2) + 5);
                break;
        }
    }
    private void parkingAtNormalRoutine(){
        PopUpInput popUp = new PopUpInput("Park your car", "Park", true,"normalpark",this.index, this.parent, this);
        parent.setEnabled(false);


    }
    private void parkingAtReservedRoutine(){
        PopUpInput popUp = new PopUpInput("Park your car", "Park", true,"reservedpark",this.index, this.parent, this);
        parent.setEnabled(false);

    }
    private void moveCarRoutine(){
        PopUpInput popUp = new PopUpInput("Move your car", "Move", false,"move", this.index, this.parent, this);
        parent.setEnabled(false);
    }
    protected void paintComponent(Graphics g) {
        Dimension arcs = new Dimension(10, 10);
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
