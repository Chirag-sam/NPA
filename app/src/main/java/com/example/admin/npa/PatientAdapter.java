package com.example.admin.npa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {

    private List<PatientJ> myItems;
    private Context mContext;
    boolean type=false;
    public PatientAdapter(List<PatientJ> myItems, Context mContext,boolean type) {
        this.myItems = myItems;
        this.mContext = mContext;
        this.type=type;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patientcard, parent, false)); // TODO
    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(myItems.get(position));
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type){
                Intent i = new Intent(mContext, QuestionsActivity.class);
                i.putExtra("uid", myItems.get(position).getPid());
                mContext.startActivity(i);}
                else {
                    Intent i = new Intent(mContext, ResultActivity.class);
                    i.putExtra("pid", myItems.get(position).getPid());
                    mContext.startActivity(i);}


            }
        });
    }


    static

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // TODO - Your view members
        public PatientJ item;
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.illness)
        TextView illness;
        @BindView(R.id.cv)
        CardView cv;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }


        public void setData(PatientJ item) {
            this.item = item;

            if (item.getGender().equals("Male"))
                iv.setImageResource(R.drawable.ic_man_shape);
            else iv.setImageResource(R.drawable.ic_woman_silhouette);
            name.setText(item.getFname() + " " + item.getLname()+", "+getage(item.getDob()));
            date.setText(item.getAppdate());
            illness.setText(item.getDisease());

            // TODO set data to view
        }


        @Override
        public void onClick(View v) {

        }
    }

    public static String getage(String date) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        Calendar cal = Calendar.getInstance();
        Calendar currentDate = Calendar.getInstance();
        try {
            cal.setTime(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int age = currentDate.get(Calendar.YEAR) - cal.get(Calendar.YEAR);

        if (currentDate.get(Calendar.DAY_OF_YEAR) < cal.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        Integer ageInt = age;

        return ageInt.toString();
    }
}
                                