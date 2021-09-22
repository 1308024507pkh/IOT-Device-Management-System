package zju.bs.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.List;

//@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ReturnCode3 implements Serializable {
    private int code;
    private String message;
    private List<IOTMessage> data;
    private String account;

    public ReturnCode3(int code, String message, List<IOTMessage> data, String account){
        this.code = code;
        this.message = message;
        this.data = data;
        this.account = account;
    }

    public int getCode(){ return code; }
    public void setCode(int code){ this.code = code; }
    public String getMessage(){ return message; }
    public void setMessage(String message){ this.message = message; }
    public List<IOTMessage> getData(){ return data; }
    public void setData(List<IOTMessage> data){ this.data = data; }
    public String getAccount(){ return account; }
    public void setAccount(String account){ this.account = account; }
}
