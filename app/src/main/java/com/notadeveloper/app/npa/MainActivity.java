package com.notadeveloper.app.npa;

import android.app.ProgressDialog;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
    @BindView(R.id.logom)
    ImageView logom;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.dl)
    DrawerLayout dl;
    private Nurse n = new Nurse();
    private DatabaseOpenHelper mHelper;
    private String lasts;
    private boolean synctype = false;
    private ActionBarDrawerToggle abdt;

    @OnClick(R.id.butt1)
    void sync() {

        ProgressDialog p = new ProgressDialog(this);
        RetrofitInterface client = RetrofitBuilder.createService(RetrofitInterface.class);
        synctype = mHelper.synctype();
        if (synctype) {
            p.setMessage("Syncing Patients");
            p.show();
            PostReport postReport = new PostReport(mHelper.getAllPatients(), mHelper.getallpatientsResponse());
            Call<Void> call0 = client.postreporttoserver(new Gson().toJson(postReport, PostReport.class));
            call0.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                    Log.e("sasasxzxz", "onResponse: " + "saxz" + response.code());

                    mHelper.deleteResponseComplete();
                    mHelper.deleteQuestionComplete();
                    mHelper.deletePatientComplete();
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss", Locale.US);
                    lasts = sdf.format(cal.getTime());
                    n.setLastsync(lasts);
                    mHelper.updatenurse(n);
                    Snackbar.make(findViewById(R.id.activity_main), "Sync Successfull", Snackbar.LENGTH_LONG).show();
                    mWelcome.setText(
                            "Press Sync To get Pending Appointments" +
                                    "\nLast Sync :" + lasts


                    );
                    p.dismiss();


                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                    Snackbar.make(findViewById(R.id.activity_main), "Sync Failed", Snackbar.LENGTH_LONG).show();
                    p.dismiss();
                }
            });
        } else {

            Call<PostReport> call = client.getallpatients(n.getNid());
            p.setMessage("Syncing Patients");
            p.show();

            call.enqueue(new Callback<PostReport>() {
                @Override
                public void onResponse(Call<PostReport> call, Response<PostReport> response) {
//                    int statusCode = response.code();
                    //    Log.e("sadxc", "onResponse: "+call.toString()+response.body().toString());
                    PostReport ls = new PostReport();
                    ls = response.body();
                    mHelper.addallpatients(ls.getPatientJs(), n.getNid());
                    mHelper.addallqns(ls.getResponse());

                    p.dismiss();
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss", Locale.US);
                    lasts = sdf.format(cal.getTime());
                    n.setLastsync(lasts);
                    mHelper.updatenurse(n);

                    mWelcome.setText(
                            "Name: " + n.getFirstname() + " " +
                                    "\nCompleted Assessments :" + mHelper.getcountcompleted() + "/" + (mHelper.getcountpending() + mHelper.getcountcompleted()) +
                                    "\nUnsynced Assessments  :" + mHelper.getcountcompleted() +
                                    "\nLast Sync :" + n.getLastsync()


                    );
                    Snackbar.make(findViewById(R.id.activity_main), "Sync Successfull", Snackbar.LENGTH_LONG).show();
                    p.dismiss();
                }

                @Override
                public void onFailure(Call<PostReport> call, Throwable t) {
//
                    // Log error here since request failed
                    p.dismiss();

                    Snackbar.make(findViewById(R.id.activity_main), "Sync Failed", Snackbar.LENGTH_LONG).show();
//                    List<PatientJ>list=new ArrayList<PatientJ>();
//                list.add(new PatientJ("1", "12", "Dolores Abernathy", "Female", "10/10/1997", "DIA-123", "DIA-123","Diabetes","Diabetes 123wqsas", "23/10/17", "pending"));
//                list.add(new PatientJ("2", "12", "Peter Abernathy", "Male", "10/10/1957", "TUB-123", "TUB-123","Tuberculosis","Tuberculosis 123waszx", "23/10/17", "pending"));
//                list.add(new PatientJ("3", "12", "Bernard Lowe", "Male", "10/10/1967", "CBR-331", "CBR-331","Cancer","Cancer asdxczczx", "23/10/17", "pending"));
//                list.add(new PatientJ("4", "12", "Elsie Hughes", "Female", "10/10/1987", "DIA-123", "DIA-123","Diabetes","Diabetes saxczzxczx", "23/10/17", "pending"));
//                list.add(new PatientJ("5", "12", "Robert Ford", "Male", "10/10/1997", "DIA-123", "DIA-123","Diabetes","Diabetes asdxczc", "23/10/17", "pending"));
//                list.add(new PatientJ("6", "12", "Maeve M", "Female", "10/10/1987", "CBR-331", "CBR-331","Cancer","Cancer sadxzczxc", "23/10/17", "pending"));
//
//                mHelper.addallpatients(list, n.getNid());
//
//                ArrayList<Question> questions = new ArrayList<>();
//                questions.add(new Question("1r", "How Would You Describe your pain?", "3", "maybe,20;lol,10;deeznuts,5;", "DIA-123"));
//                questions.add(new Question("2r", "Does your pain radiate?", "2", "maybe,20;lol,10;deeznuts,5;", "DIA-123"));
//                questions.add(new Question("3r", "What does your pain feel like on a scale of 0 to 5?", "6", null, "DIA-123"));
//                questions.add(new Question("4r", "What provokes your pain?", "1", null, "DIA-123"));
//                questions.add(new Question("5r", "Did this happen Before", "6", null, "DIA-123"));
//
//                questions.add(new Question("1m", "When Did the symptoms start?", "6", null, "TUB-123"));
//                questions.add(new Question("2m", "Is the pain progressing?", "2", "maybe,20;lol,10;deeznuts,5;zxcxzc,12;123123,5;", "TUB-123"));
//                questions.add(new Question("3m", "Does your body show any signs of fever?", "2", "maybe,20;lol,10;deeznuts,5;jsdcf,9;23zx5,123;", "TUB-123"));
//                questions.add(new Question("4m", "What does your pain feel like on a scale of 0 to 5?", "5", "0,10;2,2;4,4;8,8;10,10;", "TUB-123"));
//                questions.add(new Question("5m", "Describe your pain in words.", "1", null, "TUB-123"));
//
//                questions.add(new Question("1c", "When Did the symptoms start?", "6", null, "CBR-331"));
//                questions.add(new Question("2c", "Is the pain progressing?", "4", "maybe,20;lol,10;deeznuts,5;", "CBR-331"));
//                questions.add(new Question("3c", "Does your body show any signs of fever?", "4", "maybe,20;lol,10;deeznuts,5;", "CBR-331"));
//                questions.add(new Question("4c", "Have you taken any medications,if so what ?", "1", null, "CBR-331"));
//                questions.add(new Question("5c", "Any prior medical history", "2", "maybe,20;lol,10;deeznuts,5;zxcxzc,12;123123,5;", "CBR-331"));
//
//
//                mHelper.addallqns(questions);
                }
            });
        }

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
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        n = mHelper.getNurseDetails();
        Glide.with(this).load(n.getHosplogo())
                .diskCacheStrategy(DiskCacheStrategy.ALL).thumbnail(0.2f).placeholder(R.mipmap.ic_placeholder).into(logom);
        if (n.getLastsync() != null) {
            lasts = n.getLastsync();
        } else lasts = "Sync To Update";
        mWelcome.setText(
                "Name: " + n.getFirstname() + " " +
                        "\nCompleted Assessments :" + mHelper.getcountcompleted() + "/" + (mHelper.getcountpending() + mHelper.getcountcompleted()) +
                        "\nUnsynced Assessments  :" + mHelper.getcountcompleted() +
                        "\nLast Sync :" + lasts


        );
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();

            if (id == R.id.logout) {


                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.pop);
                builder.setMessage("Are You Sure?");
                builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                    mHelper.deleteNurseComplete();
                    mHelper.deletePatientComplete();
                    mHelper.deleteQuestionComplete();
                    mHelper.deleteResponseComplete();
                    startActivity(new Intent(MainActivity.this, LogIn.class));
                    finish();
                });
                builder.setNegativeButton("No", null);
                builder.show();
            } else if (id == R.id.tac) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.pop);
                builder.setTitle("Terms and conditions");
                builder.setMessage(n.getTcs());
                builder.setPositiveButton("OK", null);
                // builder.setNegativeButton("Cancel", null);
                builder.show();
            }

            return true;
        });


    }


    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.pop);
        builder.setMessage("Are You Sure you want to exit?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {


            finish();
            System.exit(0);
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mWelcome.setText(
                "Name: " + n.getFirstname() +
                        "\nCompleted Assessments :" + mHelper.getcountcompleted() + "/" + (mHelper.getcountpending() + mHelper.getcountcompleted()) +
                        "\nUnsynced Assessments  :" + mHelper.getcountcompleted() +
                        "\nLast Sync :" + lasts


        );
    }
}
