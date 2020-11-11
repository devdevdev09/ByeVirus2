package heo.dae.byevirus2.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
    @Value("${service.key}")
    public String SERVICE_KEY;

    @Value("${api.endpoint}")
    String END_POINT;

    @Value("${api.name}")
    String API_NAME;

    public String getApiUrl(Date targetStartDate
                          , Date targetEndDate
                          , String pageNo
                          , String numOfRows) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String startDate = format.format(targetStartDate);
        String endDate = format.format(targetEndDate);
        
        String url = END_POINT + API_NAME 
                    + "?serviceKey=" + SERVICE_KEY + "&pageNo=" + pageNo 
                    + "&numOfRows="+ numOfRows + "&startCreateDt=" + startDate 
                    + "&endCreateDt=" + endDate;

        return url;
    }
}
