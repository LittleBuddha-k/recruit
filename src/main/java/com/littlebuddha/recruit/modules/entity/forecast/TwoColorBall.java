package com.littlebuddha.recruit.modules.entity.forecast;


import com.littlebuddha.recruit.modules.base.entity.DataEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private Date drawDate;

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public Integer getRedOne() {
        return redOne;
    }

    public void setRedOne(Integer redOne) {
        this.redOne = redOne;
    }

    public Integer getRedTwo() {
        return redTwo;
    }

    public void setRedTwo(Integer redTwo) {
        this.redTwo = redTwo;
    }

    public Integer getRedThree() {
        return redThree;
    }

    public void setRedThree(Integer redThree) {
        this.redThree = redThree;
    }

    public Integer getRedFour() {
        return redFour;
    }

    public void setRedFour(Integer redFour) {
        this.redFour = redFour;
    }

    public Integer getRedFive() {
        return redFive;
    }

    public void setRedFive(Integer redFive) {
        this.redFive = redFive;
    }

    public Integer getRedSix() {
        return redSix;
    }

    public void setRedSix(Integer redSix) {
        this.redSix = redSix;
    }

    public Integer getBlue() {
        return blue;
    }

    public void setBlue(Integer blue) {
        this.blue = blue;
    }

    public String getHappySunday() {
        return happySunday;
    }

    public void setHappySunday(String happySunday) {
        this.happySunday = happySunday;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Integer getNumberOfFirstPrize() {
        return numberOfFirstPrize;
    }

    public void setNumberOfFirstPrize(Integer numberOfFirstPrize) {
        this.numberOfFirstPrize = numberOfFirstPrize;
    }

    public Double getBonusOfFirstPrize() {
        return bonusOfFirstPrize;
    }

    public void setBonusOfFirstPrize(Double bonusOfFirstPrize) {
        this.bonusOfFirstPrize = bonusOfFirstPrize;
    }

    public Integer getNumberOfSecondAward() {
        return numberOfSecondAward;
    }

    public void setNumberOfSecondAward(Integer numberOfSecondAward) {
        this.numberOfSecondAward = numberOfSecondAward;
    }

    public Double getBonusOfSecondAward() {
        return bonusOfSecondAward;
    }

    public void setBonusOfSecondAward(Double bonusOfSecondAward) {
        this.bonusOfSecondAward = bonusOfSecondAward;
    }

    public Double getTotalBets() {
        return totalBets;
    }

    public void setTotalBets(Double totalBets) {
        this.totalBets = totalBets;
    }

    public Date getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(Date drawDate) {
        this.drawDate = drawDate;
    }
}