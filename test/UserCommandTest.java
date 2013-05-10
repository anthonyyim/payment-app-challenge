import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;


public class UserCommandTest {
  private static Command command;
  private static HashMap<String, User> users;
  private static HashMap<String, CreditCard> creditCards;

  @BeforeClass
  public static void testSetup() {
    command = new UserCommand();
    users = new HashMap<String, User>();
    users.put("Thomas", new User("Thomas"));
    creditCards = new HashMap<String, CreditCard>();
  }

  @Test(expected = InvalidCommandException.class) 
  public void testCommandLengthTooShortThrowsException() throws InvalidCommandException {
    String[] commandTooShortTokens = new String[] {"user"};

    command.validate(commandTooShortTokens, users, creditCards);
  }

  @Test(expected = InvalidCommandException.class) 
  public void testCommandLengthTooLongThrowsException() throws InvalidCommandException {
    String[] commandTooLongTokens = new String[] {"user","Thomas", "foo"};

    command.validate(commandTooLongTokens, users, creditCards);
  }

  @Test(expected = InvalidCommandException.class) 
  public void testInvalidNameThrowsException() throws InvalidCommandException {
    String[] commandTokens = new String[] {"user", "!@#$%^&*()"};

    command.validate(commandTokens, users, creditCards);
  }

  @Test(expected = InvalidCommandException.class) 
  public void testUserAlreadyExistsThrowsException() throws InvalidCommandException {
    String[] commandTokens = new String[] {"user", "Thomas"};
    users.put("Thomas", new User("Thomas"));

    command.validate(commandTokens, users, creditCards);
  }

  @Test
  public void testExecuteAddsUsertoListOfUsers() {
    String[] commandTokens = new String[] {"user", "Thomas"};

    command.execute(commandTokens, users, creditCards);
 
    assertTrue(users.containsKey("Thomas"));
  }
}
