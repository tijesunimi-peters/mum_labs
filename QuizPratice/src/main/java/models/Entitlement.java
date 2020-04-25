package models;

import enums.EntitlementType;

import java.util.Date;
import java.util.TreeSet;

public class Entitlement {
    private Customer customer;
    private String EID;
    private EntitlementType type;
    private Date startDate;
    private Date endDate;
    private Boolean neverExpires = true;
    private String comment;
    private TreeSet<Product> products;

    public Entitlement(Customer customer, String EID, EntitlementType type, Date startDate) {
        this.customer = customer;
        this.EID = EID;
        this.type = type;
        this.startDate = startDate;
        this.products = new TreeSet<>();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getEID() {
        return EID;
    }

    public EntitlementType getType() {
        return type;
    }

    public Date getStartDate() {
        return startDate;
    }


    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getNeverExpires() {
        return neverExpires;
    }

    public void setNeverExpires(Boolean neverExpires) {
        this.neverExpires = neverExpires;
    }

    public String getComments() {
        return comment;
    }

    public void setComments(String comment) {
        this.comment = comment;
    }
}
