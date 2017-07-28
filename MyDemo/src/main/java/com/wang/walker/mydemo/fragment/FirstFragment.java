package com.wang.walker.mydemo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wang.walker.mydemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Holaverse on 2017/7/19.
 */

public class FirstFragment extends Fragment {
    Unbinder unbinder;

    @BindView(R.id.list)
    ListView mList;
    @BindView(R.id.layout)
    SwipeRefreshLayout mLayout;

    List<String> data;

    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add("First Page: " + i);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        final ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, data);
        mList.setAdapter(adapter);

        mLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.YELLOW);
        mLayout.setProgressBackgroundColorSchemeColor(Color.DKGRAY);

        mLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        for (int i = 0; i < 30; i++) {
                            data.add("Refresh Page: " + i);
                        }
                        adapter.notifyDataSetChanged();

                        if (mLayout.isRefreshing()) {
                            mLayout.setRefreshing(false);
                        }
                    }
                }, 5 * 1000);
            }
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
