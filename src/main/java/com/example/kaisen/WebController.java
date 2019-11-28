package com.example.kaisen;

import com.example.kaisen.model.KaisenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    @Autowired
    private KaisenService service;

    @GetMapping("Page1")
    public String getPage1(Model model){
        return "Page1";
    }

    @PostMapping("Page2")
    public String postPage1(int line, int column, Model model){
        //resultに入力した自分の座標をいれてモデルに追加
        var result = service.register(line,column);
        model.addAttribute("zahyou", result);

        return "Page2";

    }
}
