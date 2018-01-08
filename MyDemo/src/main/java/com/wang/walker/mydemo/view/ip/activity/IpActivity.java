package com.wang.walker.mydemo.view.ip.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wang.walker.mydemo.R;
import com.wang.walker.mydemo.presenter.IpPresenter;
import com.wang.walker.mydemo.presenter.impl.IpPresenterImpl;
import com.wang.walker.mydemo.view.ip.view.IpView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IpActivity extends AppCompatActivity implements IpView {
    private IpPresenter mPresenter;

    @BindView(R.id.textIP)
    TextView mTextIP;

    @BindView(R.id.viewProgress)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);
        ButterKnife.bind(this);

        mPresenter = new IpPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destory();
        }

    }

    @OnClick(R.id.btnIP)
    public void getIp() {
        mPresenter.getIpInfo();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setIpInfo(String text) {
        mTextIP.setText(text);
    }
}
