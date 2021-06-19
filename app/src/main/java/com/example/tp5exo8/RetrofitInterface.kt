package com.example.tp5exo8

import  retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    @get:GET("todos")
    val todos : Call<List<TodoModel?>?>?
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    }
}