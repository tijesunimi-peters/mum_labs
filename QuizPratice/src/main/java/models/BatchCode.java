package models;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.UUID;

public class BatchCode {
    private String id;
    private Customer customer;
    private TreeSet<Feature> features;
    private TreeSet<Product> products;

    BatchCode(Customer customer) throws Exception {
        setId(UUID.fromString(
                customer.getCRM_ID().toString()
                ).toString().substring(0, 5)
        );
        this.customer = customer;
        this.features = new TreeSet<>();
        this.products = new TreeSet<>();
    }

    private void setId(String id) throws Exception {
        if (id.length() != 5) throw new Exception("Batch code is not valid");
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TreeSet<Feature> getFeatures() {
        return features;
    }

    public void addFeature(Feature feature) {
        this.features.add(feature);
    }

    public TreeSet<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }
}
