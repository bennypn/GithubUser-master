package com.example.githubuserlist.retrofit;

import com.example.githubuserlist.model.ResponseUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

//TODO: Change Token, it only for a short period from my github access token

public interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    Call<ResponseUser> getSearchUser(
            @Query("q") String username
    );

    /*
    @GET("users/{username}")
    @Headers("Authorization: token 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")
    Call<> getDetailUser(
            @Path("username") String username
    );

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    Call<List<>> getFollowerUser(
            @Path("username") String username
    );

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    Call<List<>> getFollowingUser(
            @Path("username") String username
    );

    */

}
