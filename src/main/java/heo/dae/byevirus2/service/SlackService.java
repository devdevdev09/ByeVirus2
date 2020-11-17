package heo.dae.byevirus2.service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import heo.dae.byevirus2.vo.Response;

@Service
public class SlackService {
    @Value("${slack.value.hooks}")
    String SLACK_URL;

    public void sendMsg(Response response){
        
        RestTemplate restTemplate = new RestTemplate();

        Map<String,Object> req = new HashMap<String,Object>();
        req.put("text", "test");
        req.put("username", "virus2");
        try {
            HttpEntity<Map<String,Object>> entity = new HttpEntity<Map<String,Object>>(req);
                    
            String url = SLACK_URL;
            URI uri = URI.create(url);
            restTemplate.postForLocation(uri, req);
        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }
}
