import java.util.List;

public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected String address;
    protected String DOB;
    protected String phoneNumber;
    protected String email;

    // creating a list of accounts
    public Person(String firstName, String lastName, String address, String DOB, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.DOB = DOB;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Person person = new Person() {
    //     loggedInUser[1], // firstName
    //     loggedInUser[2], // lastName
    //     loggedInUser[3], // address
    //     loggedInUser[4], // DOB
    //     loggedInUser[5], // phoneNumber
    //     loggedInUser[6]  // email
    // };

    // public Account getAccount(String accountNumber) {
    //     for (Account account : accounts) {
    //         if (account.getAccountNumber().equals(accountNumber)) {
    //             return account;
    //         }
    //     }
    //     return null;
    // }

    // public String[] findUserByName(String firstName, String lastName) {
    //     String key = firstName + " " + lastName;
    //     return userMapByName.get(key);
    // }

    // public String[] findUserByID(String id) {
    //     //return userMapByID.get(id);
    //     return null;
    // }

    // public static boolean login(String name) {
    //     String[] user = CSVReader.getName(name);
    // }

    public String getAddress() {
        return address;
    }

    // public List<Account> getAccounts() {
    //     return accounts;
    // }
}
