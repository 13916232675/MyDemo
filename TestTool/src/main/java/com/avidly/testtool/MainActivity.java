package com.avidly.testtool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.avidly.testtool.analysis.AnalysisActivity;
import com.avidly.testtool.packages.Packages;
import com.hola.sdk.HolaAnalysis;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnPackage;
    Button btnAnalysis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HolaAnalysis.init(this, "800000", "000000");

        btnPackage = (Button) findViewById(R.id.btnPackage);
        btnAnalysis = (Button) findViewById(R.id.btnAnalysis);

        btnPackage.setOnClickListener(this);
        btnAnalysis.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnPackage) {
            startActivity(new Intent(this, Packages.class));
        }

        if (v.getId() == R.id.btnAnalysis) {
            startActivity(new Intent(this, AnalysisActivity.class));
        }
    }
}
