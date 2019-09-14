package com.techsnob.entitiy;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "drivers")
public class Driver implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="driver_seq")
	@SequenceGenerator(name = "driver_seq", sequenceName="driver_seq", allocationSize=1, initialValue = 1000)
	@Column(name = "driver_id")
	private Long driverId;
	
	@Column(name = "driver_name", nullable = false, length = 30)
	private String driverName;
	
	@Column(name = "phone_number", unique = true, nullable = false, length = 10)
	private Long phoneNumber;
	
	@Column(name = "license_number", unique = true, nullable = false, length = 30)
	private String licenseNumber;

    @Column(name = "badge_number", unique = true, nullable = false, length = 30)
    private String badgeNumber;

    @OneToOne(cascade= CascadeType.ALL,fetch= FetchType.LAZY)
	@JoinColumn(name="address_id")
    private Address address;

    public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
