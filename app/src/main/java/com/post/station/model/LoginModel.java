package com.post.station.model;

import androidx.annotation.NonNull;

import com.post.station.base.BaseModel;
import com.post.station.request.HttpResult;
import com.post.station.request.RetrofitUtil;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by ${yyy} on 18-5-17.
 * Model of SignIn SignUp BindMobileNumber
 */

public class LoginModel extends BaseModel {

    @NonNull
    private Service service = RetrofitUtil.getInstance().create(Service.class);

    interface Service {

        @POST("Netsign/checkRegionNetSign")
        @FormUrlEncoded
        Observable<HttpResult<HasSignBean>> hasSign(@Field("provinceCode") @NonNull String province,
                                                    @Field("cityCode") @NonNull String city,
                                                    @Field("network") @NonNull String network, @Field("county") @NonNull String county);
    }

    //判断是否为网签
    public void hasSign(
            @NonNull String province,
            @NonNull String city,
            @NonNull String network,
            @NonNull String county, @NonNull Action1<Throwable> onError, @NonNull Action1<HttpResult<HasSignBean>> onResult) {
        register(service.hasSign(province, city, network, county), onError, onResult);
    }


    public class HasSignBean {
        public boolean hasSigned;
    }
}
