package com.example.kaisen.model.service;

import com.example.kaisen.model.CpuKaisen;
import com.example.kaisen.model.MyKaisen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Service
public class KaisenService {
    private String plLine, plColumn;//Playerの戦艦の座標W
    private String cpLine, cpColumn;//CPUの戦艦の座標
    private int resultNumber;//勝ち1 負け2　引き分け3　再戦4

    public void setBlocks(String playerLine,String playerColumn,String cpuLine,String cpuColumn){
        this.plLine = playerLine;
        this.plColumn = playerColumn;
        this.cpLine = cpuLine;
        this.cpColumn = cpuColumn;
        this.resultNumber = -1;
    }
}
