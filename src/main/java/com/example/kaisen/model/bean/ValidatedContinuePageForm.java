package com.example.kaisen.model.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ValidatedContinuePageForm {
    //groupsでvalidationの順番を制御する
   // @NotNull
   // @Min(value=0, message = "0~4の数字を入力してください")
    //@Max(value = 4, message = "0~4の数字を入力してください")
    private String playerAttackLine;

    //@NotNull
    //@Min(value=0, message = "0~4の数字を入力してください")
    //@Max(value = 4, message = "0~4の数字を入力してください")
    private String playerAttackColumn;

    public ValidatedContinuePageForm(){
        this.playerAttackLine = "";
        this.playerAttackColumn = "";
    }
    public String getPlayerAttackLine(){
        return playerAttackLine;
    }
    public void setPlayerAttackLine(String playerAttackLine){
        this.playerAttackLine = playerAttackLine;
    }
    public String getPlayerAttackColumn(){
        return playerAttackColumn;
    }
    public void setPlayerAttackColumn(String playerAttackColumn){
        this.playerAttackColumn = playerAttackColumn;
    }
}
