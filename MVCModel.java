import java.text.DecimalFormat;
import java.util.Map;

public class MVCModel{
    public Customer loggedInUser;
    protected Map<String, Customer> customerDB;
    protected MVCView mvcView;

    /**
     * MVCModel constructor has Strict contstructor to ensure Model not instantiated with neccessary attributes
     */
    public MVCModel(Customer loggedInUser, Map<String, Customer> customerDB, MVCView mvcView) {
        this.loggedInUser = loggedInUser;
        this.customerDB = customerDB;
        this.mvcView = mvcView;

    }
    /**
     * This method will be in charge of changing LoggedInUsers
     * @param loggedInUser Next user to be making changes from
     */

    public void setLoggedInUser(Customer loggedInUser){
    this.loggedInUser = loggedInUser;
    }


    /**
     * depositOptions handels depositing a given non-negative amount into any given account depending on "choice" variable
     * also returns the logOutput for logger and logs
     * @param choice
     * @param amount
     */
    public String depositOptions(int choice, double amount){
        String logOutput = "";
        switch(choice){
        case 1://deposit into checkings account
            loggedInUser.account.get(0).deposit(amount);
            System.out.println("Checking Account Balance: $" + loggedInUser.account.get(0).getBalance());
        break;
        case 2://deposit into Savings account
            loggedInUser.account.get(1).deposit(amount);
            System.out.println("Savings Account Balance: $" + loggedInUser.account.get(1).getBalance());
        break;
        case 3://deposit into Credit account
            loggedInUser.account.get(0).deposit(amount);
            System.out.println("Credit Account Balance: $" + loggedInUser.account.get(2).getBalance());
        break;
        default:
            System.out.println("Invalid choice. Please try again.");
        break;
        }

        logOutput = buildDepositLogOutputString(choice, amount);
        return logOutput;
    }

    /**
     * Builds the String for LogOutput in depositOptions
     * @param choice input for how to build logOutput
     * @param amount amount logOutput is given
     * @return
     */
    public String buildDepositLogOutputString(int choice, double amount){
        Customer cx = loggedInUser;
        String firstLastName = cx.firstName +" "+ cx.lastName;
        String logOutput = firstLastName + " deposited $" + amount + "to ";
        if(choice == 1){
            logOutput += "Checking-" + cx.account.get(0).getAccountNumber() + ". " + firstLastName +"'s Checking balance is $" + cx.account.get(0).getBalance();
        }
        else if(choice == 2){
            logOutput +=  "Savings-" + cx.account.get(1).getAccountNumber() + ". " + firstLastName +"'s Savings balance is $" + cx.account.get(1).getBalance();
        } 
        else if(choice == 3){
            logOutput += "Credit-" + cx.account.get(2).getAccountNumber() + ". " + firstLastName+"'s Credit balance is $" + cx.account.get(2).getBalance();
        } else {
            logOutput = firstLastName + "has an input that should not work";
        }
        return logOutput;
    }
    /**
     * Payment Options handles call to verify Payment as well as displays balance and returns appropriate string for logOutput
     * @param choice
     * @param amount
     * @return
     */
    public String paymentOptions(int choice, double amount){
        String logOutput = "";
        boolean payment = false;
        switch(choice){
        case 1://deposit into checkings account
            payment = verifyPayment(loggedInUser ,0, amount);
            System.out.println("Checking Account Balance: $" + loggedInUser.account.get(0).getBalance());
            System.out.println("Credit Account Balance: $" + loggedInUser.account.get(2).getBalance());
        break;
        case 2://deposit into Savings account
            payment = verifyPayment(loggedInUser, 1, amount);
            System.out.println("Savings Account Balance: $" + loggedInUser.account.get(1).getBalance());
            System.out.println("Credit Account Balance: $" + loggedInUser.account.get(2).getBalance());
        break;
        default:
            System.out.println("Invalid choice. Please try again.");
        break;
        }

        if(payment){
            logOutput = buildPaymentLogOutputString(choice,amount);
            return logOutput;
        }
        return "There was not a proper input amount";
    }  

    /**
     * Builds the String for LogOutput in depositOptions
     * @param choice input for how to build logOutput
     * @param amount amount logOutput is given
     * @return
     */
    public String buildPaymentLogOutputString(int choice, double amount){
        Customer cx = loggedInUser;
        String firstLastName = cx.firstName +" "+ cx.lastName;
        String logOutput = firstLastName + " Made payment from";
        if(choice == 1){
            logOutput += "Checking-" + cx.account.get(0).getAccountNumber() + ". " + firstLastName +"'s Credit balance is $" + cx.account.get(2).getBalance();
        }
        else if(choice == 2){
            logOutput +=  "Savings-" + cx.account.get(1).getAccountNumber() + ". " + firstLastName +"'s Credit balance is $" + cx.account.get(2).getBalance();
        }  else {
            logOutput = firstLastName + "has an input that was not valid";
        }
        return logOutput;
    }

    /**
     * Verifies if the user has enough money in their account to make a payment.
     * 
     * @param cx
     * @param checkSaveAcc
     * @param amt
     */
    public boolean verifyPayment(Customer cx, int checkSaveAcc, double amt){
        // Check if the user has enough money in their account to make the payment
        double accTotal = cx.account.get(checkSaveAcc).getBalance();
        if(amt > accTotal){
            System.out.println("The amount youve input was too much, we will not process this transaction");
            return false;
        }
        else if(cx.account.size() > 2 && (cx.account.get(2).getBalance() + amt) > 0){
            System.out.println("Sorry thats too much money, we'll make the change for you");
            cx.account.get(checkSaveAcc).withdraw(cx.account.get(3).getBalance());
            cx.account.get(3).deposit(cx.account.get(3).getBalance());
            return true;
        } else {
            System.out.println("Approving account payment");
            System.out.println("Good Job!");

            cx.account.get(checkSaveAcc).withdraw(amt);
            cx.account.get(2).deposit(amt);
            return true;
        }
    } 
    /**
     * Handles withdraws from Accounts
     * @param choice Choice for where the account will be
     * @param amount amount being withdrawn
     * @return
     */
    public String withdrawFromAccounts(int choice, double amount){
        Customer cx = loggedInUser;
        String logOutput = "";
        // Format the balance to two decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        switch (choice) {
            case 1: // Checking Account
                cx.account.get(0).withdraw(amount);
                System.out.println("Checking Account Balance: $" + cx.account.get(0).getBalance());
                logOutput = buildWithdrawLogOutputString(choice, amount);
            case 2: // Savings Account
                cx.account.get(1).withdraw(amount);
                System.out.println("Savings Account Balance: $" + df.format(cx.account.get(1).getBalance()));
                logOutput = buildWithdrawLogOutputString(choice, amount);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
        return logOutput;
    }

    /**
     * Builds the String for LogOutput in depositOptions
     * @param choice input for how to build logOutput
     * @param amount amount logOutput is given
     * @return
     */
    public String buildWithdrawLogOutputString(int choice, double amount){
        Customer cx = loggedInUser;
        String firstLastName = cx.firstName +" "+ cx.lastName;
        String logOutput = firstLastName;
        // Format the balance to two decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        if(choice == 1){
            logOutput += " withdrew money from Checking-" + cx.account.get(0).getAccountNumber() + ". " + firstLastName +"'s balance is $" + cx.account.get(0).getBalance();
        }
        else if(choice == 2){
            logOutput +=  " withdrew money from Savings-" + cx.account.get(1).getAccountNumber() + ". " + firstLastName +"'s balance is $" + df.format(cx.account.get(1).getBalance());
        }  else {
            logOutput = firstLastName + "has an input that was not valid";
        }
        return logOutput;
    }

}

    