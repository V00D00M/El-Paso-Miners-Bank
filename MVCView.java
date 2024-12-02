public class MVCView{
    protected MVCView mvcView;

    /**
     * Not strict constructor as MVCView does not have attributes which it depends upon
     */
    MVCView(){}

    /**
     * Handles printing Deposit menu Options
     */
    public void printDepositMenuOptions(){
        System.out.println("Which account would you like to make a deposit to?");
        System.out.println("1. Checking Account");
        System.out.println("2. Savings Account");
        System.out.println("3. Credit Account");
        System.out.print("Enter your choice: ");
    }
    /**
     * Handles printing Payment menu Options
     */
    public void printPaymentMenuOptions(){
        System.out.println("Which account would you like to make a payment from?");
        System.out.println("1. Checking Account");
        System.out.println("2. Savings Account");
        System.out.print("Enter your choice: ");
    }
    /**
     * Handles Printing Withdraw Menu Options
     */
    public void printWithdrawMenuOptions(){
        System.out.println("Which account would you like to make a withdrawal from?");
        System.out.println("1. Checking Account");
        System.out.println("2. Savings Account");
        System.out.print("Enter your choice: ");
    }
    
}