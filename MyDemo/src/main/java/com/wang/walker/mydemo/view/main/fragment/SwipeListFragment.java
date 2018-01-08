package com.wang.walker.mydemo.view.main.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wang.walker.mydemo.R;
import com.wang.walker.mydemo.presenter.SwipeListPresenter;
import com.wang.walker.mydemo.presenter.impl.SwipeListPresenterImpl;
import com.wang.walker.mydemo.view.main.view.SwipeListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Holaverse on 2017/7/19.
 */

public class SwipeListFragment extends Fragment implements SwipeListView {
    Unbinder unbinder;

    @BindView(R.id.list)
    ListView mList;
    @BindView(R.id.layout)
    SwipeRefreshLayout mLayout;

    List<String> data;
    SwipeListPresenter mPresenter;
    ArrayAdapter mAdapter;

    public static SwipeListFragment newInstance() {
        SwipeListFragment fragment = new SwipeListFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add("SwipeList Page: " + i);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_swipe_list, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        mPresenter = new SwipeListPresenterImpl(this);

        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, data);
        mList.setAdapter(mAdapter);

        mLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.YELLOW);
        mLayout.setProgressBackgroundColorSchemeColor(Color.DKGRAY);

        mLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getNewDataList();
            }
        });

        return rootView;
    }

    @Override
    public void refreshDataList() {
        data.clear();
        for (int i = 0; i < 30; i++) {
            data.add("Refresh Page: " + i);
        }
        mAdapter.notifyDataSetChanged();

        if (mLayout.isRefreshing()) {
            mLayout.setRefreshing(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mPresenter = null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
