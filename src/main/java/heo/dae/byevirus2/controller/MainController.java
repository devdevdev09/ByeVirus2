package heo.dae.byevirus2.controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

@RestController
public class MainController {
    @Value("${service.key}")
    public String SERVICE_KEY;

    @Value("${api.endpoint}")
    String END_POINT;

    @Value("${api.name}")
    String API_NAME;

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
    public void test() throws UnsupportedEncodingException {
        System.out.println("serviceKey : " + SERVICE_KEY);

        String url = END_POINT + API_NAME + "?serviceKey=" + SERVICE_KEY + "&pageNo=1&numOfRows=10&startCreateDt=20200310&endCreateDt=20200315";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
        // .queryParam("serviceKey", SERVICE_KEY)
        // .queryParam("pageNo", "1")
        // .queryParam("numOfRows", "10")
        // .queryParam("startCreateDt", "20200310")
        // .queryParam("endCreateDt", "20200315");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        // String te  = URLEncoder.encode(url, "UTF-8");
        ResponseEntity<String> response = restTemplate.exchange(URI.create(url), HttpMethod.GET, entity, String.class);

        System.out.println(response);

    }
}

