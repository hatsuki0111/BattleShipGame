package com.example.kaisen;

import com.example.kaisen.model.KaisenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class GameController {

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private KaisenService service;

    @GetMapping("Page1")
    public String getPage1(Model model) {
        return "Page1";
    }

    //Page1のPost
    @PostMapping("Page2")
    public String Page1(int line, int column, Model model) {
        model.addAttribute("line", line);
        model.addAttribute("column", column);
        //resultに入力した自分の座標をいれてモデルに追加
        var myResult = service.myRegister(line, column); //プレイヤーの座標

        var cpuResultline = service.cpuRegisterline();//cpuの行座標
        System.out.println(cpuResultline);
        var cpuResultcolumn = service.cpuRegistercolumn();//cpuの列座標
        System.out.println(cpuResultcolumn);
        System.out.println("利用中のブラウザ識別番号:" + httpSession.getId());//httpsessionのIdを表示
        httpSession.setAttribute("line", line);
        httpSession.setAttribute("column", column);
        httpSession.setAttribute("cpuResultline",cpuResultline);
        httpSession.setAttribute("cpuResultcolumn",cpuResultcolumn);
        return "Page2";
    }

    @GetMapping("Page2")
    public String Page2(Model model) {
        //Page1のwをセッションで維持
        System.out.println("利用中のブラウザ識別番号:" + httpSession.getId());//httpsessionのIdを表示
        var line = (Integer) httpSession.getAttribute("line");
        var column = (Integer) httpSession.getAttribute("column");
        model.addAttribute("line", line);
        model.addAttribute("column", column);
        return "Page2";
    }

    //Page2のポスト
    //引数はPage2のプレイヤーの攻撃、Page1のCPUの座標、Page1のプレイヤーの座標、
    @PostMapping("Page3")
    public String PageX(int lineIn, int columnIn,  Model model) {
        //Page1でいれたプレイヤーの座標　W
        var line = (Integer) httpSession.getAttribute("line");
        var column = (Integer) httpSession.getAttribute("column");
       // model.addAttribute("line", line);//W
       // model.addAttribute("column", column);//W
        //Page1でランダムでできたcpuの座標
        var cRL = (Integer)httpSession.getAttribute("cpuResultline");
        var cRC = (Integer)httpSession.getAttribute("cpuResultcolumn");
        System.out.println("Page1でランダムでできたcpuの縦座標"+cRL);
        System.out.println("Page1でランダムでできたcpuの列座標"+cRC);
        //model.addAttribute(cRL);
        //model.addAttribute(cRC);

        //Page2でのプレイヤーの攻撃
       httpSession.setAttribute("lineIn", lineIn);
       httpSession.setAttribute("columnIn", columnIn);

        //Page2でのcpuの攻撃
        var cpuResultlinePage2 = service.cpuRegistResultline();//cpuの行座標
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
    }
    //Page3
    @GetMapping("Page3")
    public String PageX(Model model){
        //Wと・　プレイヤー勝ったやつモデルに追加getsession×
        System.out.println("利用中のブラウザ識別番号:" + httpSession.getId());//httpsessionのIdを表示
        //Page2でのプレイヤーの攻撃座標
        var lineIn = (Integer) httpSession.getAttribute("lineIn");
        var columnIn = (Integer) httpSession.getAttribute("columnIn");
        model.addAttribute("lineIn", lineIn);
        model.addAttribute("columnIn", columnIn);

        //Page1でランダムでできたcpuの座標
        var cRL = (Integer)httpSession.getAttribute("cpuResultline");
        var cRC = (Integer)httpSession.getAttribute("cpuResultcolumn");
        model.addAttribute(cRL);
        model.addAttribute(cRC);

        //Page1でいれたプレイヤーの座標　W
        var line = (Integer) httpSession.getAttribute("line");
        var column = (Integer) httpSession.getAttribute("column");
        model.addAttribute("line", line);//W
        model.addAttribute("column", column);//W

        //Page2のcpuの攻撃座標
        var cRRL = (Integer)httpSession.getAttribute("cpuResultlinePage2");
        var cRRC = (Integer)httpSession.getAttribute("cpuResultlinePage2");
        model.addAttribute("cRRLParse",cRRL);
        model.addAttribute("cRRCParse",cRRC);

        return "Page3";
    }
    //Page4
    @GetMapping("Page4")
    public String Page3(Model model){
        //Wと・　cpuの勝ちをモデルに追加getsesion×

        //Page2でのプレイヤーの攻撃座標
        var lineIn = (Integer) httpSession.getAttribute("lineIn");
        var columnIn = (Integer) httpSession.getAttribute("columnIn");
        model.addAttribute("lineIn", lineIn);
        model.addAttribute("columnIn", columnIn);

        return "Page4";
    }
    //Page5
    @GetMapping("Page5")
    public String Page4(Model model){
        //Wと・×両方にgetsession

        //Page2でのプレイヤーの攻撃座標
        var lineIn = (Integer) httpSession.getAttribute("lineIn");
        var columnIn = (Integer) httpSession.getAttribute("columnIn");
        model.addAttribute("lineIn", lineIn);
        model.addAttribute("columnIn", columnIn);

        return "Page5";
    }
    //Page6
    @GetMapping("Page6")
    public String Page6(int x,Model model){
        //W ・をgetsession

        //Page2でのプレイヤーの攻撃座標
        var lineIn = (Integer) httpSession.getAttribute("lineIn");
        var columnIn = (Integer) httpSession.getAttribute("columnIn");
        model.addAttribute("lineIn", lineIn);
        model.addAttribute("columnIn", columnIn);

        return "Page6";
    }
    //Page6のPost
    @PostMapping("Continue")
    public String Page6(int lineL, int columnC, Model model){

        //Page1のプレイヤーの座標
        var line = (Integer) httpSession.getAttribute("line");
        var column = (Integer) httpSession.getAttribute("column");

        //Page1でランダムでできたcpuの座標
        var cRL = (Integer)httpSession.getAttribute("cpuResultline");
        var cRC = (Integer)httpSession.getAttribute("cpuResultcolumn");

        //Page6のcpuの攻撃座標
        var cpuResultlinePage6 = service.cpuRegistResultline();//cpuの行座標
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
    }

    @GetMapping("Page7")
    public String Page7(Model model){
        return "Page7";
    }
}

