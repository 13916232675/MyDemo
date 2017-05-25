package com.wang.walker.nativeaddemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.holaverse.ad.core.AdSettings;
import com.holaverse.ad.core.HolaNativeAd;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoad = (Button) findViewById(R.id.buttonLoad);
        btnLoad.setOnClickListener(this);

        AdSettings.setup(this, true, new HolaAdAccessConfig());
    }

    public static HolaNativeAd createHolaNativeAd(Context context, String placementId) {
        HolaNativeAd ad = new HolaNativeAd(context, placementId);
        ad.setAdListener(new HolaNativeAd.NativeAdListener() {
            @Override
            public void onAdDataLoadedBeforeImageProcessed(HolaNativeAd holaNativeAd) {
                Log.e(TAG, "onAdDataLoadedBeforeImageProcessed: ");
            }

            @Override
            public void onAdLoaded(HolaNativeAd holaNativeAd) {
                Log.e(TAG, "onAdLoaded: ");
            }

            @Override
            public void onError(HolaNativeAd holaNativeAd, String s) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onAdClicked(HolaNativeAd holaNativeAd) {
                Log.e(TAG, "onAdClicked: ");
            }
        });
        return ad;
    }

    private void loadAd(HolaNativeAd ad) {
        ad.loadAd();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonLoad) {
            HolaNativeAd ad = createHolaNativeAd(MainActivity.this, HolaAdAccessConfig.HOLA_PLACEMENT);
            loadAd(ad);
        }
    }
}
