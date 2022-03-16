package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appwarehouse.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;



}
