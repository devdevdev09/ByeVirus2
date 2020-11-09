package heo.dae.byevirus2.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ApiService {
    @Value("${service.key}")
    public String SERVICE_KEY;

    @Value("${api.endpoint}")
    String END_POINT;

    @Value("${api.name}")
    String API_NAME;

    public String getApiUrl() throws UnsupportedEncodingException {
        String url = END_POINT + API_NAME + "?serviceKey=" + SERVICE_KEY + "&pageNo=1&numOfRows=10&startCreateDt=20200310&endCreateDt=20200315";

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(url)
        .queryParam("", URLEncoder.encode(SERVICE_KEY,"UTF-8"))
        .queryParam("pageNo", "1")
        .queryParam("numOfRows", "10")
        .queryParam("startCreateDt", "20200310")
        .queryParam("endCreateDt", "20200315");

        return url;
    }
}
