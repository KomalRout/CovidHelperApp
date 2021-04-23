package com.example.kiit.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class screening_one extends AppCompatActivity {

    RadioButton radio1,radio2;
    Button submit;
    CardView helpcard,resultcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screening_one);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        submit = findViewById(R.id.submit);
        helpcard = findViewById(R.id.helpcard);
        resultcard = findViewById(R.id.resultcard);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Symptom Checker");

    }
    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()){
            case R.id.radio1:
                helpcard.setVisibility(View.VISIBLE);
                helpcard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(screening_one.this,Helplinenumber.class));
                    }
                });
                if(checked){
                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                           resultcard.setVisibility(View.VISIBLE);
                        }
                    });
                }
                break;
                    case R.id.radio2:
                            if(checked){
                                     submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                             Intent j = new Intent(screening_one.this,screening_three.class);
                            startActivity(j);
                        }
                    });
                }
                            break;
        }
    }

}