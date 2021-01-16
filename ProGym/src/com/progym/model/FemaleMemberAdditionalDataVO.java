package com.progym.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Entity
@Table(name = "FEMALE_MEMBER_ADDITIONAL_DATA")
public class FemaleMemberAdditionalDataVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column
	private String date;
	
	@Column
	private String month;

	@Column
	private String day;

	@Column
	private String year;
	
	@Column
	private double weight;
	
	@Column
	private double height;
	
	@Column
	private double neck;
	
	@Column
	private double chest;
	
	@Column
	private double weist;
	
	@Column
	private double arm;
	
	@Column
	private double thigh;
	
	@Column
	private double upperHips;
	
	@Column
	private double hips;
	
	@Column
	private double calf;
	
	@Column
	private double ankle;
	
	@Column
	private int clientId;
	
	@Column
	private String discontinue;
	
	@Column
	private String gender;

	public FemaleMemberAdditionalDataVO() {
		this.date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		this.month = Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());;
		this.weight = 0.00;
		this.height = 0.00;
		this.neck = 0.00;
		this.chest = 0.00;
		this.weist = 0.00;
		this.arm = 0.00;
		this.thigh = 0.00;
		this.upperHips = 0.00;
		this.hips = 0.00;
		this.calf = 0.00;
		this.ankle = 0.00;
		this.clientId = 0;
		this.discontinue = "";
		this.gender = "";
		this.day = "";
		this.year = "";
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getNeck() {
		return neck;
	}

	public void setNeck(double neck) {
		this.neck = neck;
	}

	public double getChest() {
		return chest;
	}

	public void setChest(double chest) {
		this.chest = chest;
	}

	public double getWeist() {
		return weist;
	}

	public void setWeist(double weist) {
		this.weist = weist;
	}

	public double getArm() {
		return arm;
	}

	public void setArm(double arm) {
		this.arm = arm;
	}

	public double getThigh() {
		return thigh;
	}

	public void setThigh(double thigh) {
		this.thigh = thigh;
	}

	public double getUpperHips() {
		return upperHips;
	}

	public void setUpperHips(double upperHips) {
		this.upperHips = upperHips;
	}

	public double getHips() {
		return hips;
	}

	public void setHips(double hips) {
		this.hips = hips;
	}

	public double getCalf() {
		return calf;
	}

	public void setCalf(double calf) {
		this.calf = calf;
	}

	public double getAnkle() {
		return ankle;
	}

	public void setAnkle(double ankle) {
		this.ankle = ankle;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getDiscontinue() {
		return discontinue;
	}

	public void setDiscontinue(String discontinue) {
		this.discontinue = discontinue;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}
