package com.avanish.mcabot;

/**
 * Created by Avanish on 25-Jan-17.
 */

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Connection;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import static android.content.ContentValues.TAG;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Run extends AppCompatActivity{

    /*MqttClient client;

    public Run() {}

    public static void main(String[] args) {
        new Run().doDemo();
    }*/

    public void connect(){

        final MqttAndroidClient mqttAndroidClient = new MqttAndroidClient(this.getApplicationContext(), "tcp://test.mosquitto.org:1883", "androidSampleClient");
        try {
            mqttAndroidClient.connect(null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    System.out.println("Connection Success!");
                    try {
                        System.out.println("Subscribing to mcabot");
                        mqttAndroidClient.subscribe("mcabot", 0);
                        System.out.println("Subscribed to mcabot");
                        System.out.println("Publishing message..");
                        mqttAndroidClient.publish("mcabot", new MqttMessage("F".getBytes()));
                    } catch (MqttException ex) {

                    }
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    System.out.println("Connection Failure!");
                }
            });
        } catch (MqttException ex) {

        }
    }

    /*public void doDemo() {
        try {
            client = new MqttClient("test.mosquitto.org:1883", "pahomqttpublish1");
            client.connect();
            MqttMessage message = new MqttMessage();
            message.setPayload("A single message".getBytes());
            client.publish("pahodemo/test", message);
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }*/
}

/*public class Run{

    //Connect to the Broker Server
    //String clientId = MqttClient.generateClientId();
    //MqttAndroidClient client = new MqttAndroidClient(this.getApplicationContext(), "tcp://broker.hivemq.com:1883",clientId);
    MqttClient client;
    *//*public void connect() {

        client = new MqttClient("tcp://localhost:1883", "pahomqttpublish1");
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload("A single message".getBytes());
        client.publish("pahodemo/test", message);
        *//**//*try {
            final IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener()
            {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Log.d(TAG, "onSuccess");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d(TAG, "onFailure");

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }*//**//*
    }*//*
}*/
