package edu.ksu.canvas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Item extends BaseCanvasModel implements Serializable {
    public static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private Long position;
    private Long indent;
    private Boolean quizLti;
    private String type;
    private Long moduleId;
    private String htmlUrl;
    private String pageUrl;
    private Date publishAt;
    private Long contentId;
    private String url;

    private Boolean published;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Long getIndent() {
        return indent;
    }

    public void setIndent(Long indent) {
        this.indent = indent;
    }

    public Boolean getQuizLti() {
        return quizLti;
    }

    public void setQuizLti(Boolean quizLti) {
        this.quizLti = quizLti;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public Date getPublishAt() {
        return publishAt;
    }

    public void setPublishAt(Date publishAt) {
        this.publishAt = publishAt;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }
}
