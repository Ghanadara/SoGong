package com.example.sogong;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BoardCategoryAdapter2 extends RecyclerView.Adapter<BoardCategoryAdapter2.BoardcategoryViewHolder> {

    Context context;
    ArrayList<BoardCategory> arrayList;

    public BoardCategoryAdapter2(Context context, ArrayList<BoardCategory> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public BoardcategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_boardcategory_unselected, parent, false);

        return new BoardcategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardcategoryViewHolder holder, int position) {

        BoardCategory item = arrayList.get(position);
        holder.textView.setText(item.getName());

        ///선택값에 따른 배경색 설정
        if(item.isSelected()){

            holder.itemView.setBackgroundColor(Color.parseColor("#90CAF9"));
        }else{
            holder.itemView.setBackgroundColor(Color.parseColor("#EF9A9A"));
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class BoardcategoryViewHolder extends RecyclerView.ViewHolder{

        LinearLayout layout;

        TextView textView;

        public BoardcategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.boardcategoryLayout);
            textView = itemView.findViewById(R.id.boardcategoryName);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setMultipleSelection(getAdapterPosition());
                }
            });
        }
    }

    //다중 선택 기능
    private void setMultipleSelection(int adapterPosition){

        //반대의 값을 넣어준다.
        if(arrayList.get(adapterPosition).isSelected()){

            arrayList.get(adapterPosition).setSelected(false);
        }else{
            arrayList.get(adapterPosition).setSelected(true);
        }

        //데이터 적용
        notifyDataSetChanged();
    }
}