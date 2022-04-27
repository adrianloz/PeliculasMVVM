package com.smartsoft.peliculasmvvm.business.nowplaying

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartsoft.peliculasmvvm.data.NetworkResponse
import com.smartsoft.peliculasmvvm.models.NowPlaying
import com.smartsoft.peliculasmvvm.repository.PeliculasRepository

class NowPlayingViewModel(private  val peliculasRepository: PeliculasRepository) : ViewModel(){
    fun obtenerNowPlaying(){
        peliculasRepository.nowPlaying()
    }

    fun obtenerRespuestaNowPlaying(): MutableLiveData<NetworkResponse<NowPlaying?>?> {
        return peliculasRepository.obtenerRespuestaCostos()
    }

    fun setDefaultNowPlaying(){
        peliculasRepository.setDefaultDataCostos()
    }
}