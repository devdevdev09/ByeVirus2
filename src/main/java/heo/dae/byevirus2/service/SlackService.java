package heo.dae.byevirus2.service;

import org.springframework.stereotype.Service;

import heo.dae.byevirus2.slack.Slack;

@Service
public class SlackService {

    private Slack slack;

    public SlackService(Slack slack) {
        this.slack = slack;
    }

    public void sendMsg(String msg) {
        slack.send(msg);
    }
}
