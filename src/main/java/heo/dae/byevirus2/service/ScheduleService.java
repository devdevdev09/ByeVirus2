package heo.dae.byevirus2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    // @Scheduled(cron = "0 */5 9,10 * * *")
    public void callApi(){
        
    }

}
