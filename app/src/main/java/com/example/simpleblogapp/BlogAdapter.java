package com.example.simpleblogapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleblogapp.Entity.Author;
import com.example.simpleblogapp.Entity.Blogs;
import com.example.simpleblogapp.Entity.SimpleBlog;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {

    private Context context;
    private List<SimpleBlog> blogsList;
    private List<Blogs> blogs;
    private List<Author> authors;

    public BlogAdapter(Context context) {
        this.context = context;
    }

    public void setBlogsList(List<Blogs> blogs) {
        this.blogs = blogs;
        notifyDataSetChanged();
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public com.example.simpleblogapp.BlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.blog_recycler, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogAdapter.ViewHolder holder, int position) {
        Blogs blogsList = blogs.get(position);
        String imageUri = blogsList.getCover_photo();
        Picasso.with(context.getApplicationContext()).load(imageUri).into(holder.cover);
        holder.title.setText(this.blogs.get(position).getTitle());
        holder.cat.setText(this.blogs.get(position).getCategories());

    }


    @Override
    public int getItemCount() {
        return null != blogsList ? blogsList.size(): 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, cat;
        ImageView cover;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tvTitle);
            cat = view.findViewById(R.id.tvCat);
            cover = view.findViewById(R.id.tvCoverPhoto);
        }
    }

}
