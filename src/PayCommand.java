import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The command that executes a transfer of funds (payment) from an actor (payer) to a target (payee).
 * 
 * @author anthonyyim@gmail.com (Anthony Yim)
 */
public class PayCommand extends Command {
  public static final String INVALID_NUMBER_OF_ARGS = "Payment not processed - invalid number of arguments for command \"pay\".";
  public static final String USER_DOES_NOT_EXIST = "Payment not processed - user %s does not exist.";
  public static final String USER_MISSING_CREDIT_CARD = "Payment not processed - user %s does not have a credit card.";
  public static final String INVALID_AMOUNT_REPRESENTATION = "Payment not processed - invalid amount representation." +
  		" But be positive amount and of form $XX.XX. E.g., $12.00.";

  
  public PayCommand() {
    commandType = "pay";
  }
  
  public void validate(String[] tokens, HashMap<String, User> users, HashMap<String, CreditCard> creditCards) throws InvalidCommandException {
    if (tokens.length < 5) {
      throw new InvalidCommandException(INVALID_NUMBER_OF_ARGS);

    } else if (!users.containsKey(tokens[1])) {
      throw new InvalidCommandException(String.format(USER_DOES_NOT_EXIST, tokens[1]));

    } else if (!users.containsKey(tokens[2])) {
      throw new InvalidCommandException(String.format(USER_DOES_NOT_EXIST, tokens[2]));

    } else if (!users.get(tokens[1]).hasCreditCard()) {
      throw new InvalidCommandException(String.format(USER_MISSING_CREDIT_CARD, tokens[1]));

    } else if (!isValidAmountRepresentation(tokens[3])) {
      throw new InvalidCommandException(INVALID_AMOUNT_REPRESENTATION);
    }
  }

  public ArrayList<String> execute(String[] tokens, HashMap<String, User> users, HashMap<String, CreditCard> creditCards) {
    User actor = users.get(tokens[1]);
    User target = users.get(tokens[2]);
    BigDecimal bigDecimalAmount = new BigDecimal(tokens[3].substring(1));

    // Concatentate note, which may have been tokenized if it contains spaces, back to one string.
    String note = "";
    for(int i = 4; i < tokens.length; i++) {
      note += tokens[i] + " ";
    }
    
    // NOTE: Ensure chargeCreditCard and incrementBalance is one atomic transaction in a
    // "real" application with a database.
    actor.chargeCreditCard(bigDecimalAmount);
    actor.recordTransaction(actor, target, bigDecimalAmount, note);
    target.incrementBalance(bigDecimalAmount);
    target.recordTransaction(actor, target, bigDecimalAmount, note);

    return new ArrayList<String>();
  }

  private boolean isValidAmountRepresentation(String amount) {
    if(amount.matches("^[$][0-9]{1,}[.][0-9]{2}")) {
      return true;
    }
    return false;
  }
}
