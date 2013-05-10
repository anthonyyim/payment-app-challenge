import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;


public class AddCreditCardCommandTest {
  private static Command command;
  private static HashMap<String, User> users;
  private static HashMap<String, CreditCard> creditCards;

  @BeforeClass
  public static void testSetup() {
    command = new AddCreditCardCommand();
    users = new HashMap<String, User>();
    users.put("Thomas", new User("Thomas"));
    creditCards = new HashMap<String, CreditCard>();
  }

  @Test(expected = InvalidCommandException.class) 
  public void testCommandLengthTooShortThrowsException() throws InvalidCommandException {
    String[] commandTooShortTokens = new String[] {"add","Thomas"};

    command.validate(commandTooShortTokens, users, creditCards);
  }

  @Test(expected = InvalidCommandException.class) 
  public void testCommandLengthTooLongThrowsException() throws InvalidCommandException {
    String[] commandTooLongTokens = new String[] {"add","Thomas", "79927398713","foo"};

    command.validate(commandTooLongTokens, users, creditCards);
  }

  @Test(expected = InvalidCommandException.class) 
  public void testInvalidCreditCardNumberThrowsException() throws InvalidCommandException {
    String[] commandTokens = new String[] {"add", "Thomas", "79927398715"};

    command.validate(commandTokens, users, creditCards);
  }

  @Test(expected = InvalidCommandException.class) 
  public void testUserDoesNotExistThrowsException() throws InvalidCommandException {
    String[] commandTokens = new String[] {"add", "Non-existant", "79927398713"};

    command.validate(commandTokens, users, creditCards);
  }

  @Test(expected = InvalidCommandException.class) 
  public void testCreditCardAlreadyAddedThrowsException() throws InvalidCommandException {
    String[] commandTokens = new String[] {"add", "Thomas", "79927398713"};
    creditCards.put("79927398713", new CreditCard("79927398713"));

    command.validate(commandTokens, users, creditCards);
  }

  @Test
  public void testExecuteAddsCardtoUserAndListOfCards() {
    String[] commandTokens = new String[] {"add", "Thomas", "79927398713"};

    command.execute(commandTokens, users, creditCards);
 
    assertTrue(users.get("Thomas").hasCreditCard());
    assertTrue(creditCards.containsKey("79927398713"));
  }
}
