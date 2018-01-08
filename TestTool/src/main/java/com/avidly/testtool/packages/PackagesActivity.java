package com.avidly.testtool.packages;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.avidly.testtool.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Holaverse on 2017/6/15.
 */

public class PackagesActivity extends Activity implements View.OnClickListener {
    ListView mListView;
    Button btnInstall;
    Button btnAll;
    Button btnRect;
    ArrayList<AppBean> mAppList = new ArrayList<>();
    AppAdapter mAppAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);

        mListView = (ListView) findViewById(R.id.listView);
        mAppAdapter = new AppAdapter(this, mAppList);
        mListView.setAdapter(mAppAdapter);

        btnInstall = (Button) findViewById(R.id.btnInstall);
        btnAll = (Button) findViewById(R.id.btnAll);
        btnRect = (Button) findViewById(R.id.btnRect);

        btnInstall.setOnClickListener(this);
        btnAll.setOnClickListener(this);
        btnRect.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnInstall) {
            mAppList.clear();
            mAppList.addAll(Tools.getAllApk(this, false));
            mAppAdapter.setRect(false);
            mAppAdapter.notifyDataSetChanged();
        }

        if (v.getId() == R.id.btnAll) {
            mAppList.clear();
            mAppList.addAll(Tools.getAllApk(this, true));
            mAppAdapter.setRect(false);
            mAppAdapter.notifyDataSetChanged();
        }

        if (v.getId() == R.id.btnRect) {
            if (Tools.hasPermisson(this)) {
                mAppList.clear();
                mAppList.addAll(Tools.getAppInfos(this));
                mAppAdapter.setRect(true);
                mAppAdapter.notifyDataSetChanged();
            } else {

                Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
                startActivity(intent);
            }
        }
    }

    class AppAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        private List<AppBean> mData;

        private boolean mRect;

        public void setRect(boolean rect) {
            mRect = rect;
        }

        public AppAdapter(Context context, List<AppBean> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }


        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.package_item, null);
                holder.mIcon = (ImageView) convertView.findViewById(R.id.icon);
                holder.mAppName = (TextView) convertView.findViewById((R.id.appName));
                holder.mSystem = (TextView) convertView.findViewById((R.id.system));
                holder.mPackageName = (TextView) convertView.findViewById((R.id.packageName));
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            AppBean appBean = mData.get(position);
            holder.mIcon.setImageDrawable(appBean.getAppIcon());
            holder.mAppName.setText(appBean.getAppName());
            if (!mRect) {
                holder.mSystem.setText(appBean.isSystem() ? "系统应用" : "非系统应用");
            } else {
                holder.mSystem.setVisibility(View.GONE);
            }
            holder.mPackageName.setText(appBean.getAppPackageName());
            return convertView;
        }
    }

    class ViewHolder {
        ImageView mIcon;
        TextView mAppName;
        TextView mSystem;
        TextView mPackageName;
    }

}
