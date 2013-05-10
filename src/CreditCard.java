/**
 * Represents a credit card.
 * 
 * @author anthonyyim@gmail.com (Anthony Yim)
 */
public class CreditCard {
  String creditCardNumber;

  public CreditCard(String creditCardNumber) {
    this.creditCardNumber = creditCardNumber;
  }
  
  public static boolean isValidCreditCardNumber(String creditCardNumber) {
    if (isLuhn10(creditCardNumber) && creditCardNumber.length() <= 19) {
      return true;
    }
    return false;
  }

  /**
   * Validates whether a given number is Luhn-10 compliant.
   * Example of a number that passes Luhn10: 79927398713
   * 
   * @param input The number to be validated for luhn-10 compliance.
   * @return true if number is Luhn-10, false if not.
   */
  private static boolean isLuhn10(String input) {
    String reversedInput = new StringBuffer(input).reverse().toString();
    int sum = 0;

    for(int i = 0; i < reversedInput.length(); i++) {
      int digit = Integer.parseInt(reversedInput.substring(i, i + 1));

      if(i%2 == 0) {
        sum += digit;
      } else {
        sum += sumDigits(Integer.toString(digit * 2));
      }
    }

    if (sum%10 == 0) {
      return true;
    }
    return false;
  }
  
  private static int sumDigits(String digits) {
    int sum = 0;

    for(int i = 0; i < digits.length(); i++) {
      sum += Integer.parseInt(digits.substring(i, i + 1));
    }

    return sum;
  }
}
