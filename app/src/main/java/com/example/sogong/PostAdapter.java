package com.example.sogong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    private Context c;
    private static List<PostObject> PostList;

    public interface OnItemClickListener {
        void onItemClicked(View v, int position);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public PostAdapter(Context c, List<PostObject> PostList) {
        this.c = c;
        this.PostList = PostList;
    }

    @NonNull
    @Override
    public PostAdapter.PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(c).inflate(R.layout.recycler_boardlist, parent, false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostHolder holder, int position) {

        holder.title.setText(PostList.get(position).getTitle());
        holder.complete.setText(PostList.get(position).getAuthor());
        holder.important.setText(PostList.get(position).getCategory());

    }

    @Override
    public int getItemCount() {
        return PostList.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView complete;
        TextView important;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            complete = (TextView) itemView.findViewById(R.id.complete);
            important = (TextView) itemView.findViewById(R.id.important);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAbsoluteAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemClicked(view, pos);
                        }
                        //PostSending post  = PostList.get(pos);
                    }

                }
            });

        }
    }
    public static PostObject getItem(int position){
        return PostList.get(position);
    }
}