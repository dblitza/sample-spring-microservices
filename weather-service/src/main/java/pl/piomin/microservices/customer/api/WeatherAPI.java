package pl.piomin.microservices.customer.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.piomin.microservices.customer.intercomm.TwitterClient;
import pl.piomin.microservices.customer.model.Tweets;
import pl.piomin.microservices.customer.model.Weather;
import pl.piomin.microservices.customer.model.CustomerType;

@RestController
public class WeatherAPI {
	
	@Autowired
	private TwitterClient twitterClient;
	
	protected Logger logger = Logger.getLogger(WeatherAPI.class.getName());
	
	private List<Weather> weather;
	
	public WeatherAPI() {
		weather = new ArrayList<>();
		weather.add(new Weather(1, "12345", "Adam Kowalski", CustomerType.INDIVIDUAL));
		weather.add(new Weather(2, "12346", "Anna Malinowska", CustomerType.INDIVIDUAL));
		weather.add(new Weather(3, "12347", "Paweł Michalski", CustomerType.INDIVIDUAL));
		weather.add(new Weather(4, "12348", "Karolina Lewandowska", CustomerType.INDIVIDUAL));
	}
	
	@RequestMapping("/customers/pesel/{pesel}")
	public Weather findByPesel(@PathVariable("pesel") String pesel) {
		logger.info(String.format("Weather.findByPesel(%s)", pesel));
		return weather.stream().filter(it -> it.getPesel().equals(pesel)).findFirst().get();	
	}
	
	@RequestMapping("/customers")
	public List<Weather> findAll() {
		logger.info("Weather.findAll()");
		return weather;
	}
	
	@RequestMapping("/weather/{id}")
	public Weather findById(@PathVariable("id") Integer id) {
		logger.info(String.format("Weather.findById(%s)", id));
		Weather weathers = weather.stream().filter(it -> it.getId().intValue()==id.intValue()).findFirst().get();
		List<Tweets> accounts =  twitterClient.getAccounts(id);
		weathers.setAccounts(accounts);
		return weathers;
	}
	
}
