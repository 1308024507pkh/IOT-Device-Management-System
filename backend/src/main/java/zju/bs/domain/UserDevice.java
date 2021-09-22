package zju.bs.domain;

public class UserDevice implements Comparable<UserDevice>{
    private String account;
    private String deviceId;
    private String deviceName;
    private String deviceInfo;

    public UserDevice(String account, String deviceId, String deviceName, String deviceInfo){
        this.account = account;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceInfo = deviceInfo;
    }
    public String getAccount(){ return account; }
    public void setAccount(String account){ this.account = account; }

    public String getDeviceId(){ return deviceId; }
    public void setDeviceId(String deviceId){ this.deviceId = deviceId; }

    public String getDeviceName(){ return deviceName; }
    public void setDeviceName(String deviceName){ this.deviceName = deviceName; }

    public String getDeviceInfo(){ return deviceInfo; }
    public void setDeviceInfo(String deviceInfo){ this.deviceInfo = deviceInfo; }

    public String toString(){
        return account+" "+deviceId+" "+deviceName+" "+deviceInfo;
    }

    public int compareTo(UserDevice userDevice){
        if(this.account.equals(userDevice.account)){
            return this.deviceId.compareTo(userDevice.deviceId);
        }
        else    return this.account.compareTo(userDevice.account);
    }
}
