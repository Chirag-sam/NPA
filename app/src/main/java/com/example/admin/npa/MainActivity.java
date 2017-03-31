package com.example.admin.npa;

import android.app.ProgressDialog;
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
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    Nurse n=new Nurse();
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
        List<PatientJ> list=new ArrayList<>();



        ProgressDialog p=new ProgressDialog(this);
        RetrofitInterface client=RetrofitBuilder.createService(RetrofitInterface.class);
        PostReport postReport=new PostReport( mHelper.getAllPatients(),mHelper.getallpatientsResponse());
        Call<PostReport> call0= client.postreporttoserver(postReport);
        call0.enqueue(new Callback<PostReport>() {
            @Override
            public void onResponse(Call<PostReport> call, Response<PostReport> response) {

            }

            @Override
            public void onFailure(Call<PostReport> call, Throwable t) {

            }
        });
        Call<List<PatientJ>> call = client.getallpatients(n.getNid());
        p.setMessage("Syncing Patients");
        p.show();

        call.enqueue(new Callback<List<PatientJ>>() {
            @Override
            public void onResponse(Call<List<PatientJ>> call, Response<List<PatientJ>> response) {
                int statusCode = response.code();
            //    Log.e("sadxc", "onResponse: "+call.toString()+response.body().toString());
                List<PatientJ> ls=new ArrayList<PatientJ>();
                ls= response.body();
                mHelper.addallpatients(ls,n.getNid());
                ArrayList<Question>questions=new ArrayList<>();
                questions.add(new Question("1r","How Would You Describe your pain?","1","DIA-123"));
                questions.add(new Question("2r","Does your pain radiate?","2","DIA-123"));
                questions.add(new Question("3r", "What does your pain feel like on a scale of 0 to 5?","3","DIA-123"));
                questions.add(new Question("4r","What provokes your pain?","4","DIA-123"));
                questions.add(new Question("5r","Did this happen Before","4","DIA-123"));

                questions.add(new Question("1m","When Did the symptoms start?","4","TUB-123"));
                questions.add(new Question("2m","Is the pain progressing?","2","TUB-123"));
                questions.add(new Question("3m","Does your body show any signs of fever?","1","TUB-123"));
                questions.add(new Question("4m","What does your pain feel like on a scale of 0 to 5?","3","TUB-123"));
                questions.add(new Question("5m","Describe your pain in words.","4","TUB-123"));

                questions.add(new Question("1c","When Did the symptoms start?","4","CBR-331"));
                questions.add(new Question("2c","Is the pain progressing?","1","CBR-331"));
                questions.add(new Question("3c","Does your body show any signs of fever?","1","CBR-331"));
                questions.add(new Question("4c","Have you taken any medications,if so what ?","4","CBR-331"));
                questions.add(new Question("5c","Any prior medical history","4","CBR-331"));



                mHelper.addallqns(questions);
                p.dismiss();
                Snackbar.make(findViewById(R.id.activity_main), "Sync Successfull", Snackbar.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<List<PatientJ>> call, Throwable t) {
//
                // Log error here since request failed
                p.dismiss();
                Snackbar.make(findViewById(R.id.activity_main), "Sync Failed", Snackbar.LENGTH_LONG).show();
                list.add(new PatientJ("1","12","Dolores","Abernathy","Female","10/10/1997","DIA-123","23/10/17","pending"));
                list.add(new PatientJ("2","12","Peter","Abernathy","Male","10/10/1957","TUB-123","23/10/17","pending"));
                list.add(new PatientJ("3","12","Bernard","Lowe","Male","10/10/1967","CBR-331","23/10/17","pending"));
                list.add(new PatientJ("4","12","Elsie","Hughes","Female","10/10/1987","DIA-123","23/10/17","completed"));
                list.add(new PatientJ("5","12","Robert","Ford","Male","10/10/1997","DIA-123","23/10/17","completed"));
                list.add(new PatientJ("6","12","Maeve","M","Female","10/10/1987","CBR-331","23/10/17","pending"));

                mHelper.addallpatients(list,n.getNid());

                ArrayList<Question>questions=new ArrayList<>();
                questions.add(new Question("1r","How Would You Describe your pain?","1","DIA-123"));
                questions.add(new Question("2r","Does your pain radiate?","2","DIA-123"));
                questions.add(new Question("3r", "What does your pain feel like on a scale of 0 to 5?","3","DIA-123"));
                questions.add(new Question("4r","What provokes your pain?","4","DIA-123"));
                questions.add(new Question("5r","Did this happen Before","4","DIA-123"));

                questions.add(new Question("1m","When Did the symptoms start?","4","TUB-123"));
                questions.add(new Question("2m","Is the pain progressing?","2","TUB-123"));
                questions.add(new Question("3m","Does your body show any signs of fever?","1","TUB-123"));
                questions.add(new Question("4m","What does your pain feel like on a scale of 0 to 5?","3","TUB-123"));
                questions.add(new Question("5m","Describe your pain in words.","4","TUB-123"));

                questions.add(new Question("1c","When Did the symptoms start?","4","CBR-331"));
                questions.add(new Question("2c","Is the pain progressing?","1","CBR-331"));
                questions.add(new Question("3c","Does your body show any signs of fever?","1","CBR-331"));
                questions.add(new Question("4c","Have you taken any medications,if so what ?","4","CBR-331"));
                questions.add(new Question("5c","Any prior medical history","4","CBR-331"));



                mHelper.addallqns(questions);

            }
        });
//
//        List<String> dis=new ArrayList<>();
//        dis=mHelper.getalldiseases();
//
//        Call<List<Question>> call1=client.getallqns(dis);
//        p.setMessage("Syncing Qns");
//        p.show();
//        call1.enqueue(new Callback<List<Question>>() {
//            @Override
//            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
//                List<Question> questions=new ArrayList<Question>();
//                questions= response.body();
//                mHelper.addallqns(questions);
//                p.dismiss();
//                Snackbar.make(findViewById(R.id.activity_main), "Sync Successfull", Snackbar.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Call<List<Question>> call, Throwable t) {
//            p.dismiss();
//                Log.e("jghffh", "onFailure: " );
//                Snackbar.make(findViewById(R.id.activity_main), "Sync failed adding fake qns", Snackbar.LENGTH_LONG).show();
//
//
//
//            }
//        });





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
        n=mHelper.getNurseDetails();
        mWelcome.setText("Welcome! "+n.getFirstname()+"\n "+n.getLastname());
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
