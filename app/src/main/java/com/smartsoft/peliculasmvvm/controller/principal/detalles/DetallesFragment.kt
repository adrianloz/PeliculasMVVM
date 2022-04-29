package com.smartsoft.peliculasmvvm.controller.principal.detalles

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.smartsoft.peliculasmvvm.R
import com.smartsoft.peliculasmvvm.databinding.FragmentDetallesBinding
import com.smartsoft.peliculasmvvm.models.Result
import com.smartsoft.peliculasmvvm.utils.loadImage

class DetallesFragment : Fragment() {
    private lateinit var binding: FragmentDetallesBinding
    private lateinit var result: Result
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetallesBinding.inflate(inflater,container, false)
        result = DetallesFragmentArgs.fromBundle(requireArguments()).result
        // Inflate the layout for this fragment
        initView(result)
        return binding.root
    }

    private fun initView(result: Result) {
        loadImage(activity, result.poster_path!!,binding.imagenPelicula)
        binding.nombrePelicula.text = result.title
        binding.descripcionPelicula.text = result.overview
        binding.review.text = "(" + result.vote_count + " Review" + ")"
        binding.valoracion.text = result.vote_average.toString()
        binding.botonRegresar.setOnClickListener {
            closeFragment()
        }
    }


    private fun closeFragment() {
        Navigation.findNavController(binding.root).popBackStack()
    }
}