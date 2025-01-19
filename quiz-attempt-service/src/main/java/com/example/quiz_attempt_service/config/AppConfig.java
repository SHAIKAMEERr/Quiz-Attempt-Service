package com.example.quiz_attempt_service.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
	
	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
	
    @Bean
    public DataSource dataSource() {
        logger.info("Configuring DataSource for the application.");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/quiz_service");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        logger.info("DataSource configured successfully.");
        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        logger.info("Configuring NamedParameterJdbcTemplate.");
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create(); // Optional for pretty printing
    }
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void configureMessageConverters(java.util.List<HttpMessageConverter<?>> converters) {
        converters.add(new GsonHttpMessageConverter(gson()));
    }
}

