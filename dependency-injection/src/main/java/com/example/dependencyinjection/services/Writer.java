package com.example.dependencyinjection.services;

import com.example.dependencyinjection.interfaces.IWriter;

import org.springframework.stereotype.Service;

@Service
public class Writer implements IWriter {
    @Override
    public void write(String text) {
        System.out.println(text);
    }    
}
