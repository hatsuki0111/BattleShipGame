package com.example.kaisen.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Service
public class KaisenService {

    @Autowired
    private HttpSession httpSession;

    public int myRegister(int line, int column) {

        MyKaisen myKaisen = new MyKaisen();
        return myKaisen.myRgister(line, column);

    }
    public int cpuRegister(){
        CpuKaisen cpuKaisen = new CpuKaisen();
        return cpuKaisen.cpuRegister();
    }

}
