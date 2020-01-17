package com.example.kaisen.model.bean;

/**
 * 勝敗結果と、何手目でPlayerとCPUの勝敗がついたか
 */

public class ResultHistory {

    private String winnerandloser;//勝者、敗者
    private int count;//何手目
    private String result;//勝敗


    public ResultHistory(String winnerandloser, int count, String result){
        this.winnerandloser = winnerandloser;
        this.count = count;
        this.result = result;
    }
    public ResultHistory(){
        this.winnerandloser = "";
        this.count = 0;
        this.result ="";
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
}
