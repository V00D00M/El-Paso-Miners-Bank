public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected String address;
    protected String DOB;
    protected String phoneNumber;

    public Person(){}

    // creating a list of accounts
    public Person(String firstName, String lastName, String address, String DOB, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.DOB = DOB;
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}
