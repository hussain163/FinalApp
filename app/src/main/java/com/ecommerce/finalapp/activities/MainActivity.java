package com.ecommerce.finalapp.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ecommerce.finalapp.R;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    //Animation myanimation;
    private static int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView =(ImageView)findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.ic_launcher_background);
        //myanimation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        //myanimation.setAnimationListener(this);

        imageView.setVisibility(View.VISIBLE);
        //imageView.startAnimation(myanimation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }


}
