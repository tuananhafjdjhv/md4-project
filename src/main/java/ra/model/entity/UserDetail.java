package ra.model.entity;

public class UserDetail {
    private int userDetailId;
    private String fullName;
    private String address;
    private String email;
    private String phoneNumber;
    private String noteOder;

    public UserDetail(int userDetailId, String fullName, String address, String email, String phoneNumber, String noteOder) {
        this.userDetailId = userDetailId;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.noteOder = noteOder;
    }

    public UserDetail(String fullName, String address, String email, String phoneNumber, String noteOder) {
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.noteOder = noteOder;
    }

    public UserDetail() {

    }

    public int getUserDetailId() {
        return userDetailId;
    }

    public void setUserDetailId(int userDetailId) {
        this.userDetailId = userDetailId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
