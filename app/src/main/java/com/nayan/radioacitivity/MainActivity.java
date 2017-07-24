package com.nayan.radioacitivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nayan.radioacitivity.model.MOption;
import com.nayan.radioacitivity.model.MQuestion;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    ArrayList<MOption> optionArrayList;
    ArrayList<MQuestion> questionArrayList;
    MQuestion mQuestion;
    MOption mOption;
    OptionAdapter adapter;
    public int pos, color;
    public int wrong, correct;
    public TextView txtQues, txtResult;
    public int optClick, stop;
    Button btnNext;

    private static MainActivity instance;

    public static MainActivity getInstance() {

        return instance;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        generateToMulti();
        prepareView();
    }

    private void init() {
        instance = this;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerList);
        adapter = new OptionAdapter(this);
        btnNext = (Button) findViewById(R.id.btnNx);
        btnNext.setBackgroundColor(Color.RED);
        btnNext.setOnClickListener(this);
        txtQues = (TextView) findViewById(R.id.txtQuestion);
        txtResult = (TextView) findViewById(R.id.txtResult);
    }

    public void prepareView() {
        adapter.setData(questionArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        txtResult.setText(correct + " : " + wrong);
    }

    public void colorChange() {
        if (stop == 1) {
            btnNext.setBackgroundColor(Color.GREEN);
        }
    }

    private void generateToMulti() {
        questionArrayList = new ArrayList<>();
        optionArrayList = new ArrayList<>();

        mQuestion = new MQuestion();

        mQuestion.setQues("2+2=?");

        mOption = new MOption();
        mOption.setOption("2");
        mOption.setTag(0);

        optionArrayList.add(mOption);

        mOption = new MOption();
        mOption.setOption("7");
        mOption.setTag(0);

        optionArrayList.add(mOption);

        mOption = new MOption();
        mOption.setOption("8");
        mOption.setTag(0);

        optionArrayList.add(mOption);

        mOption = new MOption();
        mOption.setOption("4");
        mOption.setTag(1);

        optionArrayList.add(mOption);

        mQuestion.setOptionArrayList(optionArrayList);

        questionArrayList.add(mQuestion);

        // 2

        optionArrayList = new ArrayList<>();
        mQuestion = new MQuestion();
        mQuestion.setQues("5+5=?");

        mOption = new MOption();
        mOption.setOption("10");
        mOption.setTag(1);

        optionArrayList.add(mOption);

        mOption = new MOption();
        mOption.setOption("9");
        mOption.setTag(0);

        optionArrayList.add(mOption);

        mOption = new MOption();
        mOption.setOption("5");
        mOption.setTag(0);

        optionArrayList.add(mOption);

        mOption = new MOption();
        mOption.setOption("11");
        mOption.setTag(0);

        optionArrayList.add(mOption);

        mQuestion.setOptionArrayList(optionArrayList);

        questionArrayList.add(mQuestion);

        //3
        optionArrayList = new ArrayList<>();
        mQuestion = new MQuestion();
        mQuestion.setQues("2+5=?");
        mOption = new MOption();
        mOption.setOption("9");
        mOption.setTag(0);

        optionArrayList.add(mOption);

        mOption = new MOption();
        mOption.setOption("7");
        mOption.setTag(1);

        optionArrayList.add(mOption);

        mOption = new MOption();
        mOption.setOption("5");
        mOption.setTag(0);

        optionArrayList.add(mOption);

        mOption = new MOption();
        mOption.setOption("12");
        mOption.setTag(0);

        optionArrayList.add(mOption);

        mQuestion.setOptionArrayList(optionArrayList);


        questionArrayList.add(mQuestion);

        //4
        optionArrayList = new ArrayList<>();
        mQuestion = new MQuestion();
        mQuestion.setQues("7+2=?");
        mOption = new MOption();
        mOption.setOption("5");
        mOption.setTag(0);

        optionArrayList.add(mOption);

        mOption = new MOption();
        mOption.setOption("6");
        mOption.setTag(0);

        optionArrayList.add(mOption);

        mOption = new MOption();
        mOption.setOption("9");
        mOption.setTag(1);

        optionArrayList.add(mOption);

        mOption = new MOption();
        mOption.setOption("10");
        mOption.setTag(0);

        optionArrayList.add(mOption);

        mQuestion.setOptionArrayList(optionArrayList);


        questionArrayList.add(mQuestion);
//5
        optionArrayList = new ArrayList<>();
        mQuestion = new MQuestion();
        mQuestion.setQues("8+2=?");
        mOption = new MOption();
        mOption.setOption("16");
        mOption.setTag(0);

        optionArrayList.add(mOption);

        mOption = new MOption();
        mOption.setOption("12");
        mOption.setTag(0);

        optionArrayList.add(mOption);

        mOption = new MOption();
        mOption.setOption("11");
        mOption.setTag(0);

        optionArrayList.add(mOption);

        mOption = new MOption();
        mOption.setOption("10");
        mOption.setTag(1);

        optionArrayList.add(mOption);

        mQuestion.setOptionArrayList(optionArrayList);

        questionArrayList.add(mQuestion);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnNx) {
            Log.e("opt", "bt click");
            color = 1;
            txtResult.setText(correct + " : " + wrong);
            adapter.notifyDataSetChanged();
        }
    }

}
