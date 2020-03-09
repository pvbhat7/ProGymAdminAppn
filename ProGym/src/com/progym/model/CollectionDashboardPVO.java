package com.progym.model;

public class CollectionDashboardPVO {

	private Double male;
	private Double female;
	private Double total;
	private Double steam;
	private int maletotal;
	private int femaletotal;
	private int clienttotal;
	
	public Double getMale() {
		return male;
	}
	public void setMale(Double male) {
		this.male = male;
	}
	public Double getFemale() {
		return female;
	}
	public void setFemale(Double female) {
		this.female = female;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getSteam() {
		return steam;
	}
	public void setSteam(Double steam) {
		this.steam = steam;
	}
	public int getMaletotal() {
		return maletotal;
	}
	public void setMaletotal(int maletotal) {
		this.maletotal = maletotal;
	}
	public int getFemaletotal() {
		return femaletotal;
	}
	public void setFemaletotal(int femaletotal) {
		this.femaletotal = femaletotal;
	}
	public int getClienttotal() {
		return clienttotal;
	}
	public void setClienttotal(int clienttotal) {
		this.clienttotal = clienttotal;
	}
	public CollectionDashboardPVO(Double male, Double female, Double total, Double steam, int maletotal,
			int femaletotal, int clienttotal) {
		super();
		this.male = male;
		this.female = female;
		this.total = total;
		this.steam = steam;
		this.maletotal = maletotal;
		this.femaletotal = femaletotal;
		this.clienttotal = clienttotal;
	}
	@Override
	public String toString() {
		return "CollectionDashboardPVO [male=" + male + ", female=" + female + ", total=" + total + ", steam=" + steam
				+ ", maletotal=" + maletotal + ", femaletotal=" + femaletotal + ", clienttotal=" + clienttotal + "]";
	}
	
	
	
	
}
