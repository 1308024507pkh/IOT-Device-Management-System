package zju.bs.domain;

public class ReturnCode2 {
    private int code;
    private String message;
    private String token;

    public ReturnCode2(int code, String message, String token){
        this.code = code;
        this.message = message;
        this.token = token;
    }

    public int getCode(){return code;}
    public void setCode(int code){this.code = code;}
    public String getMessage(){return message;}
    public void setMessage(String message){this.message = message;}
    public String getToken(){return token;}
    public void setToken(String token){this.token = token;}
}
