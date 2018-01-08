package com.avidly.testtool.admob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.avidly.testtool.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import net.vidageek.mirror.dsl.Mirror;

import java.net.URLDecoder;

public class AdmobActivity extends AppCompatActivity {
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admob);

        findViewById(R.id.btnLoad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAd();
            }
        });

        findViewById(R.id.btnShow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }
        });
    }

    private void loadAd() {
        final AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd = new InterstitialAd(this);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();

                Log.i("TAG", "onAdClosed: ");
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);

                Log.i("TAG", "onAdFailedToLoad: ");
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();

                Log.i("TAG", "onAdLeftApplication: ");
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();

                Log.i("TAG", "onAdOpened: ");
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

                Log.i("TAG", "onAdLoaded: ");

                //                adInfo();
                xxxxxxx();
            }
        });

        mInterstitialAd.setAdUnitId("ca-app-pub-9986066456792135/1606720605");
        mInterstitialAd.loadAd(adRequest);
    }

    private void xxxxxxx() {
        try {
            Object zzsb = new Mirror().on(mInterstitialAd).get().field("zzsb");
            Object zzBg = new Mirror().on(zzsb).get().field("zzBg");
            Object zzsP = new Mirror().on(zzBg).get().field("zzsP");
            Object zzvY = new Mirror().on(zzsP).get().field("zzvY");
            Object zzTt = new Mirror().on(zzvY).get().field("zzTt");

            if (zzTt == null) {
                return;
            }

            String info = zzTt.toString();

            int start = info.indexOf("adurl");
            if (start > 0) {
                String adurl = info.substring(start);
                Log.i("TAG", "adurl: " + adurl);


                if (!TextUtils.isEmpty(adurl)) {
                    adurl = URLDecoder.decode(URLDecoder.decode(adurl, "UTF-8"), "UTF-8");
                    Log.i("TAG", "adurl: " + adurl);

                    int end = adurl.indexOf("&");
                    if (end > 0) {
                        adurl = adurl.substring(6, end);
                        Log.i("TAG", "adurl: " + adurl);
                    }
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "Exception: " + e.getStackTrace());
        }
    }

    private void adInfo() {
        try {
            Object zzpn = new Mirror().on(mInterstitialAd).get().field("zzpn");
            Object zzwh = new Mirror().on(zzpn).get().field("zzwh");
            Object zzpb = new Mirror().on(zzwh).get().field("zzpb");
            Object d = new Mirror().on(zzpb).get().field("d");
            Object j = new Mirror().on(d).get().field("j");
            Object z = new Mirror().on(j).get().field("z");

            if (z == null) {
                return;
            }

            String z_s = z.toString();
            Log.i("TAG", "z_s: " + z_s);

            int start = z_s.indexOf("adurl");
            if (start > 0) {
                String z_s_f = z_s.substring(start);
                Log.i("TAG", "z_s_f: " + z_s_f);

                if (!TextUtils.isEmpty(z_s_f)) {
                    z_s_f = URLDecoder.decode(z_s_f, "UTF-8");
                    Log.i("TAG", "z_s_f: " + z_s_f);

                    z_s_f = URLDecoder.decode(z_s_f, "UTF-8");
                    Log.i("TAG", "z_s_f: " + z_s_f);
                }
            }
        } catch (Exception e) {

        }
    }
}
