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
 * @since 2023-06-15
 */
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    /**
     * md文件源码
     */
    @JsonProperty("md_content")
    private String mdContent;

    /**
     * html源码
     */
    @JsonProperty("html_content")
    private String htmlContent;

    private String summary;

    private Integer cid;

    @JsonProperty("publish_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime publishDate;
    @JsonProperty("edit_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime editTime;

    /**
     * 0表示草稿箱，1表示已发表，2表示已删除
     */
    private Integer state;

    private Integer pageView;

    private Integer whetherNiming;

    @JsonProperty("user_name")
    private String userName;

    private Integer view;

    private Integer xihuan;

    private Integer collect;

    private String catgory;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMdContent() {
        return mdContent;
    }

    public void setMdContent(String mdContent) {
        this.mdContent = mdContent;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public LocalDateTime getEditTime() {
        return editTime;
    }

    public void setEditTime(LocalDateTime editTime) {
        this.editTime = editTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    public Integer getWhetherNiming() {
        return whetherNiming;
    }

    public void setWhetherNiming(Integer whetherNiming) {
        this.whetherNiming = whetherNiming;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Integer getXihuan() {
        return xihuan;
    }

    public void setXihuan(Integer xihuan) {
        this.xihuan = xihuan;
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    public String getCatgory() {
        return catgory;
    }

    public void setCatgory(String catgory) {
        this.catgory = catgory;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title=" + title +
                ", mdContent=" + mdContent +
                ", htmlContent=" + htmlContent +
                ", summary=" + summary +
                ", cid=" + cid +
                ", publishDate=" + publishDate +
                ", editTime=" + editTime +
                ", state=" + state +
                ", pageView=" + pageView +
                ", whetherNiming=" + whetherNiming +
                ", userName=" + userName +
                ", view=" + view +
                ", xihuan=" + xihuan +
                ", collect=" + collect +
                ", catgory=" + catgory +
                "}";
    }
}
