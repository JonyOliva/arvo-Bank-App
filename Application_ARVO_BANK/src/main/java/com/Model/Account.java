package com.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Accounts")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAccount;
	@Column(unique = true)
	private String CBU;
	private String typeAccount;
	private String nameAccount;
	private Float funds;
	private Date creationDate;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "originAccount")
	private List<Transaction> transactionMade = new ArrayList<Transaction>();
	@OneToMany(mappedBy = "destinationAccount")
	private List<Transaction> transactionReceived = new ArrayList<Transaction>();
	private Byte state;

	public Account() {
		super();
	}

	public Integer getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(Integer idAccount) {
		this.idAccount = idAccount;
	}

	public String getCBU() {
		return CBU;
	}

	public void setCBU(String cBU) {
		CBU = cBU;
	}

	public String getTypeAccount() {
		return typeAccount;
	}

	public void setTypeAccount(String typeAccount) {
		this.typeAccount = typeAccount;
	}

	public String getNameAccount() {
		return nameAccount;
	}

	public void setNameAccount(String nameAccount) {
		this.nameAccount = nameAccount;
	}

	public Float getFunds() {
		return funds;
	}

	public void setFunds(Float funds) {
		this.funds = funds;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public List<Transaction> getTransactionMade() {
		return transactionMade;
	}

	public void setTransactionMade(List<Transaction> transactionMade) {
		this.transactionMade = transactionMade;
	}

	public List<Transaction> getTransactionReceived() {
		return transactionReceived;
	}

	public void setTransactionReceived(List<Transaction> transactionReceived) {
		this.transactionReceived = transactionReceived;
	}

}
