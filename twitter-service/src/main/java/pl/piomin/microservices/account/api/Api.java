package pl.piomin.microservices.account.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.piomin.microservices.account.model.Tweets;

@RestController
public class Api {

	private List<Tweets> accounts;
	
	protected Logger logger = Logger.getLogger(Api.class.getName());
	
	public Api() {
		accounts = new ArrayList<>();
		accounts.add(new Tweets(1, 1, "111111"));
		accounts.add(new Tweets(2, 2, "222222"));
		accounts.add(new Tweets(3, 3, "333333"));
		accounts.add(new Tweets(4, 4, "444444"));
		accounts.add(new Tweets(5, 1, "555555"));
		accounts.add(new Tweets(6, 2, "666666"));
		accounts.add(new Tweets(7, 2, "777777"));
	}
	
	@RequestMapping("/accounts/{number}")
	public Tweets findByNumber(@PathVariable("number") String number) {
		logger.info(String.format("Account.findByNumber(%s)", number));
		return accounts.stream().filter(it -> it.getNumber().equals(number)).findFirst().get();
	}
	
	@RequestMapping("/accounts/customer/{customer}")
	public List<Tweets> findByCustomer(@PathVariable("customer") Integer customerId) {
		logger.info(String.format("Account.findByCustomer(%s)", customerId));
		return accounts.stream().filter(it -> it.getCustomerId().intValue()==customerId.intValue()).collect(Collectors.toList());
	}
	
	@RequestMapping("/accounts")
	public List<Tweets> findAll() {
		logger.info("Account.findAll()");
		return accounts;
	}
	
}
