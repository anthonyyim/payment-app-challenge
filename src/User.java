import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Represents a given user in the payment app.
 * 
 * @author anthonyyim@gmail.com (Anthony Yim)
 */
public class User {
  private String name;
  private BigDecimal balance;
  private boolean hasCreditCard = false;
  private ArrayList<Transaction> transactionHistory = new ArrayList<Transaction>();

  public User(String name) {
    this.name = name;
    balance = new BigDecimal("0.00");
  }

  public void addCreditCard(CreditCard creditCard) {
    hasCreditCard = true;
  }

  public void chargeCreditCard(BigDecimal amount) {
    // <Intentionally left unimplemented for puzzle>
  }

  public void incrementBalance(BigDecimal amount) {
    balance = balance.add(amount);
  }

  public ArrayList<Transaction> getTransactionHistory() {
    return transactionHistory;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void recordTransaction(User actor, User target, BigDecimal amount, String note) {
    transactionHistory.add(new Transaction(actor, target, amount, note));
  }

  public static boolean isValidName(String name) {
    if(name.matches("[a-zA-Z0-9_-]{4,15}")) {
      return true;
    }
    return false;
  }

  public String getName() {
    return name;
  }

  public boolean hasCreditCard() {
    return hasCreditCard;
  }
}