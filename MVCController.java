import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;

public class MVCController{
    public MVCView mvcView = new MVCView();
    public MVCModel mvcModel = new MVCModel(null, null, mvcView);
    
    /**
     * Constructor for MVCController Strict contstructor to ensure Controller not instantiated with neccessary attributes
     * @param mvcView mvcView holds display options and other portions of code
     * @param mvcModel mvcModel has current logged in user and CustomerDB which it will interact with
     */
    MVCController(MVCView mvcView, MVCModel mvcModel){
        this.mvcView = mvcView;
        this.mvcModel = mvcModel;
    }

    /**
     * Displays the deposit menu and allows the user to make a deposit to their account.
     * returns LogOutput
     * @param loggedInUser
     */
    public String DepositMenu(Customer loggedInUser) {
        System.out.println("Make a Deposit");
        Scanner sc = new Scanner(System.in);
        String logOutput = "";
        boolean validChoice = false;
    
        while(!validChoice) {
            mvcView.printDepositMenuOptions();
            String input = sc.next();
            try {
                int choice = Integer.parseInt(input);
                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid choice. Please try again.");
                    continue;
                }
                System.out.print("Enter the amount you would like to deposit: $");
                double amount = sc.nextDouble();
                logOutput = mvcModel.depositOptions(choice, amount);          
                validChoice = true;
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid choice. Please try again.\n");
            }
        }
        return logOutput;
    }

    /**
     * Displays the payment menu and allows the user to make a payment from their account.
     * returns LogOutput
     * @param cx
     */
    public String PaymentMenu(Customer cx) {
        System.out.println("Make a Payment");
        Scanner sc = new Scanner(System.in);
        String logOutput = "";
        try {
            mvcView.printPaymentMenuOptions();
            String input = sc.next();
            try {
                int choice = Integer.parseInt(input);
                System.out.print("Enter the amount you would like to pay: $");
                double amount = sc.nextDouble();
                switch (choice) {
                    case 1: // Checking Account
                        mvcModel.verifyPayment(cx, 0, amount);
                        System.out.println("Checking Account Balance: $" + cx.account.get(0).getBalance());
                        System.out.println("Credit Account Balance: $" + cx.account.get(2).getBalance());
                        break;
                    case 2: // Savings Account
                        mvcModel.verifyPayment(cx, 1, amount);
                        System.out.println("Savings Account Balance: $" + cx.account.get(1).getBalance());
                        System.out.println("Credit Account Balance: $" + cx.account.get(2).getBalance());
                        logOutput = (cx.account.get(1).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s Credit balance is $" + cx.account.get(2).getBalance());
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
                logOutput = mvcModel.paymentOptions(choice, amount);
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid choice. Please try again.\n");
            }
        }catch (NumberFormatException e) {
            System.out.println("\nInvalid choice. Please try again.\n");
    }
    return logOutput;
}
    
    /**
     * Displays the withdrawal menu and allows the user to make a withdrawal from their account.
     * 
     */
    public String WithdrawMenu() {
        
        System.out.println("Make a Withdrawal");
        Scanner sc = new Scanner(System.in);
        String logOutput = "";
        
            try {
                mvcView.printWithdrawMenuOptions();
                String input = sc.next();
                try {
                    int choice = Integer.parseInt(input);
                    System.out.print("Enter the amount you would like to withdraw: $");
                    double amount = sc.nextDouble();
                    logOutput = mvcModel.withdrawFromAccounts(choice, amount);
                } catch (NumberFormatException e) {
                    System.out.println("\nInvalid choice. Please try again.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid choice. Please try again.\n");
            }
            return logOutput;
        }
}
