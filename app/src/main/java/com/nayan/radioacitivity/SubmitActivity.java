package com.nayan.radioacitivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;


/**
 * Created by Nayan on 7/23/2017.
 */
public class SubmitActivity extends AppCompatActivity {

    SubmitAdapter adapter;
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    Button btnNext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNext = (Button) findViewById(R.id.btnNx);
        btnNext.setVisibility(View.GONE);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerList);
        adapter = new SubmitAdapter(this);
        databaseHelper = new DatabaseHelper(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter.setData(databaseHelper.getFavData());
        adapter.setData(MainActivity.getInstance().questionArrayList);
        recyclerView.setAdapter(adapter);
    }
}
