package com.wang.walker.nativeaddemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.holaverse.ad.core.AdAccessConfig;
import com.holaverse.ad.core.HolaNativeAd;
import com.holaverse.ad.core.PendingAdFactory;
import com.statistics.channel.ChannelS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Holaverse on 2017/1/10.
 */

public class HolaAdAccessConfig implements AdAccessConfig {
    public static final String HOLA_PLACEMENT = "lucky3";

    private static final String TAG = "HolaAdAccessConfig";

    @Override
    public PendingAdFactory getAdSerialPendingList(String s) {
        Log.w(TAG, "getAdSerialPendingList: ");

        // 顺序执行
        List<PendingAdFactory.FixedPlacementItem> list = new ArrayList<>();

        list.add(new PendingAdFactory.FixedPlacementItem("so", "-1")); // {"code":500,"msg":"NEED PARAMETER imei","data":[]}
        list.add(new PendingAdFactory.FixedPlacementItem("im", "1447173274454")); // inmobi-native-dynamicloading-sdk-5.1.0-1.jar有问题

        list.add(new PendingAdFactory.FixedPlacementItem("du", "10597")); // 成功
        list.add(new PendingAdFactory.FixedPlacementItem("an", "-1")); // 成功

        list.add(new PendingAdFactory.FixedPlacementItem("gg", "ca-app-pub-2164674174362877/1354090345")); // 成功
        list.add(new PendingAdFactory.FixedPlacementItem("qq", "4050912760267595")); // 成功
        list.add(new PendingAdFactory.FixedPlacementItem("fb", "910040559031674_910054805696916")); // 成功

        return new PendingAdFactory.FixedPendingAdFactory(HOLA_PLACEMENT, list);
    }

    @Override
    public PendingAdFactory getAdParallelPendingList(String s) {
        Log.w(TAG, "getAdParallelPendingList: ");

        // 并发执行
        List<PendingAdFactory.FixedPlacementItem> list = new ArrayList<>();

        list.add(new PendingAdFactory.FixedPlacementItem("gg", "ca-app-pub-2164674174362877/1354090345")); // 成功
        list.add(new PendingAdFactory.FixedPlacementItem("qq", "4050912760267595")); // 成功
        list.add(new PendingAdFactory.FixedPlacementItem("fb", "910040559031674_910054805696916")); // 成功

        return new PendingAdFactory.FixedPendingAdFactory(HOLA_PLACEMENT, list);
        //        return null;
    }

    @Override
    public String getPlacement(String s, String s1) {
        Log.w(TAG, "getPlacement: ");

        return null;
    }

    @Override
    public String getStatViewId(String s) {
        Log.w(TAG, "getStatViewId: ");

        return "test_stat_view_id";
    }

    @Override
    public long getCustomExpireTime(String s) {
        Log.w(TAG, "getCustomExpireTime: ");

        return 10000;
    }

    @Override
    public Map<Integer, String> getDuPlacementMapping() {
        Log.w(TAG, "getDuPlacementMapping: ");

        Map<Integer, String> ret = new HashMap<Integer, String>();
        ret.put(10597, "910040559031674_944679218901141");
        return ret;
    }

    @Override
    public List<String> getDuFbPlacementIds(String s) {
        Log.w(TAG, "getDuFbPlacementIds: ");

        return null;
    }

    @Override
    public int[] getSoAdDataCandidateType(String s) {
        Log.w(TAG, "getSoAdDataCandidateType: ");

        return new int[0];
    }

    @Override
    public String getIMAdPropertyId() {
        Log.w(TAG, "getIMAdPropertyId: ");

        return "1447173274454";
    }

    @Override
    public String getGDTAppId() {
        Log.w(TAG, "getGDTAppId: ");

        return "4050912760267595";
    }

    @Override
    public boolean inGdtDownloadServiceProcess(Context context) {
        Log.w(TAG, "inGdtDownloadServiceProcess: ");

        return true;
    }

    @Override
    public boolean useFatKeyClick(HolaNativeAd holaNativeAd) {
        Log.w(TAG, "useFatKeyClick: ");

        return false;
    }

    @Override
    public String getUid(Context context) {
        Log.w(TAG, "getUid: ");

        return null;
    }

    @Override
    public String getGaid(Context context) {
        Log.w(TAG, "getGaid: ");

        return ChannelS.getInstance().getAdvertisingId(App.getApp());
    }

    @Override
    public String getPid(Context context) {
        Log.w(TAG, "getPid: ");

        return "400102";
    }

    @Override
    public Application getApplication() {
        Log.w(TAG, "getApplication: ");

        return App.getApp();
    }

    @Override
    public boolean enableAdGG(Context context) {
        Log.w(TAG, "enableAdGG: ");

        return true;
    }

    @Override
    public boolean supportVerticalGGImage(String s) {
        Log.w(TAG, "supportVerticalGGImage: ");

        return false;
    }

    @Override
    public String getGGSdkAssetName(Context context) {
        Log.w(TAG, "getGGSdkAssetName: ");

        return "gg-native-dynamicloading-sdk.jar";
    }

    @Override
    public boolean enableAdIM(Context context) {
        Log.w(TAG, "enableAdIM: ");

        return true;
    }

    @Override
    public String getIMSdkAssetName(Context context) {
        Log.w(TAG, "getIMSdkAssetName: ");

        //        return "inmobi-native-dynamicloading-sdk-5.1.0-1.jar";
        return null;
    }

    @Override
    public void onLoadNativeAccessSuccess(Context context, String s) {
        Log.w(TAG, "onLoadNativeAccessSuccess: ");

    }

    @Override
    public void onLoadNativeAccessError(Context context, String s, int i, String s1) {
        Log.w(TAG, "onLoadNativeAccessError: ");

    }
}
