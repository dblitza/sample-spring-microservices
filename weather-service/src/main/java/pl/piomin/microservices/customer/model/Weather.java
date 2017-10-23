package pl.piomin.microservices.customer.model;

import java.util.List;

public class Weather {

	private Integer id;
	private String pesel;
	private String name;
	private CustomerType type;
	private List<Tweets> accounts;

	public Weather() {
		
	}
	
	public Weather(Integer id, String pesel, String name, CustomerType type) {
		this.id = id;
		this.pesel = pesel;
		this.name = name;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
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
