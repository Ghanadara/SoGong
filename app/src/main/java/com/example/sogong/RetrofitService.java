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
    Call<PostSending> getPosts(@Path("post") String post);

    @GET("todo/")
    Call<List<PostSending>> getAllPosts();


    //등록
    @POST("todo/")
    Call<PostSending> setPostBody(@Body PostSending post);

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
    Call<PostSending> getPutBody(
            @Path("id") String id,
            @Body PostSending PostSending
    );

    //삭제
    @DELETE("todo/{id}")
    Call<Void> deletePost(@Path("id") String id);
}
