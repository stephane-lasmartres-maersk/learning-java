package com.example.dependencyinjection.services;

import com.example.dependencyinjection.interfaces.IWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    
    private IWriter writer;

    @Autowired
    public void setWriter(IWriter writer) {
        this.writer = writer;
    }

    public void run(String text) {
        writer.write(text);
    }
}
