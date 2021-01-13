package heo.dae.byevirus2.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import heo.dae.byevirus2.service.ApiService;

@Controller
public class ScheduleController {
    private ApiService apiService;

    public ScheduleController(ApiService apiService) {
        this.apiService = apiService;
    }

    @Scheduled(cron = "0 */5 9,10 * * *")
    public void callApi(){
        apiService.callApi();
    }

}
