package com.github.pnowy.spring.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Index {

    @GetMapping("/")
    public Map<String, String> index() {
        return Map.of("message", "hello-api");
    }

}
