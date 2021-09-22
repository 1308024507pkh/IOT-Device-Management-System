package zju.bs.domain;

public class MessageQuery {
    String token;
    long beginTime;
    long endTime;
    String clientId;
    int alert;
    int highValue;
    int lowValue;


    public String toString(){
        //highValue = 2147483647;
        return "" + beginTime +" "+endTime+" "+clientId+" "+alert+" "+lowValue+" "+highValue+"/n"+token;
    }
    public String getToken(){ return token; }
    public void setToken(String token){ this.token = token; }

    public long getBeginTime(){ return beginTime; }
    public void setBeginTime(long beginTime){ this.beginTime = beginTime; }

    public long getEndTime(){ return endTime; }
    public void setEndTime(long endTime){ this.endTime = endTime; }

    public String getClientId(){ return clientId; }
    public void setClientId(String clientId){ this.clientId = clientId; }

    public int getAlert(){ return alert; }
    public void setAlert(int alert){ this.alert = alert; }

    public int getLowValue(){ return lowValue; }
    public void setLowValue(int lowValue){ this.lowValue = lowValue; }

    public int getHighValue(){ return highValue; }
    public void setHighValue(int highValue){ this.highValue = highValue; }
}
