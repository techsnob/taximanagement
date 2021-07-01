package com.techsnob.entitiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

@Entity
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name = "address_seq", sequenceName = "address_seq", allocationSize = 1, initialValue = 1000)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "house_name", nullable = false, length = 30)
    private String houseName;

    @Column(name = "street_name", length = 30)
    private String streetName;

    @Column(name = "landmark", length = 30)
    private String landmark;

    @Column(name = "area", nullable = false, length = 30)
    private String area;

    @Column(name = "pincode", nullable = false, length = 6)
    private int pincode;

    @Column(name = "village", nullable = false, length = 30)
    private String village;

    @Column(name = "district", nullable = false, length = 30)
    private String district;

    @Column(name = "state", nullable = false, length = 30)
    private String state;

    @Column(name = "country", nullable = false, length = 30)
    private String country;

    public Long getAddressId() {
        return addressId;
    }

    public void setDriverId(Long addressId) {
        this.addressId = addressId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
