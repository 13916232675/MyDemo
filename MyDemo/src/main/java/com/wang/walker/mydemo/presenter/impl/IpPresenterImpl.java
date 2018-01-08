package com.wang.walker.mydemo.presenter.impl;

import com.wang.walker.mydemo.common.utils.UrlConstants;
import com.wang.walker.mydemo.model.network.RetrofitFactory;
import com.wang.walker.mydemo.model.network.ip.service.IpApiService;
import com.wang.walker.mydemo.presenter.IpPresenter;
import com.wang.walker.mydemo.view.ip.view.IpView;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Holaverse on 2018/1/3.
 */

public class IpPresenterImpl extends BasePresenterImpl<IpView> implements IpPresenter {

    public IpPresenterImpl(IpView view) {
        super(view);
    }

    @Override
    public void getIpInfo() {
        Retrofit retrofit = RetrofitFactory.create(UrlConstants.IP_URL);
        IpApiService service = retrofit.create(IpApiService.class);

        mView.setIpInfo("");
        mView.showProgress();

        service.getIpInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgress();
                        mView.setIpInfo("fail: " + e.getMessage());
                    }

                    @Override
                    public void onNext(String body) {
                        mView.setIpInfo(body);
                    }
                });

    }
}
