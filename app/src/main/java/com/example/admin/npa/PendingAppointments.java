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
    ArrayList<Patient>list=new ArrayList<>();
    PatientAdapter adapter;
    DatabaseOpenHelper db = new DatabaseOpenHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_appointments);
        ButterKnife.bind(this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(false);

        list.add(new Patient(1,"Bill",true,"24/10/2099","jhghsdgj","Rabies","237848723"));
        list.add(new Patient(2,"Mary",false,"4/10/2099","jhghsdgj","Malaria","237848723"));
        list.add(new Patient(3,"Newt",true,"2/10/2099","jhghsdgj","Common Cold","237848723"));
        adapter=new PatientAdapter(list,this);
        recycler.setAdapter(adapter);

    }
}
