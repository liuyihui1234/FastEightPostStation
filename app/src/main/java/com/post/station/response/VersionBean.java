package com.post.station.response;

public class VersionBean {
    /**
     * id : 1
     * type : 1
     * version : 1.02
     * downloadurl : http://www.k8yz.com/su8kuaidi.apk
     * crttime : 1563520811000
     * validtime : 1564212015000
     * "forceUpdateFlage": 0,
     * "remark": "android  更新\r\n 语音识别",
     */

    private int id;
    private int type;
    private String version;
    private String downloadurl;
    private String crttime;
    private String validtime;
    private int forceUpdateFlage;
    private String remark;

    public int getForceUpdateFlage() {
        return forceUpdateFlage;
    }

    public void setForceUpdateFlage(int forceUpdateFlage) {
        this.forceUpdateFlage = forceUpdateFlage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDownloadurl() {
        return downloadurl;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl;
    }

    public String getCrttime() {
        return crttime;
    }

    public void setCrttime(String crttime) {
        this.crttime = crttime;
    }

    public String getValidtime() {
        return validtime;
    }

    public void setValidtime(String validtime) {
        this.validtime = validtime;
    }
}
