package com.jumbox.demo.mbg.vo;

import com.jumbox.demo.mbg.pojo.Funds;
import io.swagger.models.auth.In;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

public class FinalFundsVo {

    private String date;

    private Integer weekDay;

    private List<FundsVo> fundsVoList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    public List<FundsVo> getFundsVoList() {
        return fundsVoList;
    }

    public void setFundsVoList(List<FundsVo> fundsVoList) {
        this.fundsVoList = fundsVoList;
    }

    public static FinalFundsVo POJOtoVO(Funds funds) {

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CHINA);

        FinalFundsVo finalFundsVo = new FinalFundsVo();
        finalFundsVo.setDate(df.format(funds.getCreateTime()));
        finalFundsVo.setWeekDay(funds.getCreateTime().getDay());

        return finalFundsVo;
    }

}
