package com.example.kaisen.model;

import java.util.Random;

public class CpuKaisen {

    Random random = new Random();

    public int cpuLine(int cpuLine) {
        return cpuLine;
    }
    public int cpuColumn(int cpuColumn){

        return cpuColumn;
    }
    public int cpuResultLine(){
        return random.nextInt(5);
    }
    public int cpuResultColumn(){
        return random.nextInt(5);
    }
}
