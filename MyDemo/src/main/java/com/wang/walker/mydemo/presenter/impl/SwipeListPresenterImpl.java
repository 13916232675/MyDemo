package com.wang.walker.mydemo.presenter.impl;

import android.os.Handler;

import com.wang.walker.mydemo.presenter.SwipeListPresenter;
import com.wang.walker.mydemo.view.main.view.SwipeListView;

/**
 * Created by Holaverse on 2018/1/4.
 */

public class SwipeListPresenterImpl extends BasePresenterImpl<SwipeListView> implements SwipeListPresenter {

    public SwipeListPresenterImpl(SwipeListView view) {
        super(view);
    }

    @Override
    public void getNewDataList() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.refreshDataList();
            }
        }, 2 * 1000);
    }
}
