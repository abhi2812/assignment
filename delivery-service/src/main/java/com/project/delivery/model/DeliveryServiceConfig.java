package com.project.delivery.model;

import org.omg.PortableInterceptor.INACTIVE;

public class DeliveryServiceConfig {

    String host;

    Integer port;

    public DeliveryServiceConfig(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

}
