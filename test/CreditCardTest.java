import static org.junit.Assert.*;

import org.junit.Test;


public class CreditCardTest {

  @Test
  public void testLuhnCreditCardNumberReturnsTrue() {
    boolean actual = CreditCard.isValidCreditCardNumber("79927398713");
    assertTrue(actual);
  }

  @Test
  public void testNotLuhnCreditCardNumberReturnsFalse() {
    boolean actual = CreditCard.isValidCreditCardNumber("79927398715");
    assertFalse(actual);
  }

  @Test
  public void testLuhnTooLongCreditCardNumberReturnsFalse() {
    boolean actual = CreditCard.isValidCreditCardNumber("322424321321323543243245416");
    assertFalse(actual);
  }

  @Test
  public void testNotLuhnTooLongCreditCardNumberReturnsFalse() {
    boolean actual = CreditCard.isValidCreditCardNumber("322424321321323543243245412");
    assertFalse(actual);
  }
}
