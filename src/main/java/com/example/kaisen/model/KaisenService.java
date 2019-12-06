package com.example.kaisen.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Service
public class KaisenService {

    @Autowired
    private HttpSession httpSession;

    MyKaisen myKaisen = new MyKaisen();
    CpuKaisen cpuKaisen = new CpuKaisen();

    //Page1の入力
    public int myRegister(int line, int column) {

        return myKaisen.myRgister(line, column);
    }
    public int cpuRegisterline(){
        return cpuKaisen.cpuRegisterline();
    }
    public int cpuRegistercolumn(){
        return cpuKaisen.cpuRegistercolumn();
    }

    //Page2での入力
    public int myRegistResult(int line, int column){
        return myKaisen.myRegistResult(line, column);
    }

    public int cpuRegistResultline(){
        return cpuKaisen.cpuRegistResultline();
    }
    public int cpuRegistResultcolumn(){
        return cpuKaisen.cpuRegistResultcolumn();
    }

}
