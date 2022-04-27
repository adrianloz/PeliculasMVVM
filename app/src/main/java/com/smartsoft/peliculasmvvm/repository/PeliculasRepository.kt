package com.smartsoft.peliculasmvvm.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.smartsoft.peliculasmvvm.data.NetworkResponse
import com.smartsoft.peliculasmvvm.models.NowPlaying
import com.smartsoft.peliculasmvvm.utils.Constantes
import com.smartsoft.peliculasmvvm.utils.respuestaErrorWs
import com.smartsoft.peliculasmvvm.webservices.PeliculasWebservices
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class PeliculasRepository private  constructor(){
    private var peliculasServices : PeliculasWebservices
    private var nowPlaying : Call<NowPlaying?>? = null
    private var _networkResponseNowplaying = MutableLiveData<NetworkResponse<NowPlaying?>?>()


    fun nowPlaying(){
        nowPlaying?.cancel()
        nowPlaying = peliculasServices.getNowPlayingMovies()
        nowPlaying?.enqueue(object : Callback<NowPlaying?>{
            override fun onResponse(call: Call<NowPlaying?>, response: Response<NowPlaying?>) {
                if (response.code() == Constantes.SUCCESS_RESPONSE_WS){
                    _networkResponseNowplaying.value = NetworkResponse.success(response.body())
                }else{
                    _networkResponseNowplaying.value = NetworkResponse.error(response.body(), respuestaErrorWs(response.code()))
                }
            }

            override fun onFailure(call: Call<NowPlaying?>, t: Throwable) {
                if (!call.isCanceled){
                    Log.e("ErrorNowPalyeng", "no se pudo conectar al Ws ${call.request().url}", t)
                    _networkResponseNowplaying.value = NetworkResponse.error(null, Constantes.ERROR_CONEXION);
                }
            }

        })
    }

    fun obtenerRespuestaCostos(): MutableLiveData<NetworkResponse<NowPlaying?>?> {
        return _networkResponseNowplaying
    }

    fun setDefaultDataCostos(){
        nowPlaying?.cancel()
        _networkResponseNowplaying.value = null
    }

    companion object{
        private var peliculasRepository : PeliculasRepository? = null
        @get:Synchronized
        val instance : PeliculasRepository?
            get() {
                if (peliculasRepository == null){
                    peliculasRepository = PeliculasRepository()
                }
                return  peliculasRepository
            }
    }

    init {
        val logging = HttpLoggingInterceptor()
        val httpClient = OkHttpClient.Builder().apply {
            addInterceptor(Interceptor { chain ->
                val originalRequest = chain.request()
                val originalUrl = originalRequest.url
                val newRequest=originalRequest.newBuilder().apply {
                    url(originalUrl.newBuilder().addQueryParameter(
                        "api_key",Constantes.API_KEY).build())
                }.build()
                chain.proceed(newRequest)
            })
        }
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit
            .Builder()
            .baseUrl(Constantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(httpClient.build())
            .build()
        peliculasServices = retrofit.create(PeliculasWebservices::class.java)
    }
}