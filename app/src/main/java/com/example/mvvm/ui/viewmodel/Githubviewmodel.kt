package com.example.mvvm.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.api.GithubServices
import com.example.mvvm.data.models.SearchResponse
import com.example.mvvm.data.models.User
import com.example.mvvm.data.repos.GithubRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.sin

class Githubviewmodel : ViewModel() {


       val users = MutableLiveData<List<User>>()
   //    val singleUser = MutableLiveData<List<User>>()
    //   val searchedUser = MutableLiveData<SearchResponse>()

       fun fetchUsers() {

              viewModelScope.launch(Dispatchers.Main) {
                      val response = withContext(Dispatchers.IO){
                             GithubRespository.getUsers()
                      }
                  if (response != null) {
                      Log.i("its fine here" , response.get(0).toString())
                  }
                  else Log.i("f" , "f")
                      response?.let {
                          users.postValue(it)
                      }
              }
       }

    fun fetchSingleuser(name : String) {

        viewModelScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO){
                GithubRespository.getSingleUser(name)
            }
            response?.let {
                users.postValue(it)
            }
        }
    }
//       fun fecthUsers() {
//
//             viewModelScope.launch {
//
//                    val response = withContext(Dispatchers.IO) { GithubRespository.getUsers() }
//                    if (response.isSuccessful){
//
//                        response.body() ?.let {
//                            users.postValue(it)
//                        }
//                    }
//             }
//       }

//    fun searchUser(name : String) {
//
//        viewModelScope.launch {
//
//            val response = withContext(Dispatchers.IO) {GithubRespository.searchUser(name)}
//            if (response.isSuccessful){
//
//                response.body() ?.let {
//                    users.postValue(it.items)
//                }
//            }
//        }
//    }
}