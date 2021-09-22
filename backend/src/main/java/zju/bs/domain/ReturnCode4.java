package zju.bs.domain;

import java.util.List;

public class ReturnCode4 {
    private int code;
    private String message;
    private List<UserDevice> data;
    private String account;

    public ReturnCode4(int code, String message, List<UserDevice> data, String account){
        this.code = code;
        this.message = message;
        this.data = data;
        this.account = account;
    }

    public int getCode(){ return code; }
    public void setCode(int code){ this.code = code; }
    public String getMessage(){ return message; }
    public void setMessage(String message){ this.message = message; }
    public List<UserDevice> getData(){ return data; }
    public void setData(List<UserDevice> data){ this.data = data; }
    public String getAccount(){ return account; }
    public void setAccount(String account){ this.account = account; }
}
