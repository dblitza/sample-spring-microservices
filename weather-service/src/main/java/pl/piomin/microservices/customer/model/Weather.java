package pl.piomin.microservices.customer.model;

import java.util.List;

public class Weather {

	private Integer id;
	private String temperature;
	private String name;
	private CustomerType type;
	private List<Tweets> accounts;

	public Weather() {
		
	}
	
	public Weather(Integer id, String theTemperature, String name, CustomerType type) {
		this.id = id;
		this.temperature = theTemperature;
		this.name = name;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

	public List<Tweets> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Tweets> accounts) {
		this.accounts = accounts;
	}

}
