package com.wang.walker.mydemo.presenter.impl;

import com.wang.walker.mydemo.presenter.BasePresenter;
import com.wang.walker.mydemo.view.BaseView;

/**
 * Created by Holaverse on 2018/1/3.
 */

public class BasePresenterImpl<T extends BaseView> implements BasePresenter {
    protected T mView;

    public BasePresenterImpl(T view) {
        mView = view;
    }

    @Override
    public void destory() {
        mView = null;
    }
}
