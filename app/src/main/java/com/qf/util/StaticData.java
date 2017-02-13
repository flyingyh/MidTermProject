package com.qf.util;

/**
 * Created by Administrator on 2016/11/21.
 */

public class StaticData {
    //文章
    public static final String FILENAME= "message";
    public static final String PATH(int i,int n) {
        return "http://www.3dmgame.com/sitemap/api.php?row=5&typeid="+i+"&paging=1&page="+n;
    }

    //文章中的广告条
    public static String PATH5 = "http://api.ipadown.com/iphone-client/ad.flash.php?count=5";

    //文章下的评论
    public static String PATH3(String id){
        return "http://www.3dmgame.com/sitemap/api.php?type=1&aid="+id+"&pageno=1";
    }

    //提交评论接口
    public static String PATH6 = "http://www.3dmgame.com/sitemap/api.php?type=2";


//    public static final String PATH4 = "http://www.3dmgame.com/sitemap/api.php?type=2";
     public static final String HEAD_PIC = "http://www.3dmgame.com";

    //论坛网址
    public static final String PATH2 = "http://bbs.3dmgame.com/forum.php";

    //游戏下载
    public static String url(int i,int page){
        return "http://www.3dmgame.com/sitemap/api.php?row=12&typeid="+i+"&paging=1&page="+page;
    }
    public static final String MAINURL="http://www.3dmgame.com";
}
