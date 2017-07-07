package com.avidly.testtool.packages;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Holaverse on 2017/6/15.
 */

public class Tools {

    public static List<AppBean> getAllApk(Context context, boolean all) {
        List<AppBean> appBeanList = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> list = packageManager.getInstalledPackages(0);
        for (PackageInfo p : list) {
            AppBean bean = new AppBean();
            bean.setAppIcon(p.applicationInfo.loadIcon(packageManager));
            bean.setAppName(packageManager.getApplicationLabel(p.applicationInfo).toString());
            bean.setAppPackageName(p.applicationInfo.packageName);
            bean.setApkPath(p.applicationInfo.sourceDir);
            File file = new File(p.applicationInfo.sourceDir);
            bean.setAppSize((int) file.length());
            int flags = p.applicationInfo.flags;
            //判断是否是属于系统的apk
            if ((flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                bean.setSystem(true);
                if (all) {
                    appBeanList.add(bean);
                }
            } else {
                bean.setSd(true);
                appBeanList.add(bean);
            }
        }

        return appBeanList;
    }

    // 5.0以下
    public static List<AppBean> getRectApp(Context context) {
        List<AppBean> appBeanList = new ArrayList<>();

        PackageManager pm = context.getPackageManager();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RecentTaskInfo> appList4 = mActivityManager
                .getRecentTasks(5, ActivityManager.RECENT_IGNORE_UNAVAILABLE);//参数，前一个是你要取的最大数，后一个是状态
        for (ActivityManager.RecentTaskInfo running : appList4) {
            Intent intent = running.baseIntent;
            ResolveInfo resolveInfo = pm.resolveActivity(intent, 0);
            if (resolveInfo != null) {
                AppBean bean = new AppBean();

                bean.setAppIcon(resolveInfo.loadIcon(pm));
                bean.setAppName(resolveInfo.loadLabel(pm).toString());
                bean.setAppPackageName(resolveInfo.activityInfo.packageName);
                appBeanList.add(bean);
            }
        }

        return appBeanList;
    }

    // 5.1以上
    public static List<AppBean> getAppInfos(Context context) {
        List<AppBean> appBeanList = new ArrayList<>();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            try {

                // 根据最近time_ms毫秒内的应用统计信息进行排序获取当前顶端的包名
                long time = System.currentTimeMillis();
                UsageStatsManager usage = (UsageStatsManager) context.getSystemService("usagestats");
                List<UsageStats> usageStatsList = usage.queryUsageStats(UsageStatsManager.INTERVAL_BEST, time - 60 * 60 * 1000, time);

                if (usageStatsList != null && usageStatsList.size() > 0) {
                    SortedMap<Long, UsageStats> runningTask = new TreeMap<>();
                    for (UsageStats usageStats : usageStatsList) {
                        runningTask.put(usageStats.getLastTimeUsed(), usageStats);
                    }

                    if (!runningTask.isEmpty()) {
                        for (UsageStats stats : runningTask.values()) {
                            AppBean bean = new AppBean();
                            bean.setAppName(new Date(stats.getLastTimeStamp()).toString());
                            bean.setAppPackageName(stats.getPackageName());
                            appBeanList.add(bean);
                        }
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return appBeanList;
    }

    public static boolean hasPermisson(Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                PackageManager packageManager = context.getPackageManager();
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);

                AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
                int mode = appOpsManager
                        .checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, applicationInfo.uid, applicationInfo.packageName);
                return mode == AppOpsManager.MODE_ALLOWED;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

}
