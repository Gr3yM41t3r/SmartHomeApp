package com.example.smartthings.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartthings.model.Post
import com.example.smartthings.model.Power
import com.example.smartthings.repository.Repository
import kotlinx.coroutines.launch
import java.lang.invoke.MutableCallSite

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse :MutableLiveData<List<Post>> = MutableLiveData()
    val powerResponse :MutableLiveData<Power> = MutableLiveData()
    val powerHourResponse :MutableLiveData<List<Power>> = MutableLiveData()

    fun getPost(){
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value=response
        }
    }

    fun getPower(){
        viewModelScope.launch {
            val response = repository.getPower()
            powerResponse.value=response
        }
    }

    fun getLastHourPower(){
        viewModelScope.launch {
            val response = repository.getLastHourPower()
            powerHourResponse.value=response
        }
    }
}