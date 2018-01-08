package com.wang.walker.mydemo.view.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wang.walker.mydemo.R;
import com.wang.walker.mydemo.view.github.activity.GithubActivity;
import com.wang.walker.mydemo.view.ip.activity.IpActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Holaverse on 2017/7/28.
 */

public class IndexFragment extends Fragment {
    Unbinder unbinder;

    public static IndexFragment newInstance() {
        IndexFragment fragment = new IndexFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_index, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.button1)
    void onButton1Click() {
        startActivity(new Intent(getContext(), IpActivity.class));
    }

    @OnClick(R.id.button2)
    void onButton2Click() {
        startActivity(new Intent(getContext(), GithubActivity.class));
    }
}
