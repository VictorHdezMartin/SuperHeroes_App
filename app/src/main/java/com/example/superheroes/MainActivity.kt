package com.example.superheroes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.superheroes.Utils.RetroFitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        // Access Token para SuperHero API
        //  0f274f699287b7e0f20005a52387616a


       val service = RetroFitProvider.getRetroFit()

       CoroutineScope(Dispatchers.IO).launch {

          var result = service.findSuperHeroesByName("Batman").response
          println("result")

          result = service.findSuperHeroesById("71").name
          println("result")


       }




    }
}