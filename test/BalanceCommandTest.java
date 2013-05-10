import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;


public class BalanceCommandTest {
  private static Command command;
  private static HashMap<String, User> users;
  private static HashMap<String, CreditCard> creditCards;

  @BeforeClass
  public static void testSetup() {
    command = new BalanceCommand();
    users = new HashMap<String, User>();
    users.put("Thomas", new User("Thomas"));
    creditCards = new HashMap<String, CreditCard>();
  }

  @Test(expected = InvalidCommandException.class) 
  public void testCommandLengthTooShortThrowsException() throws InvalidCommandException {
    String[] commandTooShortTokens = new String[] {"balance"};

    command.validate(commandTooShortTokens, users, creditCards);
  }

  @Test(expected = InvalidCommandException.class) 
  public void testCommandLengthTooLongThrowsException() throws InvalidCommandException {
    String[] commandTooLongTokens = new String[] {"balance","Thomas", "foo"};

    command.validate(commandTooLongTokens, users, creditCards);
  }

  @Test
  public void testExecuteBalanceReturnsBalanceString() {
    String[] commandTokens = new String[] {"balance","Thomas"};

    ArrayList<String> actual = command.execute(commandTokens, users, creditCards);

    assertEquals("$0.00", actual.get(0));
  }
}
