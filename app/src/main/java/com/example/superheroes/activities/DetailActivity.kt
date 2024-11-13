package com.example.superheroes.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.superheroes.R
import com.example.superheroes.databinding.ActivityDetailBinding
import com.example.superheroes.utils.RetroFitProvider
import com.example.superheroes.data.fichaSuperHeroe
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_SUPERHERO_ID = "SUPERHEROE_ID"
    }

    lateinit var binding = ActivityDetailBinding
    lateinit var superhero = fichaSuperHeroe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id = intent.getStringExtra(EXTRA_SUPERHERO_ID)!!

        getSuperhero(id)
    }

    fun loadData() {
        supportActionBar?.title = superhero.name
        Picasso.get().load(superhero.image.url).into(binding.avatarImageView)
    }

    private fun getSuperhero(id: String) {
        val service = RetroFitProvider.getRetroFit()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                superhero = service.findSuperHeroesById(id)

                CoroutineScope(Dispatchers.Main).launch {
                    loadData()
                }
            } catch (e: Exception) {
                Log.e("API", e.stackTraceToString())
            }
        }
    }
}