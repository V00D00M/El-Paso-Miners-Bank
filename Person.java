import java.util.List;
import java.util.ArrayList;

public class Person {
    private String name;
    private String address;
    private List<Account> accounts;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
        this.accounts = new ArrayList<>();
    }

    public Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

}
