import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;


public class PayCommandTest {
  private static Command command;
  private static HashMap<String, User> users;
  private static HashMap<String, CreditCard> creditCards;

  @BeforeClass
  public static void testSetup() {
    command = new PayCommand();
    users = new HashMap<String, User>();
    creditCards = new HashMap<String, CreditCard>();
  }

  @Test(expected = InvalidCommandException.class) 
  public void testCommandTooShortThrowsException() throws InvalidCommandException {
    String[] commandTooShortTokens = new String[] {"pay","Thomas"};

    command.validate(commandTooShortTokens, users, creditCards);
  }

  @Test(expected = InvalidCommandException.class) 
  public void testActorDoesNotExistThrowsException() throws InvalidCommandException {
    users.put("Peter", new User("Peter"));
    String[] commandTokens = new String[] {"pay", "Non-existant", "Peter", "$5.00", "Stuff"};

    command.validate(commandTokens, users, creditCards);
  }

  @Test(expected = InvalidCommandException.class) 
  public void testTargetDoesNotExistThrowsException() throws InvalidCommandException {
    users.put("Thomas", new User("Thomas"));
    String[] commandTokens = new String[] {"pay", "Thomas", "Non-Existant", "$5.00", "Stuff"};

    command.validate(commandTokens, users, creditCards);
  }

  @Test(expected = InvalidCommandException.class) 
  public void testTargetDoesNotHaveCreditCardThrowsException() throws InvalidCommandException {
    users.put("Thomas", new User("Thomas"));
    users.put("Peter", new User("Peter"));
    String[] commandTokens = new String[] {"pay", "Thomas", "Peter", "$5.00", "Stuff"};

    command.validate(commandTokens, users, creditCards);
  }

  @Test(expected = InvalidCommandException.class) 
  public void testInvalidAmountRepresentationThrowsException() throws InvalidCommandException {
    User thomas = new User("Thomas");
    CreditCard creditCard = new CreditCard("79927398713");

    users.put("Thomas", thomas);
    users.put("Peter", new User("Peter"));

    thomas.addCreditCard(creditCard);
    creditCards.put("79927398713", creditCard);
    
    String[] commandTokens = new String[] {"pay", "Thomas", "Peter", "500", "Stuff"};

    command.validate(commandTokens, users, creditCards);
  }

  @Test
  public void testExecutePaymentExchangesAmountAndRecordsTransaction() {
    User thomas = new User("Thomas");
    User peter = new User("Peter");
    CreditCard creditCard = new CreditCard("79927398713");

    users.put("Thomas", thomas);
    users.put("Peter", peter);

    thomas.addCreditCard(creditCard);
    creditCards.put("79927398713", creditCard);

    String[] commandTokens = new String[] {"pay", "Thomas", "Peter", "$5.00", "Stuff"};

    command.execute(commandTokens, users, creditCards);

    //Note: Include assertion to ensure credit card was charged in a "real" application.
    assertEquals(1, thomas.getTransactionHistory().size());
    assertEquals(1, peter.getTransactionHistory().size());
    assertEquals(5L, peter.getBalance().longValue());
  }
}
