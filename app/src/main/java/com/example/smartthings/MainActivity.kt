package com.example.smartthings

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.smartthings.util.PowerActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tempsActivityButton.setOnClickListener {
            val intent = Intent(this,TempsActivity::class.java)
            startActivity(intent)
        }
        powerActivityButton.setOnClickListener {
            val intent = Intent(this,PowerActivity::class.java)
            startActivity(intent)
        }

        garageActivityButton.setOnClickListener{
            val intent = Intent(this,GarageActivity::class.java)
            startActivity(intent)
        }

        doorButton.setOnClickListener {
            val thread = Thread {
                try {
                    URL("https://home.elmaroufi.com/HomeApi/api/porte/open.php").openStream()
             } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            thread.start()
        }


    }




}