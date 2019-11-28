package com.example.kaisen.model;

import java.util.Random;

public class CpuKaisen {

    public int cpuRegister() {
        Random random = new Random();
        return random.nextInt(25) + 1;
    }
}
