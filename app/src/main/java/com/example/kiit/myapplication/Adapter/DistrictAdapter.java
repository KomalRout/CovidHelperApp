package com.example.kiit.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kiit.myapplication.EachDistrict;
import com.example.kiit.myapplication.R;
import com.example.kiit.myapplication.modal.DistrictModal;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.kiit.myapplication.Constants.DISTRICT_ACTIVE;
import static com.example.kiit.myapplication.Constants.DISTRICT_CONFIRMED;
import static com.example.kiit.myapplication.Constants.DISTRICT_CONFIRMED_NEW;
import static com.example.kiit.myapplication.Constants.DISTRICT_DEATH;
import static com.example.kiit.myapplication.Constants.DISTRICT_DEATH_NEW;
import static com.example.kiit.myapplication.Constants.DISTRICT_NAME;
import static com.example.kiit.myapplication.Constants.DISTRICT_RECOVERED;
import static com.example.kiit.myapplication.Constants.DISTRICT_RECOVERED_NEW;

public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.DistrictHolder> {
    Context mContext;
    ArrayList<DistrictModal> districtWiseModelArrayList;
    String searchText="";
    SpannableStringBuilder sb;

    public DistrictAdapter(Context mContext, ArrayList<DistrictModal> districtWiseModelArrayList) {
        this.mContext = mContext;
        this.districtWiseModelArrayList = districtWiseModelArrayList;
    }

    @NonNull
    @Override
    public DistrictHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_statewise, parent, false);
        return new DistrictHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DistrictHolder holder, final int position) {

        DistrictModal currentItem = districtWiseModelArrayList.get(position);
        String districtName = currentItem.getDistrict();
        String districtTotal = currentItem.getConfirmed();
        holder.tv_districtTotalCases.setText(NumberFormat.getInstance().format(Integer.parseInt(districtTotal)));
        //holder.tv_districtName.setText(districtName);
        if(searchText.length()>0){
            //color your text here
            int index = districtName.indexOf(searchText);
            sb = new SpannableStringBuilder(districtName);
            Pattern word = Pattern.compile(searchText.toLowerCase());
            Matcher match = word.matcher(districtName.toLowerCase());
            while(match.find()){
                ForegroundColorSpan fcs = new ForegroundColorSpan(Color.rgb(52, 195, 235)); //specify color here
                sb.setSpan(fcs, match.start(), match.end(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                //index = stateName.indexOf(searchText,index+1);

            }
            holder.tv_districtName.setText(sb);

        }else {
            holder.tv_districtName.setText(districtName);
        }
        holder.lin_district_wise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DistrictModal clickedItem = districtWiseModelArrayList.get(position);
                Intent perDistrictIntent = new Intent(mContext, EachDistrict.class);
                perDistrictIntent.putExtra(DISTRICT_NAME, clickedItem.getDistrict());
                perDistrictIntent.putExtra(DISTRICT_CONFIRMED, clickedItem.getConfirmed());
                perDistrictIntent.putExtra(DISTRICT_CONFIRMED_NEW, clickedItem.getNewConfirmed());
                perDistrictIntent.putExtra(DISTRICT_ACTIVE, clickedItem.getActive());
                perDistrictIntent.putExtra(DISTRICT_DEATH, clickedItem.getDeceased());
                perDistrictIntent.putExtra(DISTRICT_DEATH_NEW, clickedItem.getNewDeceased());
                perDistrictIntent.putExtra(DISTRICT_RECOVERED, clickedItem.getRecovered());
                perDistrictIntent.putExtra(DISTRICT_RECOVERED_NEW, clickedItem.getNewRecovered());
                mContext.startActivity(perDistrictIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return districtWiseModelArrayList==null ? 0 : districtWiseModelArrayList.size();
    }
    public void filterList(ArrayList<DistrictModal> filteredList, String search) {
        districtWiseModelArrayList = filteredList;
        this.searchText = search;
        notifyDataSetChanged();
    }

    public class DistrictHolder extends RecyclerView.ViewHolder{

        TextView tv_districtName, tv_districtTotalCases;
        LinearLayout lin_district_wise;
        public DistrictHolder(@NonNull View itemView) {
            super(itemView);
            tv_districtName = itemView.findViewById(R.id.statewise_layout_name_textview);
            tv_districtTotalCases = itemView.findViewById(R.id.statewise_layout_confirmed_textview);
            lin_district_wise = itemView.findViewById(R.id.layout_state_wise_lin);

        }
    }
}
