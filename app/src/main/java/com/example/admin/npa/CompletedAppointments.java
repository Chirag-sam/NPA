package com.example.admin.npa;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompletedAppointments extends AppCompatActivity {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    ArrayList<PatientJ> list = new ArrayList<>();
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list=mHelper.getAllPatientcomp();
        adapter = new PatientAdapter(list, this,false);
        recycler.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
