package com.peliculas.peliculas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // Configuración del módulo para resolver la referencia cíclica
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Object.class, new CustomDeserializer());
        objectMapper.registerModule(module);

        return objectMapper;
    }
}
