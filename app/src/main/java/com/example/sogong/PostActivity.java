package com.example.sogong;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    // 로그에 사용할 TAG 변수 선언
    final private String TAG = getClass().getSimpleName();

    // 사용할 컴포넌트 선언
    EditText title_et, content_et;
    Button reg_button;

    // 유저아이디 변수
    String userid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

// ListActivity 에서 넘긴 userid 를 변수로 받음
        userid = getIntent().getStringExtra("userid");

// 컴포넌트 초기화
        title_et = findViewById(R.id.title_et);
        content_et = findViewById(R.id.content_et);
        reg_button = findViewById(R.id.reg_button);

// 버튼 이벤트 추가
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// 게시물 등록 함수
//                RegBoard regBoard = new RegBoard();
//                regBoard.execute(userid, title_et.getText().toString(), content_et.getText().toString());

//                RetrofitService retrofitService = RetrofitClient.getClient().create(RetrofitService.class);
//
//                PostObject postObject = new PostObject(title_et.getText().toString(), true, true);
//                Call<PostObject> call = retrofitService.setPostBody(postObject);
//                call.enqueue(new Callback<PostObject>() {
//                    @Override
//                    public void onResponse(Call<PostObject> call, Response<PostObject> response) {
//                        if (response.isSuccessful()) {
//                            PostObject postresponse = response.body();
//                            Log.d("성공", postresponse.getTitle());
//                        } else {
//                            Log.d("실패", title_et.getText().toString() + "post 실패");
//                            return;
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<PostObject> call, Throwable t) {
//                        Log.d(TAG, "onFailure" + t.getMessage());
//                    }
//                });
                Intent intent = new Intent(PostActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

    }

    class RegBoard extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.d(TAG, "onPreExecute");
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(TAG, "onPostExecute, " + result);

            if (result.equals("success")) {
// 결과값이 success 이면
// 토스트 메시지를 뿌리고
// 이전 액티비티(ListActivity)로 이동,
// 이때 ListActivity 의 onResume() 함수 가 호출되며, 데이터를 새로 고침
                Toast.makeText(PostActivity.this, "등록되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(PostActivity.this, result, Toast.LENGTH_SHORT).show();
            }

        }


        @Override
        protected String doInBackground(String... params) {

            String userid = params[0];
            String title = params[1];
            String content = params[2];

            String response = "";

            Server.addPost(userid, title, content);
            response += "success";

            return response;
        }
    }
}