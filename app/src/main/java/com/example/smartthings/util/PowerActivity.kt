package com.example.smartthings.util

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.smartthings.R
import com.example.smartthings.model.Power
import com.example.smartthings.repository.Repository
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_power.*


class PowerActivity : AppCompatActivity() {

    val mainHandler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_power)
        mainHandler.post(object : Runnable {
            override fun run() {
                getData()
                mainHandler.postDelayed(this, 1500)
            }
        })
        getLastHourData()
    }

    fun getData(){
        lateinit var viewModel:MainViewModel
        val repository= Repository()
        val viewModelFactory= MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPower()
        viewModel.powerResponse.observe(this, Observer { response ->
            if(response.power<=1000){
                powerValue.setTextColor(Color.parseColor("#9CF30C"))
            }else if(response.power>1000 && response.power<=2000){
                powerValue.setTextColor(Color.parseColor("#F5F51F"))
            }else if(response.power>2000 && response.power<=3500){
                powerValue.setTextColor(Color.parseColor("#F5A71F"))
            }else {
                powerValue.setTextColor(Color.parseColor("#C70906"))
            }
            powerValue.text = response.power.toString()+"Wh"
            voltageValue.text = response.voltage.toString()+"V"
            currentValue.text = response.current.toString()+"A"
            powerFactorValue.text = response.powerfactor.toString()+""
            frequencyValue.text = response.frequency.toString()+"Hz"
            energyValue.text = response.energy.toString()+"KWh"

        })
    }
    fun getLastHourData(){
        lateinit var viewModel:MainViewModel
        var posts = ArrayList<Power>()
        var powers = ArrayList<Float>()
        val repository= Repository()
        val viewModelFactory= MainViewModelFactory(repository)
        viewModel = ViewModelProvider(
            this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getLastHourPower()
        viewModel.powerHourResponse.observe(this, Observer { response->
            posts = response as ArrayList<Power>
            for (post in posts){
                powers.add(post.power)
           }
            powers.reverse()
            val aaChartView = findViewById<AAChartView>(R.id.aaChartView)
            val aaChartModel : AAChartModel = AAChartModel()
                .chartType(AAChartType.Area)
                .title("Consommation électrique :")
                .subtitle("60 minutes")
                .backgroundColor("#000000")
                .dataLabelsEnabled(true)
                .series(arrayOf(
                    AASeriesElement()
                        .name("consommation en Wh")
                        .data(powers.toArray())
                        .color("#56EB0C")

                )
                )
            aaChartView.aa_drawChartWithChartModel(aaChartModel)

        })

    }





}