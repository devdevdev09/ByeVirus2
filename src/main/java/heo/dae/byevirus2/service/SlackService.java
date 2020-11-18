package heo.dae.byevirus2.service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import heo.dae.byevirus2.utils.RestUtil;
import heo.dae.byevirus2.vo.Response;

@Service
public class SlackService {

    @Autowired
    RestUtil restUtil;

    public void sendMsg(Response response, String SLACK_URL){
        Map<String, Object> req = new HashMap<String, Object>();
        req.put("username", "DailyCommit");
        req.put("text", "test");
        try {
            HttpEntity<Map<String,Object>> entity = new HttpEntity<Map<String,Object>>(req);
                    
            String url = SLACK_URL;
            
            restUtil.post(url, HttpMethod.POST,req);
        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }
}
