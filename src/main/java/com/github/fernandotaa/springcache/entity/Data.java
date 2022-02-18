package com.github.fernandotaa.springcache.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
public class Data {

    private LocalDateTime timestamp;
    private Integer number;

}
