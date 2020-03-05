package com.progym.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "PACKAGEDETAILS")
public class PackageDetails {

	
	public PackageDetails() {		
	}
	
	

	public PackageDetails(Client client, String packageName, Double packageFees, String packageStartDate,
			String packageEndDate, String packagePaymentDate, int cPackageId, String clientPackageStatus,
			Double amountPaid, String discontinue, List<PaymentTransaction> paymentTransactions) {
		super();
		this.client = client;
		this.packageName = packageName;
		this.packageFees = packageFees;
		this.packageStartDate = packageStartDate;
		this.packageEndDate = packageEndDate;
		this.packagePaymentDate = packagePaymentDate;
		this.cPackageId = cPackageId;
		this.clientPackageStatus = clientPackageStatus;
		this.amountPaid = amountPaid;
		this.discontinue = discontinue;
		this.paymentTransactions = paymentTransactions;
	}



	public String getPackageEndDate() {
		return packageEndDate;
	}

	public void setPackageEndDate(String packageEndDate) {
		this.packageEndDate = packageEndDate;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    private Client client;

    @Column
    private String packageName;
    
    @Column
    private Double packageFees;
    
    @Column
    private String packageStartDate;
    
    @Column
    private String packageEndDate;
    
    @Column
    private String packagePaymentDate;
    

    public String getPackagePaymentDate() {
		return packagePaymentDate;
	}

	public void setPackagePaymentDate(String packagePaymentDate) {
		this.packagePaymentDate = packagePaymentDate;
	}


	@Column 
    private int cPackageId;
    
    @Column
    private String clientPackageStatus;

    @Column
    private Double amountPaid;
    
    @Column
    private String discontinue;
    
    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="PACKAGE_DETAILS_ID")
	private List<PaymentTransaction> paymentTransactions;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getPackageStartDate() {
		return packageStartDate;
	}

	public void setPackageStartDate(String packageStartDate) {
		this.packageStartDate = packageStartDate;
	}

	
	public String getClientPackageStatus() {
		return clientPackageStatus;
	}

	public void setClientPackageStatus(String clientPackageStatus) {
		this.clientPackageStatus = clientPackageStatus;
	}

	public Double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDiscontinue() {
		return discontinue;
	}

	public void setDiscontinue(String discontinue) {
		this.discontinue = discontinue;
	}

	public List<PaymentTransaction> getPaymentTransactions() {
		return paymentTransactions;
	}

	public void setPaymentTransactions(List<PaymentTransaction> paymentTransactions) {
		this.paymentTransactions = paymentTransactions;
	}

	public int getcPackageId() {
		return cPackageId;
	}

	public void setcPackageId(int cPackageId) {
		this.cPackageId = cPackageId;
	}



	public String getPackageName() {
		return packageName;
	}



	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}



	public Double getPackageFees() {
		return packageFees;
	}



	public void setPackageFees(Double packageFees) {
		this.packageFees = packageFees;
	}



	
	
	
	

	
	
	
    
	
    

    
}
