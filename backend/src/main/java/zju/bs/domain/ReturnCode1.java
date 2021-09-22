package zju.bs.domain;

public class ReturnCode1 {
    private int code;
    private String message;

    public ReturnCode1(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode(){return code;}
    public void setCode(int code){this.code = code;}
    public String getMessage(){return message;}
    public void setMessage(String message){this.message = message;}

}
