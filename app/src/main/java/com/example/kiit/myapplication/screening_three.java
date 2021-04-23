package com.example.kiit.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

import java.util.HashMap;

public class screening_three extends AppCompatActivity {
    CheckBox o1,o2,o3,o4,o5,o6,o7,o8,d1,d2,d3,d4,d5,d6,d7,d8;
    RadioButton a1,a2,a3,y,n,f1,f2,f3,f4,f5;
    String result="";
    Button submit_btn;
    ViewGroup container;
    CardView fevercard;
    public static final String Separator=",";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screening_three);
        Init();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Please answer these questions below: ");
        o1.setOnClickListener(new View.OnClickListener() {
            boolean visible;
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(container);
                visible = !visible;
                fevercard.setVisibility(visible?View.VISIBLE: View.GONE);
            }
        });
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckData();
            }
        });


    }
    public void Init(){
        submit_btn=findViewById(R.id.submit_screen);
        container = findViewById(R.id.transition);
        fevercard = findViewById(R.id.cardfever);
        f1 = findViewById(R.id.f1);
        f2 = findViewById(R.id.f2);
        f3 = findViewById(R.id.f3);
        f4 = findViewById(R.id.f4);
        f5 = findViewById(R.id.f5);
        y= findViewById(R.id.yes);
        n = findViewById(R.id.no);
        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        o1 = findViewById(R.id.o1);
        o2 = findViewById(R.id.o2);
        o3 = findViewById(R.id.o3);
        o4 = findViewById(R.id.o4);
        o5 = findViewById(R.id.o5);
        o6 = findViewById(R.id.o6);
        o7 = findViewById(R.id.o7);
        o8 = findViewById(R.id.o8);
        d1 = findViewById(R.id.d1);
        d2 = findViewById(R.id.d2);
        d3 = findViewById(R.id.d3);
        d4 = findViewById(R.id.d4);
        d5 = findViewById(R.id.d5);
        d6 = findViewById(R.id.d6);
        d7 = findViewById(R.id.d7);
        d8 = findViewById(R.id.d8);
    }
    public void ChangeState() {
        if (a1.isChecked()||a2.isChecked()||a3.isChecked()||y.isChecked()||n.isChecked()||f1.isChecked()||f2.isChecked()||f3.isChecked()||f4.isChecked()||f5.isChecked()||o1.isChecked() || o2.isChecked() || o3.isChecked() || o4.isChecked() || o5.isChecked() || o6.isChecked() || o7.isChecked()||o8.isChecked()
        ||d1.isChecked()||d2.isChecked()||d3.isChecked()||d4.isChecked()||d5.isChecked()||d6.isChecked()||d7.isChecked()||d8.isChecked()) {
            a1.setChecked(false);
            a2.setChecked(false);
            a3.setChecked(false);
            y.setChecked(false);
            n.setChecked(false);
            f1.setChecked(false);
            f2.setChecked(false);
            f3.setChecked(false);
            f4.setChecked(false);
            f5.setChecked(false);
            o1.setChecked(false);
            o2.setChecked(false);
            o3.setChecked(false);
            o4.setChecked(false);
            o5.setChecked(false);
            o6.setChecked(false);
            o7.setChecked(false);
            o8.setChecked(false);
            d1.setChecked(false);
            d2.setChecked(false);
            d3.setChecked(false);
            d4.setChecked(false);
            d5.setChecked(false);
            d6.setChecked(false);
            d7.setChecked(false);
            d8.setChecked(false);
        }
    }
    public void CheckData(){
        String age1 = "",yn="",feverstring ="",fever="",o="",d="",none="";
        if(y.isChecked()){
            yn="Yes";
        }else if(n.isChecked()){
            yn="No";
        }

        if(f1.isChecked()){
            feverstring = String.valueOf(R.id.f1);
            fever = "LOW";
        }else if(f2.isChecked()){
            feverstring = String.valueOf(R.id.f2);
            fever="HIGH";
        }else if(f3.isChecked()){
            feverstring = String.valueOf(R.id.f3);
            fever = "HIGH";
        }else if(f4.isChecked()){
            feverstring = String.valueOf(R.id.f4);
            fever = "HIGH";
        }else if(f5.isChecked()){
            feverstring = String.valueOf(R.id.f5);
            fever = "MEDIUM";
        }

        if(o1.isChecked()){
            o = "Fever";
        }else if(o2.isChecked()){
            o="Dry Cough";
        }else if(o3.isChecked()){
            o="Fatigue";
        }else if (o4.isChecked()){
            o="Loss of taste or smell";
        }else if(o5.isChecked()){
            o="Shortness of breath";
        }else if(o6.isChecked()){
            o="Running nose";
        }else if(o7.isChecked()){
            o="Sore Throat";
        }else if(o8.isChecked()){
            o="None";
        }

        if(d1.isChecked()){
            d = "Asthma or chronic lung disease";
        }else if(d2.isChecked()){
            d="Cancer treatments";
        }else if(d3.isChecked()){
            d="Chronic Lung disease(COPD)";
        }else if (d4.isChecked()){
            d="Heart disease";
        }else if(d5.isChecked()){
            d="Diabetes";
        }else if(d6.isChecked()){
            d="Pregnancy";
        }else if(d7.isChecked()){
            d="Kidney Failure that needs dialysis";
        }else if(d8.isChecked()){
            d="No existing conditions";
        }
        HashMap<String,Boolean> hashmap = new HashMap<>();
        hashmap.put(getString(R.string.d1),d1.isChecked());
        hashmap.put(getString(R.string.d2),d2.isChecked());
        hashmap.put(getString(R.string.d3),d3.isChecked());
        hashmap.put(getString(R.string.d4),d4.isChecked());
        hashmap.put(getString(R.string.d5),d5.isChecked());
        hashmap.put(getString(R.string.d6),d6.isChecked());
        hashmap.put(getString(R.string.d7),d7.isChecked());
        HashMap<String,Boolean> hmap = new HashMap<>();
        hmap.put(getString(R.string.o1),o1.isChecked());
        hmap.put(getString(R.string.o2),o2.isChecked());
        hmap.put(getString(R.string.o3),o3.isChecked());
        hmap.put(getString(R.string.o4),o4.isChecked());
        hmap.put(getString(R.string.o5),o5.isChecked());
        hmap.put(getString(R.string.o6),o6.isChecked());
        hmap.put(getString(R.string.o7),o7.isChecked());
        hmap.put(getString(R.string.o7),o7.isChecked());

        if(d8.isChecked() || o8.isChecked()){
            none ="None";
        }

        if(a1.isChecked()){
            age1= "Under 18";
        }else if(a2.isChecked()){
            age1 ="Between 18 and 64";
        }else if(a3.isChecked()){
            age1="Above 64";
        }
        ChangeState();
        Intent i = new Intent(getApplicationContext(), screening_two.class);
        i.putExtra("mess", hmap);
        i.putExtra("message",hashmap);
        i.putExtra("age",age1);
        i.putExtra("yesno",yn);
        i.putExtra("fever",fever);
        i.putExtra("feverstring",feverstring);
        i.putExtra("o",o);
        i.putExtra("d",d);
        i.putExtra("none",none);
        startActivity(i);

    }
    private void onRadioClicked(){

    }
}