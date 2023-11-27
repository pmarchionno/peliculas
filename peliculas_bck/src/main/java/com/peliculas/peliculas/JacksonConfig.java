package com.peliculas.peliculas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Configuration
public class JacksonConfig {
    // @Bean
    // public ObjectMapper objectMapper() {
    //     // ObjectMapper objectMapper = new ObjectMapper();
    //     // objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    //     // // Filtra propiedades para mostrar solo id y name en la relación actores
    //     // SimpleFilterProvider filterProvider = new SimpleFilterProvider();
    //     // // filterProvider.addFilter("actorFilter", SimpleBeanPropertyFilter.filterOutAllExcept("id", "name"));
    //     // objectMapper.setFilterProvider(filterProvider);

    //     // return objectMapper;

    //     ObjectMapper objectMapper = new ObjectMapper();
    //     objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    //     return objectMapper;
    // }

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
