package com.maersk.modules.main;

import java.util.ServiceLoader;

import com.maersk.modules.hello.*;

public class MainApp {
    public static void main(String[] args) {
        HelloModules.doSomething();

        Iterable<HelloInterface> services = ServiceLoader.load(HelloInterface.class);
        HelloInterface service = services.iterator().next();
        service.sayHello(); 
    }    
}
