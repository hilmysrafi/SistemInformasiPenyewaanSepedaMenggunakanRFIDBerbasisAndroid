package com.example.bike85;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class BikeActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    private FirebaseRecyclerAdapter<Bike, BikeViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike);
        getSupportActionBar().setTitle("Daftar Sepeda");

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mRecycler = findViewById(R.id.list_bike);
        mRecycler.setHasFixedSize(true);
        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        Query query = getQuery(mDatabase);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Bike>()
                .setQuery(query, Bike.class)
                .build();
        mAdapter = new FirebaseRecyclerAdapter<Bike, BikeViewHolder>(options) {
            @Override
            public BikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new BikeViewHolder(inflater.inflate(R.layout.layoutlistbike, parent, false));
            }
            @Override
            protected void onBindViewHolder(@NonNull BikeViewHolder holder, int position, @NonNull final Bike model) {
                holder.bindToBike(model);
            }
        };

        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }

    private Query getQuery(DatabaseReference mDatabase) {
        Query query = mDatabase.child("Data BLE");
        return query;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BikeActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
