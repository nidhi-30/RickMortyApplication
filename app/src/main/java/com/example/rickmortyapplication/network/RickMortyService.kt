package com.example.rickmortyapplication.network

import com.example.rickmortyapplication.models.BaseResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RickMortyService {

    @GET("character/")
    fun getCharacters() : Call<BaseResponse>

    companion object{
        private var retrofitService: RickMortyService? = null

        fun getInstance() : RickMortyService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RickMortyService::class.java)
            }
            return retrofitService!!
        }
    }

}