/*package com.techsnob.entitiy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.techsnob.generator.VehicleIdGenerator;

import lombok.Data;

@Entity
@Data
@Table(name = "vehicles")
public class Vehicle implements Serializable{
	
	private static final long serialVersionUID = -1067954603890596442L;

	@Id
	@GeneratedValue( generator="vehicle_seq")
	@GenericGenerator(name = "vehicle_seq", strategy = "com.techsnob.generator.VehicleIdGenerator", parameters= {
			@Parameter(name = VehicleIdGenerator.INITIAL_PARAM, value = "1000")
	})
	@Column(name = "vehicle_id", unique = true, nullable = false, length = 30)
	private String vehicleId;
	
	@Column(name = "vehicle_name", nullable = false, length = 30)
	private String vehicleName;
	
	@Column(name = "vehicle_number", unique = true, nullable = false, length = 12)
	private String vehicleNumber;
	
}
*/