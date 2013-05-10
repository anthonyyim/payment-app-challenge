import java.util.ArrayList;
import java.util.HashMap;

/**
 * The command that creates a new user in the payment app.
 * 
 * @author anthonyyim@gmail.com (Anthony Yim)
 */
public class UserCommand extends Command {
  public static final String INVALID_NUMBER_OF_ARGS = "User not created - invalid number of arguments for command \"user\".";
  public static final String INVALID_NAME = "User not created - name invalid. May only contain alphanumeric characters, underscores and dashes.";
  public static final String USER_ALREADY_EXISTS = "User not created - user with the same name exists already. Please use another name.";

  public UserCommand() {
    commandType = "user";
  }
  
  public void validate(String[] tokens, HashMap<String, User> users, HashMap<String, CreditCard> creditCards) throws InvalidCommandException {
    if (tokens.length != 2) {
      throw new InvalidCommandException(INVALID_NUMBER_OF_ARGS);

    } else if (!User.isValidName(tokens[1])) {
      throw new InvalidCommandException(INVALID_NAME);

    } else if (users.containsKey(tokens[1])) {
      throw new InvalidCommandException(USER_ALREADY_EXISTS);
    }
  }

  public ArrayList<String> execute(String[] tokens, HashMap<String, User> users, HashMap<String, CreditCard> creditCards){
    users.put(tokens[1], new User(tokens[1]));

    return new ArrayList<String>();
  }
}
