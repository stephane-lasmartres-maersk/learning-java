package com.example.loggingwithjava;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.eventhubs.EventHubException;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.IOException;
import java.io.Serializable;

import java.nio.charset.Charset;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Plugin(name = "EventHub", category = "Core", elementType = "appender", printObject = true)
public final class EventHubLogAppender extends AbstractAppender {

    private ScheduledExecutorService executorService;
    private EventHubClient ehClient;
    private static int threadSize;
    private static String eventHubStr;

    private EventHubLogAppender(
            final String name,
            final Filter filter,
            final Layout<? extends Serializable> layout) {
        super(name, filter, layout);
    }

    @PluginFactory
    public static EventHubLogAppender createAppender(
            @Required(message = "Provide a Name for EventHubs Log4j Appender") @PluginAttribute("name") final String name,
            @PluginElement("Filter") final Filter filter,
            @PluginElement("Layout") final Layout<? extends Serializable> layout,
            @Required(message = "Provide EventHub connection string to append the events to") @PluginAttribute("eventHubConnectionString") final String connectionString,
            @PluginAttribute(value = "threadSize", defaultInt = 4) int size) {
        eventHubStr = connectionString;
        threadSize = size;
        return new EventHubLogAppender(name, filter, (PatternLayout) layout);
    }

    @Override
    public void append(LogEvent logEvent) {

        try {
            byte[] payloadBytes = EventHubLogAppender.formattedLog(logEvent).getBytes(Charset.defaultCharset());
            EventData sendEvent = EventData.create(payloadBytes);
            ehClient.sendSync(sendEvent);
        } catch (EventHubException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        super.start();
        executorService = Executors.newScheduledThreadPool(threadSize);
        try {
            ehClient = EventHubClient.createFromConnectionStringSync(eventHubStr, executorService);
        } catch (EventHubException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stop() {
        super.stop();
        try {
            ehClient.closeSync();
        } catch (EventHubException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    private static String formattedLog(LogEvent logEvent) throws JsonProcessingException {
        LogFormatter logFormatter = new LogFormatter(logEvent);
        return new Gson().toJson(logFormatter);
    }

}