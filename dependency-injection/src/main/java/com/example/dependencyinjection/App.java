package com.example.dependencyinjection;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.dependencyinjection.configuration.Config;
import com.example.dependencyinjection.services.MyService;
import com.example.dependencyinjection.services.Writer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        MyService springBean = context.getBean(MyService.class);
        springBean.run("let's write some text using our service build using dependency injection"); 
        context.close();
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args -> {
            MyService service = new MyService();
            service.setWriter(new Writer());
    
            service.run("let's write some text to console output");    
        };
    } 
}
