package com.example.admin.npa;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.admin.npa.PatientAdapter.getage;

public class ResultActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    static String pid;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        pid = getIntent().getExtras().getString("pid");
        Log.e("sadxxz", "onCreate: " + pid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class ResultFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        ResultQuestionAdapter adapter;
        @BindView(R.id.recyclerv)
        RecyclerView recyclerv;
        DatabaseOpenHelper mHelper;
        List<Question> list = new ArrayList<>();
        @BindView(R.id.iv)
        ImageView mIv;
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.date)
        TextView mDate;
        @BindView(R.id.illness)
        TextView mIllness;
        Unbinder mUnbinder;

        public ResultFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ResultFragment newInstance() {

            return new ResultFragment();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_result, container, false);

            mUnbinder = ButterKnife.bind(this, rootView);

            recyclerv.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerv.setHasFixedSize(false);
            mHelper = DatabaseOpenHelper.getInstance(getActivity());
            list = mHelper.getallResponse(pid);
            Nurse n = mHelper.getNurseDetails();
            PatientJ p = mHelper.getPatient(pid);
            if (p.getGender().equals("Male"))
                mIv.setImageResource(R.drawable.ic_man_shape);
            else mIv.setImageResource(R.drawable.ic_woman_silhouette);
            mName.setText(p.getFname() + " " + p.getLname() + ", " + getage(p.getDob()));
            mDate.setText(p.getAppdate());
            mIllness.setText(p.getDisease());

            Log.e("zxczxc", "onCreateView: " + list.get(0).getQid());

            adapter = new ResultQuestionAdapter(list);
            recyclerv.setAdapter(adapter);
            return rootView;
        }


        @Override
        public void onDestroyView() {
            super.onDestroyView();
            mUnbinder.unbind();
        }
    }

    public static class ReportFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        Unbinder unbinder;
        DatabaseOpenHelper mHelper;
        List<Question> list = new ArrayList<>();
        //        @BindView(R.id.gauge)
//        ScArcGauge mGauge;
//        @BindView(R.id.indicator)
//        ImageView indicator;
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.personpic)
        ImageView personpic;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.datpic)
        ImageView datpic;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.diseasepic)
        ImageView diseasepic;
        @BindView(R.id.illness)
        TextView illness;
        @BindView(R.id.cv)
        CardView cv;
        @BindView(R.id.riskscore)
        TextView riskscore;
        @BindView(R.id.providername)
        TextView providername;
        @BindView(R.id.appointmentdate)
        TextView appointmentdate;
        @BindView(R.id.reportdate)
        TextView reportdate;
        @BindView(R.id.gauge)
        GaugeView gauge;


        public ReportFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ReportFragment newInstance() {

            return new ReportFragment();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_report, container, false);

            unbinder = ButterKnife.bind(this, rootView);
            mHelper = DatabaseOpenHelper.getInstance(getActivity());
            list = mHelper.getallResponse(pid);
            PatientJ p = mHelper.getPatient(pid);
            Nurse n = mHelper.getNurseDetails();
            if (p.getGender().equals("Male"))
                iv.setImageResource(R.drawable.ic_man_shape);
            else iv.setImageResource(R.drawable.ic_woman_silhouette);
            name.setText(p.getFname() + " " + p.getLname() + ", " + getage(p.getDob()));
            date.setText(p.getAppdate());
            illness.setText(p.getDisease());
            riskscore.setText("Risk Score: " + gettotal(list) + "/" + getmaxtotal(list));
            providername.setText("Provider Name: " + n.getFirstname() + " " + n.getLastname());
            appointmentdate.setText("Appointment Date: " + p.getAppdate());
            reportdate.setText("Reporting Date: " + p.getRepdate());
            gauge.setTargetValue(60);

//            indicator.setPivotX(30f);
//            indicator.setPivotY(30f);
//            float angle = mGauge.percentageToAngle(getpercentage(list));
//            indicator.setRotation(angle);
//
//            mGauge.setOnEventListener(new ScGauge.OnEventListener() {
//                @Override
//                public void onValueChange(float v, float v1) {
//                    float angle = mGauge.percentageToAngle(v1);
//                    indicator.setRotation(angle);
//                }
//            });
            return rootView;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            unbinder.unbind();
        }

        float getpercentage(List<Question> data) {


            float total = getmaxtotal(data);

            float ptotal = gettotal(data);

            return ((ptotal / total) * 100);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (position == 0)
                return ResultFragment.newInstance();
            else return ReportFragment.newInstance();

        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Results";
                case 1:
                    return "Report";

            }
            return null;
        }
    }

    public static float gettotal(List<Question> data) {
        float ptotal = 0;

        for (int j = 0; j < data.size(); j++)

            ptotal += Float.parseFloat(data.get(j).getScore());
        return ptotal;
    }

    public static float getmaxtotal(List<Question> data) {
        float total = 0;
        for (int i = 0; i < data.size(); i++)

            total += Float.parseFloat(data.get(i).getMaxscore());

        return total;
    }
}
