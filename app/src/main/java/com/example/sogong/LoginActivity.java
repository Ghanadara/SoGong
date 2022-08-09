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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    // 로그에 사용할 TAG 변수 선언
    final private String TAG = getClass().getSimpleName();

    // 사용할 컴포넌트 선언
    EditText userid_et, passwd_et;
    Button login_button, join_button;

    RetrofitService service1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

// 사용할 컴포넌트 초기화
        userid_et = findViewById(R.id.userid_et);
        passwd_et = findViewById(R.id.passwd_et);
        login_button = findViewById(R.id.login_button);
        join_button = findViewById(R.id.join_button);

// 로그인 버튼 이벤트 추가
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// 로그인 함수
                LoginTask loginTask = new LoginTask();
                loginTask.execute(userid_et.getText().toString(), passwd_et.getText().toString());
            }
        });

// 조인 버튼 이벤트 추가
        join_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });


        //서버 데이터 가져오기
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://020e-121-150-211-188.ngrok.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service1 = retrofit.create(RetrofitService.class);

//        Call<PostResult> call = service1.getPosts("todo/8");
//        call.enqueue(new Callback<PostResult>() {
//            @Override
//            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
//                if(response.isSuccessful()){
//                    PostResult result = response.body();
//                    Log.d(TAG,"onResponse : 성공, 결과\n" + result.toString());
//                } else{
//                    Log.d(TAG,"onResponse : 실패");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PostResult> call, Throwable t) {
//                Log.d(TAG,"onFailure"+ t.getMessage());
//            }
//        });
        PostSending postSending = new PostSending("김재환","sexy","true@na.com");
        Call<PostSending> call = service1.setPostBody(postSending);
        call.enqueue(new Callback<PostSending>() {
            @Override
            public void onResponse(Call<PostSending> call, Response<PostSending> response) {
                if(response.isSuccessful()){
                    PostSending postresponse = response.body();
                    Log.d("성공",postresponse.getUserId() +" "+ postresponse.getPassword());
                }else {
                    Log.d("실패","김재환 실패");
                    return;
                }

            }

            @Override
            public void onFailure(Call<PostSending> call, Throwable t) {
                Log.d(TAG,"onFailure"+ t.getMessage());
            }
        });

    }

    class LoginTask extends AsyncTask<String, Void, String> {

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
// userid 값을 가지고 ListActivity 로 이동
                Toast.makeText(LoginActivity.this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                intent.putExtra("userid", userid_et.getText().toString());
                startActivity(intent);
            } else if (result.equals("fail")) {
                Toast.makeText(LoginActivity.this, "잘못된 정보입니다.", Toast.LENGTH_SHORT).show();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String userid = params[0];
            String passwd = params[1];

            String response = "";
            if (Server.findUser(userid, passwd)) {
                response += "success";
            } else {
                response += "fail";
            }
            return response;

//            String server_url = "http://15.164.252.136/login.php";
//
//
//
//            URL url;
//            String response = "";
//            try {
//                url = new URL(server_url);
//
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setReadTimeout(15000);
//                conn.setConnectTimeout(15000);
//                conn.setRequestMethod("POST");
//                conn.setDoInput(true);
//                conn.setDoOutput(true);
//                Uri.Builder builder = new Uri.Builder()
//                        .appendQueryParameter("userid", userid)
//                        .appendQueryParameter("passwd", passwd);
//                String query = builder.build().getEncodedQuery();
//
//                OutputStream os = conn.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(
//                        new OutputStreamWriter(os, "UTF-8"));
//                writer.write(query);
//                writer.flush();
//                writer.close();
//                os.close();
//
//                conn.connect();
//                int responseCode=conn.getResponseCode();
//
//                if (responseCode == HttpsURLConnection.HTTP_OK) {
//                    String line;
//                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                    while ((line=br.readLine()) != null) {
//                        response+=line;
//                    }
//                }
//                else {
//                    response="";
//
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return response;
        }
    }

}