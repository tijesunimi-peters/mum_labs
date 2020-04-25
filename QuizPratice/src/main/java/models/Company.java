package models;

import enums.CustomerType;

public class Company extends Customer {
    private Details billing;
    private Details shipping;
    private String name;
    private String phone;
    private String fax;
    private Contact contact;

    public Company(Long CRM_ID, Details billing, Details shipping, String name, Contact contact) {
        super(CRM_ID, CustomerType.COMPANY);
        this.billing = billing;
        this.shipping = shipping;
        this.name = name;
        this.contact = contact;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
