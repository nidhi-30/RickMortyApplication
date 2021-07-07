package com.example.rickmortyapplication.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.rickmortyapplication.R
import com.example.rickmortyapplication.models.Character
import kotlin.collections.ArrayList

class CharactersAdapter (private val listener : OnItemClickListener) : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    private var characterList = mutableListOf<Character>()

    fun setCharacters(character : ArrayList<Character>){
        this.characterList = character.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_character_item, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characterList[position]

        Glide.with(holder.itemView.context)
            .load(character.image)
            .override(Target.SIZE_ORIGINAL)
            .error(R.drawable.ic_launcher_background)
            .into(holder.characterImage)

        holder.characterName.text = character.name

    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    inner class CharacterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val characterImage = itemView.findViewById(R.id.character_image) as ImageView
        val characterName = itemView.findViewById(R.id.character_name) as TextView

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }

            Log.d("FILTERED LIST", "${characterList[position]}")
        }

    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}