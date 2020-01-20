package com.example.kaisen.controller;

import com.example.kaisen.model.Tweet.Tweet;
import com.example.kaisen.model.bean.IBtPlFmOrder;
import com.example.kaisen.model.bean.ValidatedBattlePageForm;
import com.example.kaisen.model.bean.ValidatedContinuePageForm;
import com.example.kaisen.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class GameController {

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private KaisenService kaisenService;
    @Autowired
    private ResultHistoryService resultHistoryService;
    @Autowired
    private SetPositionService setPositionService;
    @Autowired
    private CpuRandomService cpuRandomService;
    @Autowired
    private GameCountService gameCountService;

    //Playerの座標
    String[][] playerBlocks = new String[5][5];
    //CPUの座標
    String[][] cpuBlocks = new String[5][5];

    //ValidatedBattlePageFormの初期化
    @ModelAttribute
    public ValidatedBattlePageForm setupValidatedBattlePageForm(){
        return new ValidatedBattlePageForm();
    }

    @GetMapping("GameStartPage")
    public String gameStart(Model model) {
        setPositionService.setPlayerPosition(playerBlocks);
        setPositionService.setCpuPosition(cpuBlocks);
        //何手目かの初期化(count)
        gameCountService.setGameCount();
        return "GameStartPage";
    }

    //ValidatedContinuePageFormの初期化
    @ModelAttribute
    public ValidatedContinuePageForm setupValidatedContinuePageForm(){
        return new ValidatedContinuePageForm();
    }

    //GameStartPageのPost
    //Validatedでvalidationする
    //validatedBattlePageFormの直後にerrorsが引数でないといけない
    //以下空文字処理のために苦肉のStringを使用
    @PostMapping("BattlePage")
    public String battle(@Validated(IBtPlFmOrder.class) ValidatedBattlePageForm validatedBattlePageForm, Errors errors, Model model) {

        //ValidationErrorならGameStartPageに遷移させる
        if(errors.hasErrors()){
            return "GameStartPage";
        }

        //Filter時にGameStartPageの入力値をSessionに持ちforwardさせる
        System.out.println("利用中のブラウザ識別番号:"+httpSession.getId());
        httpSession.setAttribute("playerLine",validatedBattlePageForm.getPlayerLine());

        //GameStartPageでPlayerがいれた座標
        var plBlocks = setPositionService.setPlPositionGSPage(validatedBattlePageForm.getPlayerLine(), validatedBattlePageForm.getPlayerColumn(),playerBlocks);
        model.addAttribute("playerBlocks", plBlocks);

        //GameStartPageでランダムなCPU座標
        var cpuLine = cpuRandomService.cpuRandomSetLine();
        var cpuColumn = cpuRandomService.cpuRandomSetColumn();

        var cpBlocks = setPositionService.setCpRandomSetPosition(cpuLine, cpuColumn, cpuBlocks);
        model.addAttribute("cpuBlocks", cpBlocks);
        //ServiceクラスにPlayerとCPUの座標をセットする
        kaisenService.setBlocks(validatedBattlePageForm.getPlayerLine(), validatedBattlePageForm.getPlayerColumn(), cpuLine, cpuColumn);

        return "BattlePage";
    }

    //BattlePageのポスト
    //引数はBattlePageのPlayerの攻撃Line Column
    @PostMapping("ContinuePage")
    public String judge(ValidatedContinuePageForm validatedContinuePageForm, Model model) {

        /**
         *勝ち1負け2引き分け3再戦4で数字を振るそれで遷移先のページを決める(resultPageNumber)
         *PlayerかCPUのどちらが勝ったかをDBに残すための変数(winnerandloser)
         *DBに何手で勝敗がついたかを残すための変数(count)
         *ゲームが何回目かはserialでしのぐ
         */
        int resultPageNumber = 0, winnerandloser = 0;

        //Playerの攻撃判定をserviceで行うために、Playerの攻撃座標を引数に成功ならtrueを返す
        Boolean plAttackJudge = kaisenService.plAttackJudge(validatedContinuePageForm.getPlayerAttackLine(), validatedContinuePageForm.getPlayerAttackColumn());

        //CPU攻撃座標
        var cpuAttackLine = cpuRandomService.cpRandAttackSetLine();
        var cpuAttackColumn = cpuRandomService.cpRandAttackSetColumn();

        //CPUの攻撃判定をserviceで行うために、CPUの攻撃座標を引数に
        Boolean cpAttackJudge = kaisenService.cpAttackJudge(cpuAttackLine, cpuAttackColumn);


        //両者の攻撃が成功したとき
        //serviceに処理させたいがreturnで配列返したときの受け取る変数をforしたり大変になるため断念
        if (plAttackJudge && cpAttackJudge) {
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    if (validatedContinuePageForm.getPlayerAttackLine().equals(String.valueOf(y)) && validatedContinuePageForm.getPlayerAttackColumn().equals(String.valueOf(x))) {
                        cpuBlocks[y][x] = "X";//CPU陣地に攻撃 あたりX
                    }
                    if (cpuAttackLine.equals(String.valueOf(y)) && cpuAttackColumn.equals(String.valueOf(x))) {
                        playerBlocks[y][x] = "X";//Player陣地に攻撃 あたりX
                    }
                }
            }
            resultPageNumber = 3;//引き分け
            winnerandloser = 1;//Playerを記憶
        } else if (plAttackJudge) {
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    if (validatedContinuePageForm.getPlayerAttackLine().equals(String.valueOf(y)) && validatedContinuePageForm.getPlayerAttackColumn().equals(String.valueOf(x))) {
                        cpuBlocks[y][x] = "X";//あたりX
                    }
                    if (cpuAttackLine.equals(String.valueOf(y)) && cpuAttackColumn.equals(String.valueOf(x))) {
                        playerBlocks[y][x] = "・";//はずれ・
                    }
                }
            }
            resultPageNumber = 1;//Player勝ち
            winnerandloser = 1;//Playerを記憶
        } else if (cpAttackJudge) {
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    if (validatedContinuePageForm.getPlayerAttackLine().equals(String.valueOf(y)) && validatedContinuePageForm.getPlayerAttackColumn().equals(String.valueOf(x))) {
                        cpuBlocks[y][x] = "・";//はずれ・
                    }
                    if (cpuAttackLine.equals(String.valueOf(y)) && cpuAttackColumn.equals(String.valueOf(x))) {
                        playerBlocks[y][x] = "X";//あたりX
                    }
                }
            }
            resultPageNumber = 2;//Player負け
            winnerandloser = 1;//Playerを記録
        } else {
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    if (validatedContinuePageForm.getPlayerAttackLine().equals(String.valueOf(y)) && validatedContinuePageForm.getPlayerAttackColumn().equals(String.valueOf(x))){
                        cpuBlocks[y][x] = "・";//はずれ・
                    }
                    if (cpuAttackLine.equals(String.valueOf(y)) && cpuAttackColumn.equals(String.valueOf(x))) {
                        playerBlocks[y][x] = "・";//はずれ・
                    }
                }
            }
            resultPageNumber = 4;//再戦
            gameCountService.gameCount();//何手かをインクリメントする
        }
        model.addAttribute("playerBlocks",playerBlocks);
        model.addAttribute("cpuBlocks",cpuBlocks);

        var saveCount = gameCountService.gameCount();
        var count = saveCount/2;

        //DBに勝敗を残す
        //勝ち、負け、引き分けのとき
        if(winnerandloser==1) {
            resultHistoryService.register(resultPageNumber, winnerandloser, count);
        }
        resultHistoryService.setKekka(resultPageNumber);
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
    @GetMapping("ResultPage")
    public String resultGet(Model model){
        var resultHistories = resultHistoryService.findAll();
        model.addAttribute("resultHistories", resultHistories);
        var kekka = resultHistoryService.kekka();
        model.addAttribute("kekka",kekka);
        return "ResultPage";
    }
    @GetMapping("TweetPage")
    public String tweet(Model model){
        var tweetKekka = resultHistoryService.kekka();
        Tweet tweet = new Tweet();
        tweet.doHogeHoge(tweetKekka);
        tweet.getTwitter();
        return "TweetPage";
    }
}


