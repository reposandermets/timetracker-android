package com.example.veebiprogrammeerimine.interfaces;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.FieldMap;
import retrofit2.http.Query;

import com.example.veebiprogrammeerimine.responses.SessionResponse;
import com.example.veebiprogrammeerimine.responses.UserResponse;

import java.util.ArrayList;
import java.util.Map;

public interface ApiInterface {
    @GET("user")
    Call<ArrayList<UserResponse>>getUsers();

    @GET("session")
    Call<ArrayList<SessionResponse>>getSession(@Query("user_id") String userId);

    @POST("session")
    Call<SessionResponse>createSession(@Body SessionResponse session);

    @PATCH("session/{id}")
    Call<SessionResponse>patchSession(@Path("id") String id, @Body SessionResponse session);
}
