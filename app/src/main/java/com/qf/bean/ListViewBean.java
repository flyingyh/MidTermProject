package com.qf.bean;

/**
 * Created by Administrator on 2016/11/22.
 */

public class ListViewBean {
    private String litpic;//图片网址
    private String feedback;//评论数
    private String title;//标题
    private String pubdate;//日期
    private String arcurl;
    private String id;

    public ListViewBean() {
    }

    public ListViewBean(String litpic, String feedback, String title, String pubdate, String arcurl, String id) {
        this.litpic = litpic;
        this.feedback = feedback;
        this.title = title;
        this.pubdate = pubdate;
        this.arcurl = arcurl;
        this.id = id;
    }

    public String getArcurl() {
        return arcurl;
    }

    public void setArcurl(String arcurl) {
        this.arcurl = arcurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    @Override
    public String toString() {
        return "ListViewBean{" +
                "litpic='" + litpic + '\'' +
                ", feedback='" + feedback + '\'' +
                ", title='" + title + '\'' +
                ", pubdate='" + pubdate + '\'' +
                ", arcurl='" + arcurl + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
