package com.example.kiit.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.kiit.myapplication.Adapter.HelplineAdapter;
import com.example.kiit.myapplication.modal.HelplineModal;

import java.util.ArrayList;
import java.util.List;

public class Helplinenumber extends AppCompatActivity {

    RecyclerView helprecycler;
    List<HelplineModal> listhelp = new ArrayList<>();
    HelplineAdapter helplineAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helplinenumber);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Helpline Number");
        helprecycler =findViewById(R.id.helpline_recyclerview);
        String[] state_name = getResources().getStringArray(R.array.state);
        String[] phn = getResources().getStringArray(R.array.state_ph);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("National HelpLine Numbers");
        int size = state_name.length;
        for(int i=0;i<size;i++){
            HelplineModal helplineModal = new HelplineModal(state_name[i],phn[i]);
            listhelp.add(helplineModal);
        }
        helprecycler.setHasFixedSize(true);
        helprecycler.setLayoutManager(new LinearLayoutManager(this));
        helplineAdapter = new HelplineAdapter(Helplinenumber.this,listhelp);
        helprecycler.setAdapter(helplineAdapter);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}