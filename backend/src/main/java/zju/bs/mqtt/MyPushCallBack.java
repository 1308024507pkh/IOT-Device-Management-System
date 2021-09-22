package zju.bs.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import zju.bs.domain.IOTMessage;
import zju.bs.service.IOTMessageService;

public class MyPushCallBack implements MqttCallback {
    IOTMessageService iotMessageService;
    public MyPushCallBack(IOTMessageService iotMessageService){
        this.iotMessageService = iotMessageService;
    }

    public void connectionLost(Throwable cause) {
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("接收消息主题 : " + topic);
        System.out.println("接收消息Qos : " + message.getQos());
        System.out.println("接收消息内容 : " + new String(message.getPayload()));
        String ori_msg = new String(message.getPayload());
        JSONObject msgJson = JSONObject.parseObject(ori_msg);
        IOTMessage iotMessage = JSONObject.toJavaObject(msgJson,IOTMessage.class);
        boolean res = iotMessageService.addMsg(iotMessage);
        if(res==true)   System.out.println("insert成功");
        else            System.out.println("insert失败");
        System.out.println();
    }

}
