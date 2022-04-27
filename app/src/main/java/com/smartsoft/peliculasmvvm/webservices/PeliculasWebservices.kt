package com.smartsoft.peliculasmvvm.webservices

import com.smartsoft.peliculasmvvm.models.NowPlaying
import com.smartsoft.peliculasmvvm.models.Popular
import com.smartsoft.peliculasmvvm.utils.Constantes
import retrofit2.Call
import retrofit2.http.GET

interface PeliculasWebservices {

    @GET(Constantes.NOW_PLAYING_URL)
    fun getNowPlayingMovies(): Call<NowPlaying?>

    @GET(Constantes.POPULAR_MOVIES_URL)
    fun getPopularMovies(): Call<Popular?>

}