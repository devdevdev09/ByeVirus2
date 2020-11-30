package heo.dae.byevirus2.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Value("${service.key}")
    public String SERVICE_KEY;

    @Value("${api.endpoint}")
    String END_POINT;

    @Value("${api.name}")
    String API_NAME;

    public String getApiUrl(LocalDate targetStartDate
                          , LocalDate targetEndDate) {

        String startDate = targetStartDate.getYear() + "" + targetStartDate.getMonthValue() + "" + targetStartDate.getDayOfMonth();
        String endDate = targetEndDate.getYear() + "" + targetEndDate.getMonthValue() + "" + targetEndDate.getDayOfMonth();
        
        String url = END_POINT + API_NAME 
                    + "?serviceKey=" + SERVICE_KEY 
                    + "&startCreateDt=" + startDate 
                    + "&endCreateDt=" + endDate;
        
        return url;
    }
}
