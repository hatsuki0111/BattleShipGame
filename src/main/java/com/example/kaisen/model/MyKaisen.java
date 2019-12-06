package com.example.kaisen.model;

public class MyKaisen {

    private int line;
    private int column;

    public MyKaisen(){
        this.line = 0;
        this.column = 0;
    }

    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int myRgister(int line, int column){

        //入力した座標をthymeleafで比較しやすいように1～25の数字で返す
        if(line==0&&column==0){
            return 1;
        }else if(line==0&&column==1){
            return 2;
        }else if(line==0&&column==2){
            return 3;
        }else if(line==0&&column==3){
            return 4;
        }else if(line==0&&column==4){
            return 5;
        }else if(line==1&&column==0){
            return 6;
        }else if(line==1&&column==1){
            return 7;
        }else if(line==1&&column==2){
            return 8;
        }else if(line==1&&column==3){
            return 9;
        }else if(line==1&&column==4){
            return 10;
        }else if(line==2&&column==0){
            return 11;
        }else if(line==2&&column==1){
            return 12;
        }else if(line==2&&column==2){
            return 13;
        }else if(line==2&&column==3){
            return 14;
        }else if(line==2&&column==4){
            return 15;
        }else if(line==3&&column==0){
            return 16;
        }else if(line==3&&column==1){
            return 17;
        }else if(line==3&&column==2){
            return 18;
        }else if(line==3&&column==3){
            return 19;
        }else if(line==3&&column==4){
            return 20;
        }else if(line==4&&column==0){
            return 21;
        }else if(line==4&&column==1){
            return 22;
        }else if(line==4&&column==2){
            return 23;
        }else if(line==4&&column==3){
            return 24;
        }else if(line==4&&column==4){
            return 25;
        } else{
            return 0;
        }
    }

    public int myRegistResult(int lineResult, int columnResult){

        //入力した座標をthymeleafで比較しやすいように1～25の数字で返す
        if(lineResult==0&&columnResult==0){
            return 1;
        }else if(lineResult==0&&columnResult==1){
            return 2;
        }else if(lineResult==0&&columnResult==2){
            return 3;
        }else if(lineResult==0&&columnResult==3){
            return 4;
        }else if(lineResult==0&&columnResult==4){
            return 5;
        }else if(lineResult==1&&columnResult==0){
            return 6;
        }else if(lineResult==1&&columnResult==1){
            return 7;
        }else if(lineResult==1&&columnResult==2){
            return 8;
        }else if(lineResult==1&&columnResult==3){
            return 9;
        }else if(lineResult==1&&columnResult==4){
            return 10;
        }else if(lineResult==2&&columnResult==0){
            return 11;
        }else if(lineResult==2&&columnResult==1){
            return 12;
        }else if(lineResult==2&&columnResult==2){
            return 13;
        }else if(lineResult==2&&columnResult==3){
            return 14;
        }else if(lineResult==2&&columnResult==4){
            return 15;
        }else if(lineResult==3&&columnResult==0){
            return 16;
        }else if(lineResult==3&&columnResult==1){
            return 17;
        }else if(lineResult==3&&columnResult==2){
            return 18;
        }else if(lineResult==3&&columnResult==3){
            return 19;
        }else if(lineResult==3&&columnResult==4){
            return 20;
        }else if(lineResult==4&&columnResult==0){
            return 21;
        }else if(lineResult==4&&columnResult==1){
            return 22;
        }else if(lineResult==4&&columnResult==2){
            return 23;
        }else if(lineResult==4&&columnResult==3){
            return 24;
        }else if(lineResult==4&&columnResult==4){
            return 25;
        } else{
            return 0;
        }
    }
}
