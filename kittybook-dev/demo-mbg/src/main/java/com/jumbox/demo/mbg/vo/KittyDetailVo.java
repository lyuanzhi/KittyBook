package com.jumbox.demo.mbg.vo;

import com.jumbox.demo.mbg.pojo.KittyInfo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class KittyDetailVo {

    private String name;

    private String imgUrl;

    private String kittySex;

    private String kittyHealth;

    private String hairColor;

    private String kittySterilizationTime;

    private String kittySterilization;

    private String kittyBirthday;

    private String firstSeen;

    private String kittyCharacter;

    private String kittyAppearance;

    private Integer heartNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getKittySex() {
        return kittySex;
    }

    public void setKittySex(String kittySex) {
        this.kittySex = kittySex;
    }

    public String getKittyHealth() {
        return kittyHealth;
    }

    public void setKittyHealth(String kittyHealth) {
        this.kittyHealth = kittyHealth;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getKittySterilizationTime() {
        return kittySterilizationTime;
    }

    public void setKittySterilizationTime(String kittySterilizationTime) {
        this.kittySterilizationTime = kittySterilizationTime;
    }

    public String getKittySterilization() {
        return kittySterilization;
    }

    public void setKittySterilization(String kittySterilization) {
        this.kittySterilization = kittySterilization;
    }

    public String getKittyBirthday() {
        return kittyBirthday;
    }

    public void setKittyBirthday(String kittyBirthday) {
        this.kittyBirthday = kittyBirthday;
    }

    public String getFirstSeen() {
        return firstSeen;
    }

    public void setFirstSeen(String firstSeen) {
        this.firstSeen = firstSeen;
    }

    public String getKittyCharacter() {
        return kittyCharacter;
    }

    public void setKittyCharacter(String kittyCharacter) {
        this.kittyCharacter = kittyCharacter;
    }

    public String getKittyAppearance() {
        return kittyAppearance;
    }

    public void setKittyAppearance(String kittyAppearance) {
        this.kittyAppearance = kittyAppearance;
    }

    public Integer getHeartNum() {
        return heartNum;
    }

    public void setHeartNum(Integer heartNum) {
        this.heartNum = heartNum;
    }

    public static KittyDetailVo POJOtoVO(KittyInfo kittyInfo) {

        KittyDetailVo kittyDetailVo = new KittyDetailVo();

        kittyDetailVo.setName(kittyInfo.getName());
        kittyDetailVo.setKittySex(kittyInfo.getKittySex());
        kittyDetailVo.setKittyHealth(kittyInfo.getKittyHealth());
        kittyDetailVo.setKittyBirthday(kittyInfo.getKittyBirthday());
        kittyDetailVo.setKittyCharacter(kittyInfo.getKittyCharacter());
        kittyDetailVo.setKittyAppearance(kittyInfo.getKittyAppearance());
        kittyDetailVo.setFirstSeen(kittyInfo.getFirstSeen());
        kittyDetailVo.setHeartNum(kittyInfo.getHeartNum());
        kittyDetailVo.setHairColor(kittyInfo.getHairColor());
        kittyDetailVo.setImgUrl(kittyInfo.getImgUrl());
        kittyDetailVo.setKittySterilization(kittyInfo.getKittySterilization());
        kittyDetailVo.setKittySterilizationTime(kittyInfo.getKittySterilizationTime());

        return kittyDetailVo;
    }

}
