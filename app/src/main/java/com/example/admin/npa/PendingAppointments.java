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
        list.add(new PatientJ("1","Bill","M","23","23/10/17","Rabies","1","pending"));
        list.add(new PatientJ("2","Mary","F","23","23/10/17","Malaria","1","pending"));
        list.add(new PatientJ("3","Newt","M","23","23/10/17","Common Cold","1","pending"));
       mHelper.addallpatients(list);
        list.clear();
        list=mHelper.getAllPatients();
        adapter=new PatientAdapter(list,this);
        recycler.setAdapter(adapter);

    }
}
