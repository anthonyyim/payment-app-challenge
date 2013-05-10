import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages commands from the user and takes the appropriate action for
 * each of the different commands.
 * 
 * @author anthonyyim@gmail.com (Anthony Yim)
 */
public class CommandManager {
  private HashMap<String, User> users = new HashMap<String, User>();
  private HashMap<String, CreditCard> creditCards = new HashMap<String, CreditCard>();
  private Command[] commands;

  public static void main(String[] args) {
    CommandManager commandManager = new CommandManager();
    commandManager.run();
  }

  public CommandManager() {
    commands = new Command[] {new UserCommand(),
                              new AddCreditCardCommand(),
                              new PayCommand(),
                              new FeedCommand(),
                              new BalanceCommand()};
  }

  /**
   * Welcomes user and continuously listens to user input. Validates and executes command
   * if the command is supported.
   */
  public void run() {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Welcome to this mini payment app! Please type your commands and hit Enter.");

    while(true) {
      System.out.print("> ");
      try {
        String input = br.readLine();
        String[] tokens = input.split(" ");
        
        if (tokens.length > 0) {
          // Check to see which command object should be handling the command the user inputed.
          for (Command c : commands) {
            if (c.isHandler(tokens[0])) {
              try {
                // Validate user input.
                c.validate(tokens, users, creditCards);
                
                // Execute user command.
                ArrayList<String> output = c.execute(tokens, users, creditCards);

                // Output results of execute to console (if any).
                if (!output.isEmpty()) {
                  for (String s : output){
                    System.out.println(s);
                  }
                }
                break;
              } catch (InvalidCommandException e) {
                System.out.println("ERROR: " + e.getMessage());
              }
            }
          }
        }
      } catch (IOException ioe) {
        System.out.println("IO error trying to read input.");
        System.exit(1);
      }
    }
  }
}
