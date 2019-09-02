package com.techsnob.entitiy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BankAccount implements Serializable{
	
	private static final long serialVersionUID = 526158785972522314L;
	
	@Id
	private String accountNumber;
	@Column(length=11, name="ifsc_code", nullable=false)
	private String ifscCode;
	@Column(length=30, name="acct_holder_name", nullable=false)
	private String accountHolderName;
	/*@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="driver_id")
	private Driver driver;*/
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

}
