package com.progym.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "PACKAGEDETAILS")
public class PackageDetails {

	
	public PackageDetails() {		
	}
	
	public PackageDetails(Client client, String packageStartDate, CPackage cPackage, String clientPackageStatus,
			Double amountPaid) {
		super();
		this.client = client;
		this.packageStartDate = packageStartDate;
		this.cPackage = cPackage;
		this.clientPackageStatus = clientPackageStatus;
		this.amountPaid = amountPaid;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    private Client client;

    @Column
    private String packageStartDate;

    @OneToOne
    @JoinColumn(name="PACKAGE_ID")
    private CPackage cPackage;

    @Column
    private String clientPackageStatus;

    @Column
    private Double amountPaid;
    
    @Column
    private String discontinue;
    
    @OneToMany(cascade=CascadeType.ALL)
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

	public CPackage getcPackage() {
		return cPackage;
	}

	public void setcPackage(CPackage cPackage) {
		this.cPackage = cPackage;
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
    
	
    

    
}
