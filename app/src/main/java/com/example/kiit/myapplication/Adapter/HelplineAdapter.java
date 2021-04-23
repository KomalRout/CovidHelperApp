package com.example.kiit.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kiit.myapplication.R;
import com.example.kiit.myapplication.modal.HelplineModal;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HelplineAdapter extends RecyclerView.Adapter<HelplineAdapter.HelplineHolder> {

    Context context;
    List<HelplineModal> listHelp = new ArrayList<>();

    public HelplineAdapter(Context context, List<HelplineModal> listHelp) {
        this.context = context;
        this.listHelp = listHelp;
    }

    @NonNull
    @Override
    public HelplineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.call_layout,parent,false);
        return new HelplineHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HelplineHolder holder, int position) {
       final HelplineModal hmodal = listHelp.get(position);
        holder.statename.setText(hmodal.getSt_name());
        holder.phno.setText(hmodal.getPhone());
        holder.phimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonenumber = holder.phno.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+91"+phonenumber));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listHelp.size();
    }

    public class HelplineHolder extends RecyclerView.ViewHolder{

        TextView statename,phno;
        ImageView phimg;
        public HelplineHolder(@NonNull View itemView) {
            super(itemView);
            statename = itemView.findViewById(R.id.stname);
            phno=itemView.findViewById(R.id.phno);
            phimg = itemView.findViewById(R.id.call_img);
        }
    }
    public List<HelplineModal> getListHelp(){
        return listHelp;
    }
}
