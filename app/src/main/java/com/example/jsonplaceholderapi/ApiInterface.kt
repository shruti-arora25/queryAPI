package com.example.jsonplaceholderapi

import posts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {



    @GET("posts")
    fun getData(@Query("userId") userId:Int): Call<List<posts>>




    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId:Int):Call<List<comments>>
}