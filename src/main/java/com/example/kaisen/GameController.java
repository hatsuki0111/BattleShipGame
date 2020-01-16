package com.example.kaisen;

import com.example.kaisen.model.CpuKaisen;
import com.example.kaisen.model.service.KaisenService;
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

    /**
     * クラス分ける
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
                if(playerLine.equals(String.valueOf(y))&&playerColumn.equals(String.valueOf(x))) {
                    playerBlocks[y][x] = "W";
                }else{
                    //ToDo
                }
            }
        }

        model.addAttribute("playerBlocks",playerBlocks);

        //CPU座標
        Random rand = new Random();
        int cpLine = rand.nextInt(5);
        int cpColumn = rand.nextInt(5);
        String cpuLine = String.valueOf(cpLine);
        String cpuColumn = String.valueOf(cpColumn);

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                //ランダムで得たCPUの座標をもとの座標と比較する
            if(cpuLine.equals(String.valueOf(y))&&
                cpuColumn.equals(String.valueOf(x))){
                cpuBlocks[y][x] = "";//表示しない
            }
            }
        }
        model.addAttribute("cpuBlocks",cpuBlocks);
        //ServiceクラスにPlayerとCPUの座標をセットする
        service.setBlocks(playerLine,playerColumn,cpuLine,cpuColumn);

        return "BattlePage";
    }

    //BattlePageのポスト
    //引数はBattlePageのPlayerの攻撃、GameStartPageのCPUの座標、GameStartPageのPlayerの座標、
    //続けるから仕様の画面6部分
    @PostMapping("ContinuePage")
    public String judge(int playerAttackLine, int playerAttackColumn, int cpuLine, int cpuColumn, String playerLine, String playerColumn, Model model) {




        CpuKaisen cpK = new CpuKaisen();
        //GameStartPageのCPUの座標
        var cpL = cpK.cpuLine(cpuLine);
        var cpC = cpK.cpuColumn(cpuColumn);


        //CPU攻撃座標
        Random rand = new Random();
        int cpuAttackLine = rand.nextInt(5);
        int cpuAttackColumn = rand.nextInt(5);

        /**
         * if
         *BattlePageで攻撃したPlayerの座標とBattlePageのCPUの座標が同じかを比較し、同じならX
         *CPUが攻撃した座標とBattlePageのPlayerの座標が同じかを比較し,同じならX
         *else if
         * BattlePageで攻撃したPlayerの座標とBattlePageのCPUの座標が同じかを比較し、同じならX
         * CPUが攻撃した座標とBattlePageのPlayerの座標が異なるかを比較し,異なるならば・
         * else if
         *BattlePageで攻撃したPlayerの座標とBattlePageのCPUの座標が異なるかを比較し、異なるならば・
         * CPUが攻撃した座標とBattlePageのPlayerの座標が同じかを比較し,同じならX
         * else
         *両者の攻撃が当たらない
         */
        if((playerAttackLine==cpuLine&&playerAttackColumn==cpuColumn)&&
                (String.valueOf(cpuAttackLine).equals(playerLine)&&String.valueOf(cpuAttackColumn).equals(playerColumn))){
            playerBlocks[playerAttackLine][playerAttackColumn] = "X";
            cpuBlocks[cpuLine][cpuColumn] = "X";
            model.addAttribute("playerBlocks",playerBlocks);
            model.addAttribute("cpuBlocks",cpuBlocks);
            //引き分け
            return "DrawPage";
        }else if((playerAttackLine==cpuLine&&playerAttackColumn==cpuColumn)&&
                (!(String.valueOf(cpuAttackLine).equals(playerLine))&&!(String.valueOf(cpuAttackColumn).equals(playerColumn)))){
            playerBlocks[playerAttackLine][playerAttackColumn] = "X";
            cpuBlocks[cpuLine][cpuColumn] = "・";
            model.addAttribute("playerBlocks",playerBlocks);
            model.addAttribute("cpuBlocks",cpuBlocks);
            //Playerの勝ち
            return "WinPage";
        }else if((playerAttackLine!=cpuLine&&playerAttackColumn!=cpuColumn)&&
                (String.valueOf(cpuAttackLine).equals(playerLine)&&String.valueOf(cpuAttackColumn).equals(playerColumn))){
            playerBlocks[playerAttackLine][playerAttackColumn] = "・";
            cpuBlocks[cpuLine][cpuColumn] = "X";
            model.addAttribute("playerBlocks",playerBlocks);
            model.addAttribute("cpuBlocks",cpuBlocks);
            //Playerの負け
            return "LosePage";
        }else{
            playerBlocks[playerAttackLine][playerAttackColumn] = "・";
            cpuBlocks[cpuLine][cpuColumn] = "・";
            model.addAttribute("playerBlocks",playerBlocks);
            model.addAttribute("cpuBlocks",cpuBlocks);
            //もう一度
            return "ContinuePage";
        }

/*
        var line = (Integer) httpSession.getAttribute("line");
        var column = (Integer) httpSession.getAttribute("column");
        model.addAttribute("line",line);
        model.addAttribute("column",column);

        //Page1でランダムでできたcpuの座標
        var cRL = (Integer)httpSession.getAttribute("cpuResultline");
        var cRC = (Integer)httpSession.getAttribute("cpuResultcolumn");
        System.out.println("Page1でランダムでできたcpuの縦座標"+cRL);
        System.out.println("Page1でランダムでできたcpuの列座標"+cRC);
        model.addAttribute("cRL",cRL);
        model.addAttribute("cRC",cRC);
*/
        //Page2でのプレイヤーの攻撃
       //httpSession.setAttribute("lineIn", lineIn);
       //httpSession.setAttribute("columnIn", columnIn);

        //Page2でのcpuの攻撃
       /* var cpuResultlinePage2 = service.cpuRegistResultline();//cpuの行座標
        System.out.println(cpuResultlinePage2);
        var cpuResultlcolumnPage2 = service.cpuRegistResultcolumn();//cpuの列座標
        System.out.println(cpuResultlcolumnPage2);
        httpSession.setAttribute("cpuResultlinePage2",cpuResultlinePage2);
        httpSession.setAttribute("cpuResultcolumnPage2",cpuResultlcolumnPage2);

        //Page1のプレイヤーの座標==Page2のcpuの攻撃座標&&Page2のプレイヤーの攻撃座標==Page1のcpuの座標
        if ((line==cpuResultlinePage2&&column==cpuResultlcolumnPage2)&&(lineIn==cRL&&columnIn==cRC)){
            //引き分け
            return "Page5";
        }else if(lineIn==cRL&&columnIn==cRC){
            //プレイヤーの勝ち
            return "Page3";
        }else if(line==cpuResultlinePage2&&column==cpuResultlcolumnPage2){
            //CPUの勝ち
            return "Page4";
        }else{
            //攻撃を続ける
            return "Page6";
        }
        */
       //return 0;
    }
    //Page3
    @GetMapping("WinPage")
    public String PageX(Model model){
        System.out.println("利用中のブラウザ識別番号:" + httpSession.getId());//httpsessionのIdを表示

        //Page1でいれたプレイヤーの座標　W
        var line = (Integer) httpSession.getAttribute("line");
        var column = (Integer) httpSession.getAttribute("column");
        model.addAttribute("line", line);//W
        model.addAttribute("column", column);//W

        //Page1でランダムでできたcpuの座標
        var cRL = (Integer)httpSession.getAttribute("cpuResultline");
        var cRC = (Integer)httpSession.getAttribute("cpuResultcolumn");
        model.addAttribute("cRL",cRL);
        model.addAttribute("cRL",cRC);

        //Page2でのプレイヤーの攻撃座標
        var lineIn = (Integer) httpSession.getAttribute("lineIn");
        var columnIn = (Integer) httpSession.getAttribute("columnIn");
        model.addAttribute("lineIn", lineIn);
        model.addAttribute("columnIn", columnIn);

        //Page2のcpuの攻撃座標
        var cRRL = (Integer)httpSession.getAttribute("cpuResultlinePage2");
        var cRRC = (Integer)httpSession.getAttribute("cpuResultlinePage2");
        model.addAttribute("cRRLParse",cRRL);
        model.addAttribute("cRRCParse",cRRC);

        //Page6でのPlayerの攻撃座標
        var lineL = (Integer) httpSession.getAttribute("lineL");
        var columnC = (Integer) httpSession.getAttribute("columnC");
        model.addAttribute("lineL",lineL);
        model.addAttribute("columnC",columnC);

        //Page6でのcpuの攻撃座標
        var cpuResultlinePage6 = (Integer) httpSession.getAttribute("cpuResultlinePage6");
        var cpuResultcolumnPage6 = (Integer) httpSession.getAttribute("cpuResultcolumnPage6");
        model.addAttribute("cpuResultlinePage6",cpuResultlinePage6);
        model.addAttribute("cpuResultcolumnPge6",cpuResultcolumnPage6);

        return "WinPage";
    }
    //Page4
    @GetMapping("LosePage")
    public String Page3(Model model){

        //Page1でいれたプレイヤーの座標　W
        var line = (Integer) httpSession.getAttribute("line");
        var column = (Integer) httpSession.getAttribute("column");
        model.addAttribute("line", line);//W
        model.addAttribute("column", column);//W

        //Page1でランダムでできたcpuの座標
        var cRL = (Integer)httpSession.getAttribute("cpuResultline");
        var cRC = (Integer)httpSession.getAttribute("cpuResultcolumn");
        model.addAttribute("cRL",cRL);
        model.addAttribute("cRC",cRC);

        //Page2でのプレイヤーの攻撃座標
        var lineIn = (Integer) httpSession.getAttribute("lineIn");
        var columnIn = (Integer) httpSession.getAttribute("columnIn");
        model.addAttribute("lineIn", lineIn);
        model.addAttribute("columnIn", columnIn);

        //Page2のcpuの攻撃座標
        var cRRL = (Integer)httpSession.getAttribute("cpuResultlinePage2");
        var cRRC = (Integer)httpSession.getAttribute("cpuResultlinePage2");
        model.addAttribute("cRRLParse",cRRL);
        model.addAttribute("cRRCParse",cRRC);

        //Page6でのPlayerの攻撃座標
        var lineL = (Integer) httpSession.getAttribute("lineL");
        var columnC = (Integer) httpSession.getAttribute("columnC");
        model.addAttribute("lineL",lineL);
        model.addAttribute("columnC",columnC);

        //Page6でのcpuの攻撃座標
        var cpuResultlinePage6 = (Integer) httpSession.getAttribute("cpuResultlinePage6");
        var cpuResultcolumnPage6 = (Integer) httpSession.getAttribute("cpuResultcolumnPage6");
        model.addAttribute("cpuResultlinePage6",cpuResultlinePage6);
        model.addAttribute("cpuResultcolumnPge6",cpuResultcolumnPage6);

        return "LosePage";
    }
    //Page5
    @GetMapping("DrawPage")
    public String Page4(Model model){

        //Page1でいれたプレイヤーの座標　W
        var line = (Integer) httpSession.getAttribute("line");
        var column = (Integer) httpSession.getAttribute("column");
        model.addAttribute("line", line);//W
        model.addAttribute("column", column);//W

        //Page1でランダムでできたcpuの座標
        var cRL = (Integer)httpSession.getAttribute("cpuResultline");
        var cRC = (Integer)httpSession.getAttribute("cpuResultcolumn");
        model.addAttribute("cRL",cRL);
        model.addAttribute("cRC",cRC);

        //Page2でのプレイヤーの攻撃座標
        var lineIn = (Integer) httpSession.getAttribute("lineIn");
        var columnIn = (Integer) httpSession.getAttribute("columnIn");
        model.addAttribute("lineIn", lineIn);
        model.addAttribute("columnIn", columnIn);

        //Page2のcpuの攻撃座標
        var cRRL = (Integer)httpSession.getAttribute("cpuResultlinePage2");
        var cRRC = (Integer)httpSession.getAttribute("cpuResultlinePage2");
        model.addAttribute("cRRLParse",cRRL);
        model.addAttribute("cRRCParse",cRRC);

        //Page6でのPlayerの攻撃座標
        var lineL = (Integer) httpSession.getAttribute("lineL");
        var columnC = (Integer) httpSession.getAttribute("columnC");
        model.addAttribute("lineL",lineL);
        model.addAttribute("columnC",columnC);

        //Page6でのcpuの攻撃座標
        var cpuResultlinePage6 = (Integer) httpSession.getAttribute("cpuResultlinePage6");
        var cpuResultcolumnPage6 = (Integer) httpSession.getAttribute("cpuResultcolumnPage6");
        model.addAttribute("cpuResultlinePage6",cpuResultlinePage6);
        model.addAttribute("cpuResultcolumnPge6",cpuResultcolumnPage6);

        return "DrawPage";
    }
    //Page6
    @GetMapping("ContinuePage")
    public String Page6(Model model){

        //Page1でいれたプレイヤーの座標　W
        var line = (Integer) httpSession.getAttribute("line");
        var column = (Integer) httpSession.getAttribute("column");
        model.addAttribute("line", line);//W
        model.addAttribute("column", column);//W

        //Page1でランダムでできたcpuの座標
        var cRL = (Integer)httpSession.getAttribute("cpuResultline");
        var cRC = (Integer)httpSession.getAttribute("cpuResultcolumn");
        model.addAttribute("cRL",cRL);
        model.addAttribute("cRC",cRC);

        //Page2でのプレイヤーの攻撃座標
        var lineIn = (Integer) httpSession.getAttribute("lineIn");
        var columnIn = (Integer) httpSession.getAttribute("columnIn");
        model.addAttribute("lineIn", lineIn);
        model.addAttribute("columnIn", columnIn);

        //Page2のcpuの攻撃座標
        var cRRL = (Integer)httpSession.getAttribute("cpuResultlinePage2");
        var cRRC = (Integer)httpSession.getAttribute("cpuResultlinePage2");
        model.addAttribute("cRRLParse",cRRL);
        model.addAttribute("cRRCParse",cRRC);

        //Page6でのPlayerの攻撃座標
        var lineL = (Integer) httpSession.getAttribute("lineL");
        var columnC = (Integer) httpSession.getAttribute("columnC");
        model.addAttribute("lineL",lineL);
        model.addAttribute("columnC",columnC);

        //Page6でのcpuの攻撃座標
        var cpuResultlinePage6 = (Integer) httpSession.getAttribute("cpuResultlinePage6");
        var cpuResultcolumnPage6 = (Integer) httpSession.getAttribute("cpuResultcolumnPage6");
        model.addAttribute("cpuResultlinePage6",cpuResultlinePage6);
        model.addAttribute("cpuResultcolumnPge6",cpuResultcolumnPage6);

        return "Page6";
    }
    //Page6のPost
    @PostMapping("Continue")
    public String Page6(int lineL, int columnC, Model model){

        //Page1のプレイヤーの座標
        var line = (Integer) httpSession.getAttribute("line");
        var column = (Integer) httpSession.getAttribute("column");
        model.addAttribute("line",line);
        model.addAttribute("column",column);

        //Page1でランダムでできたcpuの座標
        var cRL = (Integer)httpSession.getAttribute("cpuResultline");
        var cRC = (Integer)httpSession.getAttribute("cpuResultcolumn");
        model.addAttribute("cRL",cRL);
        model.addAttribute("cRC",cRC);

        //Page2でのプレイヤーの攻撃座標
        var lineIn = (Integer) httpSession.getAttribute("lineIn");
        var columnIn = (Integer) httpSession.getAttribute("columnIn");
        model.addAttribute("lineIn", lineIn);
        model.addAttribute("columnIn", columnIn);

        //Page2のcpuの攻撃座標
        var cRRL = (Integer)httpSession.getAttribute("cpuResultlinePage2");
        var cRRC = (Integer)httpSession.getAttribute("cpuResultlinePage2");
        model.addAttribute("cRRLParse",cRRL);
        model.addAttribute("cRRCParse",cRRC);

        //Page6のPlayerの攻撃座標
        httpSession.setAttribute("lineL",lineL);
        httpSession.setAttribute("columnC",columnC);

        //Page6のcpuの攻撃座標
      /*  var cpuResultlinePage6 = service.cpuRegistResultline();//cpuの行座標
        System.out.println(cpuResultlinePage6);
        var cpuResultlcolumnPage6 = service.cpuRegistResultcolumn();//cpuの列座標
        System.out.println(cpuResultlcolumnPage6);
        httpSession.setAttribute("cpuResultlinePage6",cpuResultlinePage6);
        httpSession.setAttribute("cpuResultcolumnPage6",cpuResultlcolumnPage6);

        //Page1のプレイヤーの座標==Page6のcpuの攻撃座標&&Page6のプレイヤーの攻撃座標==Page1のcpuの座標
        if ((line==cpuResultlinePage6&&column==cpuResultlcolumnPage6)&&(lineL==cRL&&columnC==cRC)){
            //引き分け
            return "Page5";
        }else if(lineL==cRL&&columnC==cRC){
            //プレイヤーの勝ち
            return "Page3";
        }else if(line==cpuResultlinePage6&&column==cpuResultlcolumnPage6){
            //CPUの勝ち
            return "Page4";
        }else{
            //攻撃を続ける
            return "Page6";
        }
        */
      return "";
    };

    @GetMapping("Page7")
    public String Page7(Model model){
        return "Page7";
    }
}

