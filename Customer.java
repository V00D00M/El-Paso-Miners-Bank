import java.util.List;
import java.util.ArrayList;


public class Customer {
    protected String customerID;
    protected List<Account> account;

    public Customer(String customerID) {
        this.customerID = customerID;
        ArrayList<Account> account = new ArrayList<Account>();
    }

    public void addAccount(Account newAccount){
        this.account.add(newAccount);
        return;
    }
    public void removeAccount(Account accRemove){
        for(int i = 0; i < this.account.size(); i++){
            if(accRemove == this.account.get(i)){
                this.account.remove(i);
            }
        }
    }
    public Account getAccount(int accountNumber){
        Account temp;
        if (this.account.size() == 0){
            throw new IllegalArgumentException("We do not recognize this account number, please try again");
        }
        for(int i = 0; i < this.account.size(); i++){
            if(account.get(i).getAccountNumber() == accountNumber){
                temp = this.account.get(i);
            }
        }
        return temp;
    }
}
