package com.example.kaisen.model.service;

import org.springframework.stereotype.Service;

@Service
public class GameCountService {

   public int count = 1;//最初は1

    //GameCountの初期化
   public int setGameCount(){
        count = 1;
        return count;
    }

    //GameCountをするメソッド
    public int gameCount(){
        count += 1;
        return count;
    }
}
