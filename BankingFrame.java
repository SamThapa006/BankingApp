import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
public class BankingFrame extends JFrame {
private JComboBox<String> actionComboBox;
private JFormattedTextField amountField;
private JButton executeButton, viewTransactionsButton;
private JLabel balanceLabel, userLabel;
private BankingController controller;
private List<String> transactionHistory = new ArrayList<>();
private String loggedInUser;
public BankingFrame(String loggedInUser) {
super("Simple Banking App");
this.loggedInUser = loggedInUser;
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setSize(500, 400);
setLayout(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();
gbc.insets = new Insets(5, 5, 5, 5);
initializeComponents(gbc);
}
public BankingController getController() {
return this.controller;
}
public double getAmount() {
try {
return ((Number) amountField.getValue()).doubleValue();
} catch (Exception e) {

return 0.0;
}
}
public double getBalance() {
return 0.0;
}
private void initializeComponents(GridBagConstraints gbc) {
userLabel = new JLabel("Logged in as: " + loggedInUser);
gbc.gridx = 0;
gbc.gridy = 0;
gbc.gridwidth = 2;
gbc.anchor = GridBagConstraints.WEST;
add(userLabel, gbc);
balanceLabel = new JLabel("Balance: $1000.00");
gbc.gridy++;
add(balanceLabel, gbc);
String[] actions = {"Deposit", "Withdraw", "Send Money"};
actionComboBox = new JComboBox<>(actions);
gbc.gridy++;
gbc.gridwidth = 1;
add(actionComboBox, gbc);
NumberFormat format = NumberFormat.getCurrencyInstance();
amountField = new JFormattedTextField(format);
amountField.setColumns(10);
gbc.gridx++;
add(amountField, gbc);
executeButton = new JButton("Execute");
styleAndAddMouseListenerToButton(executeButton);
executeButton.addActionListener(e -> performAction());
gbc.gridy++;
gbc.gridx = 0;
gbc.gridwidth = 2;

gbc.anchor = GridBagConstraints.CENTER;
add(executeButton, gbc);
viewTransactionsButton = new JButton("View Transactions");
styleAndAddMouseListenerToButton(viewTransactionsButton);
viewTransactionsButton.addActionListener(e -> viewTransactions());
gbc.gridy++;
add(viewTransactionsButton, gbc);
setTooltips();
}
private void styleAndAddMouseListenerToButton(JButton button) {
Font originalFont = button.getFont().deriveFont(Font.BOLD, 12);
button.setFont(originalFont);
button.addMouseListener(new MouseAdapter() {
@Override
public void mouseEntered(MouseEvent e) {
button.setFont(originalFont.deriveFont(originalFont.getSize() + 2f));
}
@Override
public void mouseExited(MouseEvent e) {
button.setFont(originalFont);
}
});
}
public void setController(BankingController bankingController) {
this.controller = bankingController;
}
private void setTooltips() {
actionComboBox.setToolTipText("Select the action to perform");
amountField.setToolTipText("Enter the amount in dollars");
executeButton.setToolTipText("Click to perform the selected action");
viewTransactionsButton.setToolTipText("View your transaction history");
}
