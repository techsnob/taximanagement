package com.techsnob.entitiy;

import java.io.Serializable;
import java.sql.Blob;

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
    private String vehicleId;

    @Column(name = "vehicle_type", nullable = false, length = 30)
    private String vehicleType;

    @Column(name = "vehicle_rc_number", unique = true, nullable = false, length = 12)
    private String rcNumber;

    @Column(name = "vehicle_fitness")
    private Blob vehicleFitness;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
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

    public Blob getVehicleFitness() {
        return vehicleFitness;
    }

    public void setVehicleFitness(Blob vehicleFitness) {
        this.vehicleFitness = vehicleFitness;
    }
}
