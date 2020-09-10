package com.example.delta_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.flaviofaria.kenburnsview.KenBurnsView;

public class SplashActivity extends AppCompatActivity {
    private KenBurnsView kenBurnsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        kenBurnsView=findViewById(R.id.kenburnview);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                kenBurnsView.pause();
                Intent i= new Intent(SplashActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },4000);

    }
}
