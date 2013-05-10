import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;


public class FeedCommandTest {
  private static Command command;
  private static HashMap<String, User> users;
  private static HashMap<String, CreditCard> creditCards;

  @BeforeClass
  public static void testSetup() {
    command = new FeedCommand();
    users = new HashMap<String, User>();
    users.put("Thomas", new User("Thomas"));
    creditCards = new HashMap<String, CreditCard>();
  }

  @Test(expected = InvalidCommandException.class) 
  public void testCommandLengthTooShortThrowsException() throws InvalidCommandException {
    String[] commandTooShortTokens = new String[] {"feed"};

    command.validate(commandTooShortTokens, users, creditCards);
  }

  @Test(expected = InvalidCommandException.class) 
  public void testCommandLengthTooLongThrowsException() throws InvalidCommandException {
    String[] commandTooLongTokens = new String[] {"feed","Thomas", "foo"};

    command.validate(commandTooLongTokens, users, creditCards);
  }

  @Test(expected = InvalidCommandException.class) 
  public void testUserDoesNotExistThrowsException() throws InvalidCommandException {
    String[] commandTokens = new String[] {"feed", "Non-existant"};

    command.validate(commandTokens, users, creditCards);
  }

  @Test
  public void testExecuteFeedReturnsTransactionHistoryOldestFirst() {
    User thomas = users.get("Thomas");
    User peter = new User("Peter");
    
    thomas.recordTransaction(thomas, peter, new BigDecimal("9.50"), "Sushi at Sushi Sam");
    thomas.recordTransaction(peter, thomas, new BigDecimal("120.45"), "skiing lift tickets");
    thomas.recordTransaction(peter, thomas, new BigDecimal("75.20"), "iPod");

    String[] commandTokens = new String[] {"feed", "Thomas"};

    ArrayList<String> actual = command.execute(commandTokens, users, creditCards);

    assertEquals("You paid Peter $9.50 for Sushi at Sushi Sam", actual.get(0));
    assertEquals("Peter paid you $120.45 for skiing lift tickets", actual.get(1));
    assertEquals("Peter paid you $75.20 for iPod", actual.get(2));
  }
}
