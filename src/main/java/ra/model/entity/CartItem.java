package ra.model.entity;

public class CartItem {
  private int id ;
  private int quantity ;
  private String productName;
    private String productId;
  private int userId;
  private String image;
  private float price;
  private float  totalCartItemPrice;

    public float getTotalCartItemPrice() {
        return totalCartItemPrice;
    }

    public void setTotalCartItemPrice(float totalCartItemPrice) {
        this.totalCartItemPrice = totalCartItemPrice;
    }
  public CartItem() {
    }

    public CartItem(int quantity, String productId, String image, String productName, int userId, float price) {
        this.quantity = quantity;
        this.productId = productId;
        this.image = image;
        this.productName = productName;
        this.userId = userId;
        this.price = price;
    }

    public CartItem(int id, int quantity, String productName, String productId, int userId, String image, float price, float totalCartItemPrice) {
        this.id = id;
        this.quantity = quantity;
        this.productName = productName;
        this.productId = productId;
        this.userId = userId;
        this.image = image;
        this.price = price;
        this.totalCartItemPrice = totalCartItemPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
