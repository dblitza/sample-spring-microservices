package pl.piomin.microservices.account.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.piomin.microservices.account.model.Tweets;
import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@RestController
public class TwitterAPI {

	private List<Tweets> tweets;
	
	protected Logger logger = Logger.getLogger(TwitterAPI.class.getName());
	
	public TwitterAPI() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("KO0lv7MrezICw7UUj4Zh8rWzl")
		  .setOAuthConsumerSecret("Xq7xMfCpzMoHVzXRpwxBj6aXPkElXyuz5EBTruKtHmBcKXUbnl")
		  .setOAuthAccessToken("907732271163211776-hsiy1Xm1shNpPV924l8r6OkxOwiqfSL")
		  .setOAuthAccessTokenSecret("qc86wVUou30IMIe4cpmmK630nmA0tPY1TvjBLh6Q5ZgBB");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
	    double lat = 37.770599;
	    double lon = -122.423500;
	    double res = 50;
	    String resUnit = "mi";
	    String keyword = "weather";
	    StringBuilder stringBuilder = new StringBuilder();

	    	    
	    Query query = new Query(keyword).geoCode(new GeoLocation(lat,lon), res, resUnit); 
	    query.count(5);
	    try {
			QueryResult result = twitter.search(query);
		    for (Status tweet : result.getTweets()) {
		    		stringBuilder.append(tweet.getText());
		    }

		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    String theTweets = stringBuilder.toString();
	    logger.info(theTweets);
	    tweets = new ArrayList<>();
	    tweets.add(new Tweets(1, 1, theTweets));
		tweets.add(new Tweets(2, 2, "222222"));
	}
	
	@RequestMapping("/twitter/{number}")
	public Tweets findByNumber(@PathVariable("number") String number) {
		logger.info(String.format("Tweets.findByNumber(%s)", number));
		return tweets.stream().filter(it -> it.getNumber().equals(number)).findFirst().get();
	}
	
	@RequestMapping("/twitter/customer/{customer}")
	public List<Tweets> findByCustomer(@PathVariable("customer") Integer customerId) {
		logger.info(String.format("Account.findByCustomer(%s)", customerId));
		return tweets.stream().filter(it -> it.getCustomerId().intValue()==customerId.intValue()).collect(Collectors.toList());
	}
	
	@RequestMapping("/twitter")
	public List<Tweets> findAll() {
		logger.info("Account.findAll()");
		return tweets;
	}
	
public List<Tweets> getTweetsGeocode(List<Double> coordinates) {
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("KO0lv7MrezICw7UUj4Zh8rWzl")
		  .setOAuthConsumerSecret("Xq7xMfCpzMoHVzXRpwxBj6aXPkElXyuz5EBTruKtHmBcKXUbnl")
		  .setOAuthAccessToken("907732271163211776-hsiy1Xm1shNpPV924l8r6OkxOwiqfSL")
		  .setOAuthAccessTokenSecret("qc86wVUou30IMIe4cpmmK630nmA0tPY1TvjBLh6Q5ZgBB");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
	    double lat = coordinates.get(0);
	    double lon = coordinates.get(1);
	    double res = 50;
	    String resUnit = "mi";
	    String keyword = "weather";
	    StringBuilder stringBuilder = new StringBuilder();

	    	    
	    Query query = new Query(keyword).geoCode(new GeoLocation(lat,lon), res, resUnit); 
	    query.count(100);
	    try {
			QueryResult result = twitter.search(query);
		    for (Status tweet : result.getTweets()) {
		    		stringBuilder.append(tweet.getText());
		    }

		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    String theTweets = stringBuilder.toString();
	    tweets = new ArrayList<>();
	    tweets.add(new Tweets(1, 1, theTweets));
		tweets.add(new Tweets(2, 2, "222222"));
		return tweets;
		
		
		
		
	}
	
}
