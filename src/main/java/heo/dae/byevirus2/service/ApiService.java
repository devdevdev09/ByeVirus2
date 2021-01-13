package heo.dae.byevirus2.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import heo.dae.byevirus2.utils.RestUtil;

@Service
public class ApiService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Value("${service.key}")
    public String SERVICE_KEY;

    @Value("${api.endpoint}")
    String END_POINT;

    @Value("${api.name}")
    String API_NAME;

    private DateService dateService;
    private RestUtil restUtil;

    public ApiService(DateService dateService, RestUtil restUtil){
        this.dateService = dateService;
        this.restUtil = restUtil;
    }

    public String getApiUrl(LocalDate targetStartDate
                          , LocalDate targetEndDate) {

        String startDate = targetStartDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String endDate = targetEndDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        
        String url = END_POINT + API_NAME 
                    + "?serviceKey=" + SERVICE_KEY 
                    + "&startCreateDt=" + startDate 
                    + "&endCreateDt=" + endDate;
        
        return url;
    }

    public ResponseEntity<String> callApi(){
        String url = getApiUrl(dateService.getStartDate(), dateService.getEndDate());
        
        ResponseEntity<String> responseEntity = restUtil.get(url);

        return responseEntity;
    }
}
