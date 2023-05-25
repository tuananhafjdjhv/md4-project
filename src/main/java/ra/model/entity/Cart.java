package ra.model.entity;

import java.util.Date;
import java.util.List;

public class Cart {
    private int id;
    private User user;
    private List<Product> products;
    private Date createdDate;
    private Date updatedDate;

    public Cart(int id, User user, List<Product> products, Date createdDate, Date updatedDate) {
        this.id = id;
        this.user = user;
        this.products = products;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
