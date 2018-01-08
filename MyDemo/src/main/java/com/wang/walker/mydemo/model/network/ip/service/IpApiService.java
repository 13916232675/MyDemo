package com.wang.walker.mydemo.model.network.ip.service;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Holaverse on 2017/7/19.
 */

public interface IpApiService {
    //        @GET("/ip")
    //        Call<String> getIpInfo();


    @GET("/ip")
    Observable<String> getIpInfo();
}
