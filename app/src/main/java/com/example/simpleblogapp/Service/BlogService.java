package com.example.simpleblogapp.Service;

import com.example.simpleblogapp.Entity.Blogs;
import com.example.simpleblogapp.Entity.SimpleBlog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BlogService {

    @GET("db")
    Call<List<SimpleBlog>> getBlogs();


}
