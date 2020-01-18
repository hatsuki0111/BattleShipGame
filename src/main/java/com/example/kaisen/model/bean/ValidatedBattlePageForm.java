package com.example.kaisen.model.bean;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;

public class ValidatedBattlePageForm {
    /**
     * @Notnullは入力値になにもないとき
     * @Max @@Minは0~4以外はだめ
     *
     * tips @Patternは正規表現 @NotBlankはhibernateで6から非推奨、versionとかも地雷多いのでいれたくない
     */

    @NotNull(message = "0~4の数字を入力してください")
    @Min(value=0, message = "0~4の数字を入力してください")
    @Max(value = 4, message = "0~4の数字を入力してください")
    private int playerLine;

    @NotNull(message = "0~4の数字を入力してください")
    @Min(value=0, message = "0~4の数字を入力してください")
    @Max(value = 4, message = "0~4の数字を入力してください")
    //@Pattern(regexp="x", message = "0~4の数字を入力してください")
    private int playerColumn;

    public ValidatedBattlePageForm(){
        this.playerLine = 0;
        this.playerColumn = 0;
    }
    public int getPlayerLine(){
        return playerLine;
    }
    public void setPlayerLine(int playerLine){
        this.playerLine = playerLine;
    }
    public int getPlayerColumn(){
        return playerColumn;
    }
    public void setPlayerColumn(int playerColumn){
        this.playerColumn = playerColumn;
    }

}
