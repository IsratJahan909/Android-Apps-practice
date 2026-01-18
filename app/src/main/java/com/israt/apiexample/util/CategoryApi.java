package com.israt.apiexample.util;





import com.israt.apiexample.entity.Category;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface CategoryApi {

    // CREATE
    @Multipart
    @POST("api/categories")
    Call<Category> create(
            @Part("category") RequestBody category,
            @Part MultipartBody.Part image
    );

    // UPDATE
    @Multipart
    @PUT("api/categories/{id}")
    Call<Category> update(
            @Path("id") Long id,
            @Part("category") RequestBody category,
            @Part MultipartBody.Part image
    );

    // GET ALL
    @GET("api/categories")
    Call<List<Category>> getAll();

    // GET BY ID
    @GET("api/categories/{id}")
    Call<Category> getById(@Path("id") Long id);

    // DELETE
    @DELETE("api/categories/{id}")
    Call<Void> delete(@Path("id") Long id);
}
