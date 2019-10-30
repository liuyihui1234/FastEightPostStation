package com.post.station.response;

/*
 *  项目名： FastEightPostStation
 *  包名： com.post.station.response
 *  创建时间：2019/10/2310:38
 *  作者：wpx
 *  描述：
 */public class BandBeans {
    private String notice;
    private String name;
    private String content;
    private String time;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}