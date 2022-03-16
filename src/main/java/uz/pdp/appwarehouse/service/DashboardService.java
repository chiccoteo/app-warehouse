package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.repo.InputProductRepo;


@Service
public class DashboardService {

    @Autowired
    InputProductRepo inputProductRepo;



}
