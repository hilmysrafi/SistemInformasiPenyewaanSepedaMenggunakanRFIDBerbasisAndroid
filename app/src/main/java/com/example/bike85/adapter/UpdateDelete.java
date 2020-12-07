package com.example.bike85.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bike85.DataUserActivity;
import com.example.bike85.R;
import com.example.bike85.model.DataModel;

import java.util.List;

public class UpdateDelete extends RecyclerView.Adapter<UpdateDelete.HolderData> {
    private List<DataModel> mList ;
    private Context ctx;

    public  UpdateDelete (Context ctx, List<DataModel> mList)
    {
        this.ctx = ctx;
        this.mList = mList;
    }

    @Override
    public UpdateDelete.HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutlistupdate,parent, false);
        UpdateDelete.HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(UpdateDelete.HolderData holder, int position) {
        DataModel dm = mList.get(position);
        holder.id.setText(dm.getId());
        holder.nama.setText(dm.getNama());
        holder.nik.setText(dm.getNik());
        holder.alamat.setText(dm.getAlamat());
        holder.waktu.setText(dm.getWaktu());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends  RecyclerView.ViewHolder{
        TextView id, nama, nik, alamat, waktu;
        DataModel dm;
        public HolderData (View v)
        {
            super(v);

            id  = (TextView) v.findViewById(R.id.tvId);
            nama  = (TextView) v.findViewById(R.id.tvNama);
            nik = (TextView) v.findViewById(R.id.tvNik);
            alamat = (TextView) v.findViewById(R.id.tvAlamat);
            waktu = (TextView) v.findViewById(R.id.tvWaktu);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goInput = new Intent(ctx, DataUserActivity.class);
                    goInput.putExtra("id", dm.getId());
                    goInput.putExtra("nama", dm.getNama());
                    goInput.putExtra("nik", dm.getNik());
                    goInput.putExtra("alamat", dm.getAlamat());

                    ctx.startActivity(goInput);
                }
            });
        }
    }
}


