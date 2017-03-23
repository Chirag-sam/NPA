package com.example.admin.npa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionsActivity extends AppCompatActivity {
    int arr[] = {R.layout.questionlayoutmcqmany, R.layout.questionlayoutmcqone, R.layout.questionlayoutslider, R.layout.questionedittext};
    @BindView(R.id.questno)
    TextView mQuestno;
    @BindView(R.id.cv0)
    CardView mCv0;
    @BindView(R.id.question)
    TextView mQuestion;
    @BindView(R.id.ch1)
    CheckBox mCh1;
    @BindView(R.id.ch2)
    CheckBox mCh2;
    @BindView(R.id.ch3)
    CheckBox mCh3;
    @BindView(R.id.ch4)
    CheckBox mCh4;
    @BindView(R.id.ch5)
    CheckBox mCh5;
    @BindView(R.id.mcqmany)
    LinearLayout mMcqmany;
    @BindView(R.id.rdb1)
    RadioButton mRdb1;
    @BindView(R.id.rdb2)
    RadioButton mRdb2;
    @BindView(R.id.rdb3)
    RadioButton mRdb3;
    @BindView(R.id.rdb4)
    RadioButton mRdb4;
    @BindView(R.id.rdb5)
    RadioButton mRdb5;
    @BindView(R.id.mcqone)
    RadioGroup mMcqone;
    @BindView(R.id.seekBar)
    SeekBar mSeekBar;
    @BindView(R.id.edittextqn)
    EditText mEdittextqn;
    @BindView(R.id.edittextqntil)
    TextInputLayout mEdittextqntil;
    @BindView(R.id.cv)
    CardView mCv;
    @BindView(R.id.back)
    Button mBack;
    @BindView(R.id.next)
    Button mNext;
    DatabaseOpenHelper mHelper;
    PatientJ p;
    ArrayList<Question> l=new ArrayList<>();
    int posx=0;
    @OnClick(R.id.back)
    void setbackqn()
    {
        if (posx>0) {
            posx=posx-1;
            setAnsType(posx);
        }
        else {
            posx = 0;
            setAnsType(posx);
        }
    }
    @OnClick (R.id.next)
    void setnextquest()
    {
        if (posx< l.size()){
        int i=Integer.parseInt(l.get(posx).getRestype());

        switch (i) {

            case 1:     //Checkbox

                if (mCh1.isChecked()||mCh2.isChecked()||mCh3.isChecked()||mCh4.isChecked()||mCh5.isChecked())
                {   String x="";
                    if (mCh1.isChecked())
                        x=x.concat(mCh1.getText().toString());
                    if (mCh2.isChecked())
                        x=x.concat(mCh2.getText().toString());
                    if (mCh3.isChecked())
                        x=x.concat(mCh3.getText().toString());
                    if (mCh4.isChecked())
                        x=x.concat(mCh4.getText().toString());
                    if (mCh5.isChecked())
                        x=x.concat(mCh5.getText().toString());
                    l.get(posx).setAnswer(x);
                    Log.e("nextqb", "setnextquest: "+x);
                    posx=posx+1;
                    setAnsType(posx);
                    }
                else {
                    Toast.makeText(QuestionsActivity.this,"Choose atleast one",Toast.LENGTH_SHORT).show();
                }
                break;

            case 2:
                //Radio
                if(mMcqone.getCheckedRadioButtonId()==-1)
                    Toast.makeText(QuestionsActivity.this,"Choose one",Toast.LENGTH_SHORT).show();
                else {
                    int selected = mMcqone.getCheckedRadioButtonId();

// Gets a reference to our "selected" radio button
                    RadioButton b = (RadioButton) findViewById(selected);

// Now you can get the text or whatever you want from the "selected" radio button
                    Log.e("asxz", "setnextquest: "+b.getText().toString() );
                    l.get(posx).setAnswer(b.getText().toString());
                    posx=posx+1;
                    setAnsType(posx);

                }


                break;
            case 3:
                //Slider
                int y=mSeekBar.getProgress();
                l.get(posx).setAnswer(String.valueOf(y));
                posx=posx+1;
                setAnsType(posx);


                break;
            case 4:     //EditText


                if (TextUtils.isEmpty(mEdittextqn.getText().toString().trim()))
                    Toast.makeText(QuestionsActivity.this,"Choose one",Toast.LENGTH_SHORT).show();
                else {
                    l.get(posx).setAnswer(mEdittextqn.getText().toString());
                    posx=posx+1;
                    setAnsType(posx);
                }
                break;


            default:
                break;
        }
        if (posx== l.size())
        {
            mHelper.addallresponse(l,p.getPid());
            p.setStatus("completed");
            mHelper.updatepatient(p);
            Toast.makeText(QuestionsActivity.this,"Answers Have been saved!",Toast.LENGTH_LONG).show();
            Intent x=new Intent(QuestionsActivity.this,ResultActivity.class);
            x.putExtra("pid",p.getPid());
            startActivity(x);
            finish();
        }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = DatabaseOpenHelper.getInstance(this);
        Bundle bundle = getIntent().getExtras();
        String uid = bundle.getString("uid");
        p=mHelper.getPatient(uid);
        l=mHelper.getallquestions(p.getDisease());

        setContentView(R.layout.questioncard);
        ButterKnife.bind(this);
        setAnsType(posx);
      /*  if (message.equals("1")) {
            setContentView(arr[0]);
        } else if (message.equals("2")) {
            setContentView(arr[1]);
        } else if (message.equals("3")) {
            setContentView(arr[2]);
        } else if (message.equals("4")) {
            setContentView(arr[3]);
        }*/
    }
    public void setAnsType(int i) {
        if (i<l.size()){
        mQuestno.setText((i+1)+" of "+ l.size());
        mQuestion.setText(l.get(i).getQdesc());
        int j=Integer.parseInt(l.get(i).getRestype());
            if (i==l.size()-1)
                mNext.setText("Finish");
            else mNext.setText("Next");
        switch (j) {

            case 1:     //Checkbox
                mCh1.setChecked(false);
                mCh2.setChecked(false);
                mCh3.setChecked(false);
                mCh4.setChecked(false);
                mCh5.setChecked(false);
                mMcqmany.setVisibility(View.VISIBLE);
                mMcqone.setVisibility(View.GONE);
                mEdittextqntil.setVisibility(View.GONE);
                mSeekBar.setVisibility(View.GONE);
                if(l.get(i).getAnswer()!=null) {

                    String x=l.get(i).getAnswer();
                    Log.e("asjsajjsa.jV", "setAnsType: "+x);
                    if (x.contains("Very mild"))
                        mCh1.setChecked(true);

                     if (x.contains("Mild"))
                       mCh2.setChecked(true);

                     if (x.contains("Strong"))
                        mCh3.setChecked(true);

                     if (x.contains("Extreme"))
                        mCh4.setChecked(true);

                     if (x.contains("Unbearable"))
                        mCh5.setChecked(true);


                }

                break;

            case 2:     //Radio
                mMcqone.clearCheck();
                mMcqmany.setVisibility(View.GONE);
                mMcqone.setVisibility(View.VISIBLE);
                mEdittextqntil.setVisibility(View.GONE);
                mSeekBar.setVisibility(View.GONE);
                if (l.get(i).getAnswer()!=null) {
                String x=l.get(i).getAnswer();
                    Log.e("asxzcsa", "setAnsType: "+x);
                    if (x.equals("Very mild"))
                        mRdb1.setChecked(true);
                    else if (x.equals("Mild"))
                        mRdb2.setChecked(true);
                    else if (x.equals("Strong"))
                        mRdb3.setChecked(true);
                    else if (x.equals("Extreme"))
                        mRdb4.setChecked(true);
                    else if (x.equals("Unbearable"))
                        mRdb5.setChecked(true);

                }
                break;
            case 3:      //Slider
                mSeekBar.setProgress(0);
                mMcqmany.setVisibility(View.GONE);
                mMcqone.setVisibility(View.GONE);
                mEdittextqntil.setVisibility(View.GONE);
                mSeekBar.setVisibility(View.VISIBLE);
                if (l.get(i).getAnswer()!=null) {
                    String x=l.get(i).getAnswer();
                    mSeekBar.setProgress(Integer.parseInt(x));

                }
                break;
            case 4:     //EditText
                mEdittextqn.setText(null);
                mMcqmany.setVisibility(View.GONE);
                mMcqone.setVisibility(View.GONE);
                mEdittextqntil.setVisibility(View.VISIBLE);
                mSeekBar.setVisibility(View.GONE);
                if (l.get(i).getAnswer()!=null) {
                    String x=l.get(i).getAnswer();
                    mEdittextqn.setText(x);
                }
                break;


            default:    //Wrong Input
                mMcqmany.setVisibility(View.GONE);
                mMcqone.setVisibility(View.GONE);
                mEdittextqntil.setVisibility(View.GONE);
                mSeekBar.setVisibility(View.GONE);
                break;
        }
    }}
}
