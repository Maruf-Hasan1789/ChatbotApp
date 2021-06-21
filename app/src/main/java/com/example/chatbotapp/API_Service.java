package com.example.chatbotapp;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.GET;

public interface API_Service {
    @POST("chat")
    @FormUrlEncoded
    Call<ChatModel>getchat(@Field("chatInput") String string);

}
