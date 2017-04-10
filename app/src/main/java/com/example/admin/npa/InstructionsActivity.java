package com.example.admin.npa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InstructionsActivity extends AppCompatActivity {

    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.instructions)
    TextView instructions;
    @BindView(R.id.begin)
    Button begin;
    String uid;
    Nurse n = new Nurse();
    PatientJ p= new PatientJ();
    DatabaseOpenHelper mHelper;
    @OnClick(R.id.begin)
    void gotonext()
    {

        Intent i = new Intent(InstructionsActivity.this, QuestionsActivity.class);
        i.putExtra("uid", uid);
        startActivity(i);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        uid = bundle.getString("uid");
        mHelper = DatabaseOpenHelper.getInstance(this);
        n = mHelper.getNurseDetails();
        p=mHelper.getPatient(uid);
        Glide.with(this).load(n.getHosplogo()).thumbnail(0.2f).into(logo);
        instructions.setText(p.getSurveydesc());

    }
}
