package com.example.bike85.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bike85.DataTopUpActivity;
import com.example.bike85.R;
import com.example.bike85.model.DataModel;

import java.util.List;

public class TopUp extends RecyclerView.Adapter<TopUp.HolderData> {
    private List<DataModel> mList ;
    private Context ctx;

    public  TopUp (Context ctx, List<DataModel> mList)
    {
        this.ctx = ctx;
        this.mList = mList;
    }

    @Override
    public TopUp.HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutlisttopup,parent, false);
        TopUp.HolderData holder = new TopUp.HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(TopUp.HolderData holder, int position) {
        DataModel dm = mList.get(position);
        holder.id.setText(dm.getId());
        holder.nama.setText(dm.getNama());
        holder.saldo.setText(dm.getSaldo());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends  RecyclerView.ViewHolder{
        TextView id, nama, saldo;
        DataModel dm;
        public HolderData (View v)
        {
            super(v);

            id  = (TextView) v.findViewById(R.id.tvId);
            nama  = (TextView) v.findViewById(R.id.tvNama);
            saldo = (TextView) v.findViewById(R.id.tvSaldo);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goInput = new Intent(ctx, DataTopUpActivity.class);
                    goInput.putExtra("id", dm.getId());
                    goInput.putExtra("nama", dm.getNama());
                    goInput.putExtra("saldo", dm.getSaldo());

                    ctx.startActivity(goInput);
                }
            });
        }
    }
}

