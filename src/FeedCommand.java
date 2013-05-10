import java.util.ArrayList;
import java.util.HashMap;

/**
 * The command that displays a given user's entire transaction history ordered by oldest first.
 * 
 * @author anthonyyim@gmail.com (Anthony Yim)
 */
public class FeedCommand extends Command {
  public static final String INVALID_NUMBER_OF_ARGS = "Invalid number of arguments for command \"feed\".";
  public static final String USER_DOES_NOT_EXIST = "User does not exist.";
  

  public FeedCommand() {
    commandType = "feed";
  }
  
  public void validate(String[] tokens, HashMap<String, User> users, HashMap<String, CreditCard> creditCards) throws InvalidCommandException {
    if (tokens.length != 2) {
      throw new InvalidCommandException(INVALID_NUMBER_OF_ARGS);

    } else if (!users.containsKey(tokens[1])) {
      throw new InvalidCommandException(USER_DOES_NOT_EXIST);
    }
  }

  public ArrayList<String> execute(String[] tokens, HashMap<String, User> users, HashMap<String, CreditCard> creditCards) {
    ArrayList<Transaction> transactionHistory = users.get(tokens[1]).getTransactionHistory();
    ArrayList<String> output = new ArrayList<String>();
    User user = users.get(tokens[1]);

    for (Transaction t : transactionHistory) {
      String actorName = t.getActor().getName();
      String targetName = t.getTarget().getName();
 
      if(actorName.equals(user.getName())) {
        output.add(String.format("You paid %s $%s for %s", targetName, t.getAmount(), t.getNote()));
      } else {
        output.add(String.format("%s paid you $%s for %s", actorName, t.getAmount(), t.getNote()));
      }
    }

    return output;
  }
}
