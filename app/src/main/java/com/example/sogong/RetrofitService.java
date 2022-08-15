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
    @GET("todo/{post}")
    Call<PostObject> getPosts(@Path("post") String post);

    @GET("todo/")
    Call<List<PostObject>> getAllPosts();


    //등록
    @POST("todo/")
    Call<PostObject> setPostBody(@Body PostObject post);

//    @FormUrlEncoded
//    @POST("todo")
//    Call<PostSending> setPostField(
//            @Field("title") String title,
//            @Field("description") String description,
//            @Field("important") boolean important
//
//    );

    //수정
    @PUT("todo/{id}")
    Call<PostObject> getPutBody(
            @Path("id") String id,
            @Body PostObject PostObject
    );

    //삭제
    @DELETE("todo/{id}")
    Call<Void> deletePost(@Path("id") String id);
}
