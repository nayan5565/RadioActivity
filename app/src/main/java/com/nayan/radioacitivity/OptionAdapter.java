package com.nayan.radioacitivity;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nayan.radioacitivity.model.MQuestion;

import java.util.ArrayList;

/**
 * Created by Nayan on 7/22/2017.
 */
public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    public ArrayList<MQuestion> mItems;
    private MQuestion mItem;
    private int color;
    int pos;
    View view;


    public OptionAdapter(Context context) {
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
        view = inflater.inflate(R.layout.option_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        mItem = mItems.get(position);
        holder.tvQues.setText(mItem.getQues());
        holder.txtAnswer.setText(mItem.getAnswer());

        holder.opt1.setText(mItem.getOptionArrayList().get(0).getOption());
        holder.opt2.setText(mItem.getOptionArrayList().get(1).getOption());
        holder.opt3.setText(mItem.getOptionArrayList().get(2).getOption());
        holder.opt4.setText(mItem.getOptionArrayList().get(3).getOption());
        if (MainActivity.getInstance().color == 1) {
            for (int i = 0; i < mItem.getOptionArrayList().size(); i++) {
                if (mItem.getOptionArrayList().get(i).getTag() == 1) {
                    if (i == 0) {
                        holder.opt1.setTextColor(Color.GREEN);
                    } else if (i == 1) {
                        holder.opt2.setTextColor(Color.GREEN);
                    } else if (i == 2) {
                        holder.opt3.setTextColor(Color.GREEN);
                    } else if (i == 3) {
                        holder.opt4.setTextColor(Color.GREEN);
                    }
                } else if (mItem.getOptionArrayList().get(i).getTag() == 2) {
                    if (i == 0) {
                        holder.opt1.setTextColor(Color.RED);
                    } else if (i == 1) {
                        holder.opt2.setTextColor(Color.RED);
                    } else if (i == 2) {
                        holder.opt3.setTextColor(Color.RED);
                    } else if (i == 3) {
                        holder.opt4.setTextColor(Color.RED);
                    }
                }
            }
        }


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        RadioGroup radioGroup;
        TextView tvQues, txtAnswer;
        RadioButton opt1, opt2, opt3, opt4;

        public MyViewHolder(final View itemView) {
            super(itemView);
            tvQues = (TextView) itemView.findViewById(R.id.tvQues);
            txtAnswer = (TextView) itemView.findViewById(R.id.tvAnswer);
            opt1 = (RadioButton) itemView.findViewById(R.id.rdOption);
            opt2 = (RadioButton) itemView.findViewById(R.id.rdOption2);
            opt3 = (RadioButton) itemView.findViewById(R.id.rdOption3);
            opt4 = (RadioButton) itemView.findViewById(R.id.rdOption4);
            radioGroup = (RadioGroup) itemView.findViewById(R.id.rdGroup);
            radioGroup.clearCheck();
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    mItem = mItems.get(getAdapterPosition());

                    Log.e("opt", " pos " + checkedId);
                    if (checkedId == R.id.rdOption) {
                        pos = 0;
                        Log.e("opt", " pos " + 0);
                    } else if (checkedId == R.id.rdOption2) {
                        pos = 1;
                        Log.e("opt", " pos " + 1);
                    } else if (checkedId == R.id.rdOption3) {
                        pos = 2;
                        Log.e("opt", " pos " + 2);
                    } else if (checkedId == R.id.rdOption4) {
                        pos = 3;
                        Log.e("opt", " pos " + 3);
                    }

                    for (int i = 0; i < mItem.getOptionArrayList().size(); i++) {
                        if (mItem.getOptionArrayList().get(i).getTag() == 2) {
                            mItem.getOptionArrayList().get(i).setTag(0);
                            MainActivity.getInstance().wrong--;
                            Log.e("click", " wrong remove " + MainActivity.getInstance().wrong);
                        }
                        if (mItem.getOptionArrayList().get(i).getAnswer() == 1){
                            mItem.getOptionArrayList().get(i).setAnswer(0);
                            MainActivity.getInstance().correct--;
                            Log.e("click", " correct remove " + MainActivity.getInstance().correct);
                        }
                    }

                    if (mItem.getOptionArrayList().get(pos).getTag() == 1) {
                        mItem.getOptionArrayList().get(pos).setAnswer(1);
                        if (mItem.getOptionArrayList().get(pos).getAnswer()==1){
                            MainActivity.getInstance().correct++;
                        }

                    } else {
                        mItem.getOptionArrayList().get(pos).setTag(2);
                        MainActivity.getInstance().wrong++;
                    }
                    Log.e("click", " correct " + MainActivity.getInstance().correct);
                    Log.e("click", " wrong " + MainActivity.getInstance().wrong);

//                    MainActivity.getInstance().stop++;
//
//                    MainActivity.getInstance().optClick++;
//                    Log.e("optclick", " click " + MainActivity.getInstance().optClick);
//                    MainActivity.getInstance().colorChange();
//                    notifyDataSetChanged();
//                    if (mItem.getTag() == 1) {
//                        MainActivity.getInstance().correct++;
//                    } else {
//                        mItem.setTag(2);
//                        notifyDataSetChanged();
//                        MainActivity.getInstance().wrong++;
////                    }

//                  MainActivity.getInstance().txtResult.setText(MainActivity.getInstance().correct + " : " + MainActivity.getInstance().wrong);
                }
            });
        }
    }
}
