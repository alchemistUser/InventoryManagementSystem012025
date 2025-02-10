
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class RoundedTextField extends JTextField {

    private int radius;

    public RoundedTextField(int radius) {
        super();
        this.radius = radius;
        setOpaque(false);
        setBorder(null);
        setBackground(Color.WHITE);
        setHorizontalAlignment(JTextField.CENTER); // Center text
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
    }
}

class RoundedPasswordField extends JPasswordField {

    private int radius;

    public RoundedPasswordField(int radius) {
        super();
        this.radius = radius;
        setOpaque(false);
        setBorder(null);
        setBackground(Color.WHITE);
        setHorizontalAlignment(JTextField.CENTER); // Center text
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
    }
}

public class Login extends JFrame {

    private RoundedTextField usernameField;
    private RoundedPasswordField passwordField;
    private JButton loginButton;

    public Login() {
        setTitle("INVENSYNC - Login");
        setSize(1450, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Set Icon
        ImageIcon icon = new ImageIcon("invensync-green.png");
        setIconImage(icon.getImage());

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0x6e9277));
        mainPanel.setLayout(null);
        add(mainPanel);

        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(new Color(0xffe5b6));
        loginPanel.setBounds(520, 100, 400, 500);
        loginPanel.setLayout(null);
        mainPanel.add(loginPanel);

        ImageIcon logoIcon = new ImageIcon("src/icons/invensync-green.png");
        Image originalImage = logoIcon.getImage();
        int originalWidth = logoIcon.getIconWidth();
        int originalHeight = logoIcon.getIconHeight();
        int newWidth = 250;
        int newHeight = (newWidth * originalHeight) / originalWidth;

        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedLogoIcon = new ImageIcon(resizedImage);
        JLabel logoLabel = new JLabel(resizedLogoIcon);
        logoLabel.setBounds(80, 40, newWidth, newHeight);
        loginPanel.add(logoLabel);

        // Username Field with Centered Placeholder
        usernameField = new RoundedTextField(20);
        usernameField.setText("Enter Username");
        usernameField.setBounds(100, 200, 200, 30);
        usernameField.setForeground(Color.GRAY);
        usernameField.setHorizontalAlignment(JTextField.CENTER); // Center text
        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("Enter Username")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setForeground(Color.GRAY);
                    usernameField.setText("Enter Username");
                }
            }
        });
        loginPanel.add(usernameField);

        // Password Field with Centered Placeholder
        passwordField = new RoundedPasswordField(20);
        passwordField.setText("Enter Password");
        passwordField.setBounds(100, 250, 200, 30);
        passwordField.setForeground(Color.GRAY);
        passwordField.setEchoChar((char) 0);
        passwordField.setHorizontalAlignment(JTextField.CENTER); // Center text
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(passwordField.getPassword()).equals("Enter Password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setEchoChar('*');
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(passwordField.getPassword()).isEmpty()) {
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setText("Enter Password");
                    passwordField.setEchoChar((char) 0);
                }
            }
        });
        loginPanel.add(passwordField);

        loginButton = new JButton("LOGIN");
        loginButton.setBounds(150, 300, 100, 30);
        loginButton.setBackground(Color.WHITE);
        loginButton.setFocusable(false);
        loginPanel.add(loginButton);
        loginButton.addActionListener(e -> {
            try {
                validateLogin();
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        JLabel aboutLabel = new JLabel("<html><u>About</u></html>");
        aboutLabel.setForeground(Color.BLACK);
        aboutLabel.setBounds(183, 440, 50, 20);
        loginPanel.add(aboutLabel);

        setVisible(true);
    }

    private void validateLogin() throws IOException {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals("admin") && password.equals("admin123")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            dispose();
//            new Dashboard();
//            Main.main();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password");
        }
    }

//    public static void main(String[] args) {
//        new Login();
//    }
}
