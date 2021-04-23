package com.example.kiit.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.NumberFormat;

import static com.example.kiit.myapplication.Constants.STATE_ACTIVE;
import static com.example.kiit.myapplication.Constants.STATE_CONFIRMED;
import static com.example.kiit.myapplication.Constants.STATE_CONFIRMED_NEW;
import static com.example.kiit.myapplication.Constants.STATE_DEATH;
import static com.example.kiit.myapplication.Constants.STATE_DEATH_NEW;
import static com.example.kiit.myapplication.Constants.STATE_LAST_UPDATE;
import static com.example.kiit.myapplication.Constants.STATE_NAME;
import static com.example.kiit.myapplication.Constants.STATE_RECOVERED;
import static com.example.kiit.myapplication.Constants.STATE_RECOVERED_NEW;

public class EachState extends AppCompatActivity {

    TextView tv_confirmed, tv_confirmed_new, tv_active, tv_active_new, tv_death, tv_death_new,
            tv_recovered, tv_recovered_new, tv_lastupdatedate, tv_dist;

     String str_stateName, str_confirmed, str_confirmed_new, str_active, str_active_new, str_death, str_death_new,
            str_recovered, str_recovered_new, str_lastupdatedate;

     PieChart pieChart;

    LinearLayout lin_district;
    MainActivity activity = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_state);
        GetIntent();
        Init();
        LoadStateData();
        //Setting up the title of actionbar as State name
        getSupportActionBar().setTitle(str_stateName);

        //back menu icon on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        lin_district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(EachState.this,DistrictWise.class);
                inte.putExtra(STATE_NAME,str_stateName);
                startActivity(inte);
            }
        });
    }

    private void LoadStateData() {
        activity.ShowDialog(this);
        Handler postDelayToshowProgress = new Handler();
        postDelayToshowProgress.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_confirmed.setText(NumberFormat.getInstance().format(Integer.parseInt(str_confirmed)));
                tv_confirmed_new.setText("+"+NumberFormat.getInstance().format(Integer.parseInt(str_confirmed_new)));

                tv_active.setText(NumberFormat.getInstance().format(Integer.parseInt(str_active)));
                int int_active_new = Integer.parseInt(str_confirmed_new)
                        - (Integer.parseInt(str_recovered_new) + Integer.parseInt(str_death_new));
                tv_active_new.setText("+"+NumberFormat.getInstance().format(int_active_new<0 ? 0 : int_active_new));

                tv_death.setText(NumberFormat.getInstance().format(Integer.parseInt(str_death)));
                tv_death_new.setText("+"+NumberFormat.getInstance().format(Integer.parseInt(str_death_new)));

                tv_recovered.setText(NumberFormat.getInstance().format(Integer.parseInt(str_recovered)));
                tv_recovered_new.setText("+"+NumberFormat.getInstance().format(Integer.parseInt(str_recovered_new)));

                String formatDate = activity.FormatDate(str_lastupdatedate, 0);
                tv_lastupdatedate.setText(formatDate);

                tv_dist.setText("District data of "+str_stateName);

                //setting piechart
                pieChart.addPieSlice(new PieModel("Active", Integer.parseInt(str_active), Color.parseColor("#007afe")));
                pieChart.addPieSlice(new PieModel("Recovered", Integer.parseInt(str_recovered), Color.parseColor("#08a045")));
                pieChart.addPieSlice(new PieModel("Deceased", Integer.parseInt(str_death), Color.parseColor("#F6404F")));

                pieChart.startAnimation();
                activity.DismissDialog();
            }
        },1000);
    }

    private void Init() {
        tv_confirmed = findViewById(R.id.activity_each_state_confirmed_textView);
        tv_confirmed_new = findViewById(R.id.activity_each_state_confirmed_new_textView);
        tv_active = findViewById(R.id.activity_each_state_active_textView);
        tv_active_new = findViewById(R.id.activity_each_state_active_new_textView);
        tv_recovered = findViewById(R.id.activity_each_state_recovered_textView);
        tv_recovered_new = findViewById(R.id.activity_each_state_recovered_new_textView);
        tv_death = findViewById(R.id.activity_each_state_death_textView);
        tv_death_new = findViewById(R.id.activity_each_state_death_new_textView);
        tv_lastupdatedate = findViewById(R.id.activity_each_state_lastupdate_textView);
        tv_dist = findViewById(R.id.activity_each_state_district_data_title);
        pieChart = findViewById(R.id.activity_each_state_piechart);
        lin_district = findViewById(R.id.activity_each_state_lin);
    }

    private void GetIntent() {
        Intent intent = getIntent();
        str_stateName = intent.getStringExtra(STATE_NAME);
        str_confirmed = intent.getStringExtra(STATE_CONFIRMED);
        str_confirmed_new = intent.getStringExtra(STATE_CONFIRMED_NEW);
        str_active = intent.getStringExtra(STATE_ACTIVE);
        str_death = intent.getStringExtra(STATE_DEATH);
        str_death_new = intent.getStringExtra(STATE_DEATH_NEW);
        str_recovered = intent.getStringExtra(STATE_RECOVERED);
        str_recovered_new = intent.getStringExtra(STATE_RECOVERED_NEW);
        str_lastupdatedate = intent.getStringExtra(STATE_LAST_UPDATE);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}