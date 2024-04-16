package com.jumbox.demo.mbg.vo;

import com.jumbox.demo.mbg.pojo.Funds;

public class FundsVo {

    private String id;

    private Integer payment;

    private String title;

    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static FundsVo POJOtoVO(Funds funds) {

        FundsVo fundsVo = new FundsVo();
        fundsVo.setId(funds.getId());
        fundsVo.setContent(funds.getContent());
        fundsVo.setPayment(funds.getPayment());
        fundsVo.setTitle(funds.getTitle());

        return fundsVo;
    }

}
