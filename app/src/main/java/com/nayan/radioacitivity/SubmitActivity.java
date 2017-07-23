package com.nayan.radioacitivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


/**
 * Created by Nayan on 7/23/2017.
 */
public class SubmitActivity extends AppCompatActivity {

    SubmitAdapter adapter;
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerList);
        adapter = new SubmitAdapter(this);
        databaseHelper = new DatabaseHelper(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter.setData(databaseHelper.getFavData());
        adapter.setData(MainActivity.getInstance().questionArrayList);
        recyclerView.setAdapter(adapter);
    }
}
