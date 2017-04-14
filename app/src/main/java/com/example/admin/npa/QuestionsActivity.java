package com.example.admin.npa;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.admin.npa.ResultActivity.getmaxtotal;
import static com.example.admin.npa.ResultActivity.gettotal;

public class QuestionsActivity extends AppCompatActivity {
    int arr[] = {R.layout.questionlayoutmcqmany, R.layout.questionlayoutmcqone, R.layout.questionlayoutslider, R.layout.questionedittext};
    @BindView(R.id.questno)
    TextView mQuestno;

    @BindView(R.id.question)
    TextView mQuestion;

    @BindView(R.id.mcqmany)
    LinearLayout mMcqmany;

    @BindView(R.id.mcqone)
    RadioGroup mMcqone;
    //    @BindView(R.id.seekBar)
//    SeekBar mSeekBar;
    @BindView(R.id.lin1)
    LinearLayout mSeekBar;
    @BindView(R.id.edittextqn)
    EditText mEdittextqn;
    @BindView(R.id.edittextqntil)
    TextInputLayout mEdittextqntil;
    @BindView(R.id.cv)
    CardView mCv;
    @BindView(R.id.back)
    ImageButton mBack;
    @BindView(R.id.next)
    ImageButton mNext;
    DatabaseOpenHelper mHelper;
    PatientJ p;
    ArrayList<Question> l = new ArrayList<>();
    int posx = 0;
    @BindView(R.id.surveyname)
    TextView mSurveyname;
    @BindView(R.id.spinner)
    Spinner mSpinner;
    @BindView(R.id.date)
    TextView mDate;
    CustomSeekBar customSeekBar;
    @BindView(R.id.back1)
    Button mBack1;
    @BindView(R.id.next2)
    Button mNext2;
    @BindView(R.id.hospimage)
    ImageView mHospimage;

    @OnClick(R.id.date)
    void setdate() {
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(QuestionsActivity.this, (view1, year, monthOfYear, dayOfMonth) -> {
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
            mDate.setText(dateFormatter.format(newDate.getTime()));
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    ArrayList<String> score = new ArrayList<>();
    ArrayList<String> options = new ArrayList<>();
    private final ArrayList<RadioButton> allRb = new ArrayList<>();
    private final ArrayList<CheckBox> allCb = new ArrayList<>();

    @OnClick({R.id.back, R.id.back1})
    void setbackqn() {
        if (posx > 0) {
            posx = posx - 1;
            setAnsType(posx);
        } else {
            posx = 0;
            setAnsType(posx);
        }
    }

    @OnClick({R.id.next, R.id.next2})
    void setnextquest() {
        if (posx < l.size()) {
            int i = Integer.parseInt(l.get(posx).getRestype());

            switch (i) {
                case 1:     //EditText


                    if (TextUtils.isEmpty(mEdittextqn.getText().toString().trim()))
                        Toast.makeText(QuestionsActivity.this, "Cant be empty", Toast.LENGTH_SHORT).show();
                    else {
                        l.get(posx).setAnswer(mEdittextqn.getText().toString());
                        l.get(posx).setScore("1");
                        l.get(posx).setMaxscore("1");
                        posx = posx + 1;
                        setAnsType(posx);
                    }
                    break;
                case 2:     //Checkbox
                    String the_choices = "";
                    int sc = 0;
                    boolean at_leaset_one_checked = false;
                    for (CheckBox cb : allCb) {
                        if (cb.isChecked()) {
                            at_leaset_one_checked = true;
                            the_choices = the_choices + cb.getText().toString() + ", ";
                            sc = Integer.parseInt(score.get(options.indexOf(cb.getText().toString())));
                        }
                    }
                    if (!at_leaset_one_checked)
                        Toast.makeText(QuestionsActivity.this, "Choose atleast one option!", Toast.LENGTH_SHORT).show();


                    if (the_choices.length() > 2) {
                        the_choices = the_choices.substring(0, the_choices.length() - 2);
                        l.get(posx).setScore(String.valueOf(sc));
                        int max = 0;
                        for (String x : score)
                            max += Integer.parseInt(x);
                        l.get(posx).setAnswer(the_choices);
                        l.get(posx).setMaxscore(String.valueOf(max));
                        posx = posx + 1;
                        setAnsType(posx);
                    }

                    break;

                case 3:
                    //Radio
                    String the_choice = "";
                    boolean at_leaset_one_selected = false;
                    for (RadioButton rb : allRb) {
                        if (rb.isChecked()) {
                            at_leaset_one_selected = true;
                            the_choice = rb.getText().toString();
                        }
                    }
                    if (!at_leaset_one_selected)
                        Toast.makeText(QuestionsActivity.this, "Choose an option!", Toast.LENGTH_SHORT).show();

                    if (the_choice.length() > 0) {
                        l.get(posx).setAnswer(the_choice);
                        l.get(posx).setScore(score.get(options.indexOf(the_choice)));
                        Comparator<String> cmp = (o1, o2) -> Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
                        l.get(posx).setMaxscore(Collections.max(score, cmp));
                        posx = posx + 1;
                        setAnsType(posx);
                    }


                    break;
                case 4://spinner
                    if (mSpinner.getSelectedItem().toString().equals("Choose one from dropdown"))
                        Toast.makeText(QuestionsActivity.this, "Choose one from dropdown", Toast.LENGTH_SHORT).show();
                    else {
                        l.get(posx).setAnswer(mSpinner.getSelectedItem().toString());
                        l.get(posx).setScore(score.get(options.indexOf(mSpinner.getSelectedItem().toString())));

                        Comparator<String> cmp = (o1, o2) -> Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
                        l.get(posx).setMaxscore(Collections.max(score, cmp));
                        posx = posx + 1;
                        setAnsType(posx);
                    }
                    break;
                case 5:
                    //Slider
                    int y = customSeekBar.getSeekBar().getProgress();
                    l.get(posx).setAnswer(String.valueOf(options.get(y)));
                    l.get(posx).setScore(String.valueOf(score.get(y)));
                    Comparator<String> cmp = (o1, o2) -> Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
                    l.get(posx).setMaxscore(Collections.max(score, cmp));

                    posx = posx + 1;
                    setAnsType(posx);


                    break;
                case 6://datepicker

                    if (mDate.getText().toString().equals("MM/dd/yyyy"))
                        Toast.makeText(QuestionsActivity.this, "Choose one from dropdown", Toast.LENGTH_SHORT).show();
                    else {
                        l.get(posx).setAnswer(mDate.getText().toString());
                        l.get(posx).setScore("0");
                        l.get(posx).setMaxscore("0");
                        posx = posx + 1;
                        setAnsType(posx);
                    }
                    break;


                default:
                    break;
            }
            if (posx == l.size()) {
                mHelper.addallresponse(l, p.getPid());
                p.setStatus("completed");
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
                String strDate = sdf.format(cal.getTime());
                p.setRepdate(strDate);
                p.setRepscore(String.valueOf(gettotal(l)));
                p.setRepmaxscore(String.valueOf(getmaxtotal(l)));
                mHelper.updatepatient(p);
                Toast.makeText(QuestionsActivity.this, "Answers Have been saved!", Toast.LENGTH_LONG).show();
                Intent x = new Intent(QuestionsActivity.this, ResultActivity.class);
                x.putExtra("pid", p.getPid());
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
        p = mHelper.getPatient(uid);
        l = mHelper.getallquestions(p.getSurveyid());
        Nurse n=mHelper.getNurseDetails();
        setContentView(R.layout.questioncard);
        ButterKnife.bind(this);
        Glide.with(this).load(n.getHosplogo()) .diskCacheStrategy(DiskCacheStrategy.ALL).thumbnail(0.2f).placeholder(R.mipmap.ic_placeholder).into(mHospimage);

        mSurveyname.setText(p.getSurveyname());
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
        if (i < l.size()) {
            mQuestno.setText((i + 1) + " of " + l.size());
            mQuestion.setText(l.get(i).getQdesc());
            int j = Integer.parseInt(l.get(i).getRestype());
//            if (i==l.size()-1)
//                mNext.setText("Finish");
//            else mNext.setText("Next");
            switch (j) {
                case 1:     //EditText
                    mEdittextqn.setText(null);
                    mMcqmany.setVisibility(View.GONE);
                    mMcqone.setVisibility(View.GONE);
                    mEdittextqntil.setVisibility(View.VISIBLE);
                    mSpinner.setVisibility(View.GONE);
                    mDate.setVisibility(View.GONE);
                    mSeekBar.setVisibility(View.GONE);
                    if (l.get(i).getAnswer() != null) {
                        String x = l.get(i).getAnswer();
                        mEdittextqn.setText(x);
                    }
                    break;
                case 2:     //Checkbox

                    mMcqmany.setVisibility(View.VISIBLE);
                    mMcqmany.removeAllViews();
                    mMcqone.setVisibility(View.GONE);
                    mEdittextqntil.setVisibility(View.GONE);
                    mSpinner.setVisibility(View.GONE);
                    mDate.setVisibility(View.GONE);
                    mSeekBar.setVisibility(View.GONE);

                    getoptionsandscore(l.get(i).getOption(), options, score);
                    allCb.clear();
                    for (String choice : options) {
                        CheckBox cb = new CheckBox(QuestionsActivity.this);
                        cb.setText(choice);
                        cb.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
                        cb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        mMcqmany.addView(cb);
                        allCb.add(cb);

                    }
                    if (l.get(i).getAnswer() != null) {
                        String x = l.get(i).getAnswer();
                        for (String choice : options) {
                            if (x.contains(choice)) {
                                allCb.get(options.indexOf(choice)).setChecked(true);
                            }

                        }


                    }

                    break;

                case 3:     //Radio
                    mMcqone.clearCheck();
                    mMcqmany.setVisibility(View.GONE);
                    mMcqone.setVisibility(View.VISIBLE);
                    mEdittextqntil.setVisibility(View.GONE);
                    mSpinner.setVisibility(View.GONE);
                    mDate.setVisibility(View.GONE);
                    mSeekBar.setVisibility(View.GONE);
                    getoptionsandscore(l.get(i).getOption(), options, score);
                    mMcqone.removeAllViews();
                    allRb.clear();

                    for (String choice : options) {
                        RadioButton rb = new RadioButton(QuestionsActivity.this);
                        rb.setText(choice);

                        rb.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                        rb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        mMcqone.addView(rb);

                        allRb.add(rb);
                    }

                    if (l.get(i).getAnswer() != null) {
                        String x = l.get(i).getAnswer();
                        allRb.get(options.indexOf(x)).setChecked(true);

                    }
                    break;
                case 4://dropdown
                    mSpinner.setVisibility(View.VISIBLE);
                    mMcqmany.setVisibility(View.GONE);
                    mMcqone.setVisibility(View.GONE);
                    mEdittextqntil.setVisibility(View.GONE);
                    mDate.setVisibility(View.GONE);
                    mSeekBar.setVisibility(View.GONE);
                    getoptionsandscorespinner(l.get(i).getOption(), options, score);
                    ArrayAdapter<String> spinneradapt = new ArrayAdapter<>(this,
                            android.R.layout.simple_dropdown_item_1line, options);
                    mSpinner.setAdapter(spinneradapt);
                    if (l.get(i).getAnswer() != null) {
                        String x = l.get(i).getAnswer();
                        mSpinner.setSelection(options.indexOf(x));

                    }
                    break;

                case 5:      //Slider
                    mSeekBar.removeAllViews();
                    mMcqmany.setVisibility(View.GONE);
                    mMcqone.setVisibility(View.GONE);
                    mEdittextqntil.setVisibility(View.GONE);
                    mSpinner.setVisibility(View.GONE);
                    mDate.setVisibility(View.GONE);
                    mSeekBar.setVisibility(View.VISIBLE);
                    getoptionsandscore(l.get(i).getOption(), options, score);
                    customSeekBar = new CustomSeekBar(score.size(), Color.DKGRAY, this, options, score);
                    customSeekBar.addSeekBar(mSeekBar);
                    customSeekBar.getSeekBar().setProgress(0);

                    if (l.get(i).getAnswer() != null) {
                        String x = l.get(i).getAnswer();
                        customSeekBar.getSeekBar().setProgress(options.indexOf(x));

                    }
                    break;
                case 6://datepicker
                    mSpinner.setVisibility(View.GONE);
                    mMcqmany.setVisibility(View.GONE);
                    mMcqone.setVisibility(View.GONE);
                    mEdittextqntil.setVisibility(View.GONE);
                    mDate.setVisibility(View.VISIBLE);
                    mSeekBar.setVisibility(View.GONE);

                    if (l.get(i).getAnswer() != null) {
                        mDate.setText(l.get(i).getAnswer());

                    } else mDate.setText("MM/dd/yyyy");
                    break;

                default:    //Wrong Input
                    mMcqmany.setVisibility(View.GONE);
                    mMcqone.setVisibility(View.GONE);
                    mEdittextqntil.setVisibility(View.GONE);
                    mSeekBar.setVisibility(View.GONE);
                    mSpinner.setVisibility(View.GONE);
                    mDate.setVisibility(View.GONE);
                    break;
            }
        }
    }

    void getoptionsandscore(String x, ArrayList opt, ArrayList sco) {
        String a[] = x.split("[,;]");
        opt.clear();
        sco.clear();
        for (int i = 0; i < a.length; i += 2) {

            opt.add(a[i]);
            sco.add(a[i + 1]);

        }
    }

    void getoptionsandscoreslider(String x, ArrayList opt, ArrayList sco) {
        String a[] = x.split("[,;]");
        opt.clear();
        sco.clear();
        for (int i = 2; i < a.length; i += 2) {

            opt.add(a[i]);
            sco.add(a[i + 1]);

        }
    }

    void getoptionsandscorespinner(String x, ArrayList opt, ArrayList sco) {
        String a[] = x.split("[,;]");
        opt.clear();
        sco.clear();
        opt.add("Choose one from dropdown");
        sco.add("0");
        for (int i = 0; i < a.length; i += 2) {

            opt.add(a[i]);
            sco.add(a[i + 1]);

        }
    }

}
