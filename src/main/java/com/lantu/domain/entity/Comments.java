package com.lantu.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author gxccc
 * @since 2023-06-14
 */
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer aid;

    private String content;

    @JsonProperty("publish_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime publishDate;

    /**
     * -1表示正常回复，其他值表示是评论的回复
     */
    @JsonProperty("parent_id")
    private Integer parentId;

    @JsonProperty("user_name")
    private String userName;

    private Integer louceng;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getLouceng() {
        return louceng;
    }

    public void setLouceng(Integer louceng) {
        this.louceng = louceng;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", aid=" + aid +
                ", content=" + content +
                ", publishDate=" + publishDate +
                ", parentId=" + parentId +
                ", userName=" + userName +
                ", louceng=" + louceng +
                "}";
    }
}
