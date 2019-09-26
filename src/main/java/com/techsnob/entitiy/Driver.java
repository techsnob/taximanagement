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
	
	@Column(name = "first_name", nullable = false, length = 30)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 30)
	private String lastName;
	
	@Column(name = "phone_number", unique = true, nullable = false, length = 10)
	private Long phoneNumber;
	
	@Column(name = "license")
	private String license;

	@Column(name = "aadhaar")
	private String aadhaar;

    /*@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="address_id")
    private Address address;*/



}
