package com.example.kaisen.model.service;

import com.example.kaisen.model.bean.ResultHistory;
import com.example.kaisen.model.repository.ResultHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class ResultHistoryService {

    @Autowired
    private ResultHistoryRepository repository;

    public void register(int resultPageNumber,int winandlose, int count){
        try{
            var result = "";
            var winnerandloser = "";
            if(resultPageNumber==1){
                result = "勝ち";
            }else if(resultPageNumber==2){
                result = "負け";
            }else if(resultPageNumber==3){
                result = "引き分け";
            }

            if(winandlose==1){
                winnerandloser = "Player";
            }

            //ResultHistory
            ResultHistory resultHistory = new ResultHistory(winnerandloser,count,result);

            //勝者、何手、勝敗
            var n = repository.insert(resultHistory);
            var successInsert = n>0? n+"件を追加":"追加失敗";
            System.out.println(successInsert+"追加");
        }catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
