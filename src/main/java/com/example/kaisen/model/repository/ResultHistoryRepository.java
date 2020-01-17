package com.example.kaisen.model.repository;

import com.example.kaisen.model.bean.ResultHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ResultHistoryRepository {

    @Autowired
    private JdbcTemplate jdbc;

    //勝者、何手、勝敗
    public int insert(ResultHistory resultHistory){
        var sql = "insert into resultHistory (winnerandloser, count, result) values(?,?,?)";
        var n = jdbc.update(sql, resultHistory.getWinnerandloser(), resultHistory.getCount(), resultHistory.getResult());
        System.out.println(n);
        return n;
    }
}
