package com.example.sogong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BoardCategoryAdapter extends RecyclerView.Adapter<BoardCategoryAdapter.BoardCategoryHolder> {
    Context context;
    private String[] category;
    private ClickCallbackListener callbackListener;
    private boolean[] is_checked;

    public  interface OnItemClickListener {
        public void onItemClick(RecyclerView.ViewHolder holder, View v, int pos);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    BoardCategoryAdapter(Context context,String[] category) {
        this.context = context;
        this.category = category;
    }

    public void setCallbackListener(ClickCallbackListener callbackListener) {
        this.callbackListener = callbackListener;
    }

    public class BoardCategoryHolder extends RecyclerView.ViewHolder {
        TextView boardcategoryName;
        ClickCallbackListener callbackListener;

        OnItemClickListener listener;

        public BoardCategoryHolder(@NonNull View itemView) {
            super(itemView);
            boardcategoryName = (TextView) itemView.findViewById(R.id.boardcategoryName);

            boardcategoryName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAbsoluteAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(BoardCategoryHolder.this,view,position);

                    }
                }
            });
        }

        public void onBind(String name, ClickCallbackListener callbackListener) {
            boardcategoryName.setText(name);
            this.callbackListener = callbackListener;
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
    }

    @NonNull
    @Override
    public BoardCategoryAdapter.BoardCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;
        if (viewType == 1)
            view = inflater.inflate(R.layout.item_boardcategory_selected, parent, false);
        else
            view = inflater.inflate(R.layout.item_boardcategory_unselected, parent, false);

        BoardCategoryAdapter.BoardCategoryHolder vh = new BoardCategoryAdapter.BoardCategoryHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull BoardCategoryHolder holder, int position) {
        holder.onBind(category[position], callbackListener);

        holder.setOnItemClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return category.length;
    }

//    @Override
//    public int getItemViewType(int position) {
//        if (is_checked[position])
//            return 1;
//        else
//            return 0;
//    }
    public String getItem(int position){
        return category[position];
    }


}


