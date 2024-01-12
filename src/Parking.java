import javax.swing.*;
import java.awt.*;
import java.sql.DriverAction;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Parking extends JFrame {
    final Parking THIS = this;
    private ResultSet parkingLotsData;

    Parking() {
        this.setTitle("Parking app");
        this.setIconImage(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("./assets/TitleBarLogo.png"))).getImage());
        this.setSize(1000, 750);
        this.getContentPane().setBackground(Constants.MAIN_BACKGROUND_COLOR);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        SQLConnectionStatement statement = new SQLConnectionStatement();
        parkingLotsData = statement.getStatments("select state, color, number from parking_lots");
        drawParking();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
    private class Data {
        int index;
        String state, color;
        Data(int index, String state, String color){
            this.index = index;
            this.state = state;
            this.color = color;
        }
    }

    private void drawParking() {
        for (int i = 0; i < Constants.TOP_PARKING_LOTS_NUMBER + 1; i++) {

            this.add(new ParkingLotBorder(this.getWidth(), this.getHeight(), i, "top"));
            if (i < Constants.TOP_PARKING_LOTS_NUMBER) {
                Data data= getData();

                this.add(new ParkingLotNumber(this.getWidth(), this.getHeight(), data.index, "top"));
                this.add(new ParkingLotButton(this.getWidth(), this.getHeight(), data.index, "top", data.state, data.color, THIS));
            }
        }
        for (int i = 0; i < Constants.PARKING_LOTS_NUMBER + 1; i++) {
            this.add(new ParkingLotBorder(this.getWidth(), this.getHeight(), i, "right"));
            if (i < Constants.PARKING_LOTS_NUMBER) {
                Data data = getData();
                this.add(new ParkingLotNumber(this.getWidth(), this.getHeight(), data.index, "right"));
                this.add(new ParkingLotButton(this.getWidth(), this.getHeight(), data.index, "right", data.state, data.color, THIS));
            }
        }
        for (int i = 0; i < Constants.PARKING_LOTS_NUMBER + 1; i++) {
            this.add(new ParkingLotBorder(this.getWidth(), this.getHeight(), i, "left"));
            if (i < Constants.PARKING_LOTS_NUMBER) {
                Data data = getData();
                this.add(new ParkingLotNumber(this.getWidth(), this.getHeight(), data.index, "left"));
                this.add(new ParkingLotButton(this.getWidth(), this.getHeight(), data.index, "left", data.state, data.color, THIS));
            }
        }
        for (int i = 0; i < Constants.PARKING_LOTS_NUMBER + 1; i++) {
            this.add(new ParkingLotBorder(this.getWidth(), this.getHeight(), i, "middle"));
        }

        for (int i = 0; i < Constants.PARKING_LOTS_NUMBER; i++) {
            Data data = getData();
            this.add(new ParkingLotNumber(this.getWidth(), this.getHeight(), data.index, "middleright"));
            this.add(new ParkingLotButton(this.getWidth(), this.getHeight(), data.index, "middleright", data.state, data.color, THIS));
        }
        for (int i = 0; i < Constants.PARKING_LOTS_NUMBER; i++) {
            Data data = getData();
            this.add(new ParkingLotNumber(this.getWidth(), this.getHeight(), data.index, "middleleft"));
            this.add(new ParkingLotButton(this.getWidth(), this.getHeight(), data.index, "middleleft", data.state, data.color, THIS));

        }
        this.add(new ParkingLotBorder(this.getWidth(), this.getHeight(), 0, "middlebreak"));

    }
    public Data getData(){
        int index = 0;
        String state = "";
        String color = "";

        try {
            this.parkingLotsData.next();
            state = this.parkingLotsData.getString("state");
            color = this.parkingLotsData.getString("color");
            index = this.parkingLotsData.getInt("number");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return new Data(index, state, color);
    }
}
