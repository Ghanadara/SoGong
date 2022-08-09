package com.example.sogong;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity {
    // 로그 찍을 때 사용하는 TAG 변수
    final private String TAG = getClass().getSimpleName();

    // 사용할 컴포넌트 선언
    EditText userid_et, passwd_et;
    Button join_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

// 컴포넌트 초기화
        userid_et = findViewById(R.id.userid_et);
        passwd_et = findViewById(R.id.passwd_et);
        join_button = findViewById(R.id.join_button);

// 버튼 이벤트 추가
        join_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// 회원가입 함수 호출
                JoinTask joinTask = new JoinTask();
                joinTask.execute(userid_et.getText().toString(), passwd_et.getText().toString());
            }
        });
    }

    class JoinTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.d(TAG, "onPreExecute");
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Log.d(TAG, "onPostExecute, " + result);

// 결과값이 success 로 나오면
            if (result.equals("success")) {

//토스트 메시지를 뿌리고, 이전 액티비티(LoginActivity) 로 돌아감
                Toast.makeText(getApplicationContext(), "성공적으로 회원가입 되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            } else if (result.equals("fail")){
                Toast.makeText(JoinActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected String doInBackground(String... params) {

            String userid = params[0];
            String passwd = params[1];

            String response = "";

            if (Server.addUser(userid, passwd)) {
                Toast.makeText(JoinActivity.this, "있는 아이디입니다.", Toast.LENGTH_SHORT).show();
                response += "fail";
            } else {
                response += "success";
            }
            return response;
        }
    }
}