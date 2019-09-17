package com.post.station.model;

import androidx.annotation.NonNull;

import com.post.station.base.BaseModel;
import com.post.station.request.HttpResult;
import com.post.station.request.RetrofitUtil;
import com.post.station.response.VersionBean;

import java.util.List;
import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;
import rx.functions.Action1;

public class HomeModel extends BaseModel {

    @NonNull
    private static Service service = RetrofitUtil.getInstance().create(Service.class);

    interface Service {

        @POST("login/appLoginOut")
        Observable<HttpResult<Object>> appLoginOut(@Header("token") @NonNull String token);

        @POST("login/modifyPWD/sendSMS")
        @FormUrlEncoded
        Observable<HttpResult<String>> getForgetVerification(@Field("telephone") @NonNull String telephone);

        @POST("login/modifyPwd")
        @FormUrlEncoded
        Observable<HttpResult<Object>> modifyPwd(@Field("phoneNum") @NonNull String phoneNum,
                                                 @Field("smsCode") @NonNull String smsCode, @Field("newPwd") String newPwd);

        @POST("app/sign/getTotalPoints")
        @FormUrlEncoded
        Observable<HttpResult<Integer>> loadTotalIntegral(@Header("token") @NonNull String token, @Field("userid") @NonNull String userid);


        @POST("app/distributed/alterDistributed")
        @FormUrlEncoded
        Observable<HttpResult<Object>> upLoadSendOrderInfor(@Field("number") @NonNull String number
                , @Field("num") @NonNull String num, @Field("touserSignature") @NonNull String touserSignature,
                                                            @Field("fannex") @NonNull String fannex, @Field("id") @NonNull String id, @Field("description") @NonNull String description);


        @POST("Version/getlist")
        @FormUrlEncoded
        Observable<HttpResult<VersionBean>> getVersion(@Field("type") @NonNull String type);

        @POST("app/appoinment/alterStatus2AddOrder")
        @FormUrlEncoded
        Observable<HttpResult<Object>> pickUp(@Field("id") @NonNull String id);
    }

    //根据订单id点击收件 并在order中添加该订单
    public void pickUp(
            @NonNull String id, @NonNull Action1<Throwable> onError, @NonNull Action1<HttpResult<Object>> onResult) {
        register(service.pickUp(id), onError, onResult);
    }

    //获取服务器上的版本号
    public void getVersion(
            @NonNull String type, @NonNull Action1<Throwable> onError, @NonNull Action1<HttpResult<VersionBean>> onResult) {
        register(service.getVersion(type), onError, onResult);
    }

    /**
     * 上传派件页面派件的信息
     */
    public void upLoadSendOrderInfor(
            @NonNull String number, @NonNull String num, @NonNull String touserSignature,
            @NonNull String fannex,
            @NonNull String id,
            @NonNull String description,
            @NonNull Action1<Throwable> onError, @NonNull Action1<HttpResult<Object>> onResult) {
        register(service.upLoadSendOrderInfor(number, num, touserSignature, fannex
                , id, description), onError, onResult);
    }

    //退出登录
    public void appLoginOut(
            @NonNull String token, @NonNull Action1<Throwable> onError, @NonNull Action1<HttpResult<Object>> onResult) {
        register(service.appLoginOut(token), onError, onResult);
    }

    //获取验证码(忘记页面)
    public void getForgetVerification(
            @NonNull String telephone, @NonNull Action1<Throwable> onError, @NonNull Action1<HttpResult<String>> onResult) {
        register(service.getForgetVerification(telephone), onError, onResult);
    }

    //修改密码
    public void modifyPwd(
            @NonNull String phoneNum, @NonNull String smsCode, @NonNull String newPwd, @NonNull Action1<Throwable> onError, @NonNull Action1<HttpResult<Object>> onResult) {
        register(service.modifyPwd(phoneNum, smsCode, newPwd), onError, onResult);
    }
}
