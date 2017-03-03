package com.example.admin.npa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogIn extends AppCompatActivity {

    @BindView(R.id.edittextdial)
    EditText edittextdial;
    @BindView(R.id.edittextdialtil)
    TextInputLayout edittextdialtil;
    @BindView(R.id.edittextdial1)
    EditText edittextdial1;
    @BindView(R.id.edittextdialtil1)
    TextInputLayout edittextdialtil1;
    @BindView(R.id.cv)
    CardView cv;
    @BindView(R.id.butt)
    Button butt;
    @OnClick(R.id.butt)
    void submit()
    {
        startActivity(new Intent(LogIn.this,MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);

    }
}
