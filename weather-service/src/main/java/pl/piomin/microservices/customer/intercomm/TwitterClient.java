package pl.piomin.microservices.customer.intercomm;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.piomin.microservices.customer.model.Tweets;

@FeignClient("twitter-service")
public interface TwitterClient {

    @RequestMapping(method = RequestMethod.GET, value = "/twitter/weather/{weatherCity}")
    List<Tweets> getAccounts(@PathVariable("weatherCity") Integer weatherCity);
    
}
