package com.techsnob.entitiy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "vehicles")
public class Vehicle implements Serializable {

    private static final long serialVersionUID = -1067954603890596442L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="vehicle_seq")
    @SequenceGenerator(name = "vehicle_seq", sequenceName="vehicle_seq", allocationSize=1, initialValue = 1000)
    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "vehicle_type", nullable = false, length = 30)
    private String vehicleType;

    @Column(name = "rcnumber", unique = true, nullable = false, length = 12)
    private String rcNumber;

    @Column(name = "rcFile")
    private byte[] rcFile;
    
    @Column(name = "fitness")
    private byte[] fitness;
    
    @Column(name = "insurance")
    private byte[] insurance;
    
    @Column(name = "taxsheet")
    private byte[] taxsheet;
    
    public Vehicle() {
    }

    public Vehicle(String vehicleType, String rcNumber, byte[] rcFile, byte[] fitness, byte[] insurance,
			byte[] taxsheet) {
		this.vehicleType = vehicleType;
		this.rcNumber = rcNumber;
		this.rcFile = rcFile;
		this.fitness = fitness;
		this.insurance = insurance;
		this.taxsheet = taxsheet;
	}

	public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getRcNumber() {
        return rcNumber;
    }

    public void setRcNumber(String rcNumber) {
        this.rcNumber = rcNumber;
    }

	public byte[] getFitness() {
		return fitness;
	}

	public void setFitness(byte[] fitness) {
		this.fitness = fitness;
	}

	public byte[] getInsurance() {
		return insurance;
	}

	public void setInsurance(byte[] insurance) {
		this.insurance = insurance;
	}

	public byte[] getTaxsheet() {
		return taxsheet;
	}

	public void setTaxsheet(byte[] taxsheet) {
		this.taxsheet = taxsheet;
	}

	public byte[] getRcFile() {
		return rcFile;
	}

	public void setRcFile(byte[] rcFile) {
		this.rcFile = rcFile;
	}
	
	

}
