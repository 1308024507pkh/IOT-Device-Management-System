package zju.bs.domain;

public class DeviceConfig {
    private String token;
    private String deviceId;
    private String deviceName;
    private String deviceInfo;
/*
    public DeviceConfig(String token, String deviceId, String deviceName, String deviceInfo){
        this.token = token;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceInfo = deviceInfo;
    }
*/
    public String getToken(){ return token; }
    public void setToken(String token){ this.token = token; }

    public String getDeviceId(){ return deviceId; }
    public void setDeviceId(String deviceId){ this.deviceId = deviceId; }

    public String getDeviceName(){ return deviceName; }
    public void setDeviceName(String deviceName){ this.deviceName = deviceName; }

    public String getDeviceInfo(){ return deviceInfo; }
    public void setDeviceInfo(String deviceInfo){ this.deviceInfo = deviceInfo; }
}
