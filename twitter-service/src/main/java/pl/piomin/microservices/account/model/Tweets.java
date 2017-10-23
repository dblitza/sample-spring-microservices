package pl.piomin.microservices.account.model;

public class Tweets {

	private Integer id;
	private Integer customerId;
	private String number;

	public Tweets() {

	}

	public Tweets(Integer id, Integer customerId, String number) {
		this.id = id;
		this.customerId = customerId;
		this.number = number;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWeatherCity() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
