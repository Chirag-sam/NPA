package com.example.admin.npa;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {

    private List<Patient> myItems;
    private Context mContext;

    public PatientAdapter(List<Patient> myItems, Context mContext) {
        this.myItems = myItems;
        this.mContext = mContext;
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
    }




    static

    public class ViewHolder extends RecyclerView.ViewHolder  {

        // TODO - Your view members
        public Patient item;
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

        }


        public void setData(Patient item) {
            this.item = item;
            if (item.isGender())
                iv.setImageResource(R.drawable.ic_man_shape);
            else iv.setImageResource(R.drawable.ic_woman_silhouette);
            name.setText(item.getName());
            date.setText(item.getDate());
            illness.setText(item.getIllness());

            // TODO set data to view
        }


    }



}
                                