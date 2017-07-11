package com.avidly.testtool.scale;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.avidly.testtool.R;

public class ScaleActivity extends AppCompatActivity {
    ImageView image;
    Button start;
    RelativeLayout scale_layout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale);

        image = (ImageView) findViewById(R.id.scale_img);
        start = (Button) findViewById(R.id.scale_start);
        scale_layout = (RelativeLayout) findViewById(R.id.scale_layout);

        final ScaleAnimation animation = new ScaleAnimation(1.0f, 2.4f, 1.0f, 2.4f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(2000);//设置动画持续时间

        //animation.setRepeatCount(int repeatCount);//设置重复次数
        //animation.setFillAfter(boolean);//动画执行完后是否停留在执行完的状态
        //animation.setStartOffset(long startOffset);//执行前的等待时间
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
//                image.startAnimation(animation);

                scale_layout.startAnimation(animation);
            }
        });
    }
}
