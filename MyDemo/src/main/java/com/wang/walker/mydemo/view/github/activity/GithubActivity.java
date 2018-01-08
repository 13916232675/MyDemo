package com.wang.walker.mydemo.view.github.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wang.walker.mydemo.R;
import com.wang.walker.mydemo.presenter.GitHubPresenter;
import com.wang.walker.mydemo.presenter.impl.GitHubPresenterImpl;
import com.wang.walker.mydemo.view.github.view.GithubView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class GithubActivity extends AppCompatActivity implements GithubView {
    GitHubPresenter mPresenter;

    @BindView(R.id.viewProgress)
    ProgressBar mProgressBar;
    @BindView(R.id.textGithub)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github);
        ButterKnife.bind(this);
        mPresenter = new GitHubPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destory();
        }
    }

    @OnClick(R.id.btnGithub)
    void contributor() {
        mPresenter.getContributor("square", "retrofit");
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
    public void setGitText(String text) {
        mTextView.setText(text);
    }
}
