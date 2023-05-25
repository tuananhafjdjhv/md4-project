package ra.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class OderDetailManagement {
    private int id;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd") private Date date;
    private int quantity;
    private String userName;
    private String productName;
    private String phoneNumber;
    private String noteOder;

  private  int status;



    public OderDetailManagement(int id, String address, Date date, int quantity, String userName, String productName, String phoneNumber, String noteOder, int status) {
        this.id = id;
        this.address = address;
        this.date = date;
        this.quantity = quantity;
        this.userName = userName;
        this.productName = productName;
        this.phoneNumber = phoneNumber;
        this.noteOder = noteOder;
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public OderDetailManagement() {

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }





    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNoteOder() {
        return noteOder;
    }

    public void setNoteOder(String noteOder) {
        this.noteOder = noteOder;
    }
}
