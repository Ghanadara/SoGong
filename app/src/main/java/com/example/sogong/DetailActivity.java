package com.example.sogong;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DetailActivity extends AppCompatActivity {

    // 로그에 사용할 TAG
    final private String TAG = getClass().getSimpleName();

    // 사용할 컴포넌트 선언
    TextView title_tv, content_tv, date_tv;
    LinearLayout comment_layout;
    EditText comment_et;
    Button reg_button;

    // 선택한 게시물의 번호
    String board_seq = "";

    // 유저아이디 변수
    String userid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

// ListActivity 에서 넘긴 변수들을 받아줌
        board_seq = getIntent().getStringExtra("board_seq");
        userid = getIntent().getStringExtra("userid");

// 컴포넌트 초기화
        title_tv = findViewById(R.id.title_tv);
        content_tv = findViewById(R.id.content_tv);
        date_tv = findViewById(R.id.date_tv);

        comment_layout = findViewById(R.id.comment_layout);
        comment_et = findViewById(R.id.comment_et);
        reg_button = findViewById(R.id.reg_button);

// 등록하기 버튼을 눌렀을 때 댓글 등록 함수 호출
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegCmt regCmt = new RegCmt();
                regCmt.execute(userid, comment_et.getText().toString(), board_seq);
            }
        });

// 해당 게시물의 데이터 불러오기
        InitData();

    }

    private void InitData(){

// 해당 게시물의 데이터를 읽어오는 함수, 파라미터로 보드 번호를 넘김
        LoadBoard loadBoard = new LoadBoard();
        loadBoard.execute(board_seq);

    }

    class LoadBoard extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.d(TAG, "onPreExecute");
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(TAG, "onPostExecute, " + result);
            try {
// 결과값이 JSONArray 형태로 넘어오기 때문에
// JSONArray, JSONObject 를 사용해서 파싱
                JSONArray jsonArray = null;
                jsonArray = new JSONArray(result);

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

// Database 의 데이터들을 변수로 저장한 후 해당 TextView 에 데이터 입력
                    String title = jsonObject.optString("title");
                    String content = jsonObject.optString("content");
                    String crt_dt = jsonObject.optString("crt_dt");

                    title_tv.setText(title);
                    content_tv.setText(content);
                    date_tv.setText(crt_dt);

                }

// 해당 게시물에 대한 댓글 불러오는 함수 호출, 파라미터로 게시물 번호 넘김
                LoadCmt loadCmt = new LoadCmt();
                loadCmt.execute(board_seq);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        @Override
        protected String doInBackground(String... params) {

            String board_seq = params[0];

// 호출할 php 파일 경로
            String server_url = "http://15.164.252.136/load_board_detail.php";


            URL url;
            String response = "";
            try {
                url = new URL(server_url);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("board_seq", board_seq);
                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

                conn.connect();
                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line=br.readLine()) != null) {
                        response+=line;
                    }
                }
                else {
                    response="";

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;
        }
    }


    // 게시물의 댓글을 읽어오는 함수
    class LoadCmt extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.d(TAG, "onPreExecute");
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(TAG, "onPostExecute, " + result);

// 댓글을 뿌릴 LinearLayout 자식뷰 모두 제거
            comment_layout.removeAllViews();

            try {

// JSONArray, JSONObject 로 받은 데이터 파싱
                JSONArray jsonArray = null;
                jsonArray = new JSONArray(result);

// custom_comment 를 불러오기 위한 객체
                LayoutInflater layoutInflater = LayoutInflater.from(DetailActivity.this);

                for(int i=0;i<jsonArray.length();i++){

// custom_comment 의 디자인을 불러와서 사용
                    View customView = layoutInflater.inflate(R.layout.custom_comment, null);
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String userid= jsonObject.optString("userid");
                    String content = jsonObject.optString("content");
                    String crt_dt = jsonObject.optString("crt_dt");

                    ((TextView)customView.findViewById(R.id.cmt_userid_tv)).setText(userid);
                    ((TextView)customView.findViewById(R.id.cmt_content_tv)).setText(content);
                    ((TextView)customView.findViewById(R.id.cmt_date_tv)).setText(crt_dt);

// 댓글 레이아웃에 custom_comment 의 디자인에 데이터를 담아서 추가
                    comment_layout.addView(customView);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        @Override
        protected String doInBackground(String... params) {

            String board_seq = params[0];
            String server_url = "http://15.164.252.136/load_cmt.php";


            URL url;
            String response = "";
            try {
                url = new URL(server_url);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("board_seq", board_seq);
                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

                conn.connect();
                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line=br.readLine()) != null) {
                        response+=line;
                    }
                }
                else {
                    response="";

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;
        }
    }

    // 댓글을 등록하는 함수
    class RegCmt extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.d(TAG, "onPreExecute");
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(TAG, "onPostExecute, " + result);

// 결과값이 성공으로 나오면
            if(result.equals("success")){

//댓글 입력창의 글자는 공백으로 만듦
                comment_et.setText("");

// 소프트 키보드 숨김처리
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(comment_et.getWindowToken(), 0);

// 토스트메시지 출력
                Toast.makeText(DetailActivity.this, "댓글이 등록되었습니다.", Toast.LENGTH_SHORT).show();

// 댓글 불러오는 함수 호출
                LoadCmt loadCmt = new LoadCmt();
                loadCmt.execute(board_seq);
            }else
            {
                Toast.makeText(DetailActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String userid = params[0];
            String content = params[1];
            String board_seq = params[2];

            String server_url = "http://15.164.252.136/reg_comment.php";


            URL url;
            String response = "";
            try {
                url = new URL(server_url);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("userid", userid)
                        .appendQueryParameter("content", content)
                        .appendQueryParameter("board_seq", board_seq);
                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

                conn.connect();
                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line=br.readLine()) != null) {
                        response+=line;
                    }
                }
                else {
                    response="";

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;
        }
    }
}