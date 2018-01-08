package com.wang.walker.mydemo.presenter;

/**
 * Created by Holaverse on 2018/1/4.
 */

public interface GitHubPresenter extends BasePresenter {
    void getContributor(String owner, String repo);
}
