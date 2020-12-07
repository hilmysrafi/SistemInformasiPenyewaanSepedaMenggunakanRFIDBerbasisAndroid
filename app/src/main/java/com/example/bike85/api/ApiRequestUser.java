package com.example.bike85.api;

import com.example.bike85.model.ResponsModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequestUser {

    @GET("read.php")
    Call<ResponsModel> getUser();

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponsModel> updateData( @Field("id") String id,
                                   @Field("nama") String nama,
                                   @Field("nik") String nik,
                                   @Field("alamat") String alamat);
    @FormUrlEncoded
    @POST("topup.php")
    Call<ResponsModel> updateTopup( @Field("id") String id,
                                    @Field("saldo") String saldo);

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponsModel> deleteData(@Field("id") String id);
}
