package com.example.mvvm.data.api

import com.example.mvvm.data.models.SearchResponse
import com.example.mvvm.data.models.User
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubServices {

     @GET("/users")
     fun getUsers() : Call<List<User>>

     @GET("search/users")
     fun searchUser(@Query("q") name : String) : Call<SearchResponse>
}