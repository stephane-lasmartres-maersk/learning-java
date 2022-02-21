package com.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusErrorContext;
import com.azure.messaging.servicebus.ServiceBusException;
import com.azure.messaging.servicebus.ServiceBusFailureReason;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;

public class ServiceBusClient {
    
    private String connectionString;
    private String queueName;

    private ServiceBusProcessorClient processorClient;

    public ServiceBusClient(String connectionString, String queueName) {
        this.connectionString = connectionString;
        this.queueName = queueName;
    }

    public void receiveMessages() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        processorClient = new ServiceBusClientBuilder()
            .connectionString(connectionString)
            .processor()
            .queueName(queueName)
            .processMessage(context -> processMessage(context))
            .processError(context -> processError(context, countDownLatch))
            .buildProcessorClient();

        System.out.println("Starting the processor");
        processorClient.start();
    }

    public void close() {
        System.out.println("Stopping and closing the processor");
        processorClient.close();
    }

    private void processMessage(ServiceBusReceivedMessageContext context) {
        ServiceBusReceivedMessage message = context.getMessage();
        System.out.printf("Processing message. Session %s, Sequence #: %s, Contents: %s%n",
            message.getMessageId(),
            message.getSequenceNumber(),
            message.getBody());
    }

    private void processError(ServiceBusErrorContext context, CountDownLatch countDownLatch) {
        System.out.printf("Error when receiving messages from namesoace: '%s'. Entity: '%s'%n",
            context.getFullyQualifiedNamespace(), context.getEntityPath());
        
        if(!(context.getException() instanceof ServiceBusException)) {
            System.out.printf("Non-ServiceBusException occured: %s%n", 
                context.getException());
            return;
        }

        ServiceBusException exception = (ServiceBusException)context.getException();
        ServiceBusFailureReason reason = exception.getReason();

        if(reason == ServiceBusFailureReason.MESSAGING_ENTITY_DISABLED
        || reason == ServiceBusFailureReason.MESSAGING_ENTITY_NOT_FOUND
        || reason == ServiceBusFailureReason.UNAUTHORIZED) {
            System.out.printf("An unrecoverable error occured. Stopping processing with reason %s: %s%n",
                reason, exception.getMessage());

            countDownLatch.countDown();
            
        } else if(reason == ServiceBusFailureReason.MESSAGE_LOCK_LOST) {
            System.out.printf("Message lock lost for message: %s%n", 
                context.getException());                

        } else if(reason == ServiceBusFailureReason.SERVICE_BUSY) {
            try{
                TimeUnit.SECONDS.sleep(1);

            } catch(InterruptedException e) {
                System.err.println("Unable to sleep for period of time");
            }

        } else {
            System.out.printf("Error source %s, reason %s, message %s%n",
                context.getErrorSource(),
                reason,
                exception);
        }
    }
}
