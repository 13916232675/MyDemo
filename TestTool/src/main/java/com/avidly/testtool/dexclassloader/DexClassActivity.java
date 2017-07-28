package com.avidly.testtool.dexclassloader;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.applovin.sdk.AppLovinSdk;
import com.appnext.base.Appnext;
import com.avidly.testtool.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import dalvik.system.DexClassLoader;

public class DexClassActivity extends AppCompatActivity {
    private static final String TAG = "DexClassActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dex_class);

        copyDexFiles();

        findViewById(R.id.applovin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppLovinSdk.initializeSdk(getApplicationContext());
            }
        });

        findViewById(R.id.appnext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appnext.init(getApplicationContext());
            }
        });

    }

    private void copyDexFiles() {
        AssetManager assets = getAssets();
        try {
            String[] files = assets.list("avidly_android");
            Log.i(TAG, "files.length: " + files.length);

            if (files != null && files.length > 0) {
                File jarDir = new File(getFilesDir(), "dexes");
                if (!jarDir.exists()) {
                    jarDir.mkdir();
                }

                for (String fileName : files) {
                    File jarFile = new File(jarDir, fileName);
                    Log.i(TAG, jarFile.getAbsolutePath() + " exits: " + jarFile.exists());

                    try {
                        InputStream ins = getAssets().open("avidly_android" + File.separator + fileName);
                        if (jarFile.exists() && Md5Utils.fileMd5(ins, false).equals(Md5Utils.fileMd5(jarFile))) {
                            Log.i(TAG, fileName + " md5 equals ... ");
                            ins.close();

                            setClassLoader(jarFile);
                            continue;
                        }

                        Log.i(TAG, fileName + " read start ... ");

                        ins.reset();
                        if (jarFile.length() != ins.available()) {
                            FileOutputStream fos = new FileOutputStream(jarFile);
                            byte[] buf = new byte[4096];
                            int l;
                            while ((l = ins.read(buf)) != -1) {
                                fos.write(buf, 0, l);
                            }
                            fos.close();
                        }
                        ins.close();

                        setClassLoader(jarFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setClassLoader(File jarFile) {
        Log.i(TAG, jarFile.getAbsolutePath() + " setClassLoader ... ");

        ClassLoader cl = getClassLoader();
        File dexDir = new File(getCacheDir().getAbsolutePath() + File.separator + "dexes");
        if (!dexDir.exists()) {
            dexDir.mkdir();
        }

        String nativeLibraryDir = getApplicationInfo().nativeLibraryDir;
        Log.i(TAG, "nativeLibraryDir: " + nativeLibraryDir);

        DexClassLoader loader = new DexClassLoader(jarFile.getAbsolutePath(),
                dexDir.getAbsolutePath(), null, cl.getParent());
        try {
            Field f = ClassLoader.class.getDeclaredField("parent");
            f.setAccessible(true);
            f.set(cl, loader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
