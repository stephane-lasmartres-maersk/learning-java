package com.example.loggingwithjava;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.core.LogEvent;

@Getter
@Setter
@ToString
class LogFormatter {
    @ToString.Exclude
    private String env;
    @ToString.Exclude
    private String hostName;
    @ToString.Exclude
    private String resourceGroup;
    @ToString.Exclude
    private String resourceName;
    @ToString.Exclude
    private String region;
    @ToString.Exclude
    private final String service;
    @ToString.Exclude
    private final String Application;
    @ToString.Exclude
    private String traceId;
    @ToString.Exclude
    private String spanId;
    @ToString.Exclude
    private String threadName;
    @ToString.Exclude
    private String logLevel;
    @ToString.Exclude
    private StringBuilder message=new StringBuilder();


    LogFormatter(LogEvent logEvent) {
        this.env = System.getenv("APP_ENV");
        this.hostName = System.getenv("APP_SERVICE");
        this.resourceGroup = System.getenv("APP_RG");
        this.resourceName = System.getenv("APP_RN");
        this.Application= System.getenv("APP_SERVICE");
        this.service= System.getenv("APP_SERVICE");
        this.region=System.getenv("APP_REGION");
        this.traceId = logEvent.getContextData().getValue("traceId");
        this.spanId = logEvent.getContextData().getValue("spanId");
        this.threadName = logEvent.getThreadName();
        this.logLevel = logEvent.getLevel().name();
        this.message.append("traceId : "+logEvent.getContextData().getValue("traceId"))
                .append(" , spanId : "+logEvent.getContextData().getValue("spanId"))
                .append(" , loggerName : " + logEvent.getLoggerName())
                .append(" , Source : " + logEvent.getSource().toString())
                .append(" , Message : " + logEvent.getMessage().getFormattedMessage());
    }

}