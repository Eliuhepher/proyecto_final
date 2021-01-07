package com.mycompany.mqttclient;

import java.util.UUID;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;


public class Main {
    public static MqttAsyncClient miCliente;
    public static final String USUARIO = "jcrfnjlc";
    public static final String PASSWORD = "xJEtX6UWhdtu";
    
    public static void main (String[] args) throws MqttException{
        
        miCliente = new MqttAsyncClient("tcp://driver.cloudmqtt.com:18982", UUID.randomUUID().toString());
        
        MqttConnectOptions conOpcs;
        conOpcs = setUpConnectionOptions(USUARIO, PASSWORD);
        
        Callback miCallback = new Callback();
        miCliente.setCallback(miCallback);
        
        IMqttToken token = miCliente.connect(conOpcs);
        token.waitForCompletion();
     
        miCliente.subscribe("mytopic/test", 0);
        
        
    }

    private static MqttConnectOptions setUpConnectionOptions(String USUARIO, String PASSWORD) {
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName(USUARIO);
        connOpts.setPassword(PASSWORD.toCharArray());
        return connOpts;
    }
    
}
