import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class TransactionReader {
    
    private Scanner transactionReader;

    public TransactionReader() {}

    public void processTransactions(String fileName, Map<String, Customer> customers) throws IOException {
        this.transactionReader = new Scanner(new File(fileName));
        transactionReader.nextLine(); // Skip the header

        while (transactionReader.hasNextLine()) {
            String currLine = transactionReader.nextLine();
            String[] tokens = currLine.split(",");

            // Extract the transaction details
            String fromFirstName = tokens[0];
            String fromLastName = tokens[1];
            String fromWhere = tokens[2];
            String action = tokens[3];
            String toFirstName = tokens[4];
            String toLastName = tokens[5];
            String toWhere = tokens[6];
            double amount = Double.parseDouble(tokens[7]);

            // Finds the action and calls the appropriate method
            switch (action.toLowerCase()) {
                case "pays":
                    handlePays(fromFirstName, fromLastName, fromWhere, toFirstName, toLastName, toWhere, amount, customers);
                    break;
                case "transfers":
                    handleTransfers(fromFirstName, fromLastName, fromWhere, toFirstName, toLastName, toWhere, amount, customers);
                    break;
                case "inquires":
                    handleInquires(fromFirstName, fromLastName, fromWhere, customers);
                    break;
                case "withdraws":
                    handleWithdraws(fromFirstName, fromLastName, fromWhere, amount, customers);
                    break;
                case "deposits":
                    handleDeposits(toFirstName, toLastName, toWhere, amount, customers);
                    break;
                default:
                    System.out.println("Invalid action: " + action);
                    break;
            }
        }
    }

    private void handlePays(String fromFirstName, String fromLastName, String fromWhere, String toFirstName, String toLastName, String toWhere, double amount, Map<String, Customer> customerDB) {
        // Implement the logic to handle "pays" action
        // Find the customers and perform the transaction
    }

    private void handleTransfers(String fromFirstName, String fromLastName, String fromWhere, String toFirstName, String toLastName, String toWhere, double amount, Map<String, Customer> customerDB) {
        // Implement the logic to handle "transfers" action
        // Find the customer and perform the transfer
    }

    private void handleInquires(String fromFirstName, String fromLastName, String fromWhere, Map<String, Customer> customerDB) {
        // Implement the logic to handle "inquires" action
        // Find the customer and print the account balance
    }

    private void handleWithdraws(String fromFirstName, String fromLastName, String fromWhere, double amount, Map<String, Customer> customerDB) {
        // Implement the logic to handle "withdraws" action
        // Find the customer and perform the withdrawal
    }

    private void handleDeposits(String toFirstName, String toLastName, String toWhere, double amount, Map<String, Customer> customerDB) {
        // Implement the logic to handle "deposits" action
        // Find the customer and perform the deposit
    }
}

