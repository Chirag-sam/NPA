package com.example.admin.npa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PendingAppointments extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    ArrayList<PatientJ>list=new ArrayList<>();
    PatientAdapter adapter;
    DatabaseOpenHelper mHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_appointments);
        ButterKnife.bind(this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(false);
        mHelper = DatabaseOpenHelper.getInstance(this);

        list=mHelper.getAllPatientspend();
        adapter=new PatientAdapter(list,this);
        recycler.setAdapter(adapter);

    }
}
