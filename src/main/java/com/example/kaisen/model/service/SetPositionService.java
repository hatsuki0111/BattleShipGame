package com.example.kaisen.model.service;

import org.springframework.stereotype.Service;

@Service
public class SetPositionService {

    //戦艦の位置
    public static final String w = "W";
    //戦艦攻撃成功xxはforのxと同じだから
    public static final String xx = "X";
    //戦艦攻撃失敗
    public static final String miss = "・";

    //Player座標初期化
    public String[][] setPlayerPosition(String[][] playerBlocks) {
        //Gameリスタート時に座標をすべて空にする。
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                playerBlocks[y][x] = "";
            }
        }
        return playerBlocks;
    }

    //CPU座標初期化
    public String[][] setCpuPosition(String[][] cpuBlocks) {
        //Gameリスタート時に座標をすべて空にする。
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                cpuBlocks[y][x] = "";
            }
        }
        return cpuBlocks;
    }

    //GameStartPageでPlayerが入力した座標
    public String[][] setPlPositionGSPage(String getPlayerLine, String getPlayerColumn, String[][] playerBlocks) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                //入力した縦と横の数字と座標の比較文字列なのでString.valueof()
                if (getPlayerLine.equals(String.valueOf(y)) && getPlayerColumn.equals(String.valueOf(x))) {
                    playerBlocks[y][x] = w;
                }
            }
        }
        return playerBlocks;
    }

    //GameStartPageでCPUのランダムな座標
    public String[][] setCpRandomSetPosition(String cpuLine, String cpuColumn, String[][] cpuBlocks) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                //ランダムで得たCPUの座標をもとの座標と比較する
                if (cpuLine.equals(String.valueOf(y)) && cpuColumn.equals(String.valueOf(x))) {
                    cpuBlocks[y][x] = w;//表示しない
                }
            }
        }
        return cpuBlocks;
    }
}
