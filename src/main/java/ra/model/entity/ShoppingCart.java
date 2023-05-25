package ra.model.entity;

public class ShoppingCart {
    private String image;
    private String name;
    private int quantity;
    private float total;
    private float price;

    public ShoppingCart(String image, String name, int quantity, float total,float price) {
        this.image = image;
        this.name = name;
        this.quantity = quantity;
        this.total = total;
        this.price=price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ShoppingCart() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
