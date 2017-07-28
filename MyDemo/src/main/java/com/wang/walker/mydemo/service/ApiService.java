package com.wang.walker.mydemo.service;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Holaverse on 2017/7/19.
 */

public interface ApiService {
    @GET("/ip")
    Call<String> getIpInfo();


}
