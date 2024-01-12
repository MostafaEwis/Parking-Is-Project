import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

public class Menu extends JFrame {
    final Menu THIS = this;
    Menu() {
        this.setTitle("Parking app");
        this.setIconImage(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("./assets/TitleBarLogo.png"))).getImage());
        this.setSize(1000, 750);
        this.getContentPane().setBackground(Constants.MAIN_BACKGROUND_COLOR);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        drawButtons();
        drawLogo();
        drawBackground();
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }

    public void drawBackground() {
        ImageIcon icon = new ImageIcon(this.getClass().getResource("./assets/BackgroundImage.png"));
        JLabel BackgroundImage = new JLabel();
        BackgroundImage.setIcon(icon);

        BackgroundImage.setSize(this.getWidth(), 671);
        BackgroundImage.setLocation(0, this.getHeight() - BackgroundImage.getHeight());
        this.add(BackgroundImage);

    }

    public void drawLogo() {
        ImageIcon icon = new ImageIcon(this.getClass().getResource("./assets/logo.png"));

        icon.setImage(icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        JLabel BackgroundImage = new JLabel();
        BackgroundImage.setIcon(icon);

        BackgroundImage.setSize(200, 200);
        BackgroundImage.setLocation(this.getWidth() / 2 - BackgroundImage.getWidth() / 2, 75);
        this.add(BackgroundImage);

    }

    public void drawButtons() {
        MenuButton joinParking = new MenuButton("Join the Parking", this.getWidth() / 2 - Constants.BUTTON_WIDTH / 2, 300, 1, 20);
        MenuButton cancelSubscription = new MenuButton("Cancel subscription", this.getWidth() / 2 - Constants.BUTTON_WIDTH / 2, 370, 1, 15);
        MenuButton parkYourCar = new MenuButton("Park your car", this.getWidth() / 2 - Constants.BUTTON_WIDTH / 2, 440, 1, 20);
        MenuButton exitButton = new MenuButton("Exit", this.getWidth() / 2 - Constants.BUTTON_WIDTH / 4, 510, 1, 20);
        exitButton.setSize(Constants.BUTTON_WIDTH / 2, Constants.BUTTON_HEIGHT);
        exitButton.setBackground(Color.white);
        exitButton.setForeground(Constants.PARKING_BLUE);
        exitButton.setBorder(new RoundedBorder(10, Color.white
        ));
        exitButton.addActionListener(e -> {
            this.dispose();
        });
        parkYourCar.addActionListener(e -> {
            new Parking();
            this.dispose();
        });
        joinParking.addActionListener(e -> {
            new PopUpInput("Join the parking", "Join", false,"join",0, THIS, null);

            THIS.setEnabled(false);

        });
        cancelSubscription.addActionListener(e -> {
            new PopUpInput("Cancel your subscription", "Cancel", false,"cancel", 0, THIS, null);
            THIS.setEnabled(false);

        });
        this.add(joinParking);
        this.add(cancelSubscription);
        this.add(parkYourCar);
        this.add(exitButton);

    }
}
