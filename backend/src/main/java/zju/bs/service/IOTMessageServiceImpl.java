package zju.bs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zju.bs.dao.IOTMessageDao;
import zju.bs.domain.IOTMessage;
import zju.bs.domain.MessageQuery;
import zju.bs.domain.UserDevice;

import java.util.List;

@Service("iotMessageService")
public class IOTMessageServiceImpl implements IOTMessageService{

    @Autowired
    private IOTMessageDao iotMessageDao;

    public boolean addMsg(IOTMessage iotMessage) {
        try {
            int num = iotMessageDao.insertMessage(iotMessage);
            if(num>0)   return true;
            else        return false;
        } catch(Exception e){
            return false;
        }
    }

    public List<IOTMessage> getMessages(String account){
        try {
            List<IOTMessage> iotMessages = iotMessageDao.getMessages(account);
            return iotMessages;
        } catch(Exception e){
            return null;
        }
    }

    public List<IOTMessage> selectMessages(MessageQuery mq){
        try {
            List<IOTMessage> iotMessages = iotMessageDao.selectMessages(mq);
            return iotMessages;
        } catch(Exception e){
            return null;
        }
    }

    public List<UserDevice> getDevices(String account){
        try {
            List<UserDevice> devices = iotMessageDao.getDevices(account);
            return devices;
        } catch(Exception e){
            return null;
        }
    }

    public int addDevice(UserDevice userDevice){
        List<UserDevice> devices = getDevices(userDevice.getAccount());
        for(UserDevice device:devices){
            if(device.getDeviceId().equals(userDevice.getDeviceId()))    return 1;
            if(device.getDeviceName().equals(userDevice.getDeviceName()))    return 2;
        }
        try{
            iotMessageDao.addDevice(userDevice);
            return 0;
        } catch(Exception e){
            return 3;
        }
    }

    public int updateDevice(UserDevice userDevice){
        int flag = 0;
        List<UserDevice> devices = getDevices(userDevice.getAccount());
        for(UserDevice device:devices){
            if(device.getDeviceId().equals(userDevice.getDeviceId()))    flag = 1;
            else if(device.getDeviceName().equals(userDevice.getDeviceName()))    return 2;
        }
        if(flag==0)     return 1;
        try{
            iotMessageDao.updateDevice(userDevice);
            return 0;
        } catch(Exception e){
            return 3;
        }
    }

    public int deleteDevice(UserDevice userDevice){
        int flag = 0;
        List<UserDevice> devices = getDevices(userDevice.getAccount());
        for(UserDevice device:devices){
            if(device.getDeviceId().equals(userDevice.getDeviceId())){
                flag = 1;
                break;
            }
        }
        if(flag == 0)   return 1;
        try{
            iotMessageDao.deleteDevice(userDevice);
            return 0;
        } catch(Exception e){
            return 2;
        }
    }

    public UserDevice[] testService2() { return iotMessageDao.testDao2(); }
    public IOTMessage[] testService(){
        return iotMessageDao.testDao();
    }
}
