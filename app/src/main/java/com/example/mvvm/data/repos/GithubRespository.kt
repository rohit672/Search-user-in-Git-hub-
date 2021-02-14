package com.example.mvvm.data.repos

import android.util.Log
import android.view.KeyEvent
import androidx.lifecycle.LiveData
import com.example.mvvm.data.api.Client
import com.example.mvvm.data.models.SearchResponse
import com.example.mvvm.data.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GithubRespository {

//      suspend fun getUsers() = Client.api.getUsers()
//
//      suspend fun searchUser(name : String) = Client.api.searchUser(name)

      var data : List<User>? = null
      var sinleUser : List<User>? = null

      fun getUsers(): List<User>? {

              val userService =  Client.api

              userService.getUsers().enqueue(object : Callback<List<User>>{
                  override fun onFailure(call: Call<List<User>>?, t: Throwable?) {

                  }

                  override fun onResponse(call: Call<List<User>>?, response: Response<List<User>>?) {

                       data = response?.body()
                  }
              })
         // Log.i("last line", "dcjddnd")
          return data
      }

      fun getSingleUser(name : String) : List<User>? {

          val userService =  Client.api

          userService.searchUser(name).enqueue(object : Callback<SearchResponse>{
              override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {

              }

              override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {

                  sinleUser = response?.body()?.items

              }

          })

          return sinleUser
      }

//      val getUser = Client.api.getUsers().enqueue(object : Callback<List<User>> {
//            override fun onFailure(call: Call<List<User>>?, t: Throwable?) {
//                  Log.i("problem", ":(")
//            }
//
//            override fun onResponse(call: Call<List<User>>?, response: Response<List<User>>?) {
//
//
//            }
//
//      })
}