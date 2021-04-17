package com.bookingsroom.irfaan.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // berfungsi untuk menampung beans(Global" Variable Object pada kode)
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // DIGUNAKAN UNTUK BENAR-BENAR MEMBUAT NAMA FIELD OBJEKNYA HARUS SAMA
        return modelMapper;
    }

}

