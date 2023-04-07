package com.example.bmi;

public class ContactsInfo {
    private  String weigh;
    private  String heigh;
    private  String bmi;
    private  String date;
    private String bmiResult;

    public ContactsInfo() {
    }

    public ContactsInfo(String weigh,String heigh, String bmi, String bmiResult, String date){
        this.weigh = weigh;
        this.heigh = heigh;
        this.bmi = bmi;
        this.bmiResult=bmiResult;
        this.date=date;
    }

    public String getWeigh(){
        return this.weigh=weigh;
    }
    public String getHeigh(){
        return this.heigh=heigh;
    }
    public String getDate(){
        return this.date=date;
    }
    public String getBmi(){
        return this.bmi=bmi;
    }
    public String getBmiResult(){
        return this.bmiResult=bmiResult;
    }
}
