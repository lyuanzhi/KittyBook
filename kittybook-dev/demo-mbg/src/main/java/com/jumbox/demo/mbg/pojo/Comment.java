package com.jumbox.demo.mbg.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`comment`")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    @Column(name = "response_name")
    private String responseName;

    @Column(name = "kitty_id")
    private String kittyId;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否启用
     */
    private Integer status;

    private String content;

    @Column(name = "like_num")
    private Integer likeNum;

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
     * @return response_name
     */
    public String getResponseName() {
        return responseName;
    }

    /**
     * @param responseName
     */
    public void setResponseName(String responseName) {
        this.responseName = responseName;
    }

    /**
     * @return kitty_id
     */
    public String getKittyId() {
        return kittyId;
    }

    /**
     * @param kittyId
     */
    public void setKittyId(String kittyId) {
        this.kittyId = kittyId;
    }

    /**
     * @return parent_id
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
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
     * @return like_num
     */
    public Integer getLikeNum() {
        return likeNum;
    }

    /**
     * @param likeNum
     */
    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }
}