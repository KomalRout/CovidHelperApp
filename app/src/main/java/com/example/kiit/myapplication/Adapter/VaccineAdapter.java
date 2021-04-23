package com.example.kiit.myapplication.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kiit.myapplication.R;
import com.example.kiit.myapplication.modal.VaccineModal;

import java.util.ArrayList;
import java.util.List;

public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.StateVaccine> {
    Context context;
    ArrayList<VaccineModal> vaccineModalsList;

    public VaccineAdapter(Context context, ArrayList<VaccineModal> vaccineModalsList) {
        this.context = context;
        this.vaccineModalsList = vaccineModalsList;
        Log.i("msfasas", vaccineModalsList + "kkkk");
    }

    @NonNull
    @Override
    public StateVaccine onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vaccinestate_layout, parent, false);
        return new StateVaccine(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StateVaccine holder, int position) {
        final VaccineModal vaccineModal = vaccineModalsList.get(position);
        holder.totalvacc.setText(String.valueOf(vaccineModal.getTotal()));
        holder.stnam.setText(vaccineModal.getState());

    }

    @Override
    public int getItemCount() {
        return vaccineModalsList != null && vaccineModalsList.size() > 0 ? vaccineModalsList.size() : 0;
    }

    public class StateVaccine extends RecyclerView.ViewHolder {
        TextView stnam, totalvacc;

        public StateVaccine(@NonNull View itemView) {
            super(itemView);
            stnam = itemView.findViewById(R.id.state_vaccine);
            totalvacc = itemView.findViewById(R.id.totalvaccine);

        }
    }
}
