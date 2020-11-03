package heo.dae.byevirus2.controller;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jdk.xml.internal.JdkXmlFeatures.XmlFeature;

@RestController
public class MainController {
    @Value("${service.key}")
    public String SERVICE_KEY;

    @Value("${api.endpoint}")
    String END_POINT;

    @Value("${api.name}")
    String API_NAME;

    @RequestMapping("/test")
    public void test(){
        System.out.println("serviceKey : " + SERVICE_KEY);

        String url = END_POINT + API_NAME + "?serviceKey=" + SERVICE_KEY + "&pageNo=1&numOfRows=10&startCreateDt=20200310&endCreateDt=20200315";

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> map = new HashMap<String,Object>();

        HttpEntity entity = new HttpEntity<Map<String,Object>>(map);

        ResponseEntity response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        System.out.println(response);

    }
}
