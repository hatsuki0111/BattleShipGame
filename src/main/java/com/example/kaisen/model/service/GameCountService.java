package com.example.kaisen.model.service;

import org.springframework.stereotype.Service;

@Service
public class GameCountService {

    public int count = 1;//最初は1

    //GameCountをするメソッド
    public int gameCount(){
        count += 1;
        return count;
    }
}
