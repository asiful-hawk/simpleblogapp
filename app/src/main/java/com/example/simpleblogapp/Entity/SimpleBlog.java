package com.example.simpleblogapp.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
@Entity
public class SimpleBlog {

    @PrimaryKey
    public int id;
    @SerializedName("blogs")
    @Expose
    @TypeConverters(Converter.class)
    private List<String> blogsList;

    public List<String> getBlogsList() {
        return blogsList;
    }

    public void setBlogsList(List<String> blogsList) {
        this.blogsList = blogsList;
    }
}
