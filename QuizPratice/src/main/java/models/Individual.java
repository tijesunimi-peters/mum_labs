package models;

import enums.CustomerType;

public class Individual extends Customer {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phone;
    private String locale = "English";
    private Details billing;
    private Details shipping;

    public Individual(Long CRM_ID, String firstName, String lastName, String middleName, String email, String phone, String locale, Details billing, Details shipping) {
        super(CRM_ID, CustomerType.INDIVIDUAL);
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.phone = phone;
        this.locale = locale;
        this.billing = billing;
        this.shipping = shipping;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Details getBilling() {
        return billing;
    }

    public void setBilling(Details billing) {
        this.billing = billing;
    }

    public Details getShipping() {
        return shipping;
    }

    public void setShipping(Details shipping) {
        this.shipping = shipping;
    }


}
