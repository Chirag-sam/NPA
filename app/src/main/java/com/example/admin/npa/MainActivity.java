package com.example.admin.npa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.butt1)
    Button butt1;
    @BindView(R.id.butt2)
    Button butt2;
    @BindView(R.id.butt3)
    Button butt3;
    @BindView(R.id.butt4)
    Button butt4;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    @OnClick(R.id.butt2)
    void submit()
    {
        startActivity(new Intent(MainActivity.this,PendingAppointments.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }
}
