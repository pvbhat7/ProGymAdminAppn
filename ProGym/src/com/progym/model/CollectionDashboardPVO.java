package com.progym.model;

public class CollectionDashboardPVO {

	public int getMaleFullPaid() {
		return maleFullPaid;
	}
	public void setMaleFullPaid(int maleFullPaid) {
		this.maleFullPaid = maleFullPaid;
	}
	public int getMalePartialPaid() {
		return malePartialPaid;
	}
	public void setMalePartialPaid(int malePartialPaid) {
		this.malePartialPaid = malePartialPaid;
	}
	public int getMaleNotPaid() {
		return maleNotPaid;
	}
	public void setMaleNotPaid(int maleNotPaid) {
		this.maleNotPaid = maleNotPaid;
	}
	public int getFemaleFullPaid() {
		return femaleFullPaid;
	}
	public void setFemaleFullPaid(int femaleFullPaid) {
		this.femaleFullPaid = femaleFullPaid;
	}
	public int getFemalePartialPaid() {
		return femalePartialPaid;
	}
	public void setFemalePartialPaid(int femalePartialPaid) {
		this.femalePartialPaid = femalePartialPaid;
	}
	public int getFemaleNotPaid() {
		return femaleNotPaid;
	}
	public void setFemaleNotPaid(int femaleNotPaid) {
		this.femaleNotPaid = femaleNotPaid;
	}
	private Double male;
	private Double female;
	private Double total;
	private Double steam;
	
	private Double currentMonthMaleCollection;
	private Double currentMonthFemaleCollection;
	private Double currentMonthSteamCollection;
	private Double currentMonthTotalCollection;
	
	private Double lastMonthMaleCollection;
	private Double lastMonthFemaleCollection;
	private Double lastMonthSteamCollection;
	private Double lastMonthTotalCollection;
	
	private int maletotal;
	private int femaletotal;
	private int clienttotal;
	private int maleFullPaid;
	private int malePartialPaid;
	private int maleNotPaid;
	private int femaleFullPaid;
	private int femalePartialPaid;
	private int femaleNotPaid;
	
	private String currentMonth;
	private String lastMonth;
	
	
	public String getCurrentMonth() {
		return currentMonth;
	}
	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}
	public String getLastMonth() {
		return lastMonth;
	}
	public void setLastMonth(String lastMonth) {
		this.lastMonth = lastMonth;
	}
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
			int femaletotal, int clienttotal, int maleFullPaid, int malePartialPaid, int maleNotPaid,
			int femaleFullPaid, int femalePartialPaid, int femaleNotPaid,
			Double currentMonthMaleCollection,Double currentMonthFemaleCollection,Double currentMonthSteamCollection,Double currentMonthTotalCollection,
			Double lastMonthMaleCollection,Double lastMonthFemaleCollection,Double lastMonthSteamCollection,Double lastMonthTotalCollection,
			String currentMonthName , String lastMonthName
			
			) {
		super();
		this.male = male;
		this.female = female;
		this.total = total;
		this.steam = steam;
		this.maletotal = maletotal;
		this.femaletotal = femaletotal;
		this.clienttotal = clienttotal;
		this.maleFullPaid = maleFullPaid;
		this.malePartialPaid = malePartialPaid;
		this.maleNotPaid = maleNotPaid;
		this.femaleFullPaid = femaleFullPaid;
		this.femalePartialPaid = femalePartialPaid;
		this.femaleNotPaid = femaleNotPaid;
		
		this.currentMonthMaleCollection = currentMonthMaleCollection;
		this.currentMonthFemaleCollection = currentMonthFemaleCollection;
		this.lastMonthSteamCollection = lastMonthSteamCollection;
		this.currentMonthTotalCollection = currentMonthTotalCollection;
		
		this.lastMonthMaleCollection = lastMonthMaleCollection;
		this.lastMonthFemaleCollection = lastMonthFemaleCollection;
		this.lastMonthSteamCollection = lastMonthSteamCollection;
		this.lastMonthTotalCollection = lastMonthTotalCollection;
		
		this.currentMonth = currentMonthName;		
		this.lastMonth = lastMonthName;		
	}
	public Double getCurrentMonthMaleCollection() {
		return currentMonthMaleCollection;
	}
	public void setCurrentMonthMaleCollection(Double currentMonthMaleCollection) {
		this.currentMonthMaleCollection = currentMonthMaleCollection;
	}
	public Double getCurrentMonthFemaleCollection() {
		return currentMonthFemaleCollection;
	}
	public void setCurrentMonthFemaleCollection(Double currentMonthFemaleCollection) {
		this.currentMonthFemaleCollection = currentMonthFemaleCollection;
	}
	public Double getCurrentMonthSteamCollection() {
		return currentMonthSteamCollection;
	}
	public void setCurrentMonthSteamCollection(Double currentMonthSteamCollection) {
		this.currentMonthSteamCollection = currentMonthSteamCollection;
	}
	public Double getCurrentMonthTotalCollection() {
		return currentMonthTotalCollection;
	}
	public void setCurrentMonthTotalCollection(Double currentMonthTotalCollection) {
		this.currentMonthTotalCollection = currentMonthTotalCollection;
	}
	public Double getLastMonthMaleCollection() {
		return lastMonthMaleCollection;
	}
	public void setLastMonthMaleCollection(Double lastMonthMaleCollection) {
		this.lastMonthMaleCollection = lastMonthMaleCollection;
	}
	public Double getLastMonthFemaleCollection() {
		return lastMonthFemaleCollection;
	}
	public void setLastMonthFemaleCollection(Double lastMonthFemaleCollection) {
		this.lastMonthFemaleCollection = lastMonthFemaleCollection;
	}
	public Double getLastMonthSteamCollection() {
		return lastMonthSteamCollection;
	}
	public void setLastMonthSteamCollection(Double lastMonthSteamCollection) {
		this.lastMonthSteamCollection = lastMonthSteamCollection;
	}
	public Double getLastMonthTotalCollection() {
		return lastMonthTotalCollection;
	}
	public void setLastMonthTotalCollection(Double lastMonthTotalCollection) {
		this.lastMonthTotalCollection = lastMonthTotalCollection;
	}
	@Override
	public String toString() {
		return "CollectionDashboardPVO [male=" + male + ", female=" + female + ", total=" + total + ", steam=" + steam
				+ ", currentMonthMaleCollection=" + currentMonthMaleCollection + ", currentMonthFemaleCollection="
				+ currentMonthFemaleCollection + ", currentMonthSteamCollection=" + currentMonthSteamCollection
				+ ", currentMonthTotalCollection=" + currentMonthTotalCollection + ", lastMonthMaleCollection="
				+ lastMonthMaleCollection + ", lastMonthFemaleCollection=" + lastMonthFemaleCollection
				+ ", lastMonthSteamCollection=" + lastMonthSteamCollection + ", lastMonthTotalCollection="
				+ lastMonthTotalCollection + ", maletotal=" + maletotal + ", femaletotal=" + femaletotal
				+ ", clienttotal=" + clienttotal + ", maleFullPaid=" + maleFullPaid + ", malePartialPaid="
				+ malePartialPaid + ", maleNotPaid=" + maleNotPaid + ", femaleFullPaid=" + femaleFullPaid
				+ ", femalePartialPaid=" + femalePartialPaid + ", femaleNotPaid=" + femaleNotPaid + ", currentMonth="
				+ currentMonth + ", lastMonth=" + lastMonth + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
