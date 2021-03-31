package com.example.simpleblogapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.simpleblogapp.Client.RestClient;
import com.example.simpleblogapp.Database.AppDatabase;
import com.example.simpleblogapp.Entity.Author;
import com.example.simpleblogapp.Entity.Blogs;
import com.example.simpleblogapp.Entity.SimpleBlog;
import com.example.simpleblogapp.Service.BlogService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private BlogAdapter blogAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BlogAdapter blogAdapter = new BlogAdapter(getApplicationContext());
        Button createBlg = findViewById(R.id.blogAdd);
        createBlg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, CreateBlog.class), 100);
            }
        });
        initializeRecycler();
        loadBlog();
    }
    private void initializeRecycler(){
        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        blogAdapter = new BlogAdapter(this);
        recyclerView.setAdapter(blogAdapter);
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());



        BlogService blogService = RestClient.getRetrofitInstance().create(BlogService.class);

        blogService.getBlogs().enqueue(new Callback<List<SimpleBlog>>() {


            @Override
            public void onResponse(Call<List<SimpleBlog>> call, Response<List<SimpleBlog>> response) {
                try {
                    List<SimpleBlog> blogs = (List<SimpleBlog>) response.body();
                    for(int k = 0; k<blogs.size();k=0){
                        List<Blogs> blogsList = (List<Blogs>) response.body().get(k);
                        for (int i = 0; i < blogsList.size(); i++) {
                            String title = blogsList.get(i).getTitle();
                            String coverP = blogsList.get(i).getCover_photo();
                            String cat = blogsList.get(i).getCategories();

                            String desc = blogsList.get(i).getDescription();
                            int id = blogsList.get(i).getId();
                            if (db.blogDao().getBlogById(i) == null) {
                                Blogs blogs1 = new Blogs();
                                blogs1.setTitle(title);
                                blogs1.setCover_photo(coverP);
                                blogs1.setCategories(cat);
                                blogs1.setId(id);
                                blogs1.setDescription(desc);
                            } else {
                                loadBlog();
                            }

                        }
                    }


                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<SimpleBlog>> call, Throwable t) {
                t.printStackTrace();
            }

        });
    }

    private void loadBlog(){
        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());
        List<SimpleBlog> blogsList = db.blogDao().getAllBlog();
        for (int i = 0; i<blogsList.size(); i++){
            Blogs blogs = new Blogs();
            blogAdapter.setBlogsList((List<Blogs>) blogs);
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 100){
            loadBlog();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}