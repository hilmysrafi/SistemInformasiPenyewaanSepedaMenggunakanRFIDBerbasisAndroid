package com.example.bike85;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.bike85.adapter.AdapterData;
import com.example.bike85.api.ApiRequestUser;
import com.example.bike85.api.Retroserver;
import com.example.bike85.model.DataModel;
import com.example.bike85.model.ResponsModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDataActivity extends AppCompatActivity {
    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<DataModel> mItems = new ArrayList<>();
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        getSupportActionBar().setTitle("Data User");

        tampilData();

        /*menambah warna pada SwipeRefreshLayout*/
        final SwipeRefreshLayout dorefresh = (SwipeRefreshLayout)findViewById(R.id.swipeRefresh);
        dorefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        /*event ketika widget dijalankan*/
        dorefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                refreshItem();
            }

            void refreshItem() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dorefresh.setRefreshing(true);
                    }
                },8000);
                tampilData();
                onItemLoad();
            }

            void onItemLoad() {
                dorefresh.setRefreshing(false);
            }

        });

    }

    private void tampilData() {
        pd = new ProgressDialog(this);
        mRecycler = (RecyclerView) findViewById(R.id.recyclerTemp);
        mManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(mManager);

        pd.setMessage("Loading ...");
        pd.setCancelable(false);
        pd.show();

        ApiRequestUser api = Retroserver.getClient().create(ApiRequestUser.class);
        Call<ResponsModel> getdata = api.getUser();
        getdata.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                pd.hide();
                Log.d("RETRO", "RESPONSE : " + response.body().getKode());
                mItems = response.body().getResult();

                mAdapter = new AdapterData(ListDataActivity.this,mItems);
                mRecycler.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                pd.hide();
                Log.d("RETRO", "FAILED : respon gagal");

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ListDataActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}