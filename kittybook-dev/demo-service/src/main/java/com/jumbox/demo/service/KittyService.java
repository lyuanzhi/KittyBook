package com.jumbox.demo.service;

import com.jumbox.demo.common.utils.PageResult;
import com.jumbox.demo.mbg.pojo.Funds;
import com.jumbox.demo.mbg.vo.CommentVo;
import com.jumbox.demo.mbg.vo.FinalFundsVo;
import com.jumbox.demo.mbg.vo.KittyDetailVo;
import com.jumbox.demo.mbg.vo.KittyInfoVo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface KittyService {

    public String addKitty(String name,String kittySex,String kittyHealth,String hairColor,String kittySterilization,
                           String kittySterilizationTime,String kittyBirthday,String firstSeen,String kittyCharacter,String kittyAppearance);

    public List<KittyInfoVo> selectKittyTopFive();

    public PageResult selectALLByPage(Integer page);

    public KittyDetailVo selectKittyInfo(String id);

    public String selectKittyByName(String name);

    public String addHeart(String id, Integer heartNum);

    public List<CommentVo> getComment(String kittyId);

    public String addComment(String kittyId,String parentId,String name,String responseName,String content);

    public String addLike(String id, Integer likeNum);

    public Boolean isExist(String id);

    public String uploadAvatarImg(String id, String avatarUrl);

    public String uploadKittyImg(String id, String imgUrl);

    public String addFund(String title, String content, Integer payment);

    public List<FinalFundsVo> selectMonthlyFund();

}
