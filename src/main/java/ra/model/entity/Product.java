package ra.model.entity;

public class Product {
    private String id;
    private String name;
    private float price;
    private String image;
    private String catalogId;
    private int quantity;

    public Product(String id, String name, float price, String image, String catalogId, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.catalogId = catalogId;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Product() {
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }
}
