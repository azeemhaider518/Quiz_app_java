package com.lecodeur.javaquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.lecodeur.javaquiz.helpers.DbHelper;
import com.lecodeur.javaquiz.model.History;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainMenuActivity extends AppCompatActivity {

    String name = "";
    @BindView(R.id.tvScore)
    TextView tvScore;
    static MainMenuActivity activity;

    DbHelper dbHelper;
    boolean isAllow=true;
    Context context;
    private int FinalScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        activity = this;
        context = this;
        dbHelper = new DbHelper(this);
        name = getIntent().getStringExtra("name");
        History history = dbHelper.getLastScore();
        if (history != null) {
            tvScore.setText("Current Score: " + history.score);
        }
        //showScroeDialog(1);

    }

    @OnClick({R.id.cardVariables, R.id.cardLoops, R.id.cardErrors, R.id.cardHsitory})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cardVariables:

                Intent intent1 = new Intent(MainMenuActivity.this, QuizActivity.class);
                intent1.putExtra("key", 1);
                intent1.putExtra("name", name);
                startActivityForResult(intent1, 1);
                break;
            case R.id.cardLoops:

                Intent intent2 = new Intent(MainMenuActivity.this, QuizActivity.class);
                intent2.putExtra("key", 2);
                intent2.putExtra("name", name);
                startActivityForResult(intent2, 1);

                break;
            case R.id.cardErrors:
                Intent intent3 = new Intent(MainMenuActivity.this, QuizActivity.class);
                intent3.putExtra("key", 3);
                intent3.putExtra("name", name);
                startActivityForResult(intent3, 1);
                break;
            case R.id.cardHsitory:
                startActivity(new Intent(MainMenuActivity.this, HistoryActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                isAllow=false;
                int score = data != null ? data.getIntExtra("result", 0) : 0;
                FinalScore = score;
                tvScore.setText("Current Score: " + score);
                showScroeDialog(score);

            }

        }
    }

    private void showScroeDialog(int score) {


        new MaterialAlertDialogBuilder(context, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle("One area Completed")

                .setMessage("Well done " + name + "\n\nYour total score: " + score)


                .setPositiveButton("OK", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })


                .show();
    }

    public static MainMenuActivity getInstance() {
        return activity;
    }

    @Override
    public void onBackPressed() {
        if(isAllow){
            super.onBackPressed();
        }else {
            showAlterDialog();
        }

    }

    private void showAlterDialog() {


        new MaterialAlertDialogBuilder(context, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle("Exit?")
                .setMessage("Do you want to end session?")


                .setPositiveButton("End Session", (dialogInterface, i) -> {

                    dialogInterface.dismiss();
                    endSession();
                })

                .setNegativeButton("No", (dialogInterface, i) -> {

                    dialogInterface.dismiss();

                    finish();
                })


                .show();
    }

    private void endSession() {
        History history = new History();
        history.score = String.valueOf(FinalScore);
        history.date = getCurrentDate();
        history.name = name;
        dbHelper.saveToDB(history);
        finish();

    }


    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        Date c = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c);
        return formattedDate;
    }
}
