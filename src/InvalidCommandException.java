/**
 * An exception that should be thrown when a command of an invalid form is received.
 * 
 * @author anthonyyim@gmail.com (Anthony Yim)
 */
public class InvalidCommandException extends Exception {
  public InvalidCommandException(String message) {
    super(message);
  }
}
