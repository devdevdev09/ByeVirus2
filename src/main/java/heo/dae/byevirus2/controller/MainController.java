package heo.dae.byevirus2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class MainController {
    @Value("${service.key}")
    public String servicekey;

    @RequestMapping("/test")
    public void test(){
        System.out.println("serviceKey : " + servicekey);
    }
}
