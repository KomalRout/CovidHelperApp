package com.example.kiit.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kiit.myapplication.Adapter.VaccineAdapter;
import com.example.kiit.myapplication.modal.VaccineModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class VaccineInfo extends AppCompatActivity {
    TextView totaldose,firstdose,secdose,hcw,online,spot;
    String str_totaldose,str_firstdose,str_secdose,str_hcw,str_online,str_spot,ab_sixty_one,ab_sixty_two,ab_forty_one,ab_forty_two,session,time;
   PieChart addPieSlice;
   CardView vinfomore;
    private DatabaseReference databaseReference;
    private ArrayList<VaccineModal> vaccineModalList = new ArrayList<>();
    private RecyclerView myRcycler;
    VaccineAdapter adapter;
    private FirebaseDatabase firebaseDatabaseInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Vaccine-Info");
        Init();
        FetchData();
        vinfomore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://dashboard.cowin.gov.in/"));
                startActivity(bintent);
            }
        });

         /* --- Firebasedata --*/
        myRcycler = findViewById(R.id.vaccinerecycler);
        myRcycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//
        firebaseDatabaseInstance = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Vaccine_statewise").child("13-4-2021").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

//                VaccineModal vaccineModal = new VaccineModal();
                for (DataSnapshot dsp : snapshot.getChildren()) {
                    Log.i("MShgg",dsp.getValue()+"");
                    VaccineModal vaccineModal = new VaccineModal();
                    vaccineModal = dsp.getValue(VaccineModal.class);
                    vaccineModalList.add(vaccineModal);
                }
//                    vaccineModal = snapshot.child("MP").getValue(VaccineModal.class);
//                    vaccineModalList.add(vaccineModal);

                adapter = new VaccineAdapter(VaccineInfo.this,vaccineModalList);
                myRcycler.setAdapter(adapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void FetchData(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String apiUrl = "https://api.covid19india.org/data.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, apiUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray testData_jsonArray = null;
                try{
                    testData_jsonArray = response.getJSONArray("tested");
                    JSONObject test_data =testData_jsonArray.getJSONObject(testData_jsonArray.length()-3);
                    str_firstdose= test_data.getString("firstdoseadministered");
                    str_hcw=test_data.getString("registrationflwhcw");
                    str_online=test_data.getString("registrationonline");
                    str_secdose=test_data.getString("seconddoseadministered");
                    str_spot=test_data.getString("registrationonspot");
                    str_totaldose=test_data.getString("totaldosesadministered");

                    ab_sixty_one=test_data.getString("over60years1stdose");
                    ab_sixty_two=test_data.getString("over60years2nddose");
                    ab_forty_one=test_data.getString("over45years1stdose");
                    ab_forty_two=test_data.getString("over45years2nddose");

                    totaldose.setText(NumberFormat.getInstance().format(Integer.parseInt(str_totaldose)));
                    firstdose.setText(NumberFormat.getInstance().format(Integer.parseInt(str_firstdose)));
                    secdose.setText(NumberFormat.getInstance().format(Integer.parseInt(str_secdose)));
                   hcw.setText(NumberFormat.getInstance().format(Integer.parseInt(str_hcw)));
                    online.setText(NumberFormat.getInstance().format(Integer.parseInt(str_online)));
                    spot.setText(NumberFormat.getInstance().format(Integer.parseInt(str_spot)));
                    addPieSlice.addPieSlice(new PieModel("45-60(Dose-1)", Integer.parseInt(ab_forty_one), Color.parseColor("#29B6F6")));
                    addPieSlice.addPieSlice(new PieModel("45-60(Dose-2)", Integer.parseInt(ab_forty_two), Color.parseColor("#66BB6A")));
                    addPieSlice.addPieSlice(new PieModel("above 60(Dose-1)", Integer.parseInt(ab_sixty_one), Color.parseColor("#FB0500")));
                    addPieSlice.addPieSlice(new PieModel("above 60(Dose-2)", Integer.parseInt(ab_sixty_two), Color.parseColor("#3700B3")));
                    addPieSlice.startAnimation();
                }catch (JSONException e) {
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

    private void Init(){
        vinfomore = findViewById(R.id.vac_more_info);
     addPieSlice = findViewById(R.id.vaccinePie);
        totaldose = findViewById(R.id.td);
        firstdose = findViewById(R.id.fd);
        secdose = findViewById(R.id.sd);
        hcw=findViewById(R.id.rf);
        online=findViewById(R.id.ro);
        spot = findViewById(R.id.rs);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}