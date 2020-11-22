package heo.dae.byevirus2.controller;

import java.time.LocalDate;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<HttpStatus> callApi(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyyMMdd") 
                            LocalDate startDate
                          , @RequestParam(required = false) @DateTimeFormat(pattern = "yyyyMMdd") 
                            LocalDate endDate) {

        LocalDate targetStartDate;
        LocalDate targetEndDate;

        LocalDate localDate = LocalDate.now();

        if (startDate == null || endDate == null) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            targetStartDate = localDate.plusDays(-3);

            cal.add(Calendar.DATE, -2);
            targetEndDate = localDate.plusDays(-1);
        } else {
            targetStartDate = startDate;
            targetEndDate = endDate;
        }

        String url = apiService.getApiUrl(targetStartDate, targetEndDate);
        ResponseEntity<String> response = restUtil.get(url);
        Response responseXml = xmlService.parser(response.getBody());

        String msg = xmlService.getSlackMsg(responseXml);

        slackService.sendMsg(msg);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

