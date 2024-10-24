public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected String address;
    protected String DOB;
    protected String phoneNumber;
    protected String email;

    public Person(){}

    // creating a list of accounts
    public Person(String firstName, String lastName, String address, String DOB, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.DOB = DOB;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getAddress() {
        return address;
    }
}
