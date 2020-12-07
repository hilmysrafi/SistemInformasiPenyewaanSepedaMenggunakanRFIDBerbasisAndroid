package com.example.bike85;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bike85.api.ApiRequestUser;
import com.example.bike85.api.Retroserver;
import com.example.bike85.model.ResponsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataTopUpActivity extends AppCompatActivity {
    LinearLayout id;
    EditText nama, saldo;
    Button btntopup;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_top_up);
        getSupportActionBar().setTitle("Checkout");
        id = (LinearLayout) findViewById(R.id.etId);
        nama = (EditText) findViewById(R.id.etNama);
        saldo = (EditText) findViewById(R.id.etSaldo);
        btntopup =(Button) findViewById(R.id.btnTopup);

        Intent data = getIntent();
        final String iddata = data.getStringExtra("id");
        if(iddata != null) {
            btntopup.setVisibility(View.VISIBLE);
            nama.setText(data.getStringExtra("nama"));
            saldo.setText(data.getStringExtra("saldo"));
        }

        pd = new ProgressDialog(this);

        btntopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Top Up ....");
                pd.setCancelable(false);
                pd.show();

                ApiRequestUser api = Retroserver.getClient().create(ApiRequestUser.class);
                Call<ResponsModel> topup = api.updateTopup(iddata,saldo.getText().toString());
                topup.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        Log.d("Retro", "Response");
                        Toast.makeText(DataTopUpActivity.this,response.body().getPesan(),Toast.LENGTH_SHORT).show();
                        pd.hide();
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pd.hide();
                        Log.d("Retro", "OnFailure");

                    }
                });
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DataTopUpActivity.this, ListTopUpActivity.class);
        startActivity(intent);
        finish();
    }
}

