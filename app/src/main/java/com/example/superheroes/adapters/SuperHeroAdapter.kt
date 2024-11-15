package com.example.superheroes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes.data.SuperHeroeClass
import com.example.superheroes.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroAdapter(var items: List<SuperHeroeClass>, val onItemClick:(Int) -> Unit) : RecyclerView.Adapter<ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val superhero_selected = items[position]

        holder.render(superhero_selected)
        holder.itemView.setOnClickListener{
            onItemClick(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(items: List<SuperHeroeClass>) {
        this.items = items
        notifyDataSetChanged()
    }
}

class ViewHolder(val binding: ItemSuperheroBinding) : RecyclerView.ViewHolder(binding.root) {
    fun render(superhero: SuperHeroeClass) {
        binding.nameTextView.text = superhero.name                                 // asignamos nombre del superheroe
        Picasso.get().load(superhero.image.url).into(binding.avatarImageView)      // cargamos la imagen del superheroe con la API Picasso
    }
}