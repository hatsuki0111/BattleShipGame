package com.example.kaisen.model.bean;

/**
 * 勝敗結果と、何手目でPlayerとCPUの勝敗がついたか
 */

public class ResultHistory {

    private String winnerandloser;//勝者、敗者
    private int count;//何手目
    private String result;//勝敗
    private int gamenumber;//Gameのplay回数


    public ResultHistory(int gamenumber, String winnerandloser, int count, String result){
        this.winnerandloser = winnerandloser;
        this.count = count;
        this.result = result;
        this.gamenumber = gamenumber;
    }
    public ResultHistory(){
        this.winnerandloser = "";
        this.count = 0;
        this.result ="";
        this.gamenumber = 0;
    }


    public String getResult(){
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getWinnerandloser(){
        return winnerandloser;
    }

    public void setWinnerandloser(String winnerandloser) {
        this.winnerandloser = winnerandloser;
    }

    public int getCount(){
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getGamenumber(){
        return gamenumber;
    }

    public void setGamenumber(int gamenumber) {
        this.gamenumber = gamenumber;
    }
}
