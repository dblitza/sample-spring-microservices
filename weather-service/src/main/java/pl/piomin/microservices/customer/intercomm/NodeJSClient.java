package pl.piomin.microservices.customer.intercomm;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.piomin.microservices.customer.model.Tweets;

@FeignClient("sidecar-service")
public interface NodeJSClient {

    @RequestMapping(method = RequestMethod.GET, value = "/hitserver")
    String getNodeJS();
  
}
