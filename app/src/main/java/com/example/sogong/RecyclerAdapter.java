package com.example.sogong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private Context c;
    private List<PostSending> PostList;

    public RecyclerAdapter(Context c, List<PostSending> PostList) {
        this.c = c;
        this.PostList = PostList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(c).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {

        holder.title.setText(PostList.get(position).getTitle());
        holder.complete.setText(String.valueOf(PostList.get(position).isComplete()));
        holder.important.setText(String.valueOf(PostList.get(position).isImportant()));

    }

    @Override
    public int getItemCount() {
        return PostList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView complete;
        TextView important;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.title);
            complete = (TextView)itemView.findViewById(R.id.complete);
            important = (TextView)itemView.findViewById(R.id.important);

        }
    }
}