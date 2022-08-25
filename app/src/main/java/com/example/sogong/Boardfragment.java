package com.example.sogong;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//ㅁㅁ
public class Boardfragment extends Fragment {
    private static final String TAG = "Boardfragment";
    private RecyclerView postrecyclerView;
    private PostAdapter postAdapter;

    private RecyclerView boardRecyclerView;
    //private BoardCategoryAdapter boardCategoryAdapter;
    private BoardCategoryAdapter2 boardCategoryAdapter2;

    private String[] boardCategoryList;
    private ArrayList<BoardCategory> boardCategories;
    private ClickCallbackListener callbackListener;


    private ArrayList<PostObject> list = new ArrayList<>();
    private int prev=-1, prev_big=-1;


    String bigcat="";
    boolean[] is_checked = new boolean[12];
    boolean[] is_checked_big = new boolean[7];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_board, container, false);

        for(int i=0;i<10;i++)
            is_checked[i]=false;
        for(int i=0;i<7;i++)
            is_checked_big[i]=false;

        if (this.getArguments() != null)
            bigcat = this.getArguments().getString("bigcat");

        Log.w("omg get bigcat : ",bigcat);

        boardRecyclerView = v.findViewById(R.id.boardCategoryRecyclerView);
        boardCategoryAdapter2 = new BoardCategoryAdapter2(getContext(),boardCategories);
        boardRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        boardRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                LinearLayoutManager.VERTICAL));
        boardRecyclerView.setAdapter(boardCategoryAdapter2);
//        // 큰카테고리 뷰에따른 작은카테고리
//        boardRecyclerView = v.findViewById(R.id.boardCategoryRecyclerView);
//        boardCategoryList = getResources().getStringArray(R.array.Boardcategory);
//        int cnt=0;
//        for(String cat:boardCategoryList) {
//            if(cat.equals(bigcat)) {
//                prev_big = cnt;
//                is_checked_big[cnt]=true;
//                break;
//            }
//            cnt++;
//        }
//        boardCategoryAdapter2 = new BoardCategoryAdapter2(boardCategoryList, is_checked_big);
//
//        boardCategoryAdapter2.setOnItemClickListener(new boardCategoryAdapter2.setOnItemClickListener(); {
//            @Override
//            public void onItemClick(RecyclerView.ViewHolder holder, View v, int pos) {
//                String item = BoardCategoryAdapter.getItem(pos);
//                Toast.makeText(getActivity(), "아이템 선택됨 : " + item, Toast.LENGTH_LONG).show();
//            }
//        });
//
//        boardRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
//        boardRecyclerView.setAdapter(boardCategoryAdapter);
//
//        setRecyclerView(v,bigcat);
//

        return v;
    }

    public void setRecyclerView(View v, String boardcategory) {
        RetrofitService retrofitService = RetrofitClient.getClient().create(RetrofitService.class);
        Call<List<PostObject>> call = retrofitService.getAllPosts();
        call.enqueue(new Callback<List<PostObject>>() {
            @Override
            public void onResponse(Call<List<PostObject>> call, Response<List<PostObject>> response) {
                if(response.isSuccessful()){
                    List<PostObject> posts = response.body();
                    for(PostObject postObject : posts){
                        Log.d("성공", postObject.getTitle());
                    }
                    postAdapter = new PostAdapter(getActivity(), posts);
                    postrecyclerView.setAdapter(postAdapter);
                }else {
                    Log.d("실패","응답 실패");
                    return;
                }
            }

            @Override
            public void onFailure(Call<List<PostObject>> call, Throwable t) {
                Log.d("실패","에러남");
            }
        });
    }


    private void startToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
    private ArrayList<BoardCategory> getData(ArrayList<BoardCategory> arrayList){

        for(int i = 0; i < 10; i++){

            arrayList.add(new BoardCategory(i +"번쨰 아이템", false));
        }

        return arrayList;
    }
}