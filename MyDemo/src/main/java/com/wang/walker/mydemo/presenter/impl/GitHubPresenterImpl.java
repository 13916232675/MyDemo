package com.wang.walker.mydemo.presenter.impl;

import com.wang.walker.mydemo.common.logger.Logger;
import com.wang.walker.mydemo.common.utils.UrlConstants;
import com.wang.walker.mydemo.model.network.RetrofitFactory;
import com.wang.walker.mydemo.model.network.github.entity.Contributor;
import com.wang.walker.mydemo.model.network.github.service.GitHubApiService;
import com.wang.walker.mydemo.presenter.GitHubPresenter;
import com.wang.walker.mydemo.view.github.view.GithubView;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Holaverse on 2018/1/4.
 */

public class GitHubPresenterImpl extends BasePresenterImpl<GithubView> implements GitHubPresenter {

    public GitHubPresenterImpl(GithubView view) {
        super(view);
    }

    @Override
    public void getContributor(String owner, String repo) {
        Retrofit retrofit = RetrofitFactory.create(UrlConstants.GITHUB_URL);
        GitHubApiService service = retrofit.create(GitHubApiService.class);

        mView.setGitText("");
        mView.showProgress();

        service.contributors(owner, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Contributor>>() {
                    @Override
                    public void onCompleted() {
                        mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgress();
                        mView.setGitText("fail: " + e.getMessage());
                    }

                    @Override
                    public void onNext(List<Contributor> contributors) {
                        mView.setGitText("success");
                        for (Contributor c : contributors) {
                            Logger.log("login:" + c.getLogin() + "  contributions:" + c.getContributions());
                        }
                    }
                });

        //        CompositeSubscription mSubscriptions = new CompositeSubscription();
        //        mSubscriptions.add();

    }
}
