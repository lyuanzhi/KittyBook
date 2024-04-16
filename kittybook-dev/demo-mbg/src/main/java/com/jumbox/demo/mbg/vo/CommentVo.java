package com.jumbox.demo.mbg.vo;

import com.jumbox.demo.mbg.pojo.Comment;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CommentVo {

    private String id;

    private String name;

    private String responseName;

    private String date;

    private Date dateTime;

    private String content;

    private Integer likeNum;

    private Integer status;

    private List<CommentVo> children;

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

    public String getResponseName() {
        return responseName;
    }

    public void setResponseName(String responseName) {
        this.responseName = responseName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public List<CommentVo> getChildren() {
        return children;
    }

    public void setChildren(List<CommentVo> children) {
        this.children = children;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public static CommentVo POJOtoVO(Comment comment) {

        CommentVo commentVo = new CommentVo();

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CHINA);

        commentVo.setId(comment.getId());
        commentVo.setContent(comment.getContent());
        commentVo.setName(comment.getName());
        commentVo.setResponseName(comment.getResponseName());
        commentVo.setDate(df.format(comment.getCreateTime()));
        commentVo.setDateTime(comment.getCreateTime());
        commentVo.setLikeNum(comment.getLikeNum());
        commentVo.setStatus(comment.getStatus());

        return commentVo;
    }

}
