package com.example.simpleblogapp.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.simpleblogapp.Entity.Blogs;
import com.example.simpleblogapp.Entity.SimpleBlog;

import java.util.List;

@Dao
public interface BlogDao {
    @Query("SELECT * FROM SimpleBlog")
    List<SimpleBlog> getAllBlog();
    @Query("SELECT * FROM SimpleBlog WHERE id = :id")
    SimpleBlog getBlogById(int id);
    @Insert
    void insertBlogs(SimpleBlog simpleBlog);
}
