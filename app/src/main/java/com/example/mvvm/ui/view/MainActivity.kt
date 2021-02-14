package com.example.mvvm.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.data.models.SearchResponse
import com.example.mvvm.data.models.User
import com.example.mvvm.ui.adapter.UsersAdapter
import com.example.mvvm.ui.viewmodel.Githubviewmodel

class MainActivity : AppCompatActivity() {


    val vm by lazy {
        ViewModelProvider(this).get(Githubviewmodel::class.java)
    }
    val list = arrayListOf<User>()
    val adapter = UsersAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usersRv = findViewById<RecyclerView>(R.id.userRv)
        val refresh = findViewById(R.id.refresh) as ImageView

        val apply = usersRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        vm.fetchUsers()
        val searchView = findViewById<SearchView>(R.id.searchView)

        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                  query?.let {
                      findUser(it)
                  }
                    return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               return true
            }

        })

        vm.users.observe(this, Observer {
             if (!it.isNullOrEmpty())
             {
                  Log.i("data from viewmodel" , it.get(0).toString())
                  list.addAll(it)
                  adapter.notifyDataSetChanged()
             }

        })

        refresh.setOnClickListener {

            Log.i("image","clicked")
            adapter.notifyDataSetChanged()

        }
    }

    private fun findUser(query : String){

           vm.fetchSingleuser(query)
           vm.users.observe(this, Observer {
                  list.clear()
                  list.addAll(it)
                  adapter.notifyDataSetChanged()
           })
    }
}