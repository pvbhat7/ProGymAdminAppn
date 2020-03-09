
public class CollectionDashboardPVO {

	private Double male;
	private Double female;
	private Double total;
	private Double steam;
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
	public CollectionDashboardPVO(Double male, Double female, Double total, Double steam) {
		super();
		this.male = male;
		this.female = female;
		this.total = total;
		this.steam = steam;
	}
	
	
	
}
