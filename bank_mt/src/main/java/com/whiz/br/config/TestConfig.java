package com.whiz.br.config;

import com.whiz.br.service.DBService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@RequiredArgsConstructor
@Configuration
@Profile("test")
public class TestConfig {

    private final DBService dbService;

    @Bean
    public boolean instatiateDatabase(){
        dbService.instatiateTestDataBase();
        return true;
    }
}
