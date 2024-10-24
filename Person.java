/**
 * The Person class represents a person with basic attributes such as first name, last name, address, date of birth, and phone number.
 */
public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected String address;
    protected String DOB;
    protected String phoneNumber;
    

    /**
     * Constructs a new Person with the specified details.
     *
     * @param firstName the first name of the person
     * @param lastName the last name of the person
     * @param address the address of the person
     * @param DOB the date of birth of the person
     * @param phoneNumber the phone number of the person
     */
    public Person(String firstName, String lastName, String DOB, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.DOB = DOB;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the address of the person.
     *
     * @return the address of the person
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the date of birth of the person.
     *
     * @return the date of birth of the person
     */
    public String getDOB() {
        return DOB;
    }

    /**
     * Gets the first name of the person.
     *
     * @return the first name of the person
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the person.
     *
     * @return the last name of the person
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the phone number of the person.
     *
     * @return the phone number of the person
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the address of the person.
     *
     * @param address the address of the person
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the date of birth of the person.
     *
     * @param DOB the date of birth of the person
     */
    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    /**
     * Sets the first name of the person.
     *
     * @param firstName the first name of the person
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the person.
     *
     * @param lastName the last name of the person
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the phone number of the person.
     *
     * @param phoneNumber the phone number of the person
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
