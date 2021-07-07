package com.example.rickmortyapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmortyapplication.models.BaseResponse
import com.example.rickmortyapplication.repositories.RickMortyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// viewmodel
class RickMortyViewModel constructor(private val repository: RickMortyRepository) : ViewModel(){

    val baseResponse = MutableLiveData<BaseResponse>()
    val error = MutableLiveData<String>()

    fun getCharacters(){
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getCharacters()
            response.enqueue(object : Callback<BaseResponse> {
                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>
                ) {
                    baseResponse.postValue(response.body())
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    error.postValue(t.message)
                }
            })
        }
    }

}