package com.smartsoft.peliculasmvvm.business.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smartsoft.peliculasmvvm.repository.PeliculasRepository

class NowPlayingViewModelFactory : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return if (modelClass.isAssignableFrom(NowPlayingViewModel::class.java))
           PeliculasRepository.instance?.let { NowPlayingViewModel(it) } as T
        else
            throw IllegalAccessException("Unknown ViewModel NowPlaying class")
    }
}