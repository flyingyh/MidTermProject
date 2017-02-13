package com.qf.bean;

/**
 * Created by Administrator on 2016/11/23.
 */

public class GridViewBean {
    private String type;
    private String title;
    private String data;
    private String litpic;
    private String click;
    private String typename;
    private String arcurl;
    private String id;
    private String description;
    private String game_bbs;
    private String release_company;
    private String made_company;
    private String terrace;
    private String websit;
    private String release_date;

    public GridViewBean() {

    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getWebsit() {
        return websit;
    }

    public void setWebsit(String websit) {
        this.websit = websit;
    }

    public String getGame_bbs() {
        return game_bbs;
    }

    public void setGame_bbs(String game_bbs) {
        this.game_bbs = game_bbs;
    }

    public String getRelease_company() {
        return release_company;
    }

    public void setRelease_company(String release_company) {
        this.release_company = release_company;
    }

    public String getMade_company() {
        return made_company;
    }

    public void setMade_company(String made_company) {
        this.made_company = made_company;
    }

    public String getTerrace() {
        return terrace;
    }

    public void setTerrace(String terrace) {
        this.terrace = terrace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArcurl() {
        return arcurl;
    }

    public void setArcurl(String arcurl) {
        this.arcurl = arcurl;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }

    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }


    public String getTitle() {
        return title;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "GridViewBean{" +
                "type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", data='" + data + '\'' +
                ", litpic='" + litpic + '\'' +
                ", click='" + click + '\'' +
                '}';
    }
}
