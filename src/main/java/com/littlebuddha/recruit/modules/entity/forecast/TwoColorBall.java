package com.littlebuddha.recruit.modules.entity.forecast;


import com.littlebuddha.recruit.common.utils.excel.ExcelField;
import com.littlebuddha.recruit.modules.base.entity.DataEntity;

/**
 * 双色球实体类
 */
public class TwoColorBall extends DataEntity<TwoColorBall> {

    private String issueNumber;
    private Integer redOne;
    private Integer redTwo;
    private Integer redThree;
    private Integer redFour;
    private Integer redFive;
    private Integer redSix;
    private Integer blue;
    private String happySunday;
    private Double bonus;
    private Integer numberOfFirstPrize;
    private Double bonusOfFirstPrize;
    private Integer numberOfSecondAward;
    private Double bonusOfSecondAward;
    private Double totalBets;

    private String drawDate;

    @ExcelField(title = "期号",type = 0,align=2,sort=1)
    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    @ExcelField(title = "一号红球",type = 0,align=2,sort=2)
    public Integer getRedOne() {
        return redOne;
    }

    public void setRedOne(Integer redOne) {
        this.redOne = redOne;
    }

    @ExcelField(title = "二号红球",type = 0,align=2,sort=3)
    public Integer getRedTwo() {
        return redTwo;
    }

    public void setRedTwo(Integer redTwo) {
        this.redTwo = redTwo;
    }

    @ExcelField(title = "三号红球",type = 0,align=2,sort=4)
    public Integer getRedThree() {
        return redThree;
    }

    public void setRedThree(Integer redThree) {
        this.redThree = redThree;
    }

    @ExcelField(title = "四号红球",type = 0,align=2,sort=5)
    public Integer getRedFour() {
        return redFour;
    }

    public void setRedFour(Integer redFour) {
        this.redFour = redFour;
    }

    @ExcelField(title = "五号红球",type = 0,align=2,sort=6)
    public Integer getRedFive() {
        return redFive;
    }

    public void setRedFive(Integer redFive) {
        this.redFive = redFive;
    }

    @ExcelField(title = "六号红球",type = 0,align=2,sort=7)
    public Integer getRedSix() {
        return redSix;
    }

    public void setRedSix(Integer redSix) {
        this.redSix = redSix;
    }

    @ExcelField(title = "蓝球",type = 0,align=2,sort=8)
    public Integer getBlue() {
        return blue;
    }

    public void setBlue(Integer blue) {
        this.blue = blue;
    }

    @ExcelField(title = "欢乐星期天",type = 0,align=2,sort=9)
    public String getHappySunday() {
        return happySunday;
    }

    public void setHappySunday(String happySunday) {
        this.happySunday = happySunday;
    }

    @ExcelField(title = "奖池奖金",type = 0,align=2,sort=10)
    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    @ExcelField(title = "一等奖数量",type = 0,align=2,sort=11)
    public Integer getNumberOfFirstPrize() {
        return numberOfFirstPrize;
    }

    public void setNumberOfFirstPrize(Integer numberOfFirstPrize) {
        this.numberOfFirstPrize = numberOfFirstPrize;
    }

    @ExcelField(title = "一等奖奖金",type = 0,align=2,sort=12)
    public Double getBonusOfFirstPrize() {
        return bonusOfFirstPrize;
    }

    public void setBonusOfFirstPrize(Double bonusOfFirstPrize) {
        this.bonusOfFirstPrize = bonusOfFirstPrize;
    }

    @ExcelField(title = "二等奖数量",type = 0,align=2,sort=13)
    public Integer getNumberOfSecondAward() {
        return numberOfSecondAward;
    }

    public void setNumberOfSecondAward(Integer numberOfSecondAward) {
        this.numberOfSecondAward = numberOfSecondAward;
    }

    @ExcelField(title = "二等奖奖金",type = 0,align=2,sort=14)
    public Double getBonusOfSecondAward() {
        return bonusOfSecondAward;
    }

    public void setBonusOfSecondAward(Double bonusOfSecondAward) {
        this.bonusOfSecondAward = bonusOfSecondAward;
    }

    @ExcelField(title = "总投注金额",type = 0,align=2,sort=15)
    public Double getTotalBets() {
        return totalBets;
    }

    public void setTotalBets(Double totalBets) {
        this.totalBets = totalBets;
    }

    @ExcelField(title = "开奖日期",type = 0,align=2,sort=16)
    public String getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(String drawDate) {
        this.drawDate = drawDate;
    }
}