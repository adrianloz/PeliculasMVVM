package com.smartsoft.peliculasmvvm.controller.principal.inicio

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartsoft.peliculasmvvm.business.nowplaying.NowPlayingViewModel
import com.smartsoft.peliculasmvvm.business.nowplaying.NowPlayingViewModelFactory
import com.smartsoft.peliculasmvvm.data.NetworkResponse
import com.smartsoft.peliculasmvvm.databinding.FragmentInicioBinding
import com.smartsoft.peliculasmvvm.models.NowPlaying
import com.smartsoft.peliculasmvvm.models.Result

class InicioFragment : Fragment(), NowPlayingAdapter.InicioInterface{
    private lateinit var binding: FragmentInicioBinding
    private lateinit var nowPlayingViewModel: NowPlayingViewModel
    private lateinit var nowPlayingAdapter : NowPlayingAdapter
    private lateinit var nowPlayingList: MutableList<Result>
    private lateinit var popularList: MutableList<Result>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nowPlayingList = mutableListOf()
        popularList = mutableListOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInicioBinding.inflate(inflater, container, false)
        nowPlayingViewModel = ViewModelProvider(
            this,
            NowPlayingViewModelFactory()
        ).get(NowPlayingViewModel::class.java)

        binding.recyclerVierwNowPlaying.layoutManager = LinearLayoutManager(activity,RecyclerView.HORIZONTAL, false)

        nowPlayingViewModel.obtenerNowPlaying()

        nowPlayingViewModel.obtenerRespuestaNowPlaying().observe(viewLifecycleOwner) {
            if (it != null) {
                procesarRespuestaNowPlaying(it)
            }
        }
        return binding.root
    }

    private fun initRootView() {
       val l1 = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
       binding.recyclerVierwNowPlaying.layoutManager = l1
    }

    private fun procesarRespuestaNowPlaying(respuesta: NetworkResponse<NowPlaying?>) {
        when (respuesta.status) {
            NetworkResponse.SUCCESS -> {
                respuesta.data.let {
                    nowPlayingList.clear()
                    nowPlayingList.addAll(it?.results!!)
                    nowPlayingAdapter = NowPlayingAdapter(nowPlayingList, requireContext(), this)
                    binding.recyclerVierwNowPlaying.adapter = nowPlayingAdapter
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        nowPlayingViewModel.setDefaultNowPlaying()
    }

    override fun onItemClick(result: Result) {
        result.let {
            val action : NavDirections = InicioFragmentDirections.actionInicioFragmentToDetallesFragment(it)
            Navigation.findNavController(binding.root).navigate(action)
        }
    }
}

