import java.util.ArrayList;
import java.util.HashMap;

/**
 * Abstract class for every supported command of the payment app. Every subclass implements
 * a way to validate and execute the command. 
 *  
 * @author anthonyyim@gmail.com (Anthony Yim)
 */
public abstract class Command {
  String commandType;

  /**
   * Ensures command syntax is valid.
   *
   * @param tokens Tokenized form of the input string (tokenized on single space).
   * @param users All users that have been added to the payment app.
   * @param creditCards All credit cards that have been added to users in the app.
   * @throws InvalidCommandException
   */
  abstract public void validate(String[] tokens, HashMap<String, User> users, HashMap<String, CreditCard> creditCards) throws InvalidCommandException;
  
  /**
   * Carries out the desired behavior of the user command.
   * 
   * @param tokens Tokenized form of the input string (tokenized on single space). 
   * @param users All users that have been added to the payment app.
   * @param creditCards All credit cards that have been added to users in the app.
   * @return List of Strings containing information that should be shown to the user.
   */
  abstract public ArrayList<String> execute(String[] tokens, HashMap<String, User> users, HashMap<String, CreditCard> creditCards);

  /**
   * Specifies whether this class should be handling the given command.
   */
  public boolean isHandler(String commandType) {
    if (this.commandType.equals(commandType)) {
      return true;
    }
    return false;
  }
}
