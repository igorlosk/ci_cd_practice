package com.loskutnikov.ci_cd.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class DeployStart {

    @GetMapping("/")
    public String deploy() {
        return "Деплой успешно завершен!";
    }
}
