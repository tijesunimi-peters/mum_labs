package models;

import enums.CustomerType;

import java.util.ArrayList;
import java.util.List;

public abstract class Customer {
    private Long CRM_ID;
    private CustomerType type;
    private String description;
    private List<Entitlement> entitlements;
    private BatchCode batchCode;

    public Customer(Long CRM_ID, CustomerType type) {
        this.CRM_ID = CRM_ID;
        this.type = type;
        this.entitlements = new ArrayList<>();
        try {
            this.batchCode = new BatchCode(this);
        } catch (Exception ex) {
//            throw
        }
    }

    public BatchCode getBatchCode() {
        return batchCode;
    }

    public Long getCRM_ID() {
        return CRM_ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Entitlement> getEntitlements() {
        return entitlements;
    }

    public void addEntitlement(Entitlement entitlement) {
        this.entitlements.add(entitlement);
    }

}
