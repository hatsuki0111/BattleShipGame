package com.example.kaisen.model.service;

import org.springframework.stereotype.Service;

@Service
public class KaisenService {
    private String plLine, plColumn;//Playerの戦艦の座標W
    private String cpLine, cpColumn;//CPUの戦艦の座標

    //両者の座標のセット
    public void setBlocks(String playerLine,String playerColumn,String cpuLine,String cpuColumn){
        this.plLine = playerLine;
        this.plColumn = playerColumn;
        this.cpLine = cpuLine;
        this.cpColumn = cpuColumn;
    }
    //Playerの攻撃判定
    public boolean plAttackJudge(String playerAttackLine, String playerAttackColumn){
        if(playerAttackLine.equals(cpLine)&&playerAttackColumn.equals(cpColumn)){
            return true;//攻撃成功
        }else {
            return false;//攻撃失敗
        }
    }
    //CPUの攻撃判定
    public boolean cpAttackJudge(String cpuAttackLine, String cpuAttackColumn){
        if(cpuAttackLine.equals(plLine)&&cpuAttackColumn.equals(plColumn)){
            return true;//攻撃成功
        }else {
            return false;//攻撃失敗
        }
    }

}
