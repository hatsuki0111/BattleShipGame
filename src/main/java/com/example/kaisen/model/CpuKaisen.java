package com.example.kaisen.model;

import java.util.Random;

public class CpuKaisen {

    Random random = new Random();

    public int cpuLine() {
        return random.nextInt(5);
    }
    public int cpuColumn(){
        return random.nextInt(5);
    }
    public int cpuResultLine(){
        return random.nextInt(5);
    }
    public int cpuResultColumn(){
        return random.nextInt(5);
    }
}
