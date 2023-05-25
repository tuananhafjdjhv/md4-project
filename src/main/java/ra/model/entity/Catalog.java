package ra.model.entity;

import java.util.List;

public class Catalog {
    String id;
    String name;
    private List<Product> products;

    public Catalog(String id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Catalog(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Catalog() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
