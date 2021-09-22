package zju.bs.service;

import zju.bs.domain.IOTMessage;
import zju.bs.domain.MessageQuery;
import zju.bs.domain.UserDevice;

import java.util.List;

public interface IOTMessageService {
    public boolean addMsg(IOTMessage iotMessage);
    public List<IOTMessage> getMessages(String account);
    public List<IOTMessage> selectMessages(MessageQuery mq);
    public List<UserDevice> getDevices(String account);
    public int addDevice(UserDevice userDevice);
    public int updateDevice(UserDevice userDevice);
    public int deleteDevice(UserDevice userDevice);


    public IOTMessage[] testService();
    public UserDevice[] testService2();

}
