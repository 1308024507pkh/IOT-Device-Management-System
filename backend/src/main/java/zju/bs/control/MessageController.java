package zju.bs.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zju.bs.domain.*;
import zju.bs.service.IOTMessageService;
import zju.bs.token.JwtUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//ww22l1

@Controller
public class MessageController {
    @Autowired
    private IOTMessageService iotMessageService;

    @CrossOrigin
    @RequestMapping(value = "/getAccount", method = RequestMethod.POST)
    @ResponseBody
    public String getAccount(@RequestBody MessageQuery mq){
        String token = mq.getToken();
        //System.out.println(token);
        String account = JwtUtil.getAccount(token);
        return account;
    }

    @CrossOrigin
    @RequestMapping(value = "/getMessage", method = RequestMethod.POST)
    @ResponseBody
    public ReturnCode3 getMessages(@RequestBody MessageQuery mq){
        String token = mq.getToken();
        //System.out.println(token);
        String account = JwtUtil.getAccount(token);
        System.out.println(account);
        List<IOTMessage> iotMessage = iotMessageService.getMessages(account);
        if(iotMessage.size()!=0 && iotMessage.size()>0)Collections.sort(iotMessage);
        else if(iotMessage==null)   iotMessage = new ArrayList<IOTMessage>();
        //System.out.println(iotMessage.get(0));
        return new ReturnCode3(200, "成功获取数据！", iotMessage, account);
    }

    @CrossOrigin
    @RequestMapping(value = "/selectMessage", method = RequestMethod.POST)
    @ResponseBody
    public ReturnCode3 selectMessages(@RequestBody MessageQuery mq){
        String account = JwtUtil.getAccount(mq.getToken());
        mq.setToken(account);
        System.out.println(mq);
        List<IOTMessage> iotMessage = iotMessageService.selectMessages(mq);
        if(iotMessage!=null && iotMessage.size()>0)Collections.sort(iotMessage);
        else if(iotMessage == null) iotMessage = new ArrayList<IOTMessage>();
        return new ReturnCode3(200, "成功获取筛选数据！", iotMessage, account);
    }

    @CrossOrigin
    @RequestMapping(value = "/getDevice", method = RequestMethod.POST)
    @ResponseBody
    public ReturnCode4 getDevices(@RequestBody DeviceConfig deviceConfig){
        String token = deviceConfig.getToken();
        String account = JwtUtil.getAccount(token);
        List<UserDevice> devices = iotMessageService.getDevices(account);
        if(devices!=null && devices.size()>0)Collections.sort(devices);
        else if(devices==null)  devices = new ArrayList<UserDevice>();
        return new ReturnCode4(200, "成功获取数据！", devices, account);
    }

    @CrossOrigin
    @RequestMapping(value = "/addDevice", method = RequestMethod.POST, produces={"text/html;charset=UTF-8;","application/json;"})
    @ResponseBody
    public ReturnCode4 addDevice(@RequestBody DeviceConfig deviceConfig){
        String account = JwtUtil.getAccount(deviceConfig.getToken());
        String deviceId = deviceConfig.getDeviceId();
        String deviceName = deviceConfig.getDeviceName();
        String deviceInfo = deviceConfig.getDeviceInfo();
        UserDevice userDevice = new UserDevice(account, deviceId, deviceName, deviceInfo);
        System.out.println(userDevice);
        int res = iotMessageService.addDevice(userDevice);
        if(res == 0){
            List<UserDevice> devices = iotMessageService.getDevices(account);
            Collections.sort(devices);
            return new ReturnCode4(200, "设备创建成功！",devices, account);
        }
        else if(res == 1){
            List<UserDevice> devices = iotMessageService.getDevices(account);
            Collections.sort(devices);
            return new ReturnCode4(201, "创建失败：该设备id已存在！", devices, account);
        }
        else if(res == 2){
            List<UserDevice> devices = iotMessageService.getDevices(account);
            Collections.sort(devices);
            return new ReturnCode4(201, "创建失败：该设备名称已存在！", devices, account);
        }
        else{
            List<UserDevice> devices = iotMessageService.getDevices(account);
            Collections.sort(devices);
            return new ReturnCode4(201, "创建失败：未知错误！", devices, account);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/updateDevice", method = RequestMethod.POST)
    @ResponseBody
    public ReturnCode4 updateDevice(@RequestBody DeviceConfig deviceConfig){
        String account = JwtUtil.getAccount(deviceConfig.getToken());
        String deviceId = deviceConfig.getDeviceId();
        String deviceName = deviceConfig.getDeviceName();
        String deviceInfo = deviceConfig.getDeviceInfo();
        UserDevice userDevice = new UserDevice(account, deviceId, deviceName, deviceInfo);
        int res = iotMessageService.updateDevice(userDevice);
        if(res == 0){
            List<UserDevice> devices = iotMessageService.getDevices(account);
            Collections.sort(devices);
            return new ReturnCode4(200, "设备信息修改成功！",devices, account);
        }
        else if(res == 1){
            List<UserDevice> devices = iotMessageService.getDevices(account);
            Collections.sort(devices);
            return new ReturnCode4(201, "修改失败：该设备id不存在！", devices, account);
        }
        else if(res == 2){
            List<UserDevice> devices = iotMessageService.getDevices(account);
            Collections.sort(devices);
            return new ReturnCode4(201, "修改失败：该设备名称已存在！", devices, account);
        }
        else{
            List<UserDevice> devices = iotMessageService.getDevices(account);
            Collections.sort(devices);
            return new ReturnCode4(201, "修改失败：未知错误！", devices, account);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteDevice", method = RequestMethod.POST)
    @ResponseBody
    public ReturnCode4 deleteDevice(@RequestBody DeviceConfig deviceConfig){
        String account = JwtUtil.getAccount(deviceConfig.getToken());
        String deviceId = deviceConfig.getDeviceId();
        UserDevice userDevice = new UserDevice(account, deviceId, "", "");
        int res = iotMessageService.deleteDevice(userDevice);
        if(res == 0){
            List<UserDevice> devices = iotMessageService.getDevices(account);
            Collections.sort(devices);
            return new ReturnCode4(200, "设备删除成功！",devices,account);
        }
        else if(res == 1){
            List<UserDevice> devices = iotMessageService.getDevices(account);
            Collections.sort(devices);
            return new ReturnCode4(201, "删除失败：该设备id不存在！", devices, account);
        }
        else{
            List<UserDevice> devices = iotMessageService.getDevices(account);
            Collections.sort(devices);
            return new ReturnCode4(201, "删除失败：未知错误！", devices,account);
        }
    }



    @RequestMapping("/test2")
    @ResponseBody
    public IOTMessage[] test2(){
        return iotMessageService.testService();
    }

    @RequestMapping("/test3")
    @ResponseBody
    public UserDevice[] test3(){ return iotMessageService.testService2();}
}
