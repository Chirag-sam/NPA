package com.notadeveloper.app.npa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.text.TextUtils.isEmpty;

public class LogIn extends AppCompatActivity {

    @BindView(R.id.edittextdial)
    EditText edittextdial;
    @BindView(R.id.edittextdialtil)
    TextInputLayout edittextdialtil;
    @BindView(R.id.edittextdial1)
    EditText edittextdial1;
    @BindView(R.id.edittextdialtil1)
    TextInputLayout edittextdialtil1;
    @BindView(R.id.cv)
    CardView cv;
    @BindView(R.id.butt)
    Button butt;
    List<Nurse> ls = new ArrayList<>();
    @BindView(R.id.svvv)
    ScrollView svvv;
    private DatabaseOpenHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        if (getIntent().getBooleanExtra("Exit me", false)) {
            finish();
        }
        AnimationDrawable animationDrawable = (AnimationDrawable) svvv.getBackground();

        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(2500);

        animationDrawable.start();
        mHelper = DatabaseOpenHelper.getInstance(this);
        if (mHelper.getLoginsession()) {
            startActivity(new Intent(LogIn.this, MainActivity.class));
            finish();
        }

    }

    @OnClick(R.id.butt)
    public void onClick() {

        boolean cancel = false;


        View focusView = null;


        String email = edittextdial.getText().toString();
        String password = edittextdial1.getText().toString();

        edittextdialtil1.setError(null);
        edittextdialtil.setError(null);

        TextInputLayout email1 = (TextInputLayout) findViewById(R.id.edittextdialtil);
        TextInputLayout pass1 = (TextInputLayout) findViewById(R.id.edittextdialtil1);

        if (isEditTextEmpty(email)) {
            edittextdial.setError("Field cannot be empty!");
            focusView = email1;
            cancel = true;
        }

        if (isEditTextEmpty(password)) {
            edittextdialtil1.setError("Field cannot be empty!");
            focusView = pass1;
            cancel = true;
        }
//        if (!isValidEmail(email)) {
//            email1.setError("Invalid e-mail address");
//            focusView = email1;
//            cancel = true;
//        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

            ProgressDialog p = new ProgressDialog(this);
            RetrofitInterface client = RetrofitBuilder.createService(RetrofitInterface.class);
            Call<Nurse> call = client.Login(email, password);
            p.setMessage("Authenticating");
            p.show();
            call.enqueue(new Callback<Nurse>() {
                @Override
                public void onResponse(Call<Nurse> call, Response<Nurse> response) {
//                    int statusCode = response.code();
                    Log.e("sadxc", "onResponse: " + call.toString() + response.body().toString());
                    Nurse N = response.body();
                    N.setUname(email);
                    N.setPassword(password);
                    final String IMAGE_BASE_URL = "http://ec2-52-26-130-218.us-west-2.compute.amazonaws.com:8080/hik";
                    N.setHosplogo(IMAGE_BASE_URL + N.getHosplogo().substring(2));
                    N.setPracticelogo(IMAGE_BASE_URL + N.getPracticelogo().substring(2));
                    mHelper.addNurse(N);

                    Glide.with(LogIn.this).load(N.getHosplogo()).downloadOnly(512, 512);
                    if (N.getPracticelogo() != null)
                        Glide.with(LogIn.this).load(N.getPracticelogo()).downloadOnly(512, 512);

                    p.dismiss();
                    startActivity(new Intent(LogIn.this, MainActivity.class));
                    finish();
                }

                @Override
                public void onFailure(Call<Nurse> call, Throwable t) {
                    p.dismiss();
                    edittextdialtil.setError("Invalid Username");
                    edittextdialtil1.setError("Invalid Password");

//                    mHelper.addNurse(new Nurse("1", "a@a.com", "aaaaaa", "Flint James", "Male", "23/2/17",getString(R.string.instructions),"https://thecambridgeroom.files.wordpress.com/2012/11/images.jpg","Speciality Hospital"));
//                    Glide.with(LogIn.this).load("https://thecambridgeroom.files.wordpress.com/2012/11/images.jpg").downloadOnly(1024, 1024);
//                                        p.dismiss();
// startActivity(new Intent(LogIn.this, MainActivity.class));
////                    finish();
                    Log.e("Fail", "onFailure: ");
                    //    Log error here since request failed
//                    mHelper.addNurse(new Nurse("1","a@a.com","aaaaaa","Flint","23/2/17","Male"));

//
                }
            });

//            mHelper.addNurse(new Nurse("1","a@a.com","aaaaaa","Flint","23/2/17","Male"));
//            startActivity(new Intent(LogIn.this, MainActivity.class));
//            finish();
        }
    }

    private boolean isEditTextEmpty(String mInput) {
        return mInput.length() == 0;
    }

    private boolean isValidEmail(String email) {

        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return !isEmpty(email) && pattern.matcher(email).matches();
    }
}
