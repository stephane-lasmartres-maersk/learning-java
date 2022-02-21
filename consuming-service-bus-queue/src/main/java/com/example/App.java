package com.example;

import java.util.concurrent.TimeUnit;

public class App 
{
    static String connectionString = "<put proper service bus connection string there>";
    static String queueName = "report-user-requests-stephane";   

    public static void main( String[] args ) throws InterruptedException
    {
        ServiceBusClient client = new ServiceBusClient(connectionString, queueName);
        client.receiveMessages();

        TimeUnit.SECONDS.sleep(10);
        client.close();

    }   
}
