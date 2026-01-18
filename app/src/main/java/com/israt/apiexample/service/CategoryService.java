package com.israt.apiexample.service;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;

import com.google.gson.Gson;
import com.israt.apiexample.entity.Category;
import com.israt.apiexample.util.ApiClient;
import com.israt.apiexample.util.CategoryApi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;


public class CategoryService {
    private final CategoryApi api;
    private  final Context context;

    public CategoryService(Context context) {
        this.context = context;
        api = ApiClient.getClient().create(CategoryApi.class);
    }

 public Call<List<Category>> getAll() {
        return api.getAll();
 }

 public Call<Void> delete(Long id) {
     return api.delete(id);
 }

    // 🔹 category JSON part
    private RequestBody categoryBody(Category c) {
        return RequestBody.create(
                MediaType.parse("application/json"),
                new Gson().toJson(c)
        );
    }

    // 🔹 SAFE image multipart (NO FileUtils, Scoped Storage safe)
    private MultipartBody.Part imagePart(Uri uri) throws Exception {

        ContentResolver resolver = context.getContentResolver();
        String fileName = "upload_" + System.currentTimeMillis();

        Cursor cursor = resolver.query(uri, null, null, null, null);
        if (cursor != null) {
            int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            if (cursor.moveToFirst() && nameIndex != -1) {
                fileName = cursor.getString(nameIndex);
            }
            cursor.close();
        }

        InputStream inputStream = resolver.openInputStream(uri);
        File tempFile = new File(context.getCacheDir(), fileName);

        OutputStream outputStream = new FileOutputStream(tempFile);

        byte[] buffer = new byte[4096];
        int read;
        while ((read = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, read);
        }

        inputStream.close();
        outputStream.close();

        RequestBody body = RequestBody.create(
                MediaType.parse(resolver.getType(uri)),
                tempFile
        );

        return MultipartBody.Part.createFormData(
                "image",
                tempFile.getName(),
                body
        );
    }

    // ================= CRUD =================

    public Call<Category> create(Category c, Uri imageUri) throws Exception {
        return api.create(
                categoryBody(c),
                imageUri != null ? imagePart(imageUri) : null
        );
    }

    public Call<Category> update(Long id, Category c, Uri imageUri) throws Exception {
        return api.update(
                id,
                categoryBody(c),
                imageUri != null ? imagePart(imageUri) : null
        );
    }

}
