package com.techsnob.entitiy;

import java.io.Serializable;

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

@Entity
public class BankAccount implements Serializable{
	
	private static final long serialVersionUID = 526158785972522314L;

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="account_seq")
	@SequenceGenerator(name = "account_seq", sequenceName="account_seq", allocationSize=1, initialValue = 1000)
	@Column(name = "account_id")
	private Long accountId;

	@Column(length=20, name="account_number", nullable=false)
	private Long accountNumber;

	@Column(length=20, name="ifsc_code", nullable=false)
	private String ifscCode;

	@Column(length=30, name="acct_holder_name", nullable=false)
	private String accountHolderName;

	/*@OneToOne(cascade= CascadeType.ALL,fetch= FetchType.LAZY)
	@JoinColumn(name="driver_id")
	private Driver driver;*/
	
	public Long getAccountNumber() {
		return accountNumber;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public void setAccountNumber(Long accountNumber) {
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
