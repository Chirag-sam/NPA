package com.example.admin.npa;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class QuestionsActivity extends AppCompatActivity {
    int arr[] = {R.layout.questionlayoutmcqmany, R.layout.questionlayoutmcqone, R.layout.questionlayoutslider, R.layout.questionedittext};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");

        if (message.equals("1")) {
            setContentView(arr[0]);
        } else if (message.equals("2")) {
            setContentView(arr[1]);
        } else if (message.equals("3")) {
            setContentView(arr[2]);
        } else if (message.equals("4")) {
            setContentView(arr[3]);
        }
    }
}
