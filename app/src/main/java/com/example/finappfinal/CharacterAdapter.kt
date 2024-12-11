package com.example.finappfinal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CharacterAdapter (
    private val characters : List<Character>,
    private val context: Context,
    private val click: (character : Character) -> Unit
    ) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterImage = itemView.findViewById<ImageView>(R.id.imageView)
        val characterName = itemView.findViewById<TextView>(R.id.textViewCharName)
        val characterHouse = itemView.findViewById<TextView>(R.id.textViewCharHouse)

        fun bind(character: Character) {
            val imageUrl = character.image
            if(imageUrl.isEmpty()){
                itemView.findViewById<ImageView>(R.id.imageView).setImageResource(R.drawable.hp)
            }
            else {
                Picasso.get().load(character.image).into(characterImage)
            }
            characterName.text = character.name
            characterHouse.text = character.house
            itemView.setOnClickListener { click(character) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)

    }
}