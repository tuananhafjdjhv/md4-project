package ra.model.entity;

import java.security.SecureRandom;

public class OderManagement {
    private int id;
    private String userName;
    private float totalAmount;

    public OderManagement(int id, String userName, float totalAmount) {
        this.id = id;
        this.userName = userName;
        this.totalAmount = totalAmount;
    }

    public OderManagement() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
}
