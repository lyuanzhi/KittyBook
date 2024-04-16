package com.jumbox.demo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jumbox.demo.common.utils.PageResult;
import com.jumbox.demo.mbg.mapper.CommentMapper;
import com.jumbox.demo.mbg.mapper.FundsMapper;
import com.jumbox.demo.mbg.mapper.KittyInfoMapper;
import com.jumbox.demo.mbg.pojo.Comment;
import com.jumbox.demo.mbg.pojo.Funds;
import com.jumbox.demo.mbg.pojo.KittyInfo;
import com.jumbox.demo.mbg.vo.*;
import org.apache.ibatis.session.RowBounds;
import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class KittyServiceImpl implements KittyService {

    @Resource
    KittyInfoMapper kittyInfoMapper;

    @Resource
    CommentMapper commentMapper;

    @Resource
    FundsMapper fundsMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String addKitty(String name,String kittySex,String kittyHealth,String hairColor,String kittySterilization, String kittySterilizationTime,
                           String kittyBirthday,String firstSeen,String kittyCharacter,String kittyAppearance) {

        Example example = new Example(KittyInfo.class);
        example.createCriteria().andEqualTo("name", name);
        if(kittyInfoMapper.selectCountByExample(example)!=0){
            return "此姓名已存在";
        }

        KittyInfo kittyInfo = new KittyInfo();
        kittyInfo.setId(Sid.next());
        kittyInfo.setName(name);
        kittyInfo.setKittySex(kittySex);
        kittyInfo.setKittyHealth(kittyHealth);
        kittyInfo.setHairColor(hairColor);
        kittyInfo.setKittySterilization(kittySterilization);
        kittyInfo.setKittySterilizationTime(kittySterilizationTime);
        kittyInfo.setKittyBirthday(kittyBirthday);
        kittyInfo.setFirstSeen(firstSeen);
        kittyInfo.setKittyCharacter(kittyCharacter);
        kittyInfo.setKittyAppearance(kittyAppearance);
        kittyInfo.setCreateTime(new Date());
        kittyInfo.setStatus(1);
        kittyInfo.setHeartNum(0);

        kittyInfoMapper.insert(kittyInfo);
        return "success";
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<KittyInfoVo> selectKittyTopFive(){

        Example example = new Example(KittyInfo.class);
        example.setOrderByClause("heart_num desc");
        example.createCriteria().andEqualTo("status",1);
        RowBounds rowBounds = new RowBounds(0, 5);
        return KittyInfoVo.POJOtoVOList(kittyInfoMapper.selectByExampleAndRowBounds(example,rowBounds));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PageResult selectALLByPage(Integer page){
        PageHelper.startPage(page, 20);

        Example example = new Example(KittyInfo.class);
        example.createCriteria().andEqualTo("status",1);
        example.setOrderByClause("create_time desc");

        List<KittyInfo> kittyInfoList = kittyInfoMapper.selectByExample(example);
        List<KittyInfoVo> kittyInfoVoList = KittyInfoVo.POJOtoVOList(kittyInfoList);

        PageInfo<KittyInfo> pageList = new PageInfo<>(kittyInfoList);

        PageResult pagedResult = new PageResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(kittyInfoVoList);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public KittyDetailVo selectKittyInfo(String id){
        KittyInfo kittyInfo = new KittyInfo();
        kittyInfo.setId(id);
        return KittyDetailVo.POJOtoVO(kittyInfoMapper.selectByPrimaryKey(kittyInfo));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public String selectKittyByName(String name){
        Example example = new Example(KittyInfo.class);
        example.createCriteria().andEqualTo("name", name).andEqualTo("status",1);
        KittyInfo kittyInfo = kittyInfoMapper.selectOneByExample(example);
        if(kittyInfo==null){
            return "kittyNotFound";
        }
        return kittyInfo.getId();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String addHeart(String id, Integer heartNum){
        KittyInfo kittyInfo = new KittyInfo();
        kittyInfo.setId(id);
        kittyInfo.setHeartNum(heartNum);
        kittyInfoMapper.updateByPrimaryKeySelective(kittyInfo);
        return "success";
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CommentVo> getComment(String kittyId){

        Example example = new Example(Comment.class);
        example.createCriteria().andEqualTo("kittyId", kittyId);
        List<Comment> commentList = commentMapper.selectByExample(example);
        List<CommentVo> commentVoList = new ArrayList<>();
        for (Comment comment : commentList){
            if(comment.getParentId().equals("none")){
                CommentVo commentVo = CommentVo.POJOtoVO(comment);
                List<CommentVo> children = new ArrayList<>();
                for (Comment comment1 : commentList){
                    if(comment1.getParentId().equals(comment.getId())){
                        CommentVo commentVo1 = CommentVo.POJOtoVO(comment1);
                        commentVo1.setChildren(new ArrayList<>());
                        children.add(commentVo1);
                    }
                }
                commentVo.setChildren(children);
                commentVoList.add(commentVo);
            }
        }
        return commentVoList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String addComment(String kittyId,String parentId,String name,String responseName,String content){

        Comment comment = new Comment();
        comment.setId(Sid.next());
        comment.setKittyId(kittyId);
        comment.setParentId(parentId);
        comment.setName(name);
        comment.setResponseName(responseName);
        comment.setContent(content);
        comment.setCreateTime(new Date());
        comment.setStatus(1);
        comment.setLikeNum(0);

        commentMapper.insert(comment);
        return "success";
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String addLike(String id, Integer likeNum){
        Comment comment = new Comment();
        comment.setId(id);
        comment.setLikeNum(likeNum);
        commentMapper.updateByPrimaryKeySelective(comment);
        return "success";
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Boolean isExist(String id) {
        Example example = new Example(KittyInfo.class);
        example.createCriteria().andEqualTo("id", id);
        return kittyInfoMapper.selectCountByExample(example) == 1;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String uploadAvatarImg(String id, String avatarUrl){
        KittyInfo kittyInfo = new KittyInfo();
        kittyInfo.setId(id);
        kittyInfo.setAvatarUrl(avatarUrl);
        kittyInfoMapper.updateByPrimaryKeySelective(kittyInfo);
        return avatarUrl;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String uploadKittyImg(String id, String imgUrl){
        KittyInfo kittyInfo = new KittyInfo();
        kittyInfo.setId(id);
        kittyInfo.setImgUrl(imgUrl);
        kittyInfoMapper.updateByPrimaryKeySelective(kittyInfo);
        return imgUrl;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String addFund(String title, String content, Integer payment){
        Funds funds = new Funds();
        funds.setId(Sid.next());
        funds.setTitle(title);
        funds.setContent(content);
        funds.setPayment(payment);
        funds.setStatus(1);
        Date date = new Date();
        funds.setCreateTime(date);
        funds.setMonth(date.getMonth()+1);
        funds.setDay(date.getDate());
        fundsMapper.insert(funds);
        return "success";
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<FinalFundsVo> selectMonthlyFund(){
        Date date = new Date();
        Example example = new Example(Funds.class);
        example.createCriteria().andEqualTo("status",1).andEqualTo("month",date.getMonth()+1);
        example.setOrderByClause("create_time desc");
        List<Funds> fundsList = fundsMapper.selectByExample(example);
        List<FundsVo> fundsVoList = new ArrayList<>();
        List<FinalFundsVo> finalFundsVoList = new ArrayList<>();
        if(fundsList.isEmpty()){
            return finalFundsVoList;
        }
        Funds fundsNull = new Funds();
        fundsNull.setDay(-1);
        fundsList.add(fundsNull);
        int start = 31;
        int num = 0;
        for (Funds funds : fundsList){
            for(int i = start; i > 0; i--){
                if(funds.getDay() == i){
                    if(start != i){
                        start = i;
                        fundsVoList = new ArrayList<>(); //不知道为什么？？？？？
                        FinalFundsVo finalFundsVo = FinalFundsVo.POJOtoVO(fundsList.get(num));
                        finalFundsVo.setFundsVoList(fundsVoList);
                        finalFundsVoList.add(finalFundsVo);
                    }
                    fundsVoList.add(FundsVo.POJOtoVO(funds));
                    break;
                }
            }
            num++;
        }
        return finalFundsVoList;
    }

}
