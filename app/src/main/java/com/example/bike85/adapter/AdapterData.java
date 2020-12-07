package com.example.bike85.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bike85.ListDataActivity;
import com.example.bike85.R;
import com.example.bike85.model.DataModel;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {

    private List<DataModel> mList ;

    public  AdapterData(ListDataActivity listDataActivity, List<DataModel> mList)
    {
        this.mList = mList;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutlist,parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        DataModel dm = mList.get(position);
        holder.nama.setText(dm.getNama());
        holder.nik.setText(dm.getNik());
        holder.alamat.setText(dm.getAlamat());
        holder.saldo.setText(dm.getSaldo());
        holder.waktu.setText(dm.getWaktu());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends  RecyclerView.ViewHolder{
        TextView nama, nik, alamat, saldo, waktu;
        DataModel dm;
        public HolderData (View v)
        {
            super(v);

            nama  = (TextView) v.findViewById(R.id.tvNama);
            nik = (TextView) v.findViewById(R.id.tvNik);
            alamat = (TextView) v.findViewById(R.id.tvAlamat);
            saldo = (TextView) v.findViewById(R.id.tvSaldo);
            waktu = (TextView) v.findViewById(R.id.tvWaktu);


        }
    }
}

