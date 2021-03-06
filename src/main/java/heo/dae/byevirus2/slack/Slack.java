package heo.dae.byevirus2.slack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import heo.dae.byevirus2.utils.RestUtil;

@Component
public class Slack {

    @Autowired
    RestUtil restUtil;

    @Value("${slack.value.username}")
    String USER_NAME;

    @Value("${slack.value.hooks}")
    List<String> SLACK_HOOKS;

    public void send(String msg){
        try {
            Map<String, Object> req = new HashMap<String, Object>();
            req.put("text"    , msg);
            req.put("username", USER_NAME);

            for(String hook : SLACK_HOOKS){
                restUtil.post(hook, req);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
