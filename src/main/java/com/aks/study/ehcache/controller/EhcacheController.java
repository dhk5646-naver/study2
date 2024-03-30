package com.aks.study.ehcache.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EhcacheController {

    @GetMapping("/ehcache")
    public String getEhcache(@RequestParam String key) {
        return key;
    }
}
