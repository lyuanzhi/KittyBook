package com.jumbox.demo.mbg.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`kitty_info`")
public class KittyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "kitty_sex")
    private String kittySex;

    /**
     * 健康状况
     */
    @Column(name = "kitty_health")
    private String kittyHealth;

    /**
     * 毛色
     */
    @Column(name = "hair_color")
    private String hairColor;

    /**
     * 绝育时间
     */
    @Column(name = "kitty_sterilization_time")
    private String kittySterilizationTime;

    /**
     * 绝育
     */
    @Column(name = "kitty_sterilization")
    private String kittySterilization;

    @Column(name = "kitty_birthday")
    private String kittyBirthday;

    /**
     * 第一次见的时间
     */
    @Column(name = "first_seen")
    private String firstSeen;

    /**
     * 性格
     */
    @Column(name = "kitty_character")
    private String kittyCharacter;

    /**
     * 外貌
     */
    @Column(name = "kitty_appearance")
    private String kittyAppearance;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否启用
     */
    private Integer status;

    @Column(name = "heart_num")
    private Integer heartNum;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return img_url
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * @return avatar_url
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * @param avatarUrl
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * @return kitty_sex
     */
    public String getKittySex() {
        return kittySex;
    }

    /**
     * @param kittySex
     */
    public void setKittySex(String kittySex) {
        this.kittySex = kittySex;
    }

    /**
     * 获取健康状况
     *
     * @return kitty_health - 健康状况
     */
    public String getKittyHealth() {
        return kittyHealth;
    }

    /**
     * 设置健康状况
     *
     * @param kittyHealth 健康状况
     */
    public void setKittyHealth(String kittyHealth) {
        this.kittyHealth = kittyHealth;
    }

    /**
     * 获取毛色
     *
     * @return hair_color - 毛色
     */
    public String getHairColor() {
        return hairColor;
    }

    /**
     * 设置毛色
     *
     * @param hairColor 毛色
     */
    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    /**
     * 获取绝育时间
     *
     * @return kitty_sterilization_time - 绝育时间
     */
    public String getKittySterilizationTime() {
        return kittySterilizationTime;
    }

    /**
     * 设置绝育时间
     *
     * @param kittySterilizationTime 绝育时间
     */
    public void setKittySterilizationTime(String kittySterilizationTime) {
        this.kittySterilizationTime = kittySterilizationTime;
    }

    /**
     * 获取绝育
     *
     * @return kitty_sterilization - 绝育
     */
    public String getKittySterilization() {
        return kittySterilization;
    }

    /**
     * 设置绝育
     *
     * @param kittySterilization 绝育
     */
    public void setKittySterilization(String kittySterilization) {
        this.kittySterilization = kittySterilization;
    }

    /**
     * @return kitty_birthday
     */
    public String getKittyBirthday() {
        return kittyBirthday;
    }

    /**
     * @param kittyBirthday
     */
    public void setKittyBirthday(String kittyBirthday) {
        this.kittyBirthday = kittyBirthday;
    }

    /**
     * 获取第一次见的时间
     *
     * @return first_seen - 第一次见的时间
     */
    public String getFirstSeen() {
        return firstSeen;
    }

    /**
     * 设置第一次见的时间
     *
     * @param firstSeen 第一次见的时间
     */
    public void setFirstSeen(String firstSeen) {
        this.firstSeen = firstSeen;
    }

    /**
     * 获取性格
     *
     * @return kitty_character - 性格
     */
    public String getKittyCharacter() {
        return kittyCharacter;
    }

    /**
     * 设置性格
     *
     * @param kittyCharacter 性格
     */
    public void setKittyCharacter(String kittyCharacter) {
        this.kittyCharacter = kittyCharacter;
    }

    /**
     * 获取外貌
     *
     * @return kitty_appearance - 外貌
     */
    public String getKittyAppearance() {
        return kittyAppearance;
    }

    /**
     * 设置外貌
     *
     * @param kittyAppearance 外貌
     */
    public void setKittyAppearance(String kittyAppearance) {
        this.kittyAppearance = kittyAppearance;
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
     * @return heart_num
     */
    public Integer getHeartNum() {
        return heartNum;
    }

    /**
     * @param heartNum
     */
    public void setHeartNum(Integer heartNum) {
        this.heartNum = heartNum;
    }
}