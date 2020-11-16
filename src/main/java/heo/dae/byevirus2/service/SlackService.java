package heo.dae.byevirus2.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
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

        Map<String,Object> request = new HashMap<String,Object>();
        request.put("username", "virus2");
        request.put("text", response.body.toString());

        HttpEntity<Map<String,Object>> entity = new HttpEntity<Map<String,Object>>(request);
                    
        String url = SLACK_URL;

        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }
}
