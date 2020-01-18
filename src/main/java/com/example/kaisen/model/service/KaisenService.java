package com.example.kaisen.model.service;

import org.springframework.stereotype.Service;

@Service
public class KaisenService {
    private int plLine, plColumn;//Playerの戦艦の座標W
    private int cpLine, cpColumn;//CPUの戦艦の座標

    //両者の座標のセット
    public void setBlocks(int playerLine,int playerColumn,int cpuLine,int cpuColumn){
        this.plLine = playerLine;
        this.plColumn = playerColumn;
        this.cpLine = cpuLine;
        this.cpColumn = cpuColumn;
    }
    //Playerの攻撃判定
    public boolean plAttackJudge(int playerAttackLine, int playerAttackColumn){
        if(playerAttackLine==cpLine&&playerAttackColumn==cpColumn){
            return true;//攻撃成功
        }else {
            return false;//攻撃失敗
        }
    }
    //CPUの攻撃判定
    public boolean cpAttackJudge(int cpuAttackLine, int cpuAttackColumn){
        if(cpuAttackLine==plLine&&cpuAttackColumn==plColumn){
            return true;//攻撃成功
        }else {
            return false;//攻撃失敗
        }
    }

}
