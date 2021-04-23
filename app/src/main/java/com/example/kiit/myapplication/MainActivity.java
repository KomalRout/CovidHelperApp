package com.example.kiit.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {
    TextView confirm;
    TextView confirm_new;
    TextView active;
    TextView active_new;
    TextView reco;
    TextView reco_new;
    TextView deat;
    TextView deat_new;
    TextView test;
    TextView test_new;
    TextView date;
    TextView time;
    TextView vacc;
    int act;
    PieChart piecharti;
    ProgressDialog progressDialog;

    CardView statetrack,worldtrack;
    String str_confirmed, str_confirmed_new, str_active, str_active_new, str_recovered, str_recovered_new,
            str_death, str_death_new, str_tests, str_tests_new, str_last_update_time,str_vacc;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        FetchData();
        statetrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(MainActivity.this,StateWise.class));
            }
        });
        worldtrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WorldData.class));
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                FetchData();
                swipeRefreshLayout.setRefreshing(false);
                //Toast.makeText(MainActivity.this, "Data refreshed!", Toast.LENGTH_SHORT).show();
            }
        });
        getSupportActionBar().setTitle("Covid-19(India)");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        GetTokent();
    }

    private void GetTokent(){
        JSONObject param = new JSONObject();
        try {
            param.put("grant_type","client_credentials");
            param.put("client_id","33OkryzDZsLEUebSLXXvUIKTo9Fl2_gXmS_gZ_5skJIHvzbpxi3D60zJ6RSP93Ae0F1NWt06ywspA8G8o0vZXCHXC5d36gw9u55YdPTCaNVhWwzXYoD_mQ==");
            param.put("client_secret","lrFxI-iSEg_oeg2QhbCoWVGvSf3ekCq_F9DLryz0Xi0VjRyHNZvCK_cVgift9VWtni5fSuzy5q71tV-qq2wAHLQlnt5h-pVaj1qX0YfVy2Wf0rQzuBc1ci06-6lp62N-");

        }catch (JSONException e){
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String apiUrl = "https://outpost.mapmyindia.com/api/security/oauth/token";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, apiUrl, param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("Response",response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error",error.toString());
            }
        });
    }
    private void FetchData() {
        ShowDialog(this);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String apiUrl = "https://api.covid19india.org/data.json";
        piecharti.clearChart();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, apiUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //As the data of the json are in a nested array, so we need to define the array from which we want to fetch the data.
                        JSONArray all_state_jsonArray = null;
                        JSONArray testData_jsonArray = null;

                        try {
                            all_state_jsonArray = response.getJSONArray("statewise");
                            testData_jsonArray = response.getJSONArray("tested");
                            JSONObject data_india = all_state_jsonArray.getJSONObject(0);
                            JSONObject test_data_india = testData_jsonArray.getJSONObject(testData_jsonArray.length()-1);

                            //Fetching data for India and storing it in String
                            str_confirmed = data_india.getString("confirmed");   //Confirmed cases in India
                            str_confirmed_new = data_india.getString("deltaconfirmed");   //New Confirmed cases from last update time

                            str_active = data_india.getString("active");    //Active cases in India

                            str_recovered = data_india.getString("recovered");  //Total recovered cased in India
                            str_recovered_new = data_india.getString("deltarecovered"); //New recovered cases from last update time

                            str_death = data_india.getString("deaths");     //Total deaths in India
                            str_death_new = data_india.getString("deltadeaths");    //New death cases from last update time

                            str_last_update_time = data_india.getString("lastupdatedtime"); //Last update date and time
                            str_vacc = test_data_india.getString("totaldosesadministered");

                            str_tests = test_data_india.getString("totalsamplestested"); //Total samples tested in India
                            str_tests_new = test_data_india.getString("samplereportedtoday");   //New samples tested today

                                    //Setting text in the textview
                                    confirm.setText(NumberFormat.getInstance().format(Integer.parseInt(str_confirmed)));
                                    confirm_new.setText("+" + NumberFormat.getInstance().format(Integer.parseInt(str_confirmed_new)));

                                    active.setText(NumberFormat.getInstance().format(Integer.parseInt(str_active)));

                                    act = Integer.parseInt(str_confirmed_new)
                                            - (Integer.parseInt(str_recovered_new) + Integer.parseInt(str_death_new));
                                    active_new.setText("+"+NumberFormat.getInstance().format(act));

                                    reco.setText(NumberFormat.getInstance().format(Integer.parseInt(str_recovered)));
                                    reco_new.setText("+"+NumberFormat.getInstance().format(Integer.parseInt(str_recovered_new)));

                                    deat.setText(NumberFormat.getInstance().format(Integer.parseInt(str_death)));
                                    deat_new.setText("+"+NumberFormat.getInstance().format(Integer.parseInt(str_death_new)));

                                    test.setText(NumberFormat.getInstance().format(Integer.parseInt(str_tests)));
                                    test_new.setText("+"+NumberFormat.getInstance().format(Integer.parseInt(str_tests_new)));
                                    vacc.setText(NumberFormat.getInstance().format(Integer.parseInt(str_vacc)));

                                    date.setText(FormatDate(str_last_update_time, 1));
                                    time.setText(FormatDate(str_last_update_time, 2));

                                    piecharti.addPieSlice(new PieModel("Active", Integer.parseInt(str_active), Color.parseColor("#29B6F6")));
                                    piecharti.addPieSlice(new PieModel("Recovered", Integer.parseInt(str_recovered), Color.parseColor("#66BB6A")));
                                    piecharti.addPieSlice(new PieModel("Deceased", Integer.parseInt(str_death), Color.parseColor("#FB0500")));

                                    piecharti.startAnimation();
                                    DismissDialog();

                        } catch (JSONException e) {
                            e.printStackTrace(); }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
    public String FormatDate(String date, int testCase) {
        Date mDate = null;
        String dateFormat;
        try {
            mDate = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US).parse(date);
            if (testCase == 0) {
                dateFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm a").format(mDate);
                return dateFormat;
            } else if (testCase == 1) {
                dateFormat = new SimpleDateFormat("dd MMM yyyy").format(mDate);
                return dateFormat;
            } else if (testCase == 2) {
                dateFormat = new SimpleDateFormat("hh:mm a").format(mDate);
                return dateFormat;
            } else {
                Log.d("error", "Wrong input! Choose from 0 to 2");
                return "Error";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }

    private void Init() {
        confirm = findViewById(R.id.iconfirmed);
        confirm_new=findViewById(R.id.confirmednew);
        active=findViewById(R.id.iactive);
        active_new=findViewById(R.id.activenew);
        reco=findViewById(R.id.irecovered);
        reco_new=findViewById(R.id.recoverednew);
        deat=findViewById(R.id.deaths);
        deat_new=findViewById(R.id.deathsnew);
        test=findViewById(R.id.tested);
        test_new=findViewById(R.id.test_new);
        date=findViewById(R.id.date);
        time=findViewById(R.id.itime);
        piecharti=findViewById(R.id.piecharti);
        swipeRefreshLayout=findViewById(R.id.swipe_main);
        statetrack = findViewById(R.id.statetrack);
        worldtrack=findViewById(R.id.worldtrack);
        vacc = findViewById(R.id.vaccinedtotal);
    }
    public void ShowDialog(Context context) {
        //setting up progress dialog
        progressDialog = new ProgressDialog(context);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progressbar_layout);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    public void DismissDialog() {
        progressDialog.dismiss();
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
