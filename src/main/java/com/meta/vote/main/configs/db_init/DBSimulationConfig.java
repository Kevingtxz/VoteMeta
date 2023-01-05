package com.meta.vote.main.configs.db_init;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBSimulationConfig {


    @Autowired
    private DBService dbService;


    @Bean
    public boolean instantiateDatabase() {
        dbService.instantiateTestDatabase();
        return true;
    }

}