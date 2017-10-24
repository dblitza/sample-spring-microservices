package pl.piomin.microservices.customer.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.piomin.microservices.customer.intercomm.NodeJSClient;
import pl.piomin.microservices.customer.intercomm.TwitterClient;
import pl.piomin.microservices.customer.model.Tweets;
import pl.piomin.microservices.customer.model.Weather;
import pl.piomin.microservices.customer.model.CustomerType;

@RestController
public class WeatherAPI {
	
	@Autowired
	private TwitterClient twitterClient;
	
	@Autowired
	private NodeJSClient nodeJSClient;
	
	protected Logger logger = Logger.getLogger(WeatherAPI.class.getName());
	
	private List<Weather> weather;
	
	public WeatherAPI() {
		
		String output = "";
		String latitude = "";
		String longitude = "";
		String temperature = null;
		String theCity = null;

		  try {

			URL url = new URL("http://api.wunderground.com/api/48633266480f7fa8/conditions/q/CA/San_Francisco.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				
				if(output.contains("latitude")) {
					String[] aSplit = output.split(":\"");
					String[] newSplit = aSplit[1].split("\"");
					latitude = newSplit[0];
					
				}
				if(output.contains("longitude")) {
					String[] aSplit = output.split(":\"");
					String[] newSplit = aSplit[1].split("\"");
					longitude = newSplit[0];
				}
				if(output.contains("city")) {
					System.out.println(output);
						String[] aSplit = output.split(":\"");
						String[] newSplit = aSplit[1].split("\"");
						theCity = newSplit[0];


				}
				
				if(output.contains("temp_f")) {
					System.out.println(output);
					String[] aSplit = output.split(":");
					String[] newSplit = aSplit[1].split(",");
					temperature = newSplit[0];
				}
			}

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		  
		  weather = new ArrayList<>();
		  weather.add(new Weather(1, temperature, theCity, CustomerType.INDIVIDUAL));
		  weather.add(new Weather(2, "12346", "Anna Malinowska", CustomerType.INDIVIDUAL));
		  weather.add(new Weather(3, "12347", "Paweł Michalski", CustomerType.INDIVIDUAL));
		  weather.add(new Weather(4, "12348", "Karolina Lewandowska", CustomerType.INDIVIDUAL));

	}
	
//	@RequestMapping("/customers/pesel/{pesel}")
//	public Weather findByPesel(@PathVariable("pesel") String pesel) {
//		logger.info(String.format("Weather.findByPesel(%s)", pesel));
//		return weather.stream().filter(it -> it.getTemperature().equals(pesel)).findFirst().get();	
//	}
	
//	@RequestMapping("/customers")
//	public List<Weather> findAll() {
//		logger.info("Weather.findAll()");
//		return weather;
//	}
	
	@RequestMapping("/weather/{id}")
	public Weather findById(@PathVariable("id") Integer id) {
		logger.info(String.format("Weather.findById(%s)", id));
		Weather weathers = weather.stream().filter(it -> it.getId().intValue()==id.intValue()).findFirst().get();
		List<Tweets> tweets =  twitterClient.getAccounts(id);
//		String nodeJS = nodeJSClient.getNodeJS();
		weathers.setAccounts(tweets);
		return weathers;
	}
	
}
