package zju.bs.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import zju.bs.service.IOTMessageService;

public class ReceiveThread extends Thread {
    int qos = 2;
    String clientId = "receiver";
    String mqttServer = "tcp://localhost:1883";
    String topic = "testapp";
    IOTMessageService iotMessageService;

    public ReceiveThread(IOTMessageService iotMessageService){
        this.iotMessageService = iotMessageService;
    }

    public void run() {
        try {
            MemoryPersistence persistence = new MemoryPersistence();
            MqttClient mqttClient = new MqttClient(mqttServer, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(false);
            connOpts.setConnectionTimeout(10);
            connOpts.setKeepAliveInterval(20);
            MyPushCallBack myPushCallBack = new MyPushCallBack(iotMessageService);
            mqttClient.setCallback(myPushCallBack);
            mqttClient.connect(connOpts);
            int[] Qos  = {qos};
            String[] topics = {topic};
            mqttClient.subscribe(topics, Qos);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}
