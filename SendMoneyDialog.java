import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SendMoneyDialog extends JDialog {
private JComboBox<String> sendMethodComboBox;
private JTextField recipientField;
private JButton sendButton;
private BankingFrame parentFrame;
public SendMoneyDialog(BankingFrame parentFrame) {
super(parentFrame, "Send Money", true);
this.parentFrame = parentFrame;
setSize(300, 150);
setLayout(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();
gbc.insets = new Insets(5, 5, 5, 5);
sendMethodComboBox = new JComboBox<>(new String[]{"SMS", "Email"});
gbc.gridx = 0;
gbc.gridy = 0;
gbc.anchor = GridBagConstraints.WEST;
add(sendMethodComboBox, gbc);
recipientField = new JTextField(20);
gbc.gridx = 1;
gbc.gridy = 0;
gbc.fill = GridBagConstraints.HORIZONTAL;
add(recipientField, gbc);
sendButton = new JButton("Send");
gbc.gridx = 0;
gbc.gridy = 1;
gbc.gridwidth = 2;
gbc.anchor = GridBagConstraints.CENTER;
add(sendButton, gbc);

sendButton.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
sendMoney();
}
});
}
private void sendMoney() {
String selectedMethod = (String) sendMethodComboBox.getSelectedItem();
String recipient = recipientField.getText().trim();
double amount = parentFrame.getAmount();
boolean validRecipient = false;
if ("SMS".equals(selectedMethod) && recipient.matches("\\d{10}")) {
validRecipient = true;
} else if ("Email".equals(selectedMethod) && recipient.matches(".+@gmail\\.com")) {
validRecipient = true;
}
if (validRecipient) {
parentFrame.getController().sendMoney(amount, "Send Money (" + selectedMethod +
")", recipient);
dispose();
} else {
JOptionPane.showMessageDialog(this, "Invalid recipient.");
}
}
}
