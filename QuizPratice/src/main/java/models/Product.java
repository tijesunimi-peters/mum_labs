package models;

import enums.LockingType;
import enums.Rehost;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private BatchCode batchCode;
    private List<Feature> features;
    private Integer id;
    private String name;
    private Rehost rehost;
    private LockingType lockingType;
    private String description;
    private Product provisionalProduct;
    private List<Entitlement> entitlements;

    public Product(BatchCode batchCode, List<Feature> features, Integer id, String name, Rehost rehost, LockingType lockingType) throws Exception {
        this.batchCode = batchCode;
        this.features = features;
        this.id = id;
        this.rehost = rehost;
        this.lockingType = lockingType;
        setName(name);
        this.entitlements = new ArrayList<>();
    }

    public BatchCode getBatchCode() {
        return batchCode;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void addFeatures(Feature feature) {
        this.features.add(feature);;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) throws Exception {
        if(name.length() >= 50) throw new Exception("Product name should be less than 50 chars");
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!batchCode.equals(product.batchCode)) return false;
        if (!features.equals(product.features)) return false;
        if (!id.equals(product.id)) return false;
        if (!name.equals(product.name)) return false;
        if (rehost != product.rehost) return false;
        if (lockingType != product.lockingType) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (!provisionalProduct.equals(product.provisionalProduct)) return false;
        return entitlements.equals(product.entitlements);
    }

    @Override
    public int hashCode() {
        int result = batchCode.hashCode();
        result = 31 * result + features.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + rehost.hashCode();
        result = 31 * result + lockingType.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + provisionalProduct.hashCode();
        result = 31 * result + entitlements.hashCode();
        return result;
    }
}
