package com.example.superheroes.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.superheroes.R
import com.example.superheroes.data.FichaSuperHeroe
import com.example.superheroes.databinding.ActivityDetailBinding
import com.example.superheroes.utils.RetroFitProvider
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_SUPERHERO_ID = "SUPERHEROE_ID"
    }

    lateinit var binding: ActivityDetailBinding
    lateinit var superhero: FichaSuperHeroe

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

// Cargamos la ficha del super heroe
    private fun getSuperhero(id: String) {
        val service = RetroFitProvider.getRetroFit()                        // creamos el objeto para hacer la llamada a la API

        CoroutineScope(Dispatchers.IO).launch {            // hay que hacerlo en un hilo secundario
            try {
                superhero = service.findSuperHeroesById(id)                 // hacemos la llamada a la API y parseamos el JSON

                CoroutineScope(Dispatchers.Main).launch {  // llamamos al hilo principal para cargar los datos
                    loadData()
                }
            } catch (e: Exception) {
                Log.e("API", e.stackTraceToString())
            }
        }
    }

    fun loadData() {
        supportActionBar?.title = superhero.name
        Picasso.get().load(superhero.image.url).into(binding.avatarImageView)

        with (binding) {
            unoTextView.setText(superhero.biography.fullName)
            dosTextView.setText(superhero.biography.placeOfBirth)
            tresTextView.setText(superhero.work.base)
            cuatroTextView.setText(superhero.work.occupation)
        }
    }
}