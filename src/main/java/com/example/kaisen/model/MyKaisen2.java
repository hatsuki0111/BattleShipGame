package com.example.kaisen.model;

public class MyKaisen2 {
    private int lineIn;
    private int columnIn;

    public MyKaisen2(){
        this.lineIn = 0;
        this.columnIn = 0;
    }

    public int getColumn() {
        return columnIn;
    }

    public int getLine() {
        return lineIn;
    }

    public void setColumn(int columnIn) {
        this.columnIn = columnIn;
    }

    public void setLine(int lineIn) {
        this.lineIn = lineIn;
    }
}
