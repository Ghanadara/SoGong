package com.example.sogong;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitService {

    // @GET( EndPoint-자원위치(URI) )
    //가져오기
    @GET("{post}")
    Call<PostResult> getPosts(@Path("post") String post);


    //등록
    @POST("register/")
    Call<PostSending> setPostBody(@Body PostSending post);

    @FormUrlEncoded
    @POST("todo")
    Call<PostSending> setPostField(
            @Field("title") String title,
            @Field("description") String description,
            @Field("important") boolean important

    );

    //수정
    @PUT("todo/{id}")
    Call<PostResult> getPutBody(
            @Path("id") String id,
            @Body PostResult postResult
    );

    //삭제
    @DELETE("todo/{id}")
    Call<Void> deletePost(@Path("id") int id);
}
