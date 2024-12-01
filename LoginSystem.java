import java.util.Scanner;

public class LoginSystem {
    private BankUser loggedInUser;

    public boolean authenticate(BankUser user) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String inputPassword = scanner.nextLine();

        if (user.getPassword().equals(inputPassword)) {
            loggedInUser = user;
            return true;
        } else {
            System.out.println("Incorrect password. Please try again.");
            return false;
        }
    }

    public BankUser getLoggedInUser() {
        return loggedInUser;
    }
}