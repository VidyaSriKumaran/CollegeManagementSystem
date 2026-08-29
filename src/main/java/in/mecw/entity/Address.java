package in.mecw.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class Address {

    @Id
    private int addressId;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String pincode;

    @Column
    private int studentId;

    public Address() {
    }

    public Address(int addressId, String city, String state, String pincode, int studentId) {
        this.addressId = addressId;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.studentId = studentId;
    }


    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
