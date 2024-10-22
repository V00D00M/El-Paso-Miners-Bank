public class Customer extends Person {
    private String customerID;

    public Customer(String name,String customerID) {
        super(name);
        this.accounts = new ArrayList<>();
    }

    public void getName() {
        return name;
    }

    public void getAddress() {
        return address;
    }
}
