package com.example.kaisen;

import com.example.kaisen.model.bean.ResultHistory;
import com.example.kaisen.model.service.KaisenService;
import com.example.kaisen.model.service.ResultHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class GameController {

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private KaisenService service;
    @Autowired
    private ResultHistoryService resultHistoryService;

    /**
     * クラス分ける?
     */
    //Playerの座標
    String[][] playerBlocks = new String[5][5];
    //CPUの座標
    String[][] cpuBlocks = new String[5][5];


    @GetMapping("GameStartPage")
    public String gameStart(Model model) {

        //Gameリスタート時に座標のWが残らないようにする。
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                playerBlocks[y][x] = "";
                cpuBlocks[y][x] = "";
            }
        }
        return "GameStartPage";
    }

    //GameStartPageのPost
    @PostMapping("BattlePage")
    public String battle(String playerLine, String playerColumn, Model model) {
        //GameStartPageでPlayerがいれた座標
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                //入力した縦と横の数字と座標の比較文字列なのでString.valueof()
                if (playerLine.equals(String.valueOf(y)) && playerColumn.equals(String.valueOf(x))) {
                    playerBlocks[y][x] = "W";
                }
            }
        }

        model.addAttribute("playerBlocks", playerBlocks);

        //CPU座標
        Random rand = new Random();
        int cpLine = rand.nextInt(5);
        int cpColumn = rand.nextInt(5);
        String cpuLine = String.valueOf(cpLine);
        String cpuColumn = String.valueOf(cpColumn);

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                //ランダムで得たCPUの座標をもとの座標と比較する
                if (cpuLine.equals(String.valueOf(y)) &&
                        cpuColumn.equals(String.valueOf(x))) {
                    cpuBlocks[y][x] = "W";//表示しない
                }
            }
        }
        model.addAttribute("cpuBlocks", cpuBlocks);
        //ServiceクラスにPlayerとCPUの座標をセットする
        service.setBlocks(playerLine, playerColumn, cpuLine, cpuColumn);

        return "BattlePage";
    }

    //BattlePageのポスト
    //引数はBattlePageのPlayerの攻撃、GameStartPageのCPUの座標、GameStartPageのPlayerの座標、
    //続けるから仕様の画面6部分
    @PostMapping("ContinuePage")
    public String judge(String playerAttackLine, String playerAttackColumn, Model model) {

        /**
         *勝ち1負け2引き分け3再戦4で数字を振るそれで遷移先のページを決める(resultPageNumber)
         *PlayerかCPUのどちらが勝ったかをDBに残すための変数(winnerandloser)
         *DBに何手で勝敗がついたかを残すための変数(score)
         *ゲームが何回目かはauto_incrimentでしのぐ
         */
        int resultPageNumber = 0, winnerandloser = 0, count = 0;

        //Playerの攻撃判定をserviceで行うために、Playerの攻撃座標を引数に成功ならtrueを返す
        Boolean plAttackJudge = service.plAttackJudge(playerAttackLine, playerAttackColumn);

        //CPU攻撃座標
        Random rand = new Random();
        int cpAtLine = rand.nextInt(5);
        int cpAtColumn = rand.nextInt(5);
        String cpuAtttackLine = String.valueOf(cpAtLine);
        String cpuAttackColumn = String.valueOf(cpAtColumn);

        //CPUの攻撃判定をserviceで行うために、CPUの攻撃座標を引数に
        Boolean cpAttackJudge = service.cpAttackJudge(cpuAtttackLine, cpuAttackColumn);

        //両者の攻撃が成功したとき
        if (plAttackJudge && cpAttackJudge) {
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    if (playerAttackLine.equals(String.valueOf(y)) && playerAttackColumn.equals(String.valueOf(x))) {
                        cpuBlocks[y][x] = "X";//CPU陣地に攻撃 あたりX
                    }
                    if (cpuAtttackLine.equals(String.valueOf(y)) && cpuAttackColumn.equals(String.valueOf(x))) {
                        playerBlocks[y][x] = "X";//Player陣地に攻撃 あたりX
                    }
                }
            }
            resultPageNumber = 3;//引き分け
            winnerandloser = 1;//Playerを記憶
        } else if (plAttackJudge) {
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    if (playerAttackLine.equals(String.valueOf(y)) && playerAttackColumn.equals(String.valueOf(x))) {
                        cpuBlocks[y][x] = "X";//あたりX
                    }
                    if (cpuAtttackLine.equals(String.valueOf(y)) && cpuAttackColumn.equals(String.valueOf(x))) {
                        playerBlocks[y][x] = "・";//はずれ・
                    }
                }
            }
            resultPageNumber = 1;//Player勝ち
            winnerandloser = 1;//Playerの勝ちを記憶
        } else if (cpAttackJudge) {
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    if (playerAttackLine.equals(String.valueOf(y)) && playerAttackColumn.equals(String.valueOf(x))) {
                        cpuBlocks[y][x] = "・";//はずれ・
                    }
                    if (cpuAtttackLine.equals(String.valueOf(y)) && cpuAttackColumn.equals(String.valueOf(x))) {
                        playerBlocks[y][x] = "X";//あたりX
                    }
                }
            }
            resultPageNumber = 2;//Player負け
            winnerandloser = 1;//Playerの負けを記録
        } else {
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    if (playerAttackLine.equals(String.valueOf(y)) && playerAttackColumn.equals(String.valueOf(x))) {
                        cpuBlocks[y][x] = "・";//はずれ・
                    }
                    if (cpuAtttackLine.equals(String.valueOf(y)) && cpuAttackColumn.equals(String.valueOf(x))) {
                        playerBlocks[y][x] = "・";//はずれ・
                    }
                }
            }
            resultPageNumber = 4;//再戦
            count += 1;//何手かをインクリメントする
        }
        model.addAttribute("playerBlocks",playerBlocks);
        model.addAttribute("cpuBlocks",cpuBlocks);

        //DBに勝敗を残す
        //勝ち、負け、引き分けのとき
        if(winnerandloser==1) {
            resultHistoryService.register(resultPageNumber, winnerandloser, count);
        }
        //ページ遷移
        switch (resultPageNumber){
            case 1:
                return "WinPage";
            case 2:
                return "LosePage";
            case 3:
                return  "DrawPage";
            default:
                return "ContinuePage";
        }
    }
}


