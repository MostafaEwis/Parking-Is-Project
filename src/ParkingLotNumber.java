import javax.swing.*;
import java.awt.*;

public class ParkingLotNumber extends JLabel {
    ParkingLotNumber(int pWidth, int pHeight, int number, String placement) {
        int totalNumbersLength, numberLength;
        this.setSize(20, 20);
        this.setForeground(Color.gray);
        switch (placement.toLowerCase()) {
            case "top":
                this.setText(Integer.toString(number + 1));
                totalNumbersLength = (int) (pWidth * Constants.TOP_PARKING_PERCENTAGE);
                numberLength = totalNumbersLength / Constants.TOP_PARKING_LOTS_NUMBER;
                this.setLocation(numberLength * (number) + (int) (pWidth * Constants.TOP_PARKING_MARGIN_PERCENTAGE) + (numberLength / 2), Constants.LOT_BORDER_LENGTH );
                break;
            case "right":
                this.setText(Integer.toString(number + 1));
                totalNumbersLength = (int) (pHeight * Constants.LEFT_RIGHT_PARKING_PERCENTAGE);
                numberLength = totalNumbersLength / Constants.PARKING_LOTS_NUMBER;
                this.setLocation(pWidth - Constants.LOT_BORDER_LENGTH - 20 , numberLength * (number - 8) + (int) (pHeight * Constants.LEFT_RIGHT_PARKING_MARGIN) + (numberLength / 2));
                break;
            case "left":
                this.setText(Integer.toString(number + 1));
                totalNumbersLength = (int) (pHeight * Constants.LEFT_RIGHT_PARKING_PERCENTAGE);
                numberLength = totalNumbersLength / Constants.PARKING_LOTS_NUMBER;
                this.setLocation(Constants.LOT_BORDER_LENGTH, numberLength * (number - 13) + (int) (pHeight * Constants.LEFT_RIGHT_PARKING_MARGIN) + (numberLength / 2));
                break;
            case "middleright":
                this.setText(Integer.toString(number + 1));
                totalNumbersLength = (int) (pHeight * Constants.LEFT_RIGHT_PARKING_PERCENTAGE);
                numberLength = totalNumbersLength / Constants.PARKING_LOTS_NUMBER;
                this.setLocation((pWidth / 2) + Constants.LOT_BORDER_LENGTH , numberLength * (number - 18) + (int) (pHeight * Constants.LEFT_RIGHT_PARKING_MARGIN) + (numberLength / 2));
                break;
            case "middleleft":
                this.setText(Integer.toString(number + 1));
                totalNumbersLength = (int) (pHeight * Constants.LEFT_RIGHT_PARKING_PERCENTAGE);
                numberLength = totalNumbersLength / Constants.PARKING_LOTS_NUMBER;
                this.setLocation((pWidth / 2) - Constants.LOT_BORDER_LENGTH - 20 , numberLength * (number - 23) + (int) (pHeight * Constants.LEFT_RIGHT_PARKING_MARGIN) + (numberLength / 2));
                break;
        }
    }
}



