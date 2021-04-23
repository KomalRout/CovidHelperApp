package com.example.kiit.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kiit.myapplication.EachState;
import com.example.kiit.myapplication.R;
import com.example.kiit.myapplication.modal.StateWiseModal;

import java.util.ArrayList;

import static com.example.kiit.myapplication.Constants.STATE_ACTIVE;
import static com.example.kiit.myapplication.Constants.STATE_CONFIRMED;
import static com.example.kiit.myapplication.Constants.STATE_CONFIRMED_NEW;
import static com.example.kiit.myapplication.Constants.STATE_DEATH;
import static com.example.kiit.myapplication.Constants.STATE_DEATH_NEW;
import static com.example.kiit.myapplication.Constants.STATE_LAST_UPDATE;
import static com.example.kiit.myapplication.Constants.STATE_NAME;
import static com.example.kiit.myapplication.Constants.STATE_RECOVERED;
import static com.example.kiit.myapplication.Constants.STATE_RECOVERED_NEW;

public class StateWiseAdapter extends RecyclerView.Adapter<StateWiseAdapter.StatewiseHolder> {
    private Context mContext;
    private ArrayList<StateWiseModal> stateWiseModelArrayList;
    private String searchText = "";

    public StateWiseAdapter(Context mContext, ArrayList<StateWiseModal> stateWiseModelArrayList) {
        this.mContext = mContext;
        this.stateWiseModelArrayList = stateWiseModelArrayList;
    }

    private SpannableStringBuilder sb;

    @NonNull
    @Override
    public StatewiseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_statewise, parent, false);
        return new StatewiseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatewiseHolder holder, final int position) {

        StateWiseModal currentItem = stateWiseModelArrayList.get(position);
        String stateName = currentItem.getState();
        String stateTotal = currentItem.getConfirmed();
        holder.tv_stateTotalCases.setText(stateTotal);
        holder.tv_stateName.setText(stateName);
        holder.lin_state_wise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateWiseModal clickeditem = stateWiseModelArrayList.get(position);
                Intent perStateIntent = new Intent(mContext, EachState.class);
                perStateIntent.putExtra(STATE_NAME, clickeditem.getState());
                perStateIntent.putExtra(STATE_CONFIRMED, clickeditem.getConfirmed());
                perStateIntent.putExtra(STATE_CONFIRMED_NEW, clickeditem.getConfirmed_new());
                perStateIntent.putExtra(STATE_ACTIVE, clickeditem.getActive());
                perStateIntent.putExtra(STATE_DEATH, clickeditem.getDeath());
                perStateIntent.putExtra(STATE_DEATH_NEW, clickeditem.getDeath_new());
                perStateIntent.putExtra(STATE_RECOVERED, clickeditem.getRecovered());
                perStateIntent.putExtra(STATE_RECOVERED_NEW, clickeditem.getRecovered_new());
                perStateIntent.putExtra(STATE_LAST_UPDATE, clickeditem.getLastupdate());

                mContext.startActivity(perStateIntent);
            }
        });
    }

    public void filterList(ArrayList<StateWiseModal> filteredList, String text) {
        stateWiseModelArrayList = filteredList;
        this.searchText = text;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return stateWiseModelArrayList == null? 0 : stateWiseModelArrayList.size();
    }

    public class StatewiseHolder extends RecyclerView.ViewHolder {

        TextView tv_stateName, tv_stateTotalCases;
        LinearLayout lin_state_wise;
        public StatewiseHolder(@NonNull View itemView) {
            super(itemView);
            tv_stateName = itemView.findViewById(R.id.statewise_layout_name_textview);
            tv_stateTotalCases = itemView.findViewById(R.id.statewise_layout_confirmed_textview);
            lin_state_wise = itemView.findViewById(R.id.layout_state_wise_lin);
        }
    }
}
