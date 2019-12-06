package com.example.kaisen.model;

import java.util.Random;

public class CpuKaisen {

    Random random = new Random();

    public int cpuRegisterline() {
        return random.nextInt(5);
    }
    public int cpuRegistercolumn(){
        return random.nextInt(5);
    }
    public int cpuRegistResultline(){
        return random.nextInt(5);
    }
    public int cpuRegistResultcolumn(){
        return random.nextInt(5);
    }
}
