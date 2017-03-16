package com.example.admin.npa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
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
    DatabaseOpenHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        if (getIntent().getBooleanExtra("Exit me", false)) {
            finish();
        }

        mHelper = DatabaseOpenHelper.getInstance(this);
        if(mHelper.getLoginsession())
        { startActivity(new Intent(LogIn.this, MainActivity.class));
            finish();}

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
        if (!isValidEmail(email)) {
            email1.setError("Invalid e-mail address");
            focusView = email1;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {


            RetrofitInterface client=RetrofitBuilder.createService(RetrofitInterface.class);
            Call<Nurse> call = client.Login(email,password);
            call.enqueue(new Callback<Nurse>() {
                @Override
                public void onResponse(Call<Nurse> call, Response<Nurse> response) {
                    int statusCode = response.code();
                    Nurse N = response.body();
                    mHelper.addNurse(N);

                    startActivity(new Intent(LogIn.this, MainActivity.class));
                    finish();
                }

                @Override
                public void onFailure(Call<Nurse> call, Throwable t) {
                    // Log error here since request failed
                    Log.e("Fail", "onFailure: ");
                }
            });

            mHelper.addNurse(new Nurse("1","a@a.com","aaaaaa","Flint","23/2/17"));
            startActivity(new Intent(LogIn.this, MainActivity.class));
            finish();
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
