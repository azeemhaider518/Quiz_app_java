package com.lecodeur.javaquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NameActivity extends AppCompatActivity {

    @BindView(R.id.etName)
    TextInputEditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @OnClick(R.id.btnName)
    public void onClick() {

        if(etName.getText()!=null && !etName.getText().toString().isEmpty()){
            Intent intent=new Intent(NameActivity.this,MainMenuActivity.class);
            intent.putExtra("name",etName.getText().toString());
            startActivity(intent);
            QuizActivity.FinalScore=0;
            etName.setText("");
            //finish();
        }else {
            Toast.makeText(getApplicationContext(),"Name is required",Toast.LENGTH_SHORT).show();
        }
    }
}
