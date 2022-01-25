package com.example.smartthings.repository

import com.example.smartthings.api.RetrofitInstance
import com.example.smartthings.model.Post
import com.example.smartthings.model.Power
import retrofit2.Retrofit

class Repository {
    suspend fun getPost(): List<Post> {
        return  RetrofitInstance.api.getPost()
    }
    suspend fun getPower(): Power {
        return  RetrofitInstance.api.getPower()
    }
    suspend fun getLastHourPower(): List<Power> {
        return  RetrofitInstance.api.getLastHourPower()
    }
}