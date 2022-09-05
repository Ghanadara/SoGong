package com.example.sogong;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitService {

    // @GET( EndPoint-자원위치(URI) )
    //가져오기
    @GET("board/{post}")
    Call<PostObject> getPost(@Path("post") String post);

    @GET("board/")
    Call<List<PostObject>> getAllPosts();

    @GET("login/{uid}")
    Call<LoginObject> getLogin(@Path("uid") String uid);


    //등록
    @POST("board/")
    Call<PostObject> setPostBody(@Body PostObject post);

    @POST("login/")
    Call<LoginObject> setLoginBody(@Body LoginObject loginObject);

//    @FormUrlEncoded
//    @POST("todo")
//    Call<PostSending> setPostField(
//            @Field("title") String title,
//            @Field("description") String description,
//            @Field("important") boolean important
//
//    );

    //수정
    @PUT("board/{id}")
    Call<PostObject> getPutBody(
            @Path("id") String id,
            @Body PostObject PostObject
    );

    //삭제
    @DELETE("board/{id}")
    Call<Void> deletePost(@Path("id") String id);
}
