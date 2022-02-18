package com.github.fernandotaa.springcache.controller;

import com.github.fernandotaa.springcache.entity.Data;
import com.github.fernandotaa.springcache.service.RandomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RandomController {

    private final RandomService randomService;

    @GetMapping
    public Data get() {
        return randomService.getData();
    }

}
