package heo.dae.byevirus2.controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import heo.dae.byevirus2.parser.ParserService;
import heo.dae.byevirus2.service.ApiService;


@RestController
public class MainController {
    private ParserService parserService;
    private ApiService apiService;

    public MainController(ParserService parserService, ApiService apiService){
        this.parserService = parserService;
        this.apiService = apiService;
    }

    @RequestMapping("/date")
    public void datetest(
            @RequestParam(required = false) 
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            final Date startDate,
            @RequestParam(required = false) 
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            final Date endDate){

                if(startDate != null && endDate != null){

                }else{
                    Date yester = new Date();
                    Calendar c = Calendar.getInstance();
                    c.setTime(yester);
                    c.add(Calendar.DAY_OF_YEAR, -1);

                    System.out.println("startdate : " + c.getTime());
                    System.out.println("enddate : " + c.getTime());
                }

                
                System.out.println("date test   `   ");

    }

    @RequestMapping("/test")
    public void callApi(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyyMMdd") 
                        Date startDate,
                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyyMMdd")
                        Date endDate){
        Date targetStartDate;
        Date targetEndDate;
        if(startDate == null || endDate == null){
            targetStartDate = new Date();
            targetEndDate = new Date();
        }else{
            targetStartDate = startDate;
            targetEndDate = startDate;
        }

        String url = apiService.getApiUrl();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(URI.create(url), HttpMethod.GET, entity, String.class);
        
        Map<String,String> result = parserService.getParser(response.getBody());

        ParserService jsonParser = new ParserService();
        jsonParser.getParser(response.getBody());
        System.out.println(response);

        System.out.println(result);

    }
}

