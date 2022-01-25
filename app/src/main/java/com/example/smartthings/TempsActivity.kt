package com.example.smartthings


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.smartthings.repository.Repository
import com.example.smartthings.util.MainViewModel
import com.example.smartthings.util.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_temps.*
import java.sql.*
import androidx.lifecycle.*
import com.example.smartthings.model.Post
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class TempsActivity : AppCompatActivity() {

    private lateinit var viewModel:MainViewModel
    var posts = ArrayList<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temps)
        val repository=Repository()
        val viewModelFactory= MainViewModelFactory(repository)
        viewModel =ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()

        viewModel.myResponse.observe(this, Observer { response->
            temps.text = response[0].tempratures.toString()
            dateOfTemp.text = response[0].Date
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val formatted = current.format(formatter)
            lastUpdate.text=response.size.toString()
            posts= response as ArrayList<Post>

        })


    }

    fun populateChart(){
        val entries =  ArrayList<Entry>()
        for (i in posts.size-10..posts.size)
            entries.add(Entry(posts.get(i).Date.toFloat(),posts[i].tempratures.toFloat()))

    }

}