package com.jumbox.demo.mbg.vo;

import com.jumbox.demo.mbg.pojo.KittyInfo;

import java.util.ArrayList;
import java.util.List;

public class KittyInfoVo {

    private String id;

    private String name;

    private String avatarUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public static List<KittyInfoVo> POJOtoVOList(List<KittyInfo> kittyInfoList) {

        List<KittyInfoVo> kittyInfoVoList = new ArrayList<>();

        for (KittyInfo kittyInfo: kittyInfoList){
            KittyInfoVo kittyInfoVo = new KittyInfoVo();
            kittyInfoVo.setId(kittyInfo.getId());
            kittyInfoVo.setName(kittyInfo.getName());
            kittyInfoVo.setAvatarUrl(kittyInfo.getAvatarUrl());
            kittyInfoVoList.add(kittyInfoVo);
        }

        return kittyInfoVoList;
    }
}
