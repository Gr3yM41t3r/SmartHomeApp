package com.example.smartthings.model

data  class Power (

        val voltage: Float,
        val current:Float,
        val power:Float,
        val energy:Float,
        val frequency:Float,
        val powerfactor:Float,
        val lastHour:String
)