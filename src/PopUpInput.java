import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class PopUpInput extends JFrame {
    JTextField name;
    JTextField carID;
    JFrame parent;
    String colorName, inputType;
    ColorButton red, orange, yellow, green, babyBlue, pink, blue, purple;

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    ParkingLotButton parentButton;
    int index;

    PopUpInput(String title, String btnLabel, boolean includeColor, String inputType, int index, JFrame parent, ParkingLotButton parentButton) {
        this.setSize(500, 300);
        this.inputType = inputType;
        this.parent = parent;
        this.parentButton = parentButton;
        colorName = "";
        this.index = index;
        this.getContentPane().setBackground(Constants.POP_UP_BACKGROUND_COLOR);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setIconImage(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("./assets/TitleBarLogo.png"))).getImage());
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                parent.setEnabled(true);
            }
        });
        drawButton(btnLabel);
        drawInputFields();
        if (includeColor) drawColors();
        this.setVisible(true);
    }

    public void drawButton(String btnLabel) {

        MenuButton btn = new MenuButton(btnLabel, this.getWidth() / 2 - Constants.BUTTON_WIDTH / 3, this.getHeight() - Constants.BUTTON_HEIGHT - 60, 1, 20);
        switch (this.inputType.toLowerCase()) {
            case "join":
                btn.addActionListener(e -> joinParkingRoutine());
                break;
            case "cancel":
                btn.addActionListener(e -> cancelSubscriptionRoutine());
                break;
            case "normalpark":
                btn.addActionListener(e -> parkingCarRoutine());
                break;
            case "reservedpark":
                btn.addActionListener(e -> parkingAtReservedRoutine());
                break;
            case "move":
                btn.addActionListener(e -> moveCarRoutine());
                break;
        }

        btn.setSize((int) (Constants.BUTTON_WIDTH / 1.5), Constants.BUTTON_HEIGHT - 10);
        this.add(btn);
    }

    public void refreshColors() {
        red.updateBorder();
        red.repaint();
        red.revalidate();

        orange.updateBorder();
        orange.repaint();
        orange.revalidate();

        yellow.updateBorder();
        yellow.repaint();
        yellow.revalidate();

        green.updateBorder();
        green.repaint();
        green.revalidate();

        babyBlue.updateBorder();
        babyBlue.repaint();
        babyBlue.revalidate();

        pink.updateBorder();
        pink.repaint();
        pink.revalidate();

        blue.updateBorder();
        blue.repaint();
        blue.revalidate();

        purple.updateBorder();
        purple.repaint();
        purple.revalidate();
    }

    public void drawInputFields() {
        this.name = new JTextField();
        this.name.setSize((int) (this.getWidth() / 1.8), 30);
        this.name.setBorder(null);
        this.name.setLocation(this.getWidth() / 2 - this.name.getWidth() / 2, 50);
        this.name.setFont(new Font("ariel", Font.BOLD, 25));
        JLabel nameLabel = new JLabel();
        nameLabel.setText("Name");
        nameLabel.setFont(new Font("arial", 1, 20));
        nameLabel.setSize(70, 50);

        nameLabel.setLocation(this.getWidth() / 2 - this.name.getWidth() / 2 - 65, 50 - 10);
        nameLabel.setForeground(Color.BLACK);

        this.carID = new JTextField();
        this.carID.setSize((int) (this.getWidth() / 1.8), 30);
        this.carID.setBorder(null);
        this.carID.setLocation(this.getWidth() / 2 - this.carID.getWidth() / 2, 100);
        this.carID.setFont(new Font("ariel", Font.BOLD, 25));
        JLabel carIDLabel = new JLabel();
        carIDLabel.setText("Car ID");
        carIDLabel.setFont(new Font("arial", 1, 20));
        carIDLabel.setSize(70, 50);

        carIDLabel.setLocation(this.getWidth() / 2 - this.carID.getWidth() / 2 - 70, 100 - 10);
        carIDLabel.setForeground(Color.BLACK);

        this.add(nameLabel);
        this.add(name);
        this.add(carIDLabel);
        this.add(carID);
    }

    public void drawColors() {
        int totalWidth = (int) (this.getWidth() / 1.8);
        int initX = this.getWidth() / 2 - totalWidth / 2;
        int margin = totalWidth - (int) (8 * 30);
        red = new ColorButton("Red", Color.red, initX, 150, 0, this);
        orange = new ColorButton("Orange", Color.ORANGE, initX + margin, 150, 1, this);
        yellow = new ColorButton("Yellow", Color.YELLOW, initX + 2 * margin, 150, 2, this);
        green = new ColorButton("Green", Color.GREEN, initX + 3 * margin, 150, 3, this);
        babyBlue = new ColorButton("BabyBlue", Color.CYAN, initX + 4 * margin, 150, 4, this);
        pink = new ColorButton("Pink", Color.PINK, initX + 5 * margin, 150, 5, this);
        blue = new ColorButton("Blue", Color.BLUE, initX + 6 * margin, 150, 6, this);
        purple = new ColorButton("Purple", new Color(116, 8, 232), initX + 7 * margin, 150, 7, this);
        JLabel colorLabel = new JLabel();
        colorLabel.setText("Color");
        colorLabel.setFont(new Font("arial", 1, 20));
        colorLabel.setSize(70, 50);

        colorLabel.setLocation(this.getWidth() / 2 - totalWidth / 2 - 70, 150 - 10);
        colorLabel.setForeground(Color.BLACK);
        this.add(colorLabel);
        this.add(red);
        this.add(orange);
        this.add(yellow);
        this.add(green);
        this.add(babyBlue);
        this.add(pink);
        this.add(blue);
        this.add(purple);
    }

    private void joinParkingRoutine() {
        if (this.name.getText().trim().isEmpty() || this.carID.getText().trim().isEmpty()) {
            showMessage("Invaild input", "Please enter valid username and carID.");
        } else {

            try {
                SQLConnectionStatement statement = new SQLConnectionStatement();
                ResultSet lotsWithTheSameName = statement.getStatments("select count(*) from parking_lots where is_reserved = true and carID = '" + this.carID.getText().trim() + "'");
                lotsWithTheSameName.next();
                if (lotsWithTheSameName.getInt(1) != 0) {

                    showMessage("Warning", "You have already subscribed to the parking.");
                } else {
                    ResultSet freeLotsCount = statement.getStatments("select count(*) from parking_lots where is_reserved = false and state = 'normal' ");
                    freeLotsCount.next();
                    if (freeLotsCount.getInt(1) == 0) {
                        showMessage("Warning", "Unfortunately, there are no free lots at the moment.");
                    } else {
                        ResultSet freeLots = statement.getStatments("select  * from parking_lots where is_reserved = false and state = 'normal' limit 1");
                        freeLots.next();
                        statement.updateStatments("update parking_lots set is_reserved = true, state = 'reserved', username = '" + this.name.getText().trim() + "', carID = '" + this.carID.getText().trim() + "' where number = " + freeLots.getInt(1));
                        showMessage("Success", "You have successfully subscriped to the parking and your parking lot will be NO." + (freeLots.getInt(1) + 1));
                    }

                }

            } catch (SQLException err) {
                System.out.println(err);
            }
        }
    }

    private void cancelSubscriptionRoutine() {
        if (this.name.getText().trim().isEmpty() || this.carID.getText().trim().isEmpty()) {
            showMessage("Invaild input", "Please enter valid username and carID.");
        } else {
            try {
                SQLConnectionStatement statement = new SQLConnectionStatement();
                ResultSet lotsWithTheSameNameCount = statement.getStatments("select count(*) from parking_lots where is_reserved = true and carID = '" + this.carID.getText().trim() + "' and username = '" + this.name.getText().trim() + "' ;");
                lotsWithTheSameNameCount.next();
                if (lotsWithTheSameNameCount.getInt(1) == 0) {
                    showMessage("Warning", "It appears that there is no subscription with this carID and Name.");
                } else {
                    ResultSet lotsWithTheSameName = statement.getStatments("select number from parking_lots where is_reserved = true and carID = '" + this.carID.getText().trim() + "' and username = '" + this.name.getText().trim() + "' and number = " + this.index);
                    lotsWithTheSameName.next();
                    statement.updateStatments("update parking_lots set is_reserved = false, state = 'normal', username = null, carID = null where number = " + lotsWithTheSameName.getInt(1));
                    showMessage("Success", "You have successfully canceled your subscription.");
                }

            } catch (SQLException err) {
                System.out.println(err);
            }
        }
    }

    private void parkingCarRoutine() {
        if (this.name.getText().trim().isEmpty() || this.carID.getText().trim().isEmpty() || this.colorName.isEmpty()) {
            showMessage("Invalid input", "Please enter a valid username, carID and a color.");
        } else {
            SQLConnectionStatement statement = new SQLConnectionStatement();
            boolean duplicates = true;
            try {
                ResultSet lotsWithTheSameNameCount = statement.getStatments("select count(*) from parking_lots where carID = '" + this.carID.getText().trim() + "'");
                lotsWithTheSameNameCount.next();
                duplicates = lotsWithTheSameNameCount.getInt(1) > 0;
                if (duplicates) {
                    showMessage("Warning", "It appears that this car is already parked, try again.");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            if (!duplicates) {
                Date date = new Date();
                String parkingDate = new Timestamp(date.getTime()).toString();
                statement.updateStatments("update parking_lots set state = 'parked', username = '" + this.name.getText().trim() + "', carID = '" + this.carID.getText().trim() + "', color = '" + this.colorName + "', parkingDate = '" + parkingDate + "' where number = " + this.index);
                parent.setEnabled(true);
                this.parentButton.setCarColorName(this.colorName);
                this.parentButton.removeActionListener(this.parentButton.getActionListeners()[0]);
                this.parentButton.setState("parked");
                this.parentButton.drawButton();
                this.parentButton.setButtonAttributes();
                this.parentButton.revalidate();
                this.parentButton.repaint();
                this.dispose();
            }
        }
    }

    private void parkingAtReservedRoutine() {
        if (this.name.getText().trim().isEmpty() || this.carID.getText().trim().isEmpty() || this.colorName.isEmpty()) {
            showMessage("Invalid input", "Please enter a valid username, carID and a color.");
        } else {
            SQLConnectionStatement statement = new SQLConnectionStatement();
            boolean isTheSubscriber = true;
            try {
                ResultSet lot = statement.getStatments("select username, carID from parking_lots where number = " + this.index);
                lot.next();
                isTheSubscriber = lot.getString("username").equals(this.name.getText().trim()) && lot.getString("carID").equals(this.carID.getText().trim());
                if (!isTheSubscriber) {
                    showMessage("Warning", "This is a reserved lot, try parking on another one.");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            if (isTheSubscriber) {
                statement.updateStatments("update parking_lots set state = 'parked', username = '" + this.name.getText().trim() + "', carID = '" + this.carID.getText().trim() + "', color = '" + this.colorName + "' where number = " + this.index);
                parent.setEnabled(true);
                this.parentButton.setCarColorName(this.colorName);
                this.parentButton.removeActionListener(this.parentButton.getActionListeners()[0]);
                this.parentButton.setState("parked");
                this.parentButton.drawButton();
                this.parentButton.setButtonAttributes();
                this.parentButton.revalidate();
                this.parentButton.repaint();
                this.dispose();
            }
        }

    }

    private void moveCarRoutine() {
        if (this.name.getText().trim().isEmpty() || this.carID.getText().trim().isEmpty()) {
            showMessage("Invalid input", "Please enter a valid username and carID.");
        } else {
            SQLConnectionStatement statement = new SQLConnectionStatement();
            boolean isTheCarOwner = true;
            boolean isLotReserved = false;
            Date parkingDate = null;
            try {
                ResultSet lot = statement.getStatments("select username, carID, is_reserved, parkingDate from parking_lots where number =" + this.index);

                lot.next();
                isTheCarOwner = lot.getString("username").equals(this.name.getText().trim()) && lot.getString("carID").equals(this.carID.getText().trim());

                if (!isTheCarOwner) {
                    showMessage("Warning", "There might be something wrong in the information you entered, please try again.");
                }

                isLotReserved = lot.getBoolean("is_reserved");
                parkingDate = lot.getDate("parkingDate");
            } catch (SQLException e) {
                System.out.println(e);
            }
            if (isTheCarOwner) {
                statement.updateStatments("update parking_lots set parkingDate = null, state = '" + (isLotReserved ? "reserved" : "normal") + "' , color = null " + (isLotReserved ? " " : ", username = null, carID = null ") + " where number = " + this.index);
                this.parentButton.setCarColorName("");
                this.parentButton.removeActionListener(this.parentButton.getActionListeners()[0]);
                this.parentButton.setState(isLotReserved ? "reserved" : "normal");
                this.parentButton.drawButton();
                this.parentButton.setButtonAttributes();
                this.parentButton.revalidate();
                this.parentButton.repaint();
                if (!isLotReserved) {
                    double parkingFees = ((new Date().getTime() - parkingDate.getTime()) / 1000 / 60 / 60.0) * Constants.PARKING_HOURS_FEES;
                    showMessage("Car moved", "Thank you for parking. The parking fees are: " + String.format("%,.2f", parkingFees));
                }else{
                    this.parent.setEnabled(true);
                    this.dispose();
                }

            }
        }

    }

    private void showMessage(String title, String message) {
        new PopUpMessage(title, message, this.parent);
        this.dispose();
    }
}
