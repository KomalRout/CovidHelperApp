package com.example.kiit.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.collection.LLRBNode;

import java.util.HashMap;
import java.util.Map;

public class screening_two extends AppCompatActivity {

    TextView result_txt,age,sym,des,option,fevtemp;
    CardView endcard,faq;
    View fv, lf;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screening_two);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Symptom Checker(Result)");
        Init();
        Intent intent = getIntent();
        sym.setText(intent.getStringExtra("o"));
        des.setText(intent.getStringExtra("d"));
        age.setText(intent.getStringExtra("age"));
        option.setText(intent.getStringExtra("yesno"));
        String fever = getIntent().getStringExtra("fever");
        String none = getIntent().getStringExtra("none");
        fevtemp.setText(fever);

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(screening_two.this,Faqactivity.class));
            }
        });
        if(fevtemp.equals("null")){
            fv.setVisibility(View.GONE);
            lf.setVisibility(View.GONE);
        }else{

            fv.setVisibility(View.VISIBLE);
            lf.setVisibility(View.VISIBLE);
        }
        HashMap<String,Boolean> hashMap = new HashMap<>();
        hashMap= (HashMap<String, Boolean>) getIntent().getExtras().get("mess");
        hashMap.get(getString(R.string.o1));
        hashMap.get(getString(R.string.o2));
        hashMap.get(getString(R.string.o3));
        hashMap.get(getString(R.string.o4));
        hashMap.get(getString(R.string.o5));
        hashMap.get(getString(R.string.o6));
        hashMap.get(getString(R.string.o7));

        HashMap<String,Boolean> hMap = new HashMap<>();
        hMap= (HashMap<String, Boolean>) getIntent().getExtras().get("message");
        hMap.get(getString(R.string.d1));
        hMap.get(getString(R.string.d2));
        hMap.get(getString(R.string.d3));
        hMap.get(getString(R.string.d4));
        hMap.get(getString(R.string.d5));
        hMap.get(getString(R.string.d6));
        hMap.get(getString(R.string.d7));
        int count =0;
        int c = 0;


        for(Map.Entry<String,Boolean> mhash : hashMap.entrySet()){
            if(mhash.getValue()){
                count++;
            }
            for(Map.Entry<String,Boolean>mash : hMap.entrySet()){
                if(mhash.getValue() && mash.getValue()){
                    c++;
                }
            }
        }
        if(count == 1||fever.equals("LOW")||none.equals("None")){
            result_txt.setText("LOW");

        }
        if(c == 1|| count ==2||fever.equals("MEDIUM")){
            result_txt.setText("MEDIUM");
           // endcard.setCardBackgroundColor(R.color.cases);
        }
        if(c == 2|| c==3 || c==4|| c==5 || c==6|| count==3||count==4||count==5||count==6||count==7||count==8||fever.equals("HIGH")){
            result_txt.setText("HIGH");
           // endcard.setCardBackgroundColor(R.color.deaths);
        }
    }

    private void Init() {
        fevtemp = findViewById(R.id.gettemp);
        result_txt = findViewById(R.id.text_result);
        faq = findViewById(R.id.Faqcard);
        age=findViewById(R.id.getage);
        sym = findViewById(R.id.getsymptom);
        des = findViewById(R.id.getde);
        fv=findViewById(R.id.feverview);
        lf = findViewById(R.id.feverlay);
        option = findViewById(R.id.getyn);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}