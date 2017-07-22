package com.nayan.radioacitivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nayan.radioacitivity.model.MOption;
import com.nayan.radioacitivity.model.MQuestion;

import java.util.ArrayList;

/**
 * Created by Nayan on 7/22/2017.
 */
public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<MOption> mItems;
    private MOption mItem;
    private int color;
    View view;


    public OptionAdapter(Context context) {
        this.context = context;
        mItems = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<MOption> mItems) {
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
        holder.radioButton.setText(mItem.getOption());
        if (MainActivity.getInstance().color == 1)
            if (mItem.getTag() == 1) {
                holder.radioButton.setTextColor(Color.GREEN);
            } else if (mItem.getTag() == 2) {
                holder.radioButton.setTextColor(Color.RED);
            }

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        RadioButton radioButton;
        Button btnFav;

        public MyViewHolder(View itemView) {
            super(itemView);
            radioButton = (RadioButton) itemView.findViewById(R.id.rdOption);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItem = mItems.get(getAdapterPosition());
                    if (MainActivity.getInstance().optClick>0){
                        return;
                    }
                    MainActivity.getInstance().stop++;
                    MainActivity.getInstance().color=1;
                    MainActivity.getInstance().optClick++;
                    MainActivity.getInstance().colorChange();
                    notifyDataSetChanged();
                    if (MainActivity.getInstance().pos > 1)
                        return;
                    if (mItem.getTag() == 1) {
                        MainActivity.getInstance().correct++;
                    } else {
                       mItem.setTag(2);
                        notifyDataSetChanged();
                        MainActivity.getInstance().wrong++;
                    }

                    MainActivity.getInstance().txtResult.setText(MainActivity.getInstance().correct + " : " + MainActivity.getInstance().wrong);
                }
            });
        }
    }
}
