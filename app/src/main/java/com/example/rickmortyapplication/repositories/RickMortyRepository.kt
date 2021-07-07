package com.example.rickmortyapplication.repositories

import com.example.rickmortyapplication.network.RickMortyService

class RickMortyRepository constructor(private val rickMortyService: RickMortyService){

    fun getCharacters() =rickMortyService.getCharacters()

}