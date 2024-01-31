public class BankingController {
private BankingFrame frame;
private double balance;
public BankingController(BankingFrame frame) {
this.frame = frame;
this.balance = 1000;
frame.updateBalance(balance);
}
public void executeAction(String action, double amount) {
if (amount <= 0) {
frame.showMessage("Amount must be positive.");
return;
}
switch (action) {
case "Deposit":
deposit(amount);
break;
case "Withdraw":
withdraw(amount);
break;
case "Send Money":
break;
}
frame.updateBalance(balance);
}
private void deposit(double amount) {
balance += amount;
frame.recordTransaction("Deposit", amount);
}
private void withdraw(double amount) {
if (balance >= amount) {
balance -= amount;

frame.recordTransaction("Withdraw", amount);
} else {
frame.showMessage("Insufficient balance.");
}
}
public void sendMoney(double amount, String transactionType, String recipient) {
if (balance >= amount) {
balance -= amount;
frame.recordTransaction(transactionType, amount);
frame.updateBalance(balance);
frame.showMessage("Money sent to: " + recipient);
} else {
frame.showMessage("Insufficient balance.");
}
}
}
