package com.example.jsonplaceholderapi

import posts
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {



    @GET("posts")
    fun getData(@Query("userId") userId:Int): Call<List<posts>>




    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId:Int):Call<List<comments>>



    @POST("posts")
     fun create(@Body post:posts):Call<posts>


     @PUT("posts/{id}")
     fun putPost(@Path("id")id:Int,@Body post: posts):Call<posts>


     //need to replace the entire resource or create a new one.



     @PATCH("posts/{id}")
    fun patchPost(@Path("id")id:Int,@Body post: posts):Call<posts>
    //need to modify specific fields of a resource.


    @DELETE("posts/{id}")
    fun delete(@Path("id") id:Int) :Call<Unit>



}