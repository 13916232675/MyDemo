package com.avidly.testtool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.avidly.testtool.admob.AdmobActivity;
import com.avidly.testtool.analysis.AnalysisActivity;
import com.avidly.testtool.dexclassloader.DexClassActivity;
import com.avidly.testtool.packages.PackagesActivity;
import com.avidly.testtool.scale.ScaleActivity;
import com.hola.sdk.HolaAnalysis;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        HolaAnalysis.init(this, "800000", "000000");

        findViewById(R.id.btnPackage).setOnClickListener(this);
        findViewById(R.id.btnAnalysis).setOnClickListener(this);
        findViewById(R.id.btnScale).setOnClickListener(this);
        findViewById(R.id.btnDexClassLoader).setOnClickListener(this);
        findViewById(R.id.btnAdmob).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnPackage) {
            startActivity(new Intent(this, PackagesActivity.class));
        }

        if (v.getId() == R.id.btnAnalysis) {
            startActivity(new Intent(this, AnalysisActivity.class));
        }

        if (v.getId() == R.id.btnScale) {
            startActivity(new Intent(this, ScaleActivity.class));
        }
        if (v.getId() == R.id.btnDexClassLoader) {
            startActivity(new Intent(this, DexClassActivity.class));
        }
        if (v.getId() == R.id.btnAdmob) {
            startActivity(new Intent(this, AdmobActivity.class));
        }
    }
}
