package com.example.kaisen.model.bean;

import javax.validation.constraints.*;

public class ValidatedBattlePageForm {

    /**
     * @Notnullは入力値になにもないとき String型でないとエラーがブラウザにそのまま表示される
     * @Max @@Minは0~4以外はだめ
     *
     * tips @Patternは正規表現 @NotBlankはhibernateで6から非推奨、versionとかも地雷多いのでいれたくない
     */

    //groupsでvalidationの順番を制御する
    @NotNull(message = "0~4の数字を入力してください",groups = IValidatedBtPlFm1.class)
    @Min(value=0, message = "0~4の数字を入力してください", groups = IValidatedBtPlFm2.class)
    @Max(value = 4, message = "0~4の数字を入力してください", groups = IValidatedBtPlFm3.class)
    private String playerLine;

    @NotNull(message = "0~4の数字を入力してください",groups = IValidatedBtPlFm1.class)
    @Min(value=0, message = "0~4の数字を入力してください",groups = IValidatedBtPlFm2.class)
    @Max(value = 4, message = "0~4の数字を入力してください",groups = IValidatedBtPlFm3.class)
    private String playerColumn;

    public ValidatedBattlePageForm(){
        this.playerLine = "";
        this.playerColumn = "";
    }
    public String getPlayerLine(){
        return playerLine;
    }
    public void setPlayerLine(String playerLine){
        this.playerLine = playerLine;
    }
    public String getPlayerColumn(){
        return playerColumn;
    }
    public void setPlayerColumn(String playerColumn){
        this.playerColumn = playerColumn;
    }

}
