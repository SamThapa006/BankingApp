import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {
private JTextField usernameField;
private JPasswordField passwordField;
private JButton loginButton;
private JButton createAccountButton;
private UserManager userManager;

public LoginFrame(UserManager userManager) {
super("Login - Banking App");
this.userManager = userManager;
initializeComponents();
}

private void initializeComponents() {
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setSize(400, 250);
setLayout(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();

gbc.insets = new Insets(10, 10, 10, 10);
gbc.fill = GridBagConstraints.HORIZONTAL;

gbc.gridx = 0;
gbc.gridy = 0;
gbc.gridwidth = 2;
gbc.anchor = GridBagConstraints.CENTER;
JLabel welcomeLabel = new JLabel("Welcome to Thapa Banking Inc.");
welcomeLabel.setFont(new Font("Serif", Font.BOLD, 20));
add(welcomeLabel, gbc);

addFieldComponents(gbc);

addLoginAndCreateButtons(gbc);
setLocationRelativeTo(null);
}

private void addFieldComponents(GridBagConstraints gbc) {
gbc.gridwidth = 1;
gbc.anchor = GridBagConstraints.WEST;

gbc.gridy = 1;
add(new JLabel("Username:"), gbc);
gbc.gridy++;
usernameField = new JTextField(20);
add(usernameField, gbc);

gbc.gridy++;
add(new JLabel("Password:"), gbc);
gbc.gridy++;
passwordField = new JPasswordField(20);
add(passwordField, gbc);
}

private void addLoginAndCreateButtons(GridBagConstraints gbc) {
gbc.gridy++;
loginButton = new JButton("Login");
styleButton(loginButton);
loginButton.addActionListener(e -> login());
addMouseListenerToButton(loginButton);
add(loginButton, gbc);

gbc.gridx++;
createAccountButton = new JButton("Create Account");
styleButton(createAccountButton);
createAccountButton.addActionListener(e -> createAccount());
addMouseListenerToButton(createAccountButton);
add(createAccountButton, gbc);
}

private void styleButton(JButton button) {
button.setFont(new Font("Arial", Font.BOLD, 12));
}

private void addMouseListenerToButton(JButton button) {
button.addMouseListener(new MouseAdapter() {
Font originalFont = null;

@Override
public void mouseEntered(MouseEvent e) {
originalFont = button.getFont();
button.setFont(new Font(originalFont.getName(), Font.BOLD, originalFont.getSize() + 2));
}

@Override
public void mouseExited(MouseEvent e) {
button.setFont(originalFont);
}
});
}

private void login() {
String username = usernameField.getText();
String password = new String(passwordField.getPassword());

if (userManager.checkUser(username, password)) {
SwingUtilities.invokeLater(() -> {
BankingFrame bankingFrame = new BankingFrame(username);
bankingFrame.setController(new BankingController(bankingFrame));
bankingFrame.setVisible(true);
this.dispose();
});
} else {
JOptionPane.showMessageDialog(this, "Invalid credentials.");
}
}

private void createAccount() {
String username = usernameField.getText();
String password = new String(passwordField.getPassword());

if (userManager.addUser(username, password)) {
JOptionPane.showMessageDialog(this, "Account created successfully. You can now log in.");
usernameField.setText("");
passwordField.setText("");
} else {
JOptionPane.showMessageDialog(this, "Failed to create account. Username may already exist or
fields are empty.");
}
}
}
