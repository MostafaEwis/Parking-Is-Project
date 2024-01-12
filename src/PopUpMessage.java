import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Objects;

public class PopUpMessage extends JFrame {
    private JFrame parent;

    PopUpMessage(String title, String message, JFrame parent) {
        this.parent = parent;
        this.setSize(500, 300);
        this.getContentPane().setBackground(Constants.POP_UP_BACKGROUND_COLOR);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setIconImage(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("./assets/TitleBarLogo.png"))).getImage());
        this.setResizable(false);
        this.setLayout(null);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                parent.setEnabled(true);
            }
        });
        drawMessage(message);
        drawSideImage();

        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public void drawSideImage() {
        JLabel sideImage = new JLabel();
        ImageIcon image = new ImageIcon(this.getClass().getResource("./assets/PopUpIcon.png"));
        image.setImage(image.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        sideImage.setIcon(image);
        sideImage.setSize(80, 80);
        sideImage.setLocation(40, 25);
        this.add(sideImage);
    }

    public void drawMessage(String message) {
        JLabel messageLabel = new JLabel();
        messageLabel.setText("<html>" + message + "</html>");
        messageLabel.setForeground(Color.black);
        messageLabel.setSize(this.getWidth() - 150, this.getHeight() - 150);
        messageLabel.setLocation(40 + 80 + 15, 25 + 40 - (this.getHeight() - 150) / 3);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 25));
        this.add(messageLabel);
    }

}
