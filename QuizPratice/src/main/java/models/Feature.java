package models;

public class Feature {
    private BatchCode batchCode;
    private Integer id;
    private String name;
    private String description;

    public Feature(BatchCode batchCode, Integer id) {
        this.batchCode = batchCode;
        this.id = id;
    }

    private void setName(String name) throws Exception {
        if(name.length() >= 50) throw new Exception("Feature name should be < 50 chars");

        this.name = name;
    }

    public BatchCode getBatchCode() {
        return batchCode;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
