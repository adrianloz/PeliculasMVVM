package com.smartsoft.peliculasmvvm.controller.principal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.smartsoft.peliculasmvvm.business.nowplaying.NowPlayingViewModel
import com.smartsoft.peliculasmvvm.business.nowplaying.NowPlayingViewModelFactory
import com.smartsoft.peliculasmvvm.data.NetworkResponse
import com.smartsoft.peliculasmvvm.databinding.ActivityMainBinding
import com.smartsoft.peliculasmvvm.models.NowPlaying
import com.smartsoft.peliculasmvvm.utils.crearDialogProgresoGeneral

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var nowPlayingViewModel: NowPlayingViewModel
    private  val dialogoProgress by lazy { crearDialogProgresoGeneral() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        nowPlayingViewModel = ViewModelProvider(this, NowPlayingViewModelFactory()).get(NowPlayingViewModel::class.java)

        nowPlayingViewModel.obtenerNowPlaying()

        nowPlayingViewModel.obtenerRespuestaNowPlaying().observe(this){
            if (it != null){
                procesarRespuestaNowPlaying(it)
            }
        }

    }

    private fun procesarRespuestaNowPlaying(respuesta: NetworkResponse<NowPlaying?>) {
        when(respuesta.status){
            NetworkResponse.SUCCESS ->{
                respuesta.data.let {
                    Log.e("nowplayinRes", it.toString())
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        nowPlayingViewModel.setDefaultNowPlaying()
    }
}