package heo.dae.byevirus2.service;

import org.springframework.stereotype.Service;

import heo.dae.byevirus2.slack.Slack;
import heo.dae.byevirus2.vo.Response;

@Service
public class SlackService {

    private Slack slack;

    public SlackService(Slack slack) {
        this.slack = slack;
    }

    public void sendMsg(Response response, String SLACK_URL) {
        String url = SLACK_URL;

        slack.send(url, url);
    }
}
