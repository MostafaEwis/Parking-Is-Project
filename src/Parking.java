import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Parking extends JFrame {

    Parking() {
        this.setTitle("Parking app");
        this.setIconImage(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("./assets/TitleBarLogo.png"))).getImage());
        this.setSize(1000, 750);
        this.getContentPane().setBackground(Constants.MAIN_BACKGROUND_COLOR);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);

        drawParking();
    }

    private void drawParking() {
        for (int i = 0; i < Constants.TOP_PARKING_LOTS_NUMBER + 1; i++) {
            this.add(new ParkingLotBorder(this.getWidth(), this.getHeight(), i, "top"));
            if (i < Constants.TOP_PARKING_LOTS_NUMBER)
                this.add(new ParkingLotNumber(this.getWidth(), this.getHeight(), i, "top"));
        }
        for (int i = 0; i < Constants.PARKING_LOTS_NUMBER + 1; i++) {
            this.add(new ParkingLotBorder(this.getWidth(), this.getHeight(), i, "right"));
            if (i < Constants.PARKING_LOTS_NUMBER) {
                this.add(new ParkingLotNumber(this.getWidth(), this.getHeight(), i, "right"));
            }
        }
        for (int i = 0; i < Constants.PARKING_LOTS_NUMBER + 1; i++) {
            this.add(new ParkingLotBorder(this.getWidth(), this.getHeight(), i, "left"));
            if (i < Constants.PARKING_LOTS_NUMBER) {
                this.add(new ParkingLotNumber(this.getWidth(), this.getHeight(), i, "left"));
            }
        }
        for (int i = 0; i < Constants.PARKING_LOTS_NUMBER + 1; i++) {
            this.add(new ParkingLotBorder(this.getWidth(), this.getHeight(), i, "middle"));
            if (i < Constants.PARKING_LOTS_NUMBER) {
                this.add(new ParkingLotNumber(this.getWidth(), this.getHeight(), i, "middleright"));
                this.add(new ParkingLotNumber(this.getWidth(), this.getHeight(), i, "middleleft"));
            }
        }
        this.add(new ParkingLotBorder(this.getWidth(), this.getHeight(), 0, "middlebreak"));

    }
}
