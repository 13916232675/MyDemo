package com.avidly.testtool.analysis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.avidly.testtool.R;
import com.hola.sdk.HolaAnalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        HolaAnalysis.registGetUserIdListener(new HolaAnalysis.GetUerIdListener() {
            @Override
            public void onSuccess(String s) {
                Log.i("wt", "onSuccess: " + s);
            }
        });

        List<Map<String, String>> list = new ArrayList<>();
        for (int l = 0; l < 10; l++) {
            HashMap<String, String> map = new HashMap<>();
            for (int i = 0; i < 10; i++) {
                map.put("key" + i, "valuevaluevaluevaluevaluevaluevaluevaluevaluevaluevaluevaluevalue" + i);
            }
            list.add(map);
        }

        HolaAnalysis.log(this, "test", list);

    }
}
