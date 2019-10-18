package com.techsnob.entitiy;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyGroup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "drivers")
@JsonIgnoreProperties({"license","aadhaar"})
public class Driver implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver_seq")
    @SequenceGenerator(name = "driver_seq", sequenceName = "driver_seq", allocationSize = 1, initialValue = 1000)
    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "phone_number", unique = true, nullable = false, length = 10)
    private Long phoneNumber;

    @Column(name = "license")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] license;
    
    @Column(name = "license_contenttype", nullable = false, length = 30)
    private String license_contenttype;
    
    @Column(name = "aadhaar")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @LazyGroup("aadhaar")
    private byte[] aadhaar;
    
    @Column(name = "aadhaar_contenttype", nullable = false, length = 30)
    private String aadhaar_contenttype;
    
    public Driver() {
    	
    }
    
    public Driver(String firstName, String lastName, Long phoneNumber, byte[] license,
			String license_contenttype, byte[] aadhaar, String aadhaar_contenttype) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.license = license;
		this.license_contenttype = license_contenttype;
		this.aadhaar = aadhaar;
		this.aadhaar_contenttype = aadhaar_contenttype;
	}

	public String getLicense_contenttype() {
		return license_contenttype;
	}

	public void setLicense_contenttype(String license_contenttype) {
		this.license_contenttype = license_contenttype;
	}

	public String getAadhaar_contenttype() {
		return aadhaar_contenttype;
	}

	public void setAadhaar_contenttype(String aadhaar_contenttype) {
		this.aadhaar_contenttype = aadhaar_contenttype;
	}

	public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public byte[] getLicense() {
        return license;
    }

    public void setLicense(byte[] license) {
        this.license = license;
    }

    public byte[] getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(byte[] aadhaar) {
        this.aadhaar = aadhaar;
    }
}
