package com.example.bike85;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class BikeViewHolder extends RecyclerView.ViewHolder {
    public TextView tvSepeda;
    public TextView tvManufactur;
    public TextView tvRssi;
    public TextView tvWaktu;

    public BikeViewHolder(View itemView) {
        super(itemView);
        tvSepeda = itemView.findViewById(R.id.tvSepeda);
        tvManufactur = itemView.findViewById(R.id.tvManufactur);
        tvRssi = itemView.findViewById(R.id.tvRssi);
        tvWaktu = itemView.findViewById(R.id.tvWaktu);
    }

    public void bindToBike(Bike bike){
        tvSepeda.setText(bike.Sepeda);
        tvManufactur.setText(bike.Manufactur);
        tvRssi.setText(bike.RSSI);
        tvWaktu.setText(bike.lastUpdate);

    }
}
