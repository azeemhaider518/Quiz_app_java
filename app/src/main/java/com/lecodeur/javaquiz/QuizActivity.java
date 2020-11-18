package com.lecodeur.javaquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.lecodeur.javaquiz.helpers.DbHelper;
import com.lecodeur.javaquiz.model.History;
import com.lecodeur.javaquiz.model.Question;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuizActivity extends AppCompatActivity {

    List<Question> questionListVariables;
    int quidVariables = 0;
    Question currentQuestionVariables;

    List<Question> questionListLoops;

    int quidLoops = 0;
    Question currentQuestionLoops;


    List<Question> questionListErrors;
    int quidErrors = 0;
    Question currentQuestionError;

    Context context;

    int score = 0;
    int scoreV = 0;
    int scoreL = 0;
    int scoreE = 0;

    TextView txtQuestion;
    RadioButton rda, rdb, rdc, rdd;

    int type = 0;
  //  int totalQuesutions = 0;
    @BindView(R.id.laySave)
    LinearLayout laySave;
    @BindView(R.id.btnName)
    MaterialButton btnName;

    String name = "";
    private Menu menu;

    DbHelper dbHelper;
   public static int FinalScore = 0;
    @BindView(R.id.layQuiz)
    LinearLayout layQuiz;
    @BindView(R.id.tvScore)
    TextView tvScore;
    @BindView(R.id.layScore)
    LinearLayout layScore;
    @BindView(R.id.radioGroup1)
    RadioGroup radioGroup1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        type = getIntent().getIntExtra("key", 0);
        name = getIntent().getStringExtra("name");

        dbHelper = new DbHelper(this);


        questionListVariables = dbHelper.getAllVariablesQuestions();
        Collections.shuffle(questionListVariables);
        currentQuestionVariables = questionListVariables.get(quidVariables);

        questionListLoops = dbHelper.getAllLoopsQuestions();
        Collections.shuffle(questionListLoops);
        currentQuestionLoops = questionListLoops.get(quidLoops);

        questionListErrors = dbHelper.getAllErrorsQuestions();
        Collections.shuffle(questionListErrors);
        currentQuestionError = questionListErrors.get(quidErrors);


        txtQuestion = (TextView) findViewById(R.id.question);
        rda = (RadioButton) findViewById(R.id.radio1);
        rdb = (RadioButton) findViewById(R.id.radio2);
        rdc = (RadioButton) findViewById(R.id.radio3);
        rdd = (RadioButton) findViewById(R.id.radio4);
        setQuestionView(type);

    }

    private void setQuestionView(int typeQus) {
        radioGroup1.clearCheck();

        Question currentQuestion = null;
        if (typeQus == 1) {
            currentQuestion = questionListVariables.get(quidVariables);
        } else if (typeQus == 2) {
            currentQuestion = questionListLoops.get(quidLoops);


        } else if (typeQus == 3) {
            currentQuestion = questionListErrors.get(quidErrors);
        }
        if (currentQuestion != null) {
            txtQuestion.setText(currentQuestion.getQuestion());
            rda.setText(currentQuestion.getOptA());
            rdb.setText(currentQuestion.getOptB());
            rdc.setText(currentQuestion.getOptC());
            rdd.setText(currentQuestion.getOptD());

            if (currentQuestion.getOptC().isEmpty()) {
                rdc.setVisibility(View.GONE);
            } else {
                rdc.setVisibility(View.VISIBLE);
            }

            if (currentQuestion.getOptD().isEmpty()) {
                rdd.setVisibility(View.GONE);
            } else {
                rdd.setVisibility(View.VISIBLE);
            }
        }
        if (typeQus == 1) {
            quidVariables++;
        } else if (typeQus == 2) {
            quidLoops++;


        } else if (typeQus == 3) {
            quidErrors++;
        }


    }

    public void btClick(View view) {

        if (radioGroup1.getCheckedRadioButtonId() != -1){
            RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
            RadioButton answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());

            if (type == 1) {
                if (currentQuestionVariables.getAnswer().equals(answer.getText())) {
                    score++;
                    scoreV++;
                    updateScore(scoreV);
                }
                if (quidVariables < 5) {
                    currentQuestionVariables = questionListVariables.get(quidVariables);
                    setQuestionView(type);
                } else {


                    int scoreInSession = scoreV*2;
                    showScoreDialog(scoreInSession);
                }

            } else if (type == 2) {

                if (currentQuestionLoops.getAnswer().equals(answer.getText())) {
                    score++;
                    scoreL++;
                    updateScore(scoreL);
                }
                if (quidLoops < 5) {
                    currentQuestionLoops = questionListLoops.get(quidLoops);
                    setQuestionView(type);
                } else {

                    int scoreInSession = scoreL * 2;
                    showScoreDialog(scoreInSession);
                }

            } else if (type == 3) {

                if (currentQuestionError.getAnswer().equals(answer.getText())) {
                    score++;
                    scoreE++;
                    updateScore(scoreE);
                }
                if (quidErrors < 5) {
                    currentQuestionError = questionListErrors.get(quidErrors);
                    setQuestionView(type);
                } else {






                    int scoreInSession = scoreE * 2;
                    showScoreDialog(scoreInSession);

                }

            }
        }else {

            Toast.makeText(context,"Please select and option",Toast.LENGTH_SHORT).show();



        }



    }


    private void showScoreDialog(int inSession) {


        FinalScore = FinalScore+(score * 2);


        new MaterialAlertDialogBuilder(context, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle("One area Completed")
                .setMessage("Well done " + name + "\n\nScore in this Session " + inSession + "\n\nYour total score: " + FinalScore + "\n\nDo you want to continue next session?")



                .setPositiveButton("Try Again", (dialogInterface, i) -> {
                  reset();
                    dialogInterface.dismiss();
                })

                .setNeutralButton("Other Area", (dialogInterface, i) -> {

                    dialogInterface.dismiss();

                   /* History history = new History();
                    history.score = String.valueOf(FinalScore);
                    history.date = getCurrentDate();
                    history.name = name;
                    dbHelper.saveToDB(history);*/
                    Intent intent=new Intent();
                    intent.putExtra("result",FinalScore);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                })


                .setNegativeButton("End Session", (dialogInterface, i) -> {
                    endSession();
                    dialogInterface.dismiss();
                })
                .show();
    }

    private void endSession() {

        History history = new History();
        history.score = String.valueOf(FinalScore);
        history.date = getCurrentDate();
        history.name = name;
        dbHelper.saveToDB(history);
        Intent intent=new Intent();
        intent.putExtra("result",FinalScore);
        setResult(Activity.RESULT_OK,intent);
        MainMenuActivity.getInstance().finish();
        finish();
    }

    private void reset() {
        type=1;
        score=0;
        scoreE=0;
        scoreL=0;
        scoreV=0;
        quidErrors=0;
        quidLoops=0;
        quidVariables=0;
        FinalScore=0;
        setQuestionView(type);
        updateScore(0);


    }


    @OnClick({R.id.fabDiscard, R.id.fabSave})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabDiscard:
                finish();
                break;
            case R.id.fabSave:


                History history = new History();
                history.score = String.valueOf(FinalScore);
                history.date = getCurrentDate();
                history.name = name;
                dbHelper.saveToDB(history);
                Intent intent=new Intent();
                intent.putExtra("result",FinalScore);
                setResult(Activity.RESULT_OK,intent);
                finish();

                break;
        }
    }


    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        Date c = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c);
        return formattedDate;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.score_menu, menu);
        this.menu = menu;
        return true;
    }

    private void updateScore(int score) {
        int sc=score*2;

        MenuItem scoreItem = menu.findItem(R.id.scoreItem);
        scoreItem.setTitle("Score:"+sc);

    }

    @Override
    public void onBackPressed() {

        showAlterDialog();


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

                   /* History history = new History();
                    history.score = String.valueOf(FinalScore);
                    history.date = getCurrentDate();
                    history.name = name;
                    dbHelper.saveToDB(history);*/
                    FinalScore = FinalScore+(score * 2);
                    int sc=FinalScore;
                    Intent intent=new Intent();
                    intent.putExtra("result",sc);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                })



                .show();
    }
}