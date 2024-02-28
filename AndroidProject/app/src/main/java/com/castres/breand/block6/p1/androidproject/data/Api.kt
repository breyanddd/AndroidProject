package com.castres.breand.block6.p1.androidproject.data

import com.castres.breand.block6.p1.androidproject.data.model.User
import retrofit2.http.GET

interface Api {

    @GET("users")
    suspend fun getUserList(): User

    companion object{
        const val BASE_URL = "https://cyberservice-96805b7c1a96.herokuapp.com/"

    }
}