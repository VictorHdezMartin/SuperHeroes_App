package com.example.superheroes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.superheroes.Utils.RetroFitProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val service = RetroFitProvider.getRetroFit()
        val coroutineExceptionHandler = CoroutineExceptionHandler {_, throwable -> throwable.printStackTrace() }

        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {

            var result = service.findSuperHeroesByName("Batman")
            println(result)

            var result2 = service.findSuperHeroesById("71")
            println(result2)
        }

       }
}