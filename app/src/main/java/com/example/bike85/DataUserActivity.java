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

public class DataUserActivity extends AppCompatActivity {
    LinearLayout id;
    EditText nama, nik, alamat;
    Button btnupdate,btndelete;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_user);
        getSupportActionBar().setTitle("Edit User");
        id = (LinearLayout) findViewById(R.id.etId);
        nama = (EditText) findViewById(R.id.etNama);
        nik = (EditText) findViewById(R.id.etNik);
        alamat = (EditText) findViewById(R.id.etAlamat);
        btnupdate =(Button) findViewById(R.id.btnUpdate);
        btndelete=(Button) findViewById(R.id.btnhapus);

        Intent data = getIntent();
        final String iddata = data.getStringExtra("id");
        if(iddata != null) {
            btnupdate.setVisibility(View.VISIBLE);
            btndelete.setVisibility(View.VISIBLE);
            nama.setText(data.getStringExtra("nama"));
            nik.setText(data.getStringExtra("nik"));
            alamat.setText(data.getStringExtra("alamat"));
        }

        pd = new ProgressDialog(this);


        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Loading Hapus ...");
                pd.setCancelable(false);
                pd.show();

                ApiRequestUser api = Retroserver.getClient().create(ApiRequestUser.class);
                Call<ResponsModel> del  = api.deleteData(iddata);
                del.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        Log.d("Retro", "onResponse");
                        Toast.makeText(DataUserActivity.this, response.body().getPesan(),Toast.LENGTH_SHORT).show();
                        Intent gotampil = new Intent(DataUserActivity.this,ListDataActivity.class);
                        startActivity(gotampil);

                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pd.hide();
                        Log.d("Retro", "onFailure");
                    }
                });
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Update ....");
                pd.setCancelable(false);
                pd.show();

                ApiRequestUser api = Retroserver.getClient().create(ApiRequestUser.class);
                Call<ResponsModel> update = api.updateData(iddata,nama.getText().toString(),nik.getText().toString(),alamat.getText().toString());
                update.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        Log.d("Retro", "Response");
                        Toast.makeText(DataUserActivity.this,response.body().getPesan(),Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(DataUserActivity.this, ListUpdateDeleteActivity.class);
        startActivity(intent);
        finish();
    }
}
