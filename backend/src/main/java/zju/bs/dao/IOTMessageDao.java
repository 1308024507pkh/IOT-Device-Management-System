package zju.bs.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import zju.bs.domain.IOTMessage;
import zju.bs.domain.MessageQuery;
import zju.bs.domain.UserDevice;

import java.util.ArrayList;
import java.util.List;

@Repository("iotMessageDao")
@Mapper
public interface IOTMessageDao {
    public int insertMessage(IOTMessage iotMessage);
    public List<IOTMessage> getMessages(String account);
    public List<IOTMessage> selectMessages(MessageQuery mq);
    public List<UserDevice> getDevices(String account);
    public int addDevice(UserDevice userDevice);
    public int updateDevice(UserDevice userDevice);
    public int deleteDevice(UserDevice userDevice);

    public UserDevice[] testDao2();
    public IOTMessage[] testDao();

}
