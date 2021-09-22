package zju.bs.mqtt;

import com.alibaba.fastjson.JSONObject;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.InitializingBean;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zju.bs.service.IOTMessageService;


@Component
public class MsgReceiver implements InitializingBean {
    @Autowired
    private IOTMessageService iotMessageService;

    @Override
    public void afterPropertiesSet() throws Exception {
        (new ReceiveThread(iotMessageService)).start();
    }
}



