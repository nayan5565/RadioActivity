package com.nayan.radioacitivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nayan.radioacitivity.model.MQuestion;

import java.util.ArrayList;

/**
 * Created by Nayan on 7/23/2017.
 */
public class SubmitAdapter extends  RecyclerView.Adapter<SubmitAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<MQuestion> mItems;
    private MQuestion mItem;
    private int color;
    int pos;
    View view;


    public SubmitAdapter(Context context) {
        this.context = context;
        mItems = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<MQuestion> mItems) {
        this.mItems = mItems;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.answer_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        mItem = mItems.get(position);
        holder.tvQues.setText(mItem.getQues());
        holder.txtAnswer.setText(mItem.getAnswer());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvQues, txtAnswer;

        public MyViewHolder(final View itemView) {
            super(itemView);
            tvQues = (TextView) itemView.findViewById(R.id.txtSQues);
            txtAnswer = (TextView) itemView.findViewById(R.id.txtSAnswer);


        }
    }
}
