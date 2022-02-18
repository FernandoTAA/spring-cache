package com.github.fernandotaa.springcache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;
import java.util.Map;

public interface JsonPolymorphicUtils {

   static ObjectMapper jsonPolymorphicMapper() {
        PolymorphicTypeValidator polymorphicTypeValidator = BasicPolymorphicTypeValidator
            .builder()
            .allowIfSubType("com.github.fernandotaa.springcache.")
            .allowIfSubType(List.class)
            .allowIfSubType(Map.class)
            .allowIfSubType(Number.class)
            .build();

        var mapper = new JsonMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(polymorphicTypeValidator, ObjectMapper.DefaultTyping.NON_FINAL);

        return mapper;
    }

}
