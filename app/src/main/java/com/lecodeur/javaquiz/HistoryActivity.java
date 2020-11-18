package com.lecodeur.javaquiz;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lecodeur.javaquiz.adapters.HistoryAdapter;
import com.lecodeur.javaquiz.helpers.DbHelper;
import com.lecodeur.javaquiz.model.History;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<History> historyList;
    LinearLayoutManager linearLayoutManager;
    Context context;
    DbHelper dbHelper;
    HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.context=this;
        dbHelper=new DbHelper(context);

        historyList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        getHistory();
    }

    private void getHistory() {

        historyList=dbHelper.getHistory();
        adapter = new HistoryAdapter(historyList, context);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
