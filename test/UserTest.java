import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;


public class UserTest {

  @Test
  public void testAddCreditCardSetsUserHasCreditCardFlagToTrue() {
    User user = new User("Thomas");
    user.addCreditCard(new CreditCard("79927398713"));

    assertTrue(user.hasCreditCard());
  }

  @Test
  public void testIncrementBalanceIncrementsUserBalance() {
    User user = new User("Thomas");
    user.incrementBalance(new BigDecimal("15.12"));
    
    assertEquals(new BigDecimal("15.12").longValue(), user.getBalance().longValue());
  }

  @Test
  public void testRecordTransactionAddsToTransactionHistory() {
    User user = new User("Thomas");
    user.recordTransaction(new User("Thomas"), new User("John"), new BigDecimal("5.12"), "Stuff");
    
    assertEquals(1, user.getTransactionHistory().size());
  }

  @Test
  public void testValidNameReturnsTrue() {
    boolean actual = User.isValidName("_John-Smith56");

    assertTrue(actual);
  }

  @Test
  public void testInvalidNameReturnsFalse() {
    boolean nameTooLong = User.isValidName("Jonathan_the_Great_VIII");
    boolean nameTooShort = User.isValidName("Bob");
    boolean illegalCharacters = User.isValidName("!@#$%^&*()+-");

    assertFalse(nameTooLong);
    assertFalse(nameTooShort);
    assertFalse(illegalCharacters);
  }
  
}
