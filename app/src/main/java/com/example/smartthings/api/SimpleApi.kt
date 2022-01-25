package com.example.smartthings.api

import android.graphics.ColorSpace
import com.example.smartthings.model.Post
import com.example.smartthings.model.Power
import retrofit2.http.GET
import java.util.*
import retrofit2.Call

interface SimpleApi {

    @GET("php_rest_api/api/post/read.php")
    suspend fun getPost():List<Post>

    @GET("php_rest_api/api/power/readLatest.php")
    suspend fun getPower():Power

    @GET("php_rest_api/api/power/readLastHour.php")
    suspend fun getLastHourPower():List<Power>
}