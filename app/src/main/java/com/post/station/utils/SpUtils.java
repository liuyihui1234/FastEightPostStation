package com.post.station.utils;


/**
 * Created by sy on 2016/08/19.
 */

public class SpUtils {

    public static final String SIGNNETID = "SignNetId";
    public static final String USERID = "userId";
    public static final String CONTRACTFREE = "contractfree";//签订合同所用的费用
    public static final String AVATARURL = "avatarurl";//头像地址
    public static final String SIGNNAME = "signname";//代理的是省还是市，还是区县，还是网点
    public static final String INCNUMBER = "incnumber";//
    public static final String USERNUM = "usernum";//用户网签过后生成的账户
    public static final String TOKEN = "token";//用户登录过后生成的

    public static void saveSignNetId(String value) {
        BaseSPUtils.putString(SIGNNETID, value);
    }

    public static String getSignNetId() {
        return BaseSPUtils.getString(SIGNNETID, "");
    }

    public static void saveUserId(String value) {
        BaseSPUtils.putString(USERID, value);
    }

    public static String getUserId() {
        return BaseSPUtils.getString(USERID, "");
    }

    public static void saveContractFree(String joinprice) {
        BaseSPUtils.putString(CONTRACTFREE, joinprice);
    }

    public static String getContractFree() {
        return BaseSPUtils.getString(CONTRACTFREE, "");
    }

    public static void saveAvatarUrl(String url) {
        BaseSPUtils.putString(AVATARURL, url);
    }

    public static String getAvatarUrl() {
        return BaseSPUtils.getString(AVATARURL, "");
    }

    public static void saveSignName(String signName) {
        BaseSPUtils.putString(SIGNNAME, signName);
    }

    public static String getSignName() {
        return BaseSPUtils.getString(SIGNNAME, "");
    }

    public static void saveIncNumber(String incNumber) {
        BaseSPUtils.putString(INCNUMBER, incNumber);
    }

    public static String getIncNumber() {
        return BaseSPUtils.getString(INCNUMBER, "");
    }

    public static void saveUserNum(String userNum) {
        BaseSPUtils.putString(USERNUM, userNum);
    }

    public static String getUserNumr() {
        return BaseSPUtils.getString(USERNUM, "");
    }

    public static void saveToken(String token) {
        BaseSPUtils.putString(TOKEN, token);
    }

    public static String getToken() {
        return BaseSPUtils.getString(TOKEN, "");
    }
}
