
package com.example.admin.npa;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ResultQuestionAdapter extends RecyclerView.Adapter<ResultQuestionAdapter.ViewHolder> {


    private List<QuestionDataHolder> myItems;
    private ItemListener myListener;

    public ResultQuestionAdapter(List<QuestionDataHolder> items, ItemListener listener) {
        myItems = items;
        myListener = listener;
    }

    public void setListener(ItemListener listener) {
        myListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resultquestioncard, parent, false)); // TODO
    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(myItems.get(position),position);
    }

    public interface ItemListener {
        void onItemClick(QuestionDataHolder item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.qno)
        TextView qno;
        @BindView(R.id.qntext)
        TextView qntext;
        @BindView(R.id.answer)
        TextView answer;
        @BindView(R.id.cv)
        CardView cv;
        // TODO - Your view members
        public QuestionDataHolder item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            // TODO instantiate/assign view members
        }

        public void setData(QuestionDataHolder item,int position) {
            this.item = item;
            qno.setText(String.valueOf(position+1));
            qntext.setText(item.getQdesc());
            answer.setText(item.getAnswer());
            // TODO set data to view
        }

        @Override
        public void onClick(View v) {
            if (myListener != null) {
                myListener.onItemClick(item);
            }
        }
    }



}
                                