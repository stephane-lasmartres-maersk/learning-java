package com.example.dependencyinjection.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.example.dependencyinjection.services" })
public class Config {    
}
