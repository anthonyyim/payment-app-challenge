import java.util.ArrayList;
import java.util.HashMap;

/**
 * The command for adding a credit card to a user.
 * 
 * @author anthonyyim@gmail.com (Anthony Yim)
 */
public class AddCreditCardCommand extends Command{
  public static final String INVALID_NUMBER_OF_ARGS = "Credit card not added - invalid number of arguments for command \"add\".";
  public static final String INVALID_CREDIT_CARD_NUMBER = "Credit card not added - invalid credit card number.";
  public static final String USER_DOES_NOT_EXIST = "Credit card not added - user does not exist.";
  public static final String CARD_ALREADY_IN_USE = "Credit card not added - card already in use in system.";
   
  public AddCreditCardCommand() {
    commandType = "add";
  }
  
  public void validate(String[] tokens, HashMap<String, User> users, HashMap<String, CreditCard> creditCards) throws InvalidCommandException {
    if (tokens.length != 3) {
      throw new InvalidCommandException(INVALID_NUMBER_OF_ARGS);

    } else if (!CreditCard.isValidCreditCardNumber(tokens[2])) {
      throw new InvalidCommandException(INVALID_CREDIT_CARD_NUMBER);

    } else if (!users.containsKey(tokens[1])) {
      throw new InvalidCommandException(USER_DOES_NOT_EXIST);

    } else if (creditCards.containsKey(tokens[2])) {
      throw new InvalidCommandException(CARD_ALREADY_IN_USE);
    }
  }

  public ArrayList<String> execute(String[] tokens, HashMap<String, User> users, HashMap<String, CreditCard> creditCards) {
    CreditCard creditCard = new CreditCard(tokens[2]);
    users.get(tokens[1]).addCreditCard(creditCard);
    creditCards.put(tokens[2], creditCard);

    return new ArrayList<String>();
  }
}
