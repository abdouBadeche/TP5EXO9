package com.example.tp5exo8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var rf = Retrofit.Builder()
            .baseUrl(RetrofitInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        var API = rf.create(RetrofitInterface::class.java)
        var call = API.todos

        call?.enqueue(object:Callback<List<TodoModel?>?>{
            override fun onResponse(
                call: Call<List<TodoModel?>?>,
                response: Response<List<TodoModel?>?>
            ) {

                var todosList  : List<TodoModel>? = response.body() as List<TodoModel>
                var todos = arrayOfNulls<String>(todosList!!.size)

                for (i in todosList!!.indices)
                    todos[i] = todosList!![i].title

                var adapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_dropdown_item_1line , todos)
                listview.adapter = adapter
            }

            override fun onFailure(call: Call<List<TodoModel?>?>, t: Throwable) {
            }

        })
    }
}