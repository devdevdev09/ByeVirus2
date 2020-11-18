package heo.dae.byevirus2.utils;

import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestUtil{

    @Bean
    public RestTemplate restTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        TrustStrategy acceptingTrustStrategy = (new TrustStrategy() {

            // ssl 인증 무시
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                // TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String
                // authType) -> true;
                return true;
            }
        });

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        return restTemplate;
    }


    public ResponseEntity<String> get(String url) throws Exception {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate().exchange(URI.create(url), HttpMethod.GET, entity, String.class);

        return response;
    }

    
    public <T> void post(String url, HttpMethod method, Map<String,Object> req) throws Exception {
        URI uri = URI.create(url);
        restTemplate().postForLocation(uri, req);
    }

    /**
     * 
     * @param <T>
     * @param url
     * @param req
     * @param type
     * @return api 호출 결과 상태 반환
     * @throws Exception
     */
    public <T> int post(String url, Map<String, String> body, HttpHeaders headers) throws Exception {
        HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String, String>>(body, headers);

        ResponseEntity<String> response = restTemplate().exchange(url, HttpMethod.POST, entity, String.class);

        int status = response.getStatusCodeValue();

        return status;
    }

    // x-www-urlencoded
    public <T> int post2(String url, Map<String, Object> body, HttpHeaders headers) throws Exception {
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();

        Map<String, String> map = new HashMap<String, String>();
        map.put("object_type", "text");
        map.put("text", "text");
        map.put("object_type", "text");

        form.add("template_object", map);

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(form, headers);

        restTemplate().postForLocation(url, entity);

        return 1;
    }


}