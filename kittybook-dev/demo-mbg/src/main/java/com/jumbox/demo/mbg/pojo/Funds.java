package com.jumbox.demo.mbg.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`funds`")
public class Funds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 判断进账还是出账
     */
    private Integer payment;

    private String title;

    private String content;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否启用
     */
    private Integer status;

    private Integer month;

    private Integer day;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取判断进账还是出账
     *
     * @return payment - 判断进账还是出账
     */
    public Integer getPayment() {
        return payment;
    }

    /**
     * 设置判断进账还是出账
     *
     * @param payment 判断进账还是出账
     */
    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取是否启用
     *
     * @return status - 是否启用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置是否启用
     *
     * @param status 是否启用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return month
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * @param month
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * @return day
     */
    public Integer getDay() {
        return day;
    }

    /**
     * @param day
     */
    public void setDay(Integer day) {
        this.day = day;
    }
}