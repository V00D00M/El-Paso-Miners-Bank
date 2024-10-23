import java.util.List;

public abstract class Person {
    private String name;
    private String address;
    private static CSVReader csvReader;
    private List<Account> accounts;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
        Person.csvReader = csvReader;
    }

    public Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public String[] findUserByName(String firstName, String lastName) {
        String key = firstName + " " + lastName;
        return userMapByName.get(key);
    }

    public String[] findUserByID(String id) {
        //return userMapByID.get(id);
        return null;
    }

    public static boolean login(String name) {
        String[] user = CSVReader.findUserByName(name);
    }

    public String getAddress() {
        return address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
