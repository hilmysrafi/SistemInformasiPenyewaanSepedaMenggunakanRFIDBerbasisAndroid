package com.example.bike85;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();

        ImageView logout = (ImageView) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ImageView maps = (ImageView) findViewById(R.id.maps);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MapsActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ImageView datauser = (ImageView) findViewById(R.id.datauser);
        datauser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ListDataActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ImageView updateuser = (ImageView) findViewById(R.id.updateuser);
        updateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ListUpdateDeleteActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ImageView topup = (ImageView) findViewById(R.id.topup);
        topup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ListTopUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ImageView bike = (ImageView) findViewById(R.id.sepeda);
        bike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BikeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed () {

    }
}
