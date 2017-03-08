package com.example.admin.npa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class temporary extends AppCompatActivity {


    @BindView(R.id.mcqmany)
    Button mcqmany;
    @BindView(R.id.mcqone)
    Button mcqone;
    @BindView(R.id.slider)
    Button slider;
    @BindView(R.id.text)
    Button text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporary);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.mcqmany, R.id.mcqone, R.id.slider, R.id.text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mcqmany:
                Intent intent = new Intent(temporary.this, QuestionsActivity.class);
                intent.putExtra("message", "1");
                startActivity(intent);
                break;
            case R.id.mcqone:
                Intent intent1 = new Intent(temporary.this, QuestionsActivity.class);
                intent1.putExtra("message", "2");
                startActivity(intent1);
                break;
            case R.id.slider:
                Intent intent2 = new Intent(temporary.this, QuestionsActivity.class);
                intent2.putExtra("message", "3");
                startActivity(intent2);
                break;
            case R.id.text:
                Intent intent3 = new Intent(temporary.this, QuestionsActivity.class);
                intent3.putExtra("message", "4");
                startActivity(intent3);
                break;
        }
    }
}
