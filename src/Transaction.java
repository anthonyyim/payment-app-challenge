import java.math.BigDecimal;

/**
 * Represents a single transaction that has occured between two users.
 * Actor - person initiating the payment (payer).
 * Target - person receiving the payment (payee).
 * 
 * @author anthonyyim@gmail.com (Anthony Yim)
 */
public class Transaction {
  User actor;
  User target;
  BigDecimal amount;
  String note;

  public Transaction(User actor, User target, BigDecimal amount, String note){
    this.actor = actor;
    this.target = target;
    this.amount = amount;
    this.note = note;
  }

  public User getActor() {
    return actor;
  }

  public User getTarget() {
    return target;
  }
  
  public BigDecimal getAmount() {
    return amount;
  }

  public String getNote() {
    return note;
  }
}
