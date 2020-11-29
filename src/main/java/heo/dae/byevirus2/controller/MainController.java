package heo.dae.byevirus2.controller;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import heo.dae.byevirus2.service.ApiService;
import heo.dae.byevirus2.service.SlackService;
import heo.dae.byevirus2.service.XmlService;
import heo.dae.byevirus2.utils.RestUtil;
import heo.dae.byevirus2.vo.Response;

@RestController
@RequestMapping("/api")
public class MainController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private ApiService apiService;
    private XmlService xmlService;
    private SlackService slackService;

    @Autowired
    RestUtil restUtil;

    public MainController(ApiService apiService, XmlService xmlService, SlackService slackService) {
        this.apiService = apiService;
        this.xmlService = xmlService;
        this.slackService = slackService;
    }

    @GetMapping("/virus")
    public ResponseEntity<Response> callApi(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyyMMdd") 
                            LocalDate startDate
                          , @RequestParam(required = false) @DateTimeFormat(pattern = "yyyyMMdd") 
                            LocalDate endDate) {

        LocalDate targetStartDate;
        LocalDate targetEndDate;

        LocalDate localDate = LocalDate.now();

        if (startDate == null || endDate == null) {
            targetStartDate = localDate.plusDays(-1);
            targetEndDate = localDate.plusDays(0);
        } else {
            targetStartDate = startDate;
            targetEndDate = endDate;
        }

        String url = apiService.getApiUrl(targetStartDate, targetEndDate);
        
        ResponseEntity<String> responseEntity = restUtil.get(url);

        Response responseXml = xmlService.parser(responseEntity.getBody());

        return new ResponseEntity<Response>(responseXml, HttpStatus.OK);
    }

    @PostMapping("/virus")
    public <T> ResponseEntity<?> callApi() {
        LocalDate targetStartDate;
        LocalDate targetEndDate;

        LocalDate localDate = LocalDate.now();

        targetStartDate = localDate.plusDays(-1);
        targetEndDate = localDate.plusDays(0);

        String url = apiService.getApiUrl(targetStartDate, targetEndDate);
        
        ResponseEntity<String> responseEntity = restUtil.get(url);

        Response responseXml = xmlService.parser(responseEntity.getBody());

        String msg = xmlService.getSlackMsg(responseXml);

        slackService.sendMsg(msg);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

