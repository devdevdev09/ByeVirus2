package heo.dae.byevirus2.controller;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import heo.dae.byevirus2.service.ApiService;
import heo.dae.byevirus2.service.SlackService;
import heo.dae.byevirus2.service.XmlService;
import heo.dae.byevirus2.vo.Response;


@RestController
@RequestMapping("/api")
public class MainController {
    private final ApiService apiService;
    private final XmlService xmlService;
    private final SlackService slackService;

    public MainController(ApiService apiService, XmlService xmlService
                         ,SlackService slackService){
        this.apiService = apiService;
        this.xmlService = xmlService;
        this.slackService = slackService;
    }

    @GetMapping("/virus")
    public Response callApi(@RequestParam(required = false) 
                        @DateTimeFormat(pattern = "yyyyMMdd") 
                        Date startDate
                        
                        ,@RequestParam(required = false) 
                        @DateTimeFormat(pattern = "yyyyMMdd")
                        Date endDate
                        
                        ,@RequestParam(required = false, defaultValue = "1")
                        String pageNo
                        
                        ,@RequestParam(required = false, defaultValue = "10")
                        String numOfRows){

        Date targetStartDate;
        Date targetEndDate;

        if(startDate == null || endDate == null){
            targetStartDate = new Date();
            targetEndDate = new Date();
        }else{
            targetStartDate = startDate;
            targetEndDate = startDate;
        }

        String url = apiService.getApiUrl(targetStartDate, targetEndDate, pageNo, numOfRows);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(URI.create(url), HttpMethod.GET, entity, String.class);
        
        Response responseXml = xmlService.parser(response.getBody());

        System.out.println(responseXml.body.toString());
        System.out.println(responseXml.body.numOfRows);

        slackService.sendMsg(responseXml);

        return responseXml;
    }
}

