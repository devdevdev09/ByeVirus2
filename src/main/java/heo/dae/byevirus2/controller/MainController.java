package heo.dae.byevirus2.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import heo.dae.byevirus2.service.ApiService;
import heo.dae.byevirus2.service.XmlService;
import heo.dae.byevirus2.utils.RestUtil;
import heo.dae.byevirus2.vo.Response;

@RestController
@RequestMapping("/api")
public class MainController {
    private ApiService apiService;
    private XmlService xmlService;

    @Autowired
    RestUtil restUtil;

    @Value("${slack.value.hooks}")
    String SLACK_URL;

    public MainController(ApiService apiService, XmlService xmlService) {
        this.apiService = apiService;
        this.xmlService = xmlService;
    }

    @GetMapping("/virus")
    public Response callApi(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyyMMdd") Date startDate

            , @RequestParam(required = false) @DateTimeFormat(pattern = "yyyyMMdd") Date endDate

            , @RequestParam(required = false, defaultValue = "1") String pageNo

            , @RequestParam(required = false, defaultValue = "10") String numOfRows) {

        Date targetStartDate;
        Date targetEndDate;

        if (startDate == null || endDate == null) {
            targetStartDate = new Date();
            targetEndDate = new Date();
        } else {
            targetStartDate = startDate;
            targetEndDate = startDate;
        }

        String url = apiService.getApiUrl(targetStartDate, targetEndDate, pageNo, numOfRows);

        ResponseEntity<String> response = null;
        Response responseXml = null;
        try {
            response = restUtil.get(url);
            responseXml = xmlService.parser(response.getBody());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        return responseXml;
    }
}

