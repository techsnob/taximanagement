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
public class Vehicle extends Auditable<String> implements Serializable {

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

    @Column(name = "rcFile_type", nullable = false, length = 30)
    private String rcFileType;
    
    @Column(name = "fitness")
    private byte[] fitness;

    @Column(name = "fitness_type", nullable = false, length = 30)
    private String fitnessType;
    
    @Column(name = "insurance")
    private byte[] insurance;

    @Column(name = "insurance_type", nullable = false, length = 30)
    private String insuranceType;
    
    @Column(name = "taxsheet")
    private byte[] taxsheet;

    @Column(name = "taxsheet_type", nullable = false, length = 30)
    private String taxsheetType;
    
    public Vehicle() {
    }

    public Vehicle(String vehicleType, String rcNumber, byte[] rcFile, String rcFileType, byte[] fitness, String fitnessType, byte[] insurance, String insuranceType, byte[] taxsheet, String taxsheetType) {
        this.vehicleType = vehicleType;
        this.rcNumber = rcNumber;
        this.rcFile = rcFile;
        this.rcFileType = rcFileType;
        this.fitness = fitness;
        this.fitnessType = fitnessType;
        this.insurance = insurance;
        this.insuranceType = insuranceType;
        this.taxsheet = taxsheet;
        this.taxsheetType = taxsheetType;
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

    public String getRcFileType() {
        return rcFileType;
    }

    public void setRcFileType(String rcFileType) {
        this.rcFileType = rcFileType;
    }

    public String getFitnessType() {
        return fitnessType;
    }

    public void setFitnessType(String fitnessType) {
        this.fitnessType = fitnessType;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getTaxsheetType() {
        return taxsheetType;
    }

    public void setTaxsheetType(String taxsheetType) {
        this.taxsheetType = taxsheetType;
    }
}
