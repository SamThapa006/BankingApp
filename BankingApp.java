import javax.swing.JFrame;

public class BankingApp {
public static void main(String[] args) {
UserManager userManager = new UserManager();
LoginFrame loginFrame = new LoginFrame(userManager);
loginFrame.setVisible(true);
}
}
