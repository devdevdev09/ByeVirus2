package heo.dae.byevirus2.slack;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import heo.dae.byevirus2.utils.RestUtil;

@Component
public class Slack {

    @Autowired
    RestUtil restClientUtil;

    String USER_NAME = "DailyCommit";

    public void send(String msg, String hooks){
        try {
            Map<String, Object> req = new HashMap<String, Object>();
            // req.put("text"    , msg);
            // req.put("username", USER_NAME);
            req.put("text", "tetetetet");
            String url = hooks;

            restClientUtil.post(url, HttpMethod.POST, req);

        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
