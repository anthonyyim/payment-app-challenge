import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The command that displays a given user's current balance in their account.
 * 
 * @author anthonyyim@gmail.com (Anthony Yim)
 */
public class BalanceCommand extends Command{
  public static final String INVALID_NUMBER_OF_ARGS = "Invalid number of arguments for command \"balance\".";
  public static final String USER_DOES_NOT_EXIST = "User does not exist.";
  
  public BalanceCommand() {
    commandType = "balance";
  }
  
  public void validate(String[] tokens, HashMap<String, User> users, HashMap<String, CreditCard> creditCards) throws InvalidCommandException {
    if (tokens.length != 2) {
      throw new InvalidCommandException(INVALID_NUMBER_OF_ARGS);

    } else if (!users.containsKey(tokens[1])) {
      throw new InvalidCommandException(USER_DOES_NOT_EXIST);
    }
  }

  public ArrayList<String> execute(String[] tokens, HashMap<String, User> users, HashMap<String, CreditCard> creditCards) {
    ArrayList<String> output = new ArrayList<String>();
    BigDecimal balance = users.get(tokens[1]).getBalance();

    output.add(String.format(String.format("$%s", balance)));

    return output;
  }
}
