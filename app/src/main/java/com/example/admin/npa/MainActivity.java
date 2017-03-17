package com.example.admin.npa;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatButton;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    DatabaseOpenHelper mHelper;
    @BindView(R.id.iv)
    ImageView iv;

    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    @BindView(R.id.butt1)
    AppCompatButton mButt1;
    @BindView(R.id.butt2)
    AppCompatButton mButt2;
    @BindView(R.id.butt3)
    AppCompatButton mButt3;
    @BindView(R.id.welcome)
    TextView mWelcome;
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;


    @OnClick(R.id.butt1)
    void sync() {
        ArrayList<PatientJ> list=new ArrayList<>();
        list.add(new PatientJ("1","Bill","M","23","23/10/17","Rabies","pending"));
        list.add(new PatientJ("2","Mary","F","23","23/10/17","Malaria","pending"));
        list.add(new PatientJ("3","Newt","M","23","23/10/17","Common Cold","completed"));
        list.add(new PatientJ("4","Logan","F","23","23/10/17","Malaria","pending"));
        list.add(new PatientJ("5","Dean","M","23","23/10/17","Common Cold","pending"));
        list.add(new PatientJ("6","Simone","F","23","23/10/17","Malaria","completed"));
        list.add(new PatientJ("7","Jay","M","23","23/10/17","Common Cold","pending"));

        ArrayList<Question>questions=new ArrayList<>();
        questions.add(new Question("1r","How Would You Describe your pain?","1","Rabies"));
        questions.add(new Question("2r","Does your pain radiate?","2","Rabies"));
        questions.add(new Question("3r", "What does your pain feel like on a scale of 0 to 5?","3","Rabies"));
        questions.add(new Question("4r","What provokes your pain?","4","Rabies"));
        questions.add(new Question("5r","Did this happen Before","4","Rabies"));

        questions.add(new Question("1m","When Did the symptoms start?","4","Malaria"));
        questions.add(new Question("2m","Is the pain progressing?","2","Malaria"));
        questions.add(new Question("3m","Does your body show any signs of fever?","1","Malaria"));
        questions.add(new Question("4m","What does your pain feel like on a scale of 0 to 5?","3","Malaria"));
        questions.add(new Question("5m","Describe your pain in words.","4","Malaria"));

        questions.add(new Question("1c","When Did the symptoms start?","4","Common Cold"));
        questions.add(new Question("2c","Is the pain progressing?","1","Common Cold"));
        questions.add(new Question("3c","Does your body show any signs of fever?","1","Common Cold"));
        questions.add(new Question("4c","Have you taken any medications,if so what ?","4","Common Cold"));
        questions.add(new Question("5c","Any prior medical history","4","Common Cold"));



        mHelper.addallqns(questions);
        mHelper.addallpatients(list);



        Snackbar.make(findViewById(R.id.activity_main), "Sync Successfull", Snackbar.LENGTH_LONG).show();
    }

    @OnClick(R.id.butt2)
    void submit() {
        startActivity(new Intent(MainActivity.this, PendingAppointments.class));
    }

    @OnClick(R.id.butt3)
    void completed() {
        startActivity(new Intent(MainActivity.this, CompletedAppointments.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // db.addNurse(new Nurse("Vijay","329","sad","zxc","asd","asd","asd"));
        dl = (DrawerLayout) findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        mHelper = DatabaseOpenHelper.getInstance(this);
        dl.addDrawerListener(abdt);
        abdt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Nurse n=mHelper.getNurseDetails();
        mWelcome.setText("Welcome! "+n.getName()+"\n Last Sync:"+n.getLastsync());
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.logout) {


                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.pop);
                    builder.setMessage("Are You Sure?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mHelper.deleteNurseComplete();
                            mHelper.deletePatientComplete();
                            mHelper.deleteQuestionComplete();
                            mHelper.deleteResponseComplete();
                            startActivity(new Intent(MainActivity.this, LogIn.class));
                            finish();
                        }
                    });
                    builder.setNegativeButton("No", null);
                    builder.show();
                } else if (id == R.id.tac) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.pop);
                    builder.setTitle("Terms and conditions");
                    builder.setMessage("T & C");
                    builder.setPositiveButton("OK", null);
                    // builder.setNegativeButton("Cancel", null);
                    builder.show();
                } else if (id == R.id.settings) {

                }

                return true;
            }
        });


    }


    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.pop);
        builder.setMessage("Are You Sure you want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                finish();
                System.exit(0);
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (abdt.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
