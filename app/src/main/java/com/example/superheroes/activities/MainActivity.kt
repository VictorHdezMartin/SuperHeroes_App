package com.example.superheroes.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.superheroes.R
import com.example.superheroes.adapters.SuperheroAdapter
import com.example.superheroes.data.SuperHero
import com.example.superheroes.databinding.ActivityMainBinding
import com.example.superheroes.utils.RetroFitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var adapter: SuperheroAdapter

    var superheroList: List<SuperHero> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SuperheroAdapter(superheroList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        searchSuperheroes("")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)

        val menuItem = menu?.findItem(R.id.menu_search)!!
        val searchView = menuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchSuperheroes(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return true
    }

    private fun searchSuperheroes(query: String) {
        val service = RetroFitProvider.getRetroFit()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = service.findSuperHeroesByName(query)

                CoroutineScope(Dispatchers.Main).launch {
                    if (result.response == "success") {
                        adapter.updateItems(result.results)
                    } else {
                        // TODO: Mostrar mensaje de que no se ha encontrado nada
                    }
                }
            } catch (e: Exception) {
                Log.e("API", e.stackTraceToString())
            }
        }
    }
}