package com.example.kaisen.model.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CpuRandomService {

    Random rand = new Random();
    //CPUのy軸(縦)座標
    public String cpuRandomSetLine(){
        int cpLine = rand.nextInt(5);
        String cpuLine = String.valueOf(cpLine);
        return cpuLine;
    }
    //CPUのx軸(横)座標
    public String cpuRandomSetColumn(){
        int cpColumn = rand.nextInt(5);
        String cpuColumn = String.valueOf(cpColumn);
        return cpuColumn;
    }
    //CPUの攻撃座標Line
    public String cpRandAttackSetLine(){
        int cpAtttackLine = rand.nextInt(5);
        String cpuAtttackLine = String.valueOf(cpAtttackLine);
        return cpuAtttackLine;
    }
    //CPUの攻撃座標Column
    public String cpRandAttackSetColumn(){
        int cpAttackColumn = rand.nextInt(5);
        String cpuAttackColumn = String.valueOf(cpAttackColumn);
        return cpuAttackColumn;
    }
}
