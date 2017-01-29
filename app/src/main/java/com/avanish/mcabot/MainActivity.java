package com.avanish.mcabot;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

//import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.graphics.Typeface;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //final MqttAndroidClient mqttAndroidClient = new MqttAndroidClient(this.getApplicationContext(), "tcp://test.mosquitto.org:1883", "androidSampleClient");

    public void controller(View view) {

        final RadioButton run = (RadioButton) findViewById(R.id.radioButton);
        final RadioButton stop = (RadioButton) findViewById(R.id.radioButton2);
        Button forward = (Button) findViewById(R.id.button);
        Button left = (Button) findViewById(R.id.button2);
        Button right = (Button) findViewById(R.id.button3);
        Button back = (Button) findViewById(R.id.button4);
        //final Run runn = new Run();

        final boolean checked = ((RadioButton) view).isChecked(); //is the radio button checked
        final MqttAndroidClient mqttAndroidClient = new MqttAndroidClient(this.getApplicationContext(), "tcp://test.mosquitto.org:1883", "androidSampleClient");
        //final Control control = new Control();

        switch (view.getId())
        {
            case R.id.radioButton:
                if (checked) {
                    run.setTypeface(null, Typeface.BOLD_ITALIC);
                    stop.setChecked(false);
                    //runn.connect();
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
                                    mqttAndroidClient.publish("mcabot", new MqttMessage("Welcome to mcabot control".getBytes()));

                                } catch (MqttException ex) {
                                }
                            }

                            @Override
                            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                                System.out.println("Connection Failure!");
                            }
                        });
                    } catch (MqttException ex) {}
                }
                break;

            /*case R.id.radioButton2:
                if (checked) {
                    stop.setTypeface(null, Typeface.BOLD_ITALIC);
                    run.setChecked(false);

                }
                break;*/
            //default: break;
        }

       /* run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run.setTypeface(null, Typeface.BOLD_ITALIC);
                stop.setChecked(false);

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
                                mqttAndroidClient.publish("mcabot", new MqttMessage("Welcome to mcabot control".getBytes()));

                            } catch (MqttException ex) {
                            }
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            System.out.println("Connection Failure!");
                        }
                    });
                } catch (MqttException ex) {}
            }
        });*/


        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop.setTypeface(null, Typeface.BOLD_ITALIC);
                run.setChecked(false);
                System.out.println("Stop pressed");
                try {mqttAndroidClient.publish("mcabot", new MqttMessage("remote:s".getBytes()));}
                catch (MqttException ex) {}
            }
        });


        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(run.isChecked())
                {
                    System.out.println("Fwd pressed");
                    try {mqttAndroidClient.publish("mcabot", new MqttMessage("remote:f".getBytes()));}
                    catch (MqttException ex) {}
                }
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(run.isChecked())
                {
                    System.out.println("Left pressed");
                    try {mqttAndroidClient.publish("mcabot", new MqttMessage("remote:l".getBytes()));}
                    catch (MqttException ex) {}
                }
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(run.isChecked())
                {
                    System.out.println("Right pressed");
                    try {mqttAndroidClient.publish("mcabot", new MqttMessage("remote:r".getBytes()));}
                    catch (MqttException ex) {}
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(run.isChecked())
                {
                    System.out.println("Back pressed");
                    try {mqttAndroidClient.publish("mcabot", new MqttMessage("remote:b".getBytes()));}
                    catch (MqttException ex) {}
                }
            }
        });
    }
}



