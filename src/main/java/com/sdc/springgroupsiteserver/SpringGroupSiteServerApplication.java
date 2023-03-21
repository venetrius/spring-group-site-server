package com.sdc.springgroupsiteserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class SpringGroupSiteServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringGroupSiteServerApplication.class, args);
    }
}
