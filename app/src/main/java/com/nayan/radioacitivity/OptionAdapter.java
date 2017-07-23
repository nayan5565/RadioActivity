package com.nayan.radioacitivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import com.nayan.radioacitivity.model.MOption;
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

        holder.opr1.setText(mItem.getOptionArrayList().get(0).getOption());
        holder.opt2.setText(mItem.getOptionArrayList().get(1).getOption());
        holder.opt3.setText(mItem.getOptionArrayList().get(2).getOption());
        holder.opt4.setText(mItem.getOptionArrayList().get(3).getOption());

//        if (MainActivity.getInstance().color == 1)
//            if (mItem.getTag() == 1) {
//                holder.radioButton.setTextColor(Color.GREEN);
//            } else if (mItem.getTag() == 2) {
//                holder.radioButton.setTextColor(Color.RED);
//            }

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        RadioButton radioButton;
        RadioGroup radioGroup;
        TextView tvQues,txtAnswer;
        RadioButton opr1, opt2, opt3, opt4;
        Button btnFav;

        public MyViewHolder(final View itemView) {
            super(itemView);
            tvQues = (TextView) itemView.findViewById(R.id.tvQues);
            txtAnswer = (TextView) itemView.findViewById(R.id.tvAnswer);
            opr1 = (RadioButton) itemView.findViewById(R.id.rdOption);
            opt2 = (RadioButton) itemView.findViewById(R.id.rdOption2);
            opt3 = (RadioButton) itemView.findViewById(R.id.rdOption3);
            opt4 = (RadioButton) itemView.findViewById(R.id.rdOption4);
            radioGroup = (RadioGroup) itemView.findViewById(R.id.rdGroup);
            radioGroup.clearCheck();
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    mItem = mItems.get(getAdapterPosition());
                    pos = getAdapterPosition();
                    Log.e("opt", " pos " + pos);
//                    if (MainActivity.getInstance().optClick>0) {
//                        return;
//                    }
//                    }
                    int childCount = group.getChildCount();
                    for (int x = 0; x < childCount; x++) {
                        RadioButton btn = (RadioButton) group.getChildAt(x);

                        if (btn.getId() == checkedId) {
                            mItem.setAnswer(btn.getText().toString());
                            DatabaseHelper db=new DatabaseHelper(context);
                            db.addFavData(mItem);
//                            mItems.add(mItem);
                            Toast.makeText(context, btn.getText(), Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                    }

                    MainActivity.getInstance().stop++;
                    MainActivity.getInstance().color = 1;
                    MainActivity.getInstance().optClick++;
                    Log.e("optclick", " click " + MainActivity.getInstance().optClick);
                    MainActivity.getInstance().colorChange();
                    notifyDataSetChanged();
//                    if (mItem.getTag() == 1) {
//                        MainActivity.getInstance().correct++;
//                    } else {
//                        mItem.setTag(2);
//                        notifyDataSetChanged();
//                        MainActivity.getInstance().wrong++;
////                    }

//                    MainActivity.getInstance().txtResult.setText(MainActivity.getInstance().correct + " : " + MainActivity.getInstance().wrong);
                }
            });
        }
    }
}
