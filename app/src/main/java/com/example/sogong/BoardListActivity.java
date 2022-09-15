package com.example.sogong;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardListActivity extends AppCompatActivity {
    // 로그에 사용할 TAG 변수
    final private String TAG = getClass().getSimpleName();

    // 사용할 컴포넌트 선언
    ListView listView;
    Button profile_button;
    String userid = "";
    // 리스트뷰에 사용할 제목 배열
    ArrayList<String> titleList = new ArrayList<>();

    // 클릭했을 때 어떤 게시물을 클릭했는지 게시물 번호를 담기 위한 배열
    ArrayList<String> seqList = new ArrayList<>();

    RecyclerView recyclerView;
    BoardCategoryAdapter boardCategoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        /*test 용 나중에 삭제할 것*/
        //setContentView(R.layout.activity_main);

        Resources resources = getResources();
        String[] boardCategories =resources.getStringArray(R.array.Boardcategory);

        recyclerView = findViewById(R.id.recyclerView);
        boardCategoryAdapter = new BoardCategoryAdapter(getApplicationContext(),boardCategories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(boardCategoryAdapter);

// LoginActivity 에서 넘긴 userid 값 받기
        userid = getIntent().getStringExtra("userid");

// 컴포넌트 초기화
//        listView = findViewById(R.id.listView);
//
//// listView 를 클릭했을 때 이벤트 추가
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//// 어떤 값을 선택했는지 토스트를 뿌려줌
//                Toast.makeText(ListActivity.this, adapterView.getItemAtPosition(i)+ " 클릭", Toast.LENGTH_SHORT).show();
//
//// 게시물의 번호와 userid를 가지고 DetailActivity 로 이동
//                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
//                intent.putExtra("board_seq", seqList.get(i));
//                intent.putExtra("userid", userid);
//                startActivity(intent);
//
//            }
//        });

// 버튼 컴포넌트 초기화
        profile_button = findViewById(R.id.profile_button);

// 버튼 이벤트 추가
        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

// userid 를 가지고 PostActivity 로 이동
                Intent intent = new Intent(BoardListActivity.this, PostActivity.class);
                intent.putExtra("userid", userid);
                startActivity(intent);
            }
        });

    }


    // onResume() 은 해당 액티비티가 화면에 나타날 때 호출됨
    @Override
    protected void onResume() {
        super.onResume();


//// 해당 액티비티가 활성화 될 때, 게시물 리스트를 불러오는 함수를 호출
//        GetBoard getBoard = new GetBoard();
//        getBoard.execute();
//        boardCategoryAdapter.setOnItemClickListener(new BoardCategoryAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClicked(View v, int position) {
//                PostObject item = PostAdapter.getItem(position);
//                Toast.makeText(getApplicationContext(),"postiion" + position +"제목" +item.getTitle(),Toast.LENGTH_LONG).show();
//            }
//        });
//        recyclerView.setAdapter(boardCategoryAdapter);

    }


    // 게시물 리스트를 읽어오는 함수
    class GetBoard extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.d(TAG, "onPreExecute");
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(TAG, "onPostExecute, " + result);

// 배열들 초기화
            titleList.clear();
            seqList.clear();

            // 결과물이 JSONArray 형태로 넘어오기 때문에 파싱
            //JSONArray jsonArray = new JSONArray(result);
            ArrayList<Post> postArrayList = Server.getPostlist();

            for (int i = 0; i < postArrayList.size(); i++) {
                String title = postArrayList.get(i).getTitle();
                String seq = Integer.toString(i);

                titleList.add(title);
                seqList.add(seq);
                Log.d(TAG, title + " 추가");
            }


//                for(int i=0;i<jsonArray.length();i++){
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                    String title = jsonObject.optString("title");
//                    String seq = jsonObject.optString("seq");
//
//// title, seq 값을 변수로 받아서 배열에 추가
//                    titleList.add(title);
//                    seqList.add(seq);
//
//                }

// ListView 에서 사용할 arrayAdapter를 생성하고, ListView 와 연결
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(BoardListActivity.this, android.R.layout.simple_list_item_1, titleList);
            listView.setAdapter(arrayAdapter);

// arrayAdapter의 데이터가 변경되었을때 새로고침
            arrayAdapter.notifyDataSetChanged();


        }


        @Override
        protected String doInBackground(String... params) {
//
// String userid = params[0];
// String passwd = params[1];

//            String server_url = "http://15.164.252.136/load_board.php";
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
//                        .appendQueryParameter("userid", "");
//// .appendQueryParameter("passwd", passwd);
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
            return "success";
        }
    }
}
